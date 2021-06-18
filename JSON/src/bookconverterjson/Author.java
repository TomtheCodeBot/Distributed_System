package bookconverterjson;

public class Author {
	private String name;
	private long age;
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
	public Author(String name, long age) {
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Author [name=" + name + ", age=" + age + "]";
	}
	
}
