package dataObjects;

public class UserAccount {
	private String username;
	private String ownerName;
	private BankAccount[] bankAccounts;
	private String type;
	private String password;
	
	public UserAccount(String username, String ownerName, BankAccount[] bankAccounts, String type, String password){
		this.setUsername(username);
		this.setOwnerName(ownerName);
		this.setBankAccounts(bankAccounts);
		this.setType(type);
		this.setPassword(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public BankAccount[] getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(BankAccount[] bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
