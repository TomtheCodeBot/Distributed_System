package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

public class GradesSynchronization {
	public static void sync() throws SQLException, NamingException {
		String url = "jdbc:mysql://localhost:3306/"; 

	    //In order to connect to specific database specify its name
	    String dbGerman = "db1";
	    String dbVietnam = "db2";
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
	    
        Connection con1 = DriverManager.getConnection(url + dbGerman, userName, password); 
        Connection con2 = DriverManager.getConnection(url + dbVietnam, userName, password); 
		boolean checker=true;
		List<Student> fromVietnam = GradesVietnameseDatabase.getAllStudent();
		List<Student> fromGermany = GradesGermanDatabase.getAllStudent();
		List<Integer> idVietnam=new ArrayList<Integer>();
		List<Integer> idGermany=new ArrayList<Integer>();
		for(int i=0;i<fromVietnam.size();i++) {
			idVietnam.add(fromVietnam.get(i).getID());
		}
		for(int i=0;i<fromGermany.size();i++) {
			idGermany.add(fromGermany.get(i).getID());
		}
		for(int i=0;i<fromVietnam.size();i++) {
			if(!idGermany.contains(fromVietnam.get(i).getID())) {
				Statement stmt = con1.createStatement();
				int rs = stmt.executeUpdate("INSERT INTO student VALUE("+String.valueOf(fromVietnam.get(i).getID())+",'"
												+fromVietnam.get(i).name+"',"
												+String.valueOf(fromVietnam.get(i).getGPA())+")");
				if(rs==1) {
					System.out.println("Insert Successful Student ID: "+String.valueOf(fromVietnam.get(i).getID()));
				}
				else {
					System.out.println("Insert Failed");
									}
			}
		}
		for(int i = 0;i<fromGermany.size();i++) {
			if(!idVietnam.contains(fromGermany.get(i).getID())) {
				Statement stmt = con2.createStatement();
				int rs = stmt.executeUpdate("INSERT INTO student VALUE("+String.valueOf(fromGermany.get(i).getID())+",'"
												+fromGermany.get(i).name+"',"
												+String.valueOf(fromGermany.get(i).getGPA())+")");
				if(rs==1) {
					System.out.println("Insert Successful Student ID: "+String.valueOf(fromGermany.get(i).getID()));
				}
				else {
					System.out.println("Insert Failed");
									}
			}
		}
		
	} 
	public static boolean checkID(int ID) throws SQLException{
		String url = "jdbc:mysql://localhost:3306/"; 

	    //In order to connect to specific database specify its name
	    String dbID = "id_check";

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

        Connection con1 = DriverManager.getConnection(url + dbID, userName, password);
        Statement stmt = con1.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT id FROM student");
		while(rs.next()) {
			if(rs.getInt(1)==ID) {
				return true;
			}
			
		}
		stmt.executeUpdate("INSERT INTO student VALUES("+String.valueOf(ID)+")");
		return false;
	}
}
