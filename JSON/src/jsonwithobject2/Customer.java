package jsonwithobject2;

import java.util.List;

public class Customer {
	public long ID;
	public List<String> names;
	public long Age;
	public List<Account> accounts;
	public Customer(long iD, List<String> names, long age, List<Account> accounts) {
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
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
	public long getAge() {
		return Age;
	}
	public void setAge(long age) {
		Age = age;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	@Override
	public String toString() {
		return "Customer [ID=" + ID + ", names=" + names + ", Age=" + Age + ", accounts=" + accounts + "]";
	}
}
