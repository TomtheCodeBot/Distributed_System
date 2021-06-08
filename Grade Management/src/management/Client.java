package management;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import management.Student;


public class Client {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, TransformerException {
		int serverPort = 2001;
		Socket socket = null;
		ObjectOutputStream toServer = null;
		ObjectInputStream fromServer = null;
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Which server you want to connect to?(German - Vietnamese)");
			
			String server = scanner.nextLine();
			if(server.contentEquals("Vietnamese")) {
				serverPort=2002;
			}
			InetAddress serverHost = InetAddress.getByName("localhost");
			System.out.println("Connecting to server on port " + serverPort);
			socket = new Socket(serverHost, serverPort);
			System.out.println("Just connected to " + socket.getRemoteSocketAddress());
			String choice;
			System.out.println("What do you want to do?(Insert Object,Import from server,Synchronize database)");
			choice = scanner.nextLine();
			switch(choice) {
				case "Insert Object":
					System.out.println("Input ID");
					int id =Integer.valueOf(scanner.nextLine());
					System.out.println("Input Name");
					String name = scanner.nextLine();
					System.out.println("Input GPA");
					float gpa =Float.valueOf(scanner.nextLine());
					Student student = new Student(id, name, gpa);
					String xmlString1 = Utils.XMLFromObject(student);
					toServer = new ObjectOutputStream(socket.getOutputStream());
					toServer.writeObject("Insert object");
					toServer.writeObject(xmlString1);
					fromServer = new ObjectInputStream(socket.getInputStream());
					String msgFromReply1 = (String) fromServer.readObject();
					System.out.println(msgFromReply1);
					break;
				case "Import from server":
					toServer = new ObjectOutputStream(socket.getOutputStream());
					toServer.writeObject("Show current db");
					fromServer = new ObjectInputStream(socket.getInputStream());
					String msgFromReply2 = (String) fromServer.readObject();
					
					Document document1 = Utils.convertStringToXMLDocument(msgFromReply2);
			        
			        NodeList studentList = document1.getElementsByTagName("student");
			        List<Student> studentArray =new ArrayList<Student>();
			        if(studentList.getLength()==0) {
						System.out.println("no entry from this database");
					}
			        for(int i=0;i<studentList.getLength();i++) {
			        	Element student1 = (Element) studentList.item(i);
			        	int id1 = Integer.valueOf(student1.getElementsByTagName("id").item(0).getTextContent());
			        	String name1 = student1.getElementsByTagName("name").item(0).getTextContent();
			        	float gpa1 = Float.valueOf(student1.getElementsByTagName("gpa").item(0).getTextContent());
			        	System.out.println(new Student(id1, name1, gpa1));
			        }
					break;
				case "Synchronize database":
					toServer = new ObjectOutputStream(socket.getOutputStream());
					toServer.writeObject("Synchronize db");
					fromServer = new ObjectInputStream(socket.getInputStream());
					String response = (String) fromServer.readObject();
					System.out.println(response);
					break;
				default:
					System.out.println("No service like this one;");
					break;
			}
			scanner.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
