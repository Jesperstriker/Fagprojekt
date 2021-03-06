package api;

import java.util.List;
import org.junit.*;

import dataObjects.UserAccount;



public class TestLogin extends sampleDataSetup{
	/*
	 * Author: Michael
	 * Test name: testLogin
	 * Test description:
	 * 		Tests that a user can log in using the controller
	 * Test steps:
	 * 		1. Generates a user
	 * 		2. Tests that no one is logged in
	 * 		3. Loggin in
	 * 		4. Tests that the login was a success
	 * 		5. Logs out
	 * 		6. Tests that no one is logged in
	 */
	
	@Test
	public void testLogin() {
		Controller cont = new Controller();
		// Step 1: Generating a user
		String pw = "adminPW";
		String username = "Admin";
		String ownerName = "Michael";
		String type = "admin";
		UserAccount user = cont.CreateUserAccount(username,ownerName,type,pw);
		
		// Step 2: Tests that no one is logged in
		assertFalse(cont.isLoggedin);
		
		// Step 3: Logging in
		boolean isLoggedin = cont.Login(username,pw);
		
		//Step 4: Tests that the login was a success
		assertTrue(isLoggedin);
		assertTrue(cont.isLoggedin);
		
		// Step 5: Logs out
		cont.Logout();
		
		// Step 6: Tests that no one is logged in
		assertFalse(cont.isLoggedin);
	}
	
	/*
	 * Author: Michael
	 * Test name: testLoginCredentialsException
	 * Test description:
	 * 		Tests that the correct exceptions are thrown when logging in using bad credentials
	 * Test steps:
	 * 		1. Generates a user
	 * 		2. Tests that no one is logged in
	 * 		3. Loggin in with bad credentials (wrong password)
	 * 		4. Tests that the login was a fail and 
	 * 		   that the correct exception is thrown
	 * 		5. Loggin in with bad credentials (wrong username)
	 * 		6. Tests that the login was a fail and 
	 * 		   that the correct exception is thrown
	 */
	
	@Test
	public void testLoginCredentialsException() throws Exception{
		Controller cont = new Controller();
		
		// Step 1: Generating a user
		String pw = "adminPW";
		String username = "Admin";
		String ownerName = "Michael";
		String type = "admin";
		UserAccount user = cont.CreateUserAccount(username,ownerName,type,pw);
		
		// Step 2: Tests that no one is logged in
		assertFalse(cont.isLoggedin);
		
		// Step 3: Loggin in with bad credentials
		String badPW = "adminpw";
		try {
			cont.Login(username,badPW);
			fail("A LoginException should have been thrown");
		} catch ( LoginException e) {
			assertEquals("Wrong password or username was entered", e.getMessage());
			assertEquals("BadPW", e.getOperation());
		}
		
		//Step 4: Tests that the login was a fail
		assertFalse(cont.isLoggedin);
		
		// Step 5: Loggin in with bad credentials
		String badUsername = "notAdmin";
		try {
			cont.Login(badUsername,pw);
			fail("A LoginException should have been thrown");
		} catch ( LoginException e) {
			assertEquals("Wrong password or username was entered", e.getMessage());
			assertEquals("BadUsername", e.getOperation());
		}
		
		//Step 6: Tests that the login was a fail
		assertFalse(cont.isLoggedin);
	}
	
	/*
	 * Author: Michael
	 * Test name: testLoginCredentialsException
	 * Test description:
	 * 		Tests that the correct exceptions are thrown when logging in 
	 * 		without entering a username
	 * Test steps:
	 * 		1. Login in with empty username field
	 * 		2. Tests that login was unsuccessful
	 */
	
	@Test
	public void testLoginNoUsernameException() throws Exception{
		Controller cont = new Controller();
		
		// Step 1: Login in with empty username field
		try {
			cont.Login("","somePassword");
			fail("A LoginException should have been thrown");
		} catch ( LoginException e) {
			assertEquals("Wrong password or username was entered", e.getMessage());
			assertEquals("NoUsername", e.getOperation());
		}
		
		// Step 2: Tests that login was unsuccessful
		assertFalse(cont.isLoggedin);
	}

}

		