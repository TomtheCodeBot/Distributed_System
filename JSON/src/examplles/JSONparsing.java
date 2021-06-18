package examplles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONparsing {
	public static void main(String args[]) throws IOException, ParseException {
		FileReader fr = new FileReader("customer.json");
		JSONObject jo = (JSONObject) new JSONParser().parse(fr);
		
		Long id = (long) jo.get("id");
		String name = (String) jo.get("name");
		Long age = (long) jo.get("age");
		
		System.out.println(new Customer(id, name, age));
	}
}
