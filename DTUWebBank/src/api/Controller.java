package api;


import java.math.BigDecimal;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Controller {
	private static Properties properties = new Properties();
	private static String url = "jdbc:db2://192.86.32.54:5040/"
			+ "DALLASB:retrieveMessagesFromServerOnGetMessage=true;"
			+ "emulateParameterMetaDataForZCalls=1;"
			;private static Connection con;
	public static void main(String[] args) {
	BigDecimal val = BigDecimal.valueOf(200.0);
	try{
		transaction(1111,2222,val,"DKK");
	}
	catch(SQLException e){
		e.printStackTrace();
	}
	
	}
	
	private static void transaction(int sender,int recipient, BigDecimal amount, String currency) 
			throws SQLException {
		properties.put("user", "DTU06");
		properties.put("password", "FAGP2016");
		con = DriverManager.getConnection(url, properties);
		 CallableStatement cstmt = con.prepareCall("{CALL \"DTUGRP03\".Transfer(?,?,?,?,?)}");
	      cstmt.setInt(1, sender);
	      cstmt.setInt(2,recipient);
	      cstmt.setBigDecimal(3, amount);
	      cstmt.setString(4, currency);
	      cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
	      cstmt.execute();
	      System.out.println(cstmt.getString(5));
	}
	
	private int transactionHistory(int bankAcc) throws SQLException{
		Statement stmt;
		String sql;
		ResultSet rs;
		properties.put("user", "DTU06");
		properties.put("password", "FAGP2016");
		con = DriverManager.getConnection(url, properties);
		stmt = con.createStatement();
		sql = "SELECT * FROM \"DTUGRP03\".\"Transaction History\" "
				+ "WHERE sender = "+bankAcc+"AND Recipient ="+bankAcc+"GROUP BY date";
		rs = stmt.executeQuery(sql);
		
		return 0;
	}
	
	private ArrayList<Integer> seeBankAcc(int userAcc) throws SQLException {
		Statement stmt;
		String sql;
		ResultSet rs;
		properties.put("user", "DTU06");
		properties.put("password", "FAGP2016");
		con = DriverManager.getConnection(url, properties);
		stmt = con.createStatement();
		sql = "SELECT Bank Account FROM \"DTUGRP03\".\"Account Associativity\" WHERE User Account = "+userAcc;
		rs = stmt.executeQuery(sql);
	return null;
	}
	
	private void deleteBankAcc(int bankAcc) throws SQLException{
		Statement stmt;
		String sql;
		ResultSet rs;
		properties.put("user", "DTU06");
		properties.put("password", "FAGP2016");
		con = DriverManager.getConnection(url, properties);
		stmt = con.createStatement();
		sql = "DELETE FROM \"DTUGRP03\".\"Bank Account\" WHERE Bank Account = "+bankAcc;
		stmt.executeQuery(sql);
	}
	
	
	private void addBankAcc(int userAcc) throws SQLException{
		Statement stmt;
		String sql;
		ResultSet rs;
		properties.put("user", "DTU06");
		properties.put("password", "FAGP2016");
		con = DriverManager.getConnection(url, properties);
		stmt = con.createStatement();
		sql = "INSERT INTO \"DTUGRP03\".\"Bank Account\" VALUES() ";
		rs = stmt.executeQuery(sql);
	}
	
	
	
	

}
