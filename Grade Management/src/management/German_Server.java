package management;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;




public class German_Server {
	public static void main(String[] args) throws SQLException, NamingException, ParserConfigurationException, TransformerException, ClassNotFoundException {
		int serverPort = 2001;
		ServerSocket serverSocket = null;
		ObjectOutputStream toClient = null;
		ObjectInputStream fromClient = null;
		
		try {
			serverSocket = new ServerSocket(serverPort);
			System.out.println("connected");
			
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Just connected to " + socket.getRemoteSocketAddress());
				
				toClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				fromClient = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				
				String choice= (String) fromClient.readObject();
				switch (choice) {
					case "Show current db":
						String ret=Utils.XMLFromObjectList(GradesGermanDatabase.getAllStudent());
						toClient.writeObject(ret); 
						break;
					case "Synchronize db":
						GradesSynchronization.sync();
						toClient.writeObject("Sync Successful"); 
						break;
					case "Insert object":
						String insertXML = (String) fromClient.readObject();
						Document document = Utils.convertStringToXMLDocument(insertXML);
				        boolean insert_check=true;
				        NodeList studentList = document.getElementsByTagName("student");
				        List<Student> studentArray =new ArrayList<Student>();
				        for(int i=0;i<studentList.getLength();i++) {
				        	Element student = (Element) studentList.item(i);
				        	int id = Integer.valueOf(student.getElementsByTagName("id").item(0).getTextContent());
				        	String name = student.getElementsByTagName("name").item(0).getTextContent();
				        	float gpa = Float.valueOf(student.getElementsByTagName("gpa").item(0).getTextContent());
				        	
				        	studentArray.add(new Student(id, name, gpa));
				        	insert_check=GradesGermanDatabase.insertStudent(studentArray.get(i));
				        	if(insert_check) {
				        		toClient.writeObject("Insert "+studentArray.get(i)+".");
				        	}else {
				        		toClient.writeObject("Can't insert "+studentArray.get(i)+".");
				        	}
				        }
						break;
					default:
						toClient.writeObject("No command exist.");
						break;

				}
				toClient.flush();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
