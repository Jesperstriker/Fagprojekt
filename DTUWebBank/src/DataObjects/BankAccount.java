package dataObjects;
public class BankAccount {
	private double balance;
	private String currency;
	private String[] history;
	private long accNumber;
	
	
	public BankAccount(double balance, String currency, String[] history, long accNumber){
		this.setBalance(balance);
		this.setCurrency(currency);
		this.setHistory(history);
		this.setAccNumber(accNumber);
	}


	public long getAccNumber() {
		return accNumber;
	}


	public void setAccNumber(long accNumber2) {
		this.accNumber = accNumber2;
	}


	public String[] getHistory() {
		return history;
	}


	public void setHistory(String[] history) {
		this.history = history;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
