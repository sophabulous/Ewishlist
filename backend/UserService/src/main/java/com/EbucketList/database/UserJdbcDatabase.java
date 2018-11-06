package com.EbucketList.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import io.swagger.model.*;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.Map;

public class UserJdbcDatabase {
	private JdbcTemplate jdbcTemplate;
	private DriverManagerDataSource dataSource;

	public UserJdbcDatabase() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		// replace with local database
		dataSource.setUrl("localhost");
		dataSource.setUsername("username");
		dataSource.setPassword("password");
		jdbcTemplate = new JdbcTemplate(dataSource);
		// check connection
		try {
			dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * store new user info in database
	 * 
	 * @param user
	 */
	public void saveUser(NewUserRequest user) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("storeUser");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("user_name", user.getUsername());
		in.addValue("password", user.getPassword());
		in.addValue("email", user.getEmail());
		Map<String, Object> out = jdbcCall.execute(in);
	}

	/**
	 * check if user is in database
	 * 
	 * @param user
	 */
	public String findUser(LoginRequest user) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("findUser");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("user_name", user.getUsername());
		in.addValue("password", user.getPassword());
		in.addValue("email", user.getPassword());
		Map<String, Object> out = jdbcCall.execute(in);
		return (String) out.get("user_name");
	}

	/**
	 * store token in database
	 * 
	 * @param token
	 */
	public void storeToken(LoginToken token) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("storeToken");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("user_name", token.getUsername());
		in.addValue("token", token.getSessionToken());
		Map<String, Object> out = jdbcCall.execute(in);
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
