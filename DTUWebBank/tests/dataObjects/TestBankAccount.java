package dataObjects;

import static org.junit.Assert.*;
import org.junit.*;

public class TestBankAccount {
	/*
	 * Author: Michael
	 * Test name: testBankAccount
	 * Test description:
	 * 		Tests that the system can properly manipulate the data objects
	 * 		These tests are mostly trivial as they only test getters and setters,
	 * 		But we implement them to achieve 100% code coverage
	 * Test steps:
	 * 		1. Create a BankAccount object with test values
	 * 		2. Test that constructor and .get methods work
	 * 		3. Create new test values and use .set methods
	 * 		4. Test that .set methods work
	 */
	
	@Test
	public void testBankAccount() {
		
		// Step 1:
		// Constructor for BankAccount:
		// BankAccount(double balance, String currency, String[] history, long accNumber)
		double bal = 100.00;
		String curr = "USD";
		String[] hist = {""};
		long accN = 999999999999L; //12 ciphers
		final double DELTA = 1e-15; //used for maximum variance when asserting equals for doubles
		
		BankAccount bankAcc = new BankAccount(bal,curr,hist,accN);
		
		// Step 2: Tests constructor and the .get methods
		assertEquals(bankAcc.getAccNumber(),accN);
		assertEquals(bankAcc.getBalance(),bal,DELTA);
		assertEquals(bankAcc.getCurrency(),curr);
		assertEquals(bankAcc.getHistory()[0],hist[0]);
		assertEquals(bankAcc.getHistory().length,1);
		
		
		// Step 3: Create new test values and use .set methods
		bal = 120.20;
		curr = "DKK";
		hist[0] = "From: 999999999990; To: 999999999999; Amount: 20; timestamp: 20/04/2016/10:53";
		accN = 999999999990L;
		bankAcc.setAccNumber(accN);
		bankAcc.setBalance(bal);
		bankAcc.setCurrency(curr);
		bankAcc.setHistory(hist);
		
		// Step 4: Test that .set methods work
		assertEquals(bankAcc.getAccNumber(),accN);
		assertEquals(bankAcc.getBalance(),bal,DELTA);
		assertEquals(bankAcc.getCurrency(),curr);
		assertEquals(bankAcc.getHistory()[0],hist[0]);
		assertEquals(bankAcc.getHistory().length,1);
		
	}
	
}
