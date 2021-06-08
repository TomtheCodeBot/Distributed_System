package management;

public class Student {
	public int ID;
	public String name;
	public float GPA;
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getGPA() {
		return GPA;
	}
	public void setGPA(float gPA) {
		GPA = gPA;
	}
	public Student(int iD, String name, float gPA) {
		super();
		ID = iD;
		this.name = name;
		GPA = gPA;
	}
	@Override
	public String toString() {
		return "Student [ID=" + ID + ", name=" + name + ", GPA=" + GPA + "]";
	}
	
}
