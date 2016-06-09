import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Controller {
	private static Properties properties = new Properties();
	private static String url = "jdbc:db2://192.86.32.54:5040/" + "DALLASB:retrieveMessagesFromServerOnGetMessage=true;"
			+ "emulateParameterMetaDataForZCalls=1;";
	private static Connection con;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// transactionHistory(1111, "DKK");
		//editUserAccount("Jesper",123,"123",2800,"USD");
		getBankAcc("Jesper");
	}

	private static void transaction(BigDecimal amount, 
			int sender, int recipient, String currency)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		properties.put("user", "DTU06");
		properties.put("password", "FAGP2016");
		con = DriverManager.getConnection(url, properties);
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
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		properties.put("user", "DTU06");
		properties.put("password", "FAGP2016");
		con = DriverManager.getConnection(url, properties);

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
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		properties.put("user", "DTU06");
		properties.put("password", "FAGP2016");
		con = DriverManager.getConnection(url, properties);
		CallableStatement cstmt = con.prepareCall("{CALL \"DTUGRP03\".DeleteUser(?,?)}");
		cstmt.setString(1, userName);
		cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
		cstmt.execute();
		System.out.println(cstmt.getInt(2));
	}
	
	public static void deleteBankAcc(int AccNumber) 
			throws SQLException, ClassNotFoundException {
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		properties.put("user", "DTU06");
		properties.put("password", "FAGP2016");
		con = DriverManager.getConnection(url, properties);
		CallableStatement cstmt = con.prepareCall("{CALL \"DTUGRP03\".DeleteBankAcc(?,?)}");	
		cstmt.setInt(1, AccNumber);
		cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
		cstmt.execute();
		System.out.println(cstmt.getInt(2));
	}
	
	public static UserAccount getUserInfo(String userName) throws ClassNotFoundException, SQLException{
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		properties.put("user", "DTU06");
		properties.put("password", "FAGP2016");
		con = DriverManager.getConnection(url, properties);
		String sqlQuery = "SELECT * FROM \"DTUGRP03\".\"UserAccount\" WHERE \"UserName\" = "+userName; 
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sqlQuery);
		UserAccount user = new UserAccount();

		user.userName = userName;
		
		while (rs.next()){
		user.fullName = rs.getString(2);
		user.telephoneNumber = rs.getInt(3);
		user.password = rs.getString(4);
		user.type = rs.getString(5);
		user.postnumber = rs.getInt(6);
		user.currency = rs.getString(7);
		}
		
		return user;
	}
	
	public static void editUserAccount(String userName, 
			int telnumber, String passw, int postnumber, String Currency) throws ClassNotFoundException, SQLException{
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		properties.put("user", "DTU06");
		properties.put("password", "FAGP2016");
		con = DriverManager.getConnection(url, properties);
		
		String sqlQuery = "UPDATE \"DTUGRP03\".\"UserAccount\" "
				+ "SET \"TelephoneNumber\" = "+telnumber+ ", \"City_Postnumber\" = "+postnumber
				+ ", \"Password\" = '"+passw+"', \"Valuta_Currency\" = '"
				+Currency+"' WHERE \"UserName\" = '"+userName+"'";
	Statement stmt = con.createStatement();
	stmt.executeUpdate(sqlQuery);
	}
	
	
	public static void getBankAcc(String username) throws ClassNotFoundException, SQLException{
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		properties.put("user", "DTU06");
		properties.put("password", "FAGP2016");
		con = DriverManager.getConnection(url, properties);
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
	
	
	
}
