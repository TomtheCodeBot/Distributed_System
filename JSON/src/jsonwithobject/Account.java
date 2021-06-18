package jsonwithobject;

public class Account {
	public long accountID;
	public long balance;
	public long getAccountID() {
		return accountID;
	}
	public void setAccountID(long accountID) {
		this.accountID = accountID;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public Account(long accountID, long balance) {
		this.accountID = accountID;
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", balance=" + balance + "]";
	}
}
