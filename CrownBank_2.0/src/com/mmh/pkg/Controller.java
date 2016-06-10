package com.mmh.pkg;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.sql.DataSource;

public class Controller {
	@Resource(name="jdbc/exampleDS")
	private static	DataSource ds1; 
	private static String conUser = "DTU07";
	private static String conPassword = "FAGP2016";

//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		// transactionHistory(1111, "DKK");
//		//editUserAccount("Jesper",123,"123",2800,"USD");
//		getBankAcc("Jesper");
//	}

	public static void transaction(BigDecimal amount, 
			int sender, int recipient, String currency)
			throws SQLException, ClassNotFoundException {
		Connection con = null;
		try{
			con=ds1.getConnection(conUser,conPassword);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		CallableStatement cstmt = con.prepareCall("{CALL \"DTUGRP03\".Transfer(?,?,?,?,?)}");
		cstmt.setBigDecimal(1, amount);
		cstmt.setInt(2, sender);
		cstmt.setInt(3, recipient);
		cstmt.setString(4, currency);
		cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
		cstmt.execute();
		System.out.println(cstmt.getString(5));
		cstmt.close();
	}

	public static TransactionHistory transactionHistory(int bankAcc, String currency)
			throws SQLException, ClassNotFoundException {
		Connection con = null;
		try{
			con=ds1.getConnection(conUser,conPassword);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		String rateQuery = "SELECT \"Rate\" FROM \"DTUGRP03\".\"Valuta\" " + "WHERE '" + currency + "' = \"Currency\"";
		String sqlQuery = "SELECT \"BankAccount_Sender\",\"BankAccount_Recipient\", " + "\"Amount\", \"Date\" "
				+ "FROM \"DTUGRP03\".\"TransactionHistory\" " + "WHERE " + bankAcc + " = \"BankAccount_Sender\""
				+ " OR " + bankAcc + " = \"BankAccount_Recipient \"";

		Statement stmt = con.createStatement();
		ResultSet rateset = stmt.executeQuery(rateQuery);
		TransactionHistory transHist = new TransactionHistory();
		while (rateset.next()) {
			transHist.rate = rateset.getBigDecimal(1);
		}
		rateset.close();

		ResultSet rs = stmt.executeQuery(sqlQuery);
		BigDecimal bg = new BigDecimal("100.0");
		while (rs.next()) {
			transHist.sender.add(rs.getInt(1));
			transHist.recipient.add(rs.getInt(2));
			transHist.amount.add((rs.getBigDecimal(3).multiply(bg)).divide(transHist.rate));
			transHist.dateOfTransfer.add(rs.getTimestamp(4));
		}
		rs.close();
		con.close();
		return transHist;
	}

	public static void deleteuser(String userName) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try{
			con=ds1.getConnection(conUser,conPassword);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		CallableStatement cstmt = con.prepareCall("{CALL \"DTUGRP03\".DeleteUser(?,?)}");
		cstmt.setString(1, userName);
		cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
		cstmt.execute();
		System.out.println(cstmt.getInt(2));
	}
	
	public static void deleteBankAcc(int AccNumber) 
			throws SQLException, ClassNotFoundException {
		Connection con = null;
		try{
			con=ds1.getConnection(conUser,conPassword);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		CallableStatement cstmt = con.prepareCall("{CALL \"DTUGRP03\".DeleteBankAcc(?,?)}");	
		cstmt.setInt(1, AccNumber);
		cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
		cstmt.execute();
		System.out.println(cstmt.getInt(2));
	}
	
	public userData getUserInfo(String username, Connection con) throws SQLException {
		
		String sqlQuery = "SELECT * FROM \"DTUGRP03\".\"UserAccount\" WHERE \"UserName\" = "+username; 
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sqlQuery);
		userData user = new userData();

		user.setUsername(username);
		
		while (rs.next()){
			if(rs.getString(5)=="Client"){
				user.setAdmin(false);
			}else{
				user.setAdmin(true);
			}
		user.setFullName(rs.getString(2));
		user.setTelephoneNumber(rs.getInt(3));
		user.setPassword(rs.getString(4));
		 
		user.setPostnumber(rs.getInt(6));
		user.setCurrency(rs.getString(7));
		}
		
		return user;
	}
	
	public void editUserAccount(userData user, Connection con) throws ClassNotFoundException, SQLException{
		
		String sqlQuery = "UPDATE \"DTUGRP03\".\"UserAccount\" "
				+ "SET \"TelephoneNumber\" = "+user.getTelephoneNumber()+ ", \"City_Postnumber\" = "+user.getPostnumber()
				+ ", \"Password\" = '"+user.getPassword()+"', \"Valuta_Currency\" = '"
				+user.getCurrency()+"' WHERE \"UserName\" = '"+user.getUsername()+"'";
	Statement stmt = con.createStatement();
	stmt.executeUpdate(sqlQuery);
	}
	
	
	public static void getBankAcc(String username) throws ClassNotFoundException, SQLException{
		Connection con = null;
		try{
			con=ds1.getConnection(conUser,conPassword);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sqlQuery = "SELECT \"AccountNumber\", \"Balance\", "
				+ "\"Intrest\", \"Valuta_Currency\" FROM "
				+ "\"DTUGRP03\".\"BankAccount\" INNER JOIN "
				+ "\"DTUGRP03\".\"AccountAssociativity\" ON \"AccountNumber\" = "
				+ "\"BankAccount_AccountNumber\" WHERE \"UserAccount_UserName\" = '"+username+"'";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(sqlQuery);
		 while (rs.next()){
			 
		 }
	}

	public int Login(String login, String password, Connection con) {
		
		CallableStatement cstmt;
		try {
			cstmt = con.prepareCall("{CALL \"DTUGRP03\".Verification(?,?,?)}");
			cstmt.setString(1, login);
		 	cstmt.setString(2, password);
		 	cstmt.registerOutParameter(3, java.sql.Types.INTEGER);
		 	cstmt.execute();
		 	return cstmt.getInt(3);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} 
		
	}

	public String CreateAcc(userData user, Connection con) {
		CallableStatement cstmt;
		int result = 0;
		try {
			cstmt = con.prepareCall("{CALL \"DTUGRP03\".CreateUserAccount(?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, user.getUsername());
		 	cstmt.setString(2, user.getFullName());
		 	cstmt.setInt(3, user.getTelephoneNumber());
		 	cstmt.setString(4, user.getPassword());
		 	cstmt.setString(5, user.getType());
		 	cstmt.setInt(6, user.getPostnumber());
		 	cstmt.setString(7, user.getCurrency());
		 	cstmt.registerOutParameter(8, java.sql.Types.VARCHAR);
		 	cstmt.registerOutParameter(9, java.sql.Types.INTEGER);
		 	cstmt.execute();

		 	result = cstmt.getInt(9);
		 	String status = cstmt.getString(8);
		 	
		 	return result + ";" + status;
		 	
		} catch (SQLException e) {
			String status = "An sql exception was thrown by the database";
			e.printStackTrace();
			return result + ";" + status;
		} 
	}
}

