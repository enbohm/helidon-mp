package se.enbohms.helidon;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/helidon")
public class HelloEndpoint {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{name}")
	public Response sayHi(@PathParam("name") String name) {
		return Response.ok(Json.createObjectBuilder().add("Hello ", name).build()).build();
	}
}
