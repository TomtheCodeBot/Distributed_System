package script;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.naming.NamingException;
@Path("/example")
public class Greetingscontroller {
	@GET
	@Path("/greeting")
	public Response getMessage(@DefaultValue("") @QueryParam("id") String id, 
			@DefaultValue("") @QueryParam("name") String name) throws SQLException, NamingException   {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		JsonObject object =  builder.add("id", id).add("name", name).build();
		return Response.ok().entity(object.toString()).build();
	}
	
	@POST
	@Path("/answers")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendbackMessage(Greetings greeting) {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		JsonObject object =  builder.add("id", greeting.getId()).add("name", greeting.getName()).build();
		return Response.ok().entity(object.toString()).build();
	}
	@POST
	@Path("/moreanswers")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendbackMoreMessage(Customer customer) {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("id", customer.getID()).add("name", customer.getNames())
				.add("age", customer.getAge());
		JsonArrayBuilder arrbuilder = Json.createArrayBuilder();
		
		JsonArray arr = arrbuilder.add(customer.getAccounts().get(0)).add(customer.getAccounts().get(1)).build();
		builder.add("accounts", arr);
		JsonObject object =  builder.build();
		return Response.ok().entity(object.toString()).build();
	}
}
