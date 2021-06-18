package bookconverterjson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSONparser {
	public static String object2JSONstring(Book book) throws IOException {
		JSONObject jo = new JSONObject();
		jo.put("title", book.getTitle());
		jo.put("publisher", book.getPublisher());
		
		Author author = book.getAuthor(); 
		
		JSONObject jsonAuthor = new JSONObject();
		jsonAuthor.put("name", author.getName());
		jsonAuthor.put("age", author.getAge());
		
		jo.put("author", jsonAuthor);
		
		return jo.toJSONString();
		
	}
	public static Book JSONstring2object(String JSONString) throws IOException, ParseException {
		JSONObject jo = (JSONObject) new JSONParser().parse(JSONString);
		
		String title = (String) jo.get("title");
		String publisher = (String) jo.get("publisher");
		
		JSONObject authorJSON = (JSONObject) jo.get("author");
		
		String name = (String) authorJSON.get("name");
		Long age = (long) authorJSON.get("age");
		
		return new Book(title,publisher,new Author(name,age));
	}
	public static void main(String[] args) throws IOException, ParseException {
		Author author = new Author("Duy",123);
		Book book = new Book("Bruh momment","ya boi",author);
		System.out.println(JSONstring2object(object2JSONstring(book)));
	}
}
