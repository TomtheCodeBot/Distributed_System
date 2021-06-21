package script;

import java.util.List;

public class Customer {
	public long ID;
	public String names;
	public long Age;
	public List<String> accounts;
	public Customer(long iD, String names, long age, List<String> accounts) {
		ID = iD;
		this.names = names;
		Age = age;
		this.accounts = accounts;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public long getAge() {
		return Age;
	}
	public void setAge(long age) {
		Age = age;
	}
	public List<String> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<String> accounts) {
		this.accounts = accounts;
	}
	@Override
	public String toString() {
		return "Customer [ID=" + ID + ", names=" + names + ", Age=" + Age + ", accounts=" + accounts + "]";
	}
}
