package exercise;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONparsing {
	public static Customer readingObject(String filename) throws IOException, ParseException {
		FileReader fr = new FileReader(filename);
		JSONObject jo = (JSONObject) new JSONParser().parse(fr);
		
		Long id = (long) jo.get("id");
		List name = (List) jo.get("name");
		Long age = (long) jo.get("age");
		List addresses = (List) jo.get("addresses");
		
		return new Customer(id,name,age,addresses);
	}
	public static void writingObject(String filename, Customer customer) throws IOException {
		JSONObject jo = new JSONObject();
		
		jo.put("id",customer.getID());
		jo.put("name",customer.getNames());
		jo.put("age", customer.getAge());
		jo.put("addresses",customer.getAddresses());
		
		FileWriter fr = new FileWriter(filename);
		fr.write(jo.toString());
		fr.flush();
		fr.close();
	}
	public static void main(String args[]) throws IOException, ParseException {
		Customer customer = readingObject("customer2.json");
		System.out.println(readingObject("customer2.json"));
		writingObject("customer3.json",customer);
	}
}
