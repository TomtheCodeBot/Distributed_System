package jsonwithobject;

import java.util.List;

public class Customer {
	public long id;
	public String name;
	public long age;
	public List<Account> accounts;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getAge() {
		return age;
	}
	public void setAge(long age) {
		this.age = age;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public Customer(long id, String name, long age, List<Account> accounts) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.accounts = accounts;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", age=" + age + ", accounts=" + accounts + "]";
	}
	
}
