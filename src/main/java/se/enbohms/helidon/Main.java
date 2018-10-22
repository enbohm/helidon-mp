package se.enbohms.helidon;

import java.io.IOException;

import io.helidon.microprofile.server.Server;

public final class Main {

	/**
	 * Application main entry point.
	 * 
	 * @param args
	 *            command line arguments
	 * @throws IOException
	 *
	 */
	public static void main(final String[] args) throws IOException {
		Server.create().start();
	}
}
