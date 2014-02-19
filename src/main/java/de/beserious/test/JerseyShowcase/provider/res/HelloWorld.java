package de.beserious.test.JerseyShowcase.provider.res;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Path("hello")
public class HelloWorld {

	@Context
	SecurityContext securityContext;

	@GET
	public Response greetings() {

		// Jackson json implementation
		ObjectMapper om = new ObjectMapper();
		ObjectNode on = om.createObjectNode();
		on.put("Hello", "World!");

		return Response.ok(on, MediaType.APPLICATION_JSON_TYPE).build();
	}

	@GET
	@Path("secure")
	public Response greetingsSecure() {

		if (securityContext != null) {
			ObjectMapper om = new ObjectMapper();
			ObjectNode on = om.createObjectNode();
			if (securityContext.getUserPrincipal() != null) {
				on.put("Hello", securityContext.getUserPrincipal().getName());

				if (securityContext.isUserInRole("admin")) {
					on.put("admin", true);
					return Response.ok(on, MediaType.APPLICATION_JSON_TYPE)
							.build();
				}
			}
		}
		return Response.status(Status.UNAUTHORIZED)
				.header("WWW-Authenticate", "Basic realm=\"Its secure here.\"")
				.build();
	}
}
