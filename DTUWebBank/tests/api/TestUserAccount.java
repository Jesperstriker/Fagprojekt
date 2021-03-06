package api;

import org.junit.*;
import java.util.*;

import api.Controller;
import dataObjects.UserAccount;

public class TestUserAccount {
	
	/*
	 * Author: Michael
	 * Test name: testCreateUserAdmin
	 * Test description:
	 * 		Tests that the system can create a user who is an administrator
	 * Test steps:
	 * 		1. Creating values for a new user account
	 * 		2. Tests that the user does not already exist
	 * 		3. Generates the user
	 * 		4. Tests if the newly added user now exists
	 * 		5. Tests that the user object is looking as expected
	 */
	@Test
	public void testCreateUserAdmin(){
		Controller cont = new Controller();
		
		// Step 1: Creating values for a new user account
		String pw = "adminPW";
		String username = "Admin";
		String ownerName = "Michael";
		String type = "admin";
		
		// Step 2: Tests that the user does not already exist
		assertFalse(cont.userExists(username));
		
		// Step 3: Generates the user
		UserAccount user = cont.CreateUserAccount(username,ownerName,type,pw);
		
		// Step 4: Tests if the newly added user now exists
		assertTrue(cont.UserExists(username));
		
		// Step 5: Tests that the user object is looking as expected
		assertEquals(user.getPW(),pw);
		assertEquals(user.getUsername(),username);
		assertEquals(user.getOwnerName(),ownerName);
		assertEquals(user.getType(),type);
		assertEquals(user.getBankAccounts().size,0);
		assertTrue(user.isAdmin());
	}
	
	/*
	 * Author: Michael
	 * Test name: testDeleteUser
	 * Test description:
	 * 		Tests that the system can delete a user
	 * Test steps:
	 * 		1. Creating an admin user whom can delete other user accounts
	 * 		2. Creating user to be deleted
	 * 		3. Creating an extra non-admin user, whom attempts to use delete
	 * 		4. Tests that non-admin can't delete other users
	 * 		5. Tests that the user to be deleted still exists
	 * 		6. Tests that admin users cant delete users with balance not 0
	 * 		7. Tests that admin users can delete users with balance 0
	 * 		8. Tests that the user to be deleted does no longer exist
	 * 
	 * Note: deleting a user uses Controller.Delete(target user,own password)
	 * 		
	 */
	@Test
	public void testDeleteUser() throws Exception{
		Controller cont = new Controller();
		
		// Step 1: Creating an admin user whom can delete other user accounts
		String pw = "adminPW";
		String username = "Admin";
		String ownerName = "Michael";
		String type = "admin";
		UserAccount userAdmin = cont.CreateUserAccount(username,ownerName,type,pw);
		
		// Step 2: Creating user to be deleted
		pw = "adminPW";
		username = "toBeDeleted";
		ownerName = "Jesper";
		type = "customer";
		UserAccount userDelete = cont.CreateUserAccount(username,ownerName,type,pw);
		
		// Step 3: Creating non-admin user, who attempts to use delete
		pw = "adminPW";
		username = "notAdmin";
		ownerName = "Michael";
		type = "customer";		
		UserAccount userNotAdmin = cont.CreateUserAccount(username,ownerName,type,pw);
		
		// Step 4: Logs in as not admin, and attempts external deletion
		cont.Login(userNotAdmin.getUsername,userNotAdmin.getPW);
		
		try {
			cont.Delete(userDelete.getUsername,userNotAdmin.getPW);
			fail("An OperationNotAllowedException should have been thrown");
		} catch ( OperationNotAllowedException e) {
			assertEquals("You cant delete external users if you do not have administrator privileges", e.getMessage());
			assertEquals("DeleteAttemptNotAdmin", e.getOperation());
		}
		
		// Step 5: Tests that userDelete still exists:
		assertTrue(cont.UserExists("toBeDeleted"));
		
		// Step 6: Tests that the admin cant delete external users if their balance is not 0
		/**
		 * 
		 * MISSING!
		 * 
		 * Cant be made until we have decided how BankAccounts work
		 * 
		 * MISSING!
		 */
		
		// Step 7: Tests that an admin can delete external user with total balance 0:
		assertEquals(userDelete.getTotalBalance,0);
		cont.Delete(userDelete.getUsername,userAdmin.getPW);
		
		// Step 8: Tests that userDelete has been deleted:
		assertFalse(cont.UserExists("toBeDeleted"));
		
		
	}
	
}
