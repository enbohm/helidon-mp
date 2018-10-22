package se.enbohms.helidon;

import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.helidon.microprofile.server.Server;

public class HelloEndpointTest {

	private static Server server;

	@BeforeClass
	public static void startTheServer() throws Exception {
		server = Server.create();
		server.start();
	}

	@AfterClass
	public static void stopServer() throws Exception {
		server.stop();
	}

	@Test
	public void should_return_valid_name() throws Exception {
		HttpURLConnection conn = getURLConnection("GET", "/helidon/hello/JD");
		JsonReader jsonReader = Json.createReader(conn.getInputStream());
		JsonObject jsonObject = jsonReader.readObject();
		assertEquals("Name should be JD", "JD", jsonObject.getString("Hello"));
	}

	private HttpURLConnection getURLConnection(String method, String path) throws Exception {
		URL url = new URL("http://localhost:8080" + path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		conn.setRequestProperty("Accept", "application/json");
		return conn;
	}
}
