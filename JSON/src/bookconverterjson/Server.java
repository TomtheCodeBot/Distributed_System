package bookconverterjson;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Server {
	public static void main(String[] args) throws SQLException {
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

				Book insertBook = (Book) fromClient.readObject();
				DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		        Document document = documentBuilder.parse(new File("Books.xml"));
		        
		        Element books = document.getDocumentElement();
		        Element newbook = document.createElement("book");
		        
		        Element title = (Element) document.createElement("name");
		        Element publisher = (Element) document.createElement("publisher");
		        Element author = document.createElement("author");
		        Element authorname = (Element) document.createElement("name");
		        Element age = (Element) document.createElement("age");
		        
		        title.appendChild(document.createTextNode(insertBook.getTitle()));
		        publisher.appendChild(document.createTextNode(insertBook.getPublisher()));
		        authorname.appendChild(document.createTextNode(insertBook.getAuthor().getName()));
		        age.appendChild(document.createTextNode(String.valueOf(insertBook.getAuthor().getAge())));


		        author.appendChild(authorname);
		        author.appendChild(age);
		        
		        newbook.appendChild(title);
		        newbook.appendChild(publisher);
		        newbook.appendChild(author);
		        
		        books.appendChild(newbook);
		        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource domSource = new DOMSource(document);
	            StreamResult streamResult = new StreamResult(new File("Books.xml"));
	            transformer.transform(domSource, streamResult);
				toClient.writeObject("Data inserted");
				toClient.flush();
			}
			
		} catch (IOException|ClassNotFoundException|ParserConfigurationException|SAXException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
