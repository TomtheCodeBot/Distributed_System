package step3;

import java.io.Serializable;

public final class Message implements Serializable {
	private String name;
	private String ID;
	private String Year;
	private String Gender;
	Message(String name, String ID, String Year,String Gender) {
		this.name = name;
		this.ID = ID;
		this.Year = Year;
		this.Gender = Gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	
}