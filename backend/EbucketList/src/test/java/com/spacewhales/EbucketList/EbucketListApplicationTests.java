package com.spacewhales.EbucketList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.api.UsersApiController;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersApiController.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class EbucketListApplicationTests
{
    @LocalServerPort
    private int port;

	private TestRestTemplate restTemplate;

	private HttpHeaders headers;

	private ObjectMapper om;

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	@Before
	public void setup()
	{
		restTemplate = new TestRestTemplate();
		
		headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		om = new ObjectMapper();
	}
	
	@Test
	public void contextLoads() {}
	
	
	@Test
	public void hitUserService() throws Exception
	{
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users"),
				HttpMethod.GET, entity, String.class);

		assert(response.getStatusCode().equals(HttpStatus.NOT_IMPLEMENTED));
	}
	
	/*
	Removed for now due to serialization issues.
	
	@Test
	public void hitEndpointTokenValidate() throws Exception 
	{
		LoginToken t = new LoginToken();
		t.setUsername("testUsername");
		t.sessionToken("randomToken");
		t.setExpiryTime(OffsetDateTime.now());
		
		HttpEntity<String> entity = new HttpEntity<String>(om.writeValueAsString(t), headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users/token/validate"),
				HttpMethod.GET, entity, String.class);

		assert(response.getStatusCode().equals(HttpStatus.NOT_IMPLEMENTED));
	}
	*/
	
}
