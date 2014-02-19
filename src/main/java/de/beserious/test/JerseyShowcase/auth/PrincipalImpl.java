package de.beserious.test.JerseyShowcase.auth;

import java.security.Principal;

public class PrincipalImpl implements Principal {

	String username = null;
	
	public PrincipalImpl(String username) {
		this.username = username;
	}
	
	@Override
	public String getName() {
		return this.username;
	}

}
