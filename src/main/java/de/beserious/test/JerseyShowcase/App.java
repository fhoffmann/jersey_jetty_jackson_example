package de.beserious.test.JerseyShowcase;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

public class App {
	public static void main(String[] args) {
		new App();
	}

	private Server server = null;
	private ServletContextHandler sch = null;
	private ServletHolder jerseyServletHolder = null;
	private int port = 8080;

	public App() {

		this.server = new Server(this.port);

		this.sch = new ServletContextHandler(server, "/servlets");

		this.jerseyServletHolder = new ServletHolder(ServletContainer.class);
		jerseyServletHolder.setInitParameter(
				ServerProperties.PROVIDER_PACKAGES,
				"de.beserious.test.JerseyShowcase.provider");
		jerseyServletHolder.setInitParameter(
				ServerProperties.PROVIDER_SCANNING_RECURSIVE, "true");

		// Add Jersey Servlet Holder to Jetty ServletContextHandler
		sch.addServlet(jerseyServletHolder, "/jersey/*");

		try {
			this.server.start();
			this.server.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
