package exercise;

import java.util.List;

public class Customer {
	public long ID;
	public List<String> names;
	public long Age;
	public List<String> addresses;
	public Customer(long iD, List<String> names, long age, List<String> addresses) {
		ID = iD;
		this.names = names;
		Age = age;
		this.addresses = addresses;
	}
	@Override
	public String toString() {
		return "Customer [ID=" + ID + ", names=" + names + ", Age=" + Age + ", addresses=" + addresses + "]";
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
	public List<String> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}
	
}
