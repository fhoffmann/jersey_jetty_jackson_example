package de.beserious.test.JerseyShowcase.auth;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

public class SecurityContextImpl implements SecurityContext {

	Principal principal = null;
	String role = null;
	String auth_scheme = null;
	boolean https = false;
	
	public SecurityContextImpl(Principal p, String role, boolean https, String auth_scheme) {
		this.principal = p;
		this.role = role;
		this.https = https;
		this.auth_scheme = auth_scheme;
	}
	
	@Override
	public Principal getUserPrincipal() {
		return this.principal;
	}

	@Override
	public boolean isUserInRole(String role) {
		return role.equals(this.role);
	}

	@Override
	public boolean isSecure() {
		return this.https;
	}

	@Override
	public String getAuthenticationScheme() {
		return this.auth_scheme;
	}

}
