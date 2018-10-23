package se.enbohms.helidon;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.annotation.Counted;

import io.opentracing.Scope;
import io.opentracing.Tracer;

/**
 * Represent a simple endpoint which just returns a JSON structure taking the
 * path parameter as input (e.g. http://.../helidon/hello/john -> {"Hello " :
 * "John"})
 * 
 * @author enbohm
 *
 */
@ApplicationScoped
@Path("/")
public class HelloEndpoint {

	@Inject
	private Tracer tracer;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/hello/{name}")
	@Counted
	public Response sayHi(@PathParam("name") String name) {
		try (Scope scope = tracer.buildSpan("sayHi").withTag("name", name).startActive(true);) {
			return Response.ok(Json.createObjectBuilder().add("Hello", name).build()).build();
		}
	}
}
