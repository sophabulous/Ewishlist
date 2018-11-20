package com.spacewhales.EbucketList;

import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
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
import io.swagger.model.LoginRequest;
import io.swagger.model.LoginToken;
import io.swagger.model.NewUserRequest;
import io.swagger.model.UpdateUserRequest;

@Ignore
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

	private String createURLWithPort(String uri)
	{
		return "http://localhost:" + port + uri;
	}
	
	private LoginToken createMockLoginToken()
	{
		LoginToken t = new LoginToken();
		t.setUsername("testUsername");
		t.sessionToken("TokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenTokenToken");
		t.setExpiryTime(new Date());
		return t;
	}
	
	@Before
	public void setup()
	{
		restTemplate = new TestRestTemplate();
		
		headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		om = new ObjectMapper();
		om.findAndRegisterModules();
	}
	
	@Test
	public void contextLoads() {}
	
	
	@Test
	public void hitUserService() throws Exception
	{
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users/ping"),
				HttpMethod.GET, entity, String.class);

		assert(response.getStatusCode().equals(HttpStatus.NOT_IMPLEMENTED));
	}

	@Test
	public void hitEndpointCreateUser() throws Exception 
	{
		NewUserRequest r = new NewUserRequest();
		r.setUsername("testUsr");
		r.setPassword("testPwd");
		r.setEmail("test@mail.com");
		
		HttpEntity<String> entity = new HttpEntity<String>(om.writeValueAsString(r), headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users/manage"),
	 			HttpMethod.PUT, entity, String.class);
		assert(response.getStatusCode().equals(HttpStatus.OK) || response.getStatusCode().equals(HttpStatus.UNAUTHORIZED));
	}

	@Test
	public void hitEndpointUpdateUser() throws Exception 
	{
		UpdateUserRequest r = new UpdateUserRequest();
		r.setLoginToken(createMockLoginToken());
		
		HttpEntity<String> entity = new HttpEntity<String>(om.writeValueAsString(r), headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users/manage"),
				HttpMethod.POST, entity, String.class);

		assert(response.getStatusCode().equals(HttpStatus.NOT_IMPLEMENTED));
	}
	
	@Test
	public void hitEndpointDeleteUser() throws Exception 
	{
		UpdateUserRequest r = new UpdateUserRequest();
		r.setLoginToken(createMockLoginToken());
		
		HttpEntity<String> entity = new HttpEntity<String>(om.writeValueAsString(r), headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users/manage"),
				HttpMethod.DELETE, entity, String.class);

		assert(response.getStatusCode().equals(HttpStatus.NOT_IMPLEMENTED));
	}
	
	@Test
	public void hitEndpointLogin() throws Exception
	{
		LoginRequest r = new LoginRequest();
		r.setUsername("testUsr");
		r.setPassword("testPwd");
		
		HttpEntity<String> entity = new HttpEntity<String>(om.writeValueAsString(r), headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users/token/login"),
				HttpMethod.POST, entity, String.class);

		assert(response.getStatusCode().equals(HttpStatus.OK) || response.getStatusCode().equals(HttpStatus.METHOD_NOT_ALLOWED));
	}
	
	@Test
	public void hitEndpointTokenInvalidate() throws Exception 
	{
		LoginToken t = createMockLoginToken();
		
		HttpEntity<String> entity = new HttpEntity<String>(om.writeValueAsString(t), headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users/token/invalidateToken"),
				HttpMethod.POST, entity, String.class);

		assert(response.getStatusCode().equals(HttpStatus.OK) || response.getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)
				|| response.getStatusCode().equals(HttpStatus.METHOD_NOT_ALLOWED));
	}
	
	@Test
	public void hitEndpointTokenValidate() throws Exception 
	{
		LoginToken t = createMockLoginToken();
		
		HttpEntity<String> entity = new HttpEntity<String>(om.writeValueAsString(t), headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users/token/validateToken"),
				HttpMethod.POST, entity, String.class);

		assert(response.getStatusCode().equals(HttpStatus.OK) || response.getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)
				|| response.getStatusCode().equals(HttpStatus.METHOD_NOT_ALLOWED));
	}
	/*
	@Test
	public void connectToDb() throws Exception
	{
		UserJdbcDatabase db = new UserJdbcDatabase();
	}
	*/
}
