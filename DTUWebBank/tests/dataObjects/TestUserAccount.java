package dataObjects;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class TestUserAccount {

	/*
	 * Author: Michael
	 * Test name: testUserAccount
	 * Test description:
	 * 		Tests that the system can properly manipulate the data objects
	 * 		These tests are mostly trivial as they only test getters and setters,
	 * 		But we implement them to achieve 100% code coverage
	 * Test steps:
	 * 		1. Create a UserAccount object with test values
	 * 		2. Test that constructor and .get methods work
	 * 		3. Create new test values and use .set methods
	 * 		4. Test that .set methods work
	 */
	
	@Test
	public void testUserAccount() {
		
		// Step 1: Create a UserAccount object with test values
		String username = "Michael";
		String ownerName = "Michael Svendsen";
		BankAccount[] bankAccounts = generateDummyBankAccounts();
		String type = "customer";
		String password = "test";
		
		UserAccount uacc = new UserAccount(username, ownerName, bankAccounts, type, password);
		
		// Step 2: Tests constructor and the .get methods
		assertEquals(uacc.getBankAccounts()[0],bankAccounts[0]);
		assertEquals(uacc.getBankAccounts()[1],bankAccounts[1]);
		assertEquals(uacc.getBankAccounts()[2],bankAccounts[2]);
		assertEquals(uacc.getBankAccounts().length,3);
		assertEquals(uacc.getPassword(),password);
		assertEquals(uacc.getType(),type);
		assertEquals(uacc.getUsername(),username);
		assertEquals(uacc.getOwnerName(),ownerName);
		
		// Step 3: Create new test values and use .set methods
		username = "Jesper";
		ownerName = "Jesper Douglas";
		BankAccount temp = bankAccounts[0];
		bankAccounts[0] = bankAccounts[1];
		bankAccounts[1] = bankAccounts[2];
		bankAccounts[2] = temp;
		type = "admin";
		password = "testtest";
		uacc.setBankAccounts(bankAccounts);
		uacc.setUsername(username);
		uacc.setOwnerName(ownerName);
		uacc.setPassword(password);
		uacc.setType(type);
		
		
		// Step 4: Test that .set methods work
		assertEquals(uacc.getBankAccounts()[0],bankAccounts[0]);
		assertEquals(uacc.getBankAccounts()[1],bankAccounts[1]);
		assertEquals(uacc.getBankAccounts()[2],bankAccounts[2]);
		assertEquals(uacc.getBankAccounts().length,3);
		assertEquals(uacc.getPassword(),password);
		assertEquals(uacc.getType(),type);
		assertEquals(uacc.getUsername(),username);
		assertEquals(uacc.getOwnerName(),ownerName);
		
		
	}

	private BankAccount[] generateDummyBankAccounts() {
		double bal1 = 100.00;
		String curr1 = "USD";
		String[] hist1 = {""};
		long accN1 = 999999999999L;
		
		double bal2 = 120.20;
		String curr2 = "DKK";
		String[] hist2 = {"From: 999999999990; To: 999999999999; Amount: 20; timestamp: 20/04/2016/10:53"};
		long accN2 = 999999999990L;

		double bal3 = 0.00;
		String curr3 = "USD";
		String[] hist3 = {""};
		long accN3 = 000000000001L;
		
		BankAccount acc1 = new BankAccount(bal1,curr1,hist1,accN1);
		BankAccount acc2 = new BankAccount(bal2,curr2,hist2,accN2);
		BankAccount acc3 = new BankAccount(bal3,curr3,hist3,accN3);
		
		return new BankAccount[]{acc1,acc2,acc3};
	}
	
	
}
