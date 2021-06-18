package jsonwithobject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONparsing {
	public static void writefile(String filename,Customer customer) throws IOException {
		JSONObject jo = new JSONObject();
		jo.put("id", customer.getId());
		jo.put("name", customer.getName());
		jo.put("age", customer.getAge());
		
		JSONArray arr = new JSONArray();
		
		List<Account> list_acc = customer.getAccounts();
		for(int i=0;i< list_acc.size();i++) {
			JSONObject acc = new JSONObject();
			acc.put("accountid", list_acc.get(i).getAccountID());
			acc.put("balance", list_acc.get(i).getBalance());
			arr.add(acc);
		}
		jo.put("accounts", arr);
		
		FileWriter fr = new FileWriter(filename);
		fr.write(jo.toString());
		fr.flush();
		fr.close();
	}
	public static Customer readfile(String filename) throws IOException, ParseException {
		FileReader fr = new FileReader(filename);
		JSONObject jo = (JSONObject) new JSONParser().parse(fr);
		
		Long id = (long) jo.get("id");
		String name = (String) jo.get("name");
		Long age = (long) jo.get("age");
		JSONArray accountsJson = (JSONArray) jo.get("accounts");
		List<Account> accountsinput = new ArrayList<Account>();
		
		for(int i = 0;i < accountsJson.size();i++) {
			Long accountid = (long) ((JSONObject) accountsJson.get(i)).get("accountid");
			Long balance = (long) ((JSONObject) accountsJson.get(i)).get("balance");
			accountsinput.add(new Account(accountid,balance));
		}
		
				
		return new Customer(id,name,age,accountsinput);
	}
	public static void main(String[] args) throws IOException, ParseException {
		writefile("customer5.json",readfile("customer4.json"));
	}
}
