package com.EbucketList.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import io.swagger.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

@Configuration
@Component
public class ProductJdbcDatabase {

	@Value("${DB_URL}")
	String dbUrl;

	private DriverManagerDataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public ProductJdbcDatabase() {

	}

	@PostConstruct
	public void init(){
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		// replace with local database
		System.out.println(dbUrl);
		dataSource.setUrl(dbUrl);
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
	 * add product to wishlist
	 * 
	 * @param product
	 */
	public void trackProduct(ProductRequest product) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("trackProduct");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("token", product.getLoginToken());
		in.addValue("site", product.getUrl());
		Map<String, Object> out = jdbcCall.execute(in);
	}

	/**
	 * delete product to wishlist
	 * 
	 * @param productId
	 */
	public void untrackProduct(Long productId, ProductRequest product) {

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("untrackProduct");
		//MapSqlParameterSource in = new MapSqlParameterSource().addValue("token", product.getLoginToken());  //does this validatte the token? If so I already check if token is valid
		//in.addValue("site", product.getUrl());
		//Map<String, Object> out = jdbcCall.execute(in);
	}

	/**
	 * update produce price
	 * 
	 * @param product
	 */
	public void updatePrice(ProductItem product) {
		// check log in id
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("updateProduct");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("trigger_price", product.getCurrentPrice());
		in.addValue("site", product.getUrl());
		Map<String, Object> out = jdbcCall.execute(in);
	}

	/**
	 * getProduct info
	 * 
	 * @param product
	 */
	public Map<String, Object> getProduct(ProductRequest product) {
		// check log in id
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("getProduct");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("token", product.getLoginToken());
		in.addValue("site", product.getUrl());
		Map<String, Object> out = jdbcCall.execute(in);
		return out;

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
