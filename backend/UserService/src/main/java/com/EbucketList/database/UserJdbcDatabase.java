package com.EbucketList.database;

import org.apache.tomcat.util.http.fileupload.FileItemStream.ItemSkippedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import io.swagger.model.*;

import javax.sql.DataSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

public class UserJdbcDatabase {
	private JdbcTemplate jdbcTemplate;
	private DriverManagerDataSource dataSource;
	@Value("#{new String('{DB_URL}')}")
	String dbUrl;

	public UserJdbcDatabase() throws IOException {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		// replace with local database
		// this worked for me, you need to specify the actual path on the localhost
		// dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
		dataSource.setUrl(dbUrl);
		dataSource.setUsername("username");
		dataSource.setPassword("password");
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		// check connection
		try {
			dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException();
		}
	}

	/**
	 * create new user in database
	 * 
	 * @param user
	 * @throws IOException
	 */
	public void createUser(NewUserRequest user) throws IOException {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("createUser");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("user_name", user.getUsername());
		in.addValue("password", user.getPassword());
		in.addValue("email", user.getEmail());
		Map<String, Object> out = jdbcCall.execute(in);
		
		if ((boolean)out.get("status") == false)
		{
			throw new IOException("invalid login arguments");
		}
	}

	/**
	 * logs a user in (creates session token)
	 * 
	 * @param user
	 * @throws IOException 
	 */
	public LoginToken loginUser(LoginRequest user) throws IOException {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("loginUser");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("user_name", user.getUsername());
		in.addValue("password", user.getPassword());
		//in.addValue("email", user.getPassword());
		Map<String, Object> out = jdbcCall.execute(in);

		if (out.get("session_token") == null || out.get("expiry") == null)
		{
			throw new IOException("invalid login information");
		}
		
		LoginToken t = new LoginToken();
		t.setUsername(user.getUsername());
		t.setExpiryTime((Date) out.get("expiry"));
		t.setSessionToken((String) out.get("session_token"));
		
		return t;
	}

	/**
	 * Validates whether a token is valid or not
	 * 
	 * @param token
	 * @throws IOException
	 */
	public boolean validateToken(LoginToken token) throws IOException {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("validateToken");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("user_name", token.getUsername());
		in.addValue("session_token", token.getSessionToken());
		
		Map<String, Object> out = jdbcCall.execute(in);

		if (!(boolean)out.get("status") && (Date)out.get("expiry") == null)
		{
			throw new IOException("counterfeit token");
		}
		else if ((Date)out.get("expiry") == null)
		{
			// expired token
			return false;
		}
		
		return true;
	}
	
	/**
	 * Manually invalidates a token if it is a legal token and still valid
	 *
	 * @param token
	 * @throws IOException
	 */
	public boolean invalidateToken(LoginToken token) throws IOException {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("invalidateToken");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("user_name", token.getUsername());
		in.addValue("session_token", token.getSessionToken());

		Map<String, Object> out = jdbcCall.execute(in);

		if (!(boolean)out.get("legaltoken"))
		{
			throw new IOException("counterfeit token");
		}

		return (boolean)out.get("status");
	}
	
	/**
	 * @return JdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @return DataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

}
