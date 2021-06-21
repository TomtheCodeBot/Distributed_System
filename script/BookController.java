package script;

import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/example2")
public class BookController {
	@GET
	@Path("/getbook")
	public Response getMessage(@DefaultValue("") @QueryParam("title") String title, 
			@DefaultValue("") @QueryParam("publisher") String publisher,@DefaultValue("") @QueryParam("name") String name, 
			@DefaultValue("") @QueryParam("age") String age) throws SQLException, NamingException   {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		JsonObjectBuilder builder2 = Json.createObjectBuilder();
		JsonObject author =  builder2.add("name", name).add("age", age).build();
		JsonObject object =  builder.add("title", title).add("publisher", publisher).add("author", author).build();
		return Response.ok().entity(object.toString()).build();
	}
	
	@POST
	@Path("/setbook")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendbackMessage(Book Book) {
		System.out.println(Book);
		return Response.ok().build();
	}
}
