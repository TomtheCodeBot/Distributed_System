import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Server implements service {
    public Server() {}
    public int newsNum(){
    	try {
			int result = 0;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
			DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "newuser", "password");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select count(*) from news_paper");
			
			
			if(rs.next()) {
				result =  rs.getInt(1);
			}
			con.close();
			return result;
			
		} catch (Exception e) {
			System.out.println(e);
		}
    	return 0;
    }
    public int bookNum(){
    	try {
			int result = 0;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
			DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "newuser", "password");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select count(*) from book");
		
			
			if(rs.next()) {
				result= rs.getInt(1);
			}
			con.close();
			return result;

		} catch (Exception e) {
			System.out.println(e);
		}
    	return 0;
    }
    public void addBook(int id,String name) {
    	try {
			int result = 0;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "newuser", "password");

			Statement stmt = con.createStatement();
			result = stmt.executeUpdate("insert into book values("+String.valueOf(id)+",\""+name+"\")");
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
    	
    }
    public void addNews(int id,String name) {
    	try {
			int result = 0;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "newuser", "password");

			Statement stmt = con.createStatement();
			result = stmt.executeUpdate("insert into news_paper values("+String.valueOf(id)+",\""+name+"\")");
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
    	
    }
    public static void main(String args[]) {
        try {
            Server obj = new Server();
            service stub = (service) 
			                 UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(1);
            registry.bind("service", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
