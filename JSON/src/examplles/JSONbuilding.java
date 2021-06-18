package examplles;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONbuilding {
	public static void main(String args[]) throws IOException, ParseException {
		JSONObject jo = new JSONObject();
		
		jo.put("id",11110);
		jo.put("name","Tom");
		jo.put("age", 32);
		
		Long id = ((Number) jo.get("id")).longValue();;
		String name = (String) jo.get("name");
		Long age = ((Number) jo.get("age")).longValue();;
		
		System.out.println(new Customer(id, name, age));
		

	}
}
