package de.beserious.test.JerseyShowcase.provider.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

import de.beserious.test.JerseyShowcase.auth.PrincipalImpl;
import de.beserious.test.JerseyShowcase.auth.SecurityContextImpl;

@Provider
public class SimpleRequestFilter implements ContainerRequestFilter {

	public static String HEADER_ATTRIBUTE_AUTHORIZATION = "Authorization";
	public static String HEADER_ATTRIBUTE_AUTHORIZATION_BASICAUTH = "Basic ";

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {

		MultivaluedMap<String, String> headers = requestContext.getHeaders();
		String authorization = headers.getFirst(HEADER_ATTRIBUTE_AUTHORIZATION);

		String username = null, password = null;

		if (authorization != null) {
			if (authorization
					.startsWith(HEADER_ATTRIBUTE_AUTHORIZATION_BASICAUTH)) {

				String auth_decoded = Base64.decodeAsString(authorization
						.substring(HEADER_ATTRIBUTE_AUTHORIZATION_BASICAUTH
								.length()));
				String[] data = auth_decoded.split(":");
				if (data.length == 2) {
					username = data[0];
					password = data[1];

					// Simple Compare
					// Do something better ;)
					if (username.equals("admin") && password.equals("123")) {

						requestContext
								.setSecurityContext(new SecurityContextImpl(
										new PrincipalImpl("admin"), "admin",
										false,
										HEADER_ATTRIBUTE_AUTHORIZATION_BASICAUTH));
					}
				}
			}
		}
	}
}
