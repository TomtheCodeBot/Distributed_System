package script;


import java.sql.SQLException;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/example")
public class ExampleResources {
	@GET
	public String getMessage() throws SQLException, NamingException   {
		return "Hello World!";
	}
}