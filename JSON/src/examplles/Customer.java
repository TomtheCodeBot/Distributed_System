package examplles;

public class Customer {
	public long ID;
	public String name;
	public long Age;
	public Customer(long iD, String name, long age) {
		super();
		ID = iD;
		this.name = name;
		Age = age;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getAge() {
		return Age;
	}
	public void setAge(long age) {
		Age = age;
	}
	@Override
	public String toString() {
		return "Customer [ID=" + ID + ", name=" + name + ", Age=" + Age + "]";
	}
}
