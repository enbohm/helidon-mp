package se.enbohms.helidon;

import java.io.IOException;

import io.helidon.microprofile.server.Server;

public class Main {

	/**
	 * Cannot be instantiated.
	 */
	private Main() {
	}

	/**
	 * Application main entry point.
	 * 
	 * @param args
	 *            command line arguments
	 * @throws IOException
	 *             if there are problems reading logging properties
	 */
	public static void main(final String[] args) throws IOException {
		startServer();
	}

	/**
	 * Start the server.
	 * 
	 * @return the created {@link Server} instance
	 * @throws IOException
	 *             if there are problems reading logging properties
	 */
	protected static Server startServer() throws IOException {
		// Server will automatically pick up configuration from
		// microprofile-config.properties
		Server server = Server.create();
		server.start();
		return server;
	}
}
