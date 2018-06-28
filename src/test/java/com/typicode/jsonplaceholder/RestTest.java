package com.typicode.jsonplaceholder;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.testng.annotations.Test;

import com.typicode.jsonplaceholder.data.Post;

public class RestTest {

	@Test
	public void testGet() {
		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target("https://jsonplaceholder.typicode.com");

		WebTarget employeeWebTarget = webTarget.path("posts/1");

		Invocation.Builder invocationBuilder = employeeWebTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.get();
		
		Response response2 = ClientBuilder.newClient().target("https://jsonplaceholder.typicode.com").path("posts/1").request(MediaType.APPLICATION_JSON).get();

		assertEquals(response.getStatus(), 200);

		Post post = invocationBuilder.get(Post.class);

		assertEquals(1, post.getId());
		assertEquals(1, post.getUserId());
		assertNotNull(post.getBody());
		assertNotNull(post.getTitle());
	}

	@Test
	public void testPost() {
		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target("https://jsonplaceholder.typicode.com");

		WebTarget employeeWebTarget = webTarget.path("posts");

		Invocation.Builder invocationBuilder = employeeWebTarget.request(MediaType.APPLICATION_JSON);
		
		Post post = new Post(3, "TEST POST", "URA");
		
		Response response = invocationBuilder.post(Entity.entity(post, MediaType.APPLICATION_JSON));

		assertEquals(response.getStatus(), 201);
	}
	
	@Test
	public void testPut() {
		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target("https://jsonplaceholder.typicode.com");

		WebTarget employeeWebTarget = webTarget.path("posts/1");

		Invocation.Builder invocationBuilder = employeeWebTarget.request(MediaType.APPLICATION_JSON);
		
		Post post = new Post(1, "TEST POST", "URA");
		
		Response response = invocationBuilder.put(Entity.entity(post, MediaType.APPLICATION_JSON));

		assertEquals(response.getStatus(), 200);
	}
	
	@Test
	public void testDelete() {
		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target("https://jsonplaceholder.typicode.com");

		WebTarget employeeWebTarget = webTarget.path("posts/1");

		Invocation.Builder invocationBuilder = employeeWebTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.delete();

		assertEquals(response.getStatus(), 200);
	}

}
