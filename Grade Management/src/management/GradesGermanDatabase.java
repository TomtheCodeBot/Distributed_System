package management;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import management.GradesVietnameseDatabase;
import management.Student;

public class GradesGermanDatabase {
	public static Connection getAcademiaConnection() throws SQLException, NamingException {
		String url = "jdbc:mysql://localhost:3306/"; 

	    //In order to connect to specific database specify its name
	    String dbName = "db1";

	    //Initialize a driver so you can open a communications channel with the database.
	    String driver = "com.mysql.jdbc.Driver"; 
	    try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //credentials required to connect 
	    String userName = "newuser";  
	    String password = "password";

        Connection con = DriverManager.getConnection(url + dbName, userName, password); 
		return con;
	} 
	public static boolean insertStudent(Student student) throws SQLException, NamingException {
		Connection con = getAcademiaConnection();
		Statement stmt = con.createStatement();
		if(GradesSynchronization.checkID(student.getID())) {
			return false;
		}
		int rs = stmt.executeUpdate("INSERT INTO student VALUE("+String.valueOf(student.getID())+",'"
										+student.name+"',"
										+String.valueOf(student.getGPA())+")");
		if(rs==1) {
			System.out.println("Insert Successful Student ID: "+String.valueOf(student.getID()));
			return true;
		}
		else {
			System.out.println("Insert Failed");
			return false;
		}
	}
	public static List<Student> getAllStudent() throws SQLException, NamingException{
		List<Student>studentList = new ArrayList<Student>();
		Connection con = getAcademiaConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM student");
		while(rs.next()) {
			studentList.add(new Student(rs.getInt(1),rs.getString(2),rs.getFloat(3)));
		}
		return studentList;
	}
	public static List<Student> getStudentByID(int ID) throws SQLException, NamingException{
		List<Student>studentList = new ArrayList<Student>();
		Connection con = getAcademiaConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM student WHERE ID="+String.valueOf(ID));
		while(rs.next()) {
			studentList.add(new Student(rs.getInt(1),rs.getString(2),rs.getFloat(3)));
		}
		return studentList;
	}
	public static void main(String[] args) throws SQLException, NamingException {
		List<Student> studentList =getAllStudent();
		for(int i=0;i<studentList.size();i++) {
			System.out.println(studentList.get(i));
		}
	}

}
