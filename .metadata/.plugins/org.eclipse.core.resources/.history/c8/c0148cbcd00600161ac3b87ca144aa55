package api;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Controller {
	private Properties properties = new Properties();
	private String url = "jdbc:db2://myhost:5021/mydb";
	private Connection con;

	private void transaction(int sender, 
			int recipient, double amount, String Valuta) throws SQLException {
		Statement stmt;
		String sql;
		ResultSet rs;
		properties.put("user", "DTU06");
		properties.put("password", "FAGP2016");
		con = DriverManager.getConnection(url, properties);
		stmt = con.createStatement();
		sql = "CALL Transfer(" + amount + "," + sender + ", " + recipient + ")";
		rs = stmt.executeQuery(sql);
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
