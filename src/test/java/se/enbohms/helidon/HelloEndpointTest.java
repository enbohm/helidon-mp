package se.enbohms.helidon;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import javax.json.Json;
import javax.json.JsonObject;

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
		var httpClient = HttpClient.newBuilder().build();
		var request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/helidon/hello/JD")).build();
		var response = httpClient.send(request, BodyHandlers.ofString());
		JsonObject jsonObject = Json.createReader(new StringReader(response.body())).readObject();
		assertEquals("Name should be JD", "JD", jsonObject.getString("Hello"));
	}

}
