package management;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;


public class Utils {
	
	public static String XMLFromObject(Student student) throws ParserConfigurationException, TransformerException {
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
		Element students = document.createElement("students");
        Element studentXML = document.createElement("student");
        Element name = document.createElement("name");
        Element id = document.createElement("id");
        Element gpa = document.createElement("gpa");
        
        name.appendChild(document.createTextNode(student.getName()));
        id.appendChild(document.createTextNode(String.valueOf(student.getID())));
        gpa.appendChild(document.createTextNode(String.valueOf(student.getGPA())));
        
        studentXML.appendChild(name);
        studentXML.appendChild(id);
        studentXML.appendChild(gpa);
        
        students.appendChild(studentXML);
        
        document.appendChild(students);
        transformer = tf.newTransformer();
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(writer));
 
        String xmlString = writer.getBuffer().toString();
		return xmlString;
	}
	public static String XMLFromObjectList(List<Student> studentlist) throws ParserConfigurationException, TransformerException {
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
		Element students = document.createElement("students");
		for(int i = 0;i<studentlist.size();i++) {
			Student student = studentlist.get(i);
			Element studentXML = document.createElement("student");
	        Element name = document.createElement("name");
	        Element id = document.createElement("id");
	        Element gpa = document.createElement("gpa");
	        
	        name.appendChild(document.createTextNode(student.getName()));
	        id.appendChild(document.createTextNode(String.valueOf(student.getID())));
	        gpa.appendChild(document.createTextNode(String.valueOf(student.getGPA())));
	        
	        studentXML.appendChild(name);
	        studentXML.appendChild(id);
	        studentXML.appendChild(gpa);
	        students.appendChild(studentXML);
		}
        document.appendChild(students);
        transformer = tf.newTransformer();
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(writer));
 
        String xmlString = writer.getBuffer().toString();
        System.out.println("flag");

        System.out.println(xmlString);
		return xmlString;
	}
	public static Document convertStringToXMLDocument(String xmlString) 
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
             
            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }
}
