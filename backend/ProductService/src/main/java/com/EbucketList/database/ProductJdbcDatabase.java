package com.EbucketList.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import io.swagger.model.*;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.Map;

public class ProductJdbcDatabase {

//    @Autowired
	private DriverManagerDataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public ProductJdbcDatabase() {
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
	 * add product to wishlist
	 * 
	 * @param product
	 */
	public void trackProduct(NewProductRequest product) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("trackProduct");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("token", product.getLoginToken());
		in.addValue("site", product.getUrl());
		Map<String, Object> out = jdbcCall.execute(in);
	}

	/**
	 * delete product to wishlist
	 * 
	 * @param product
	 */
	public void untrackProduct(NewProductRequest product) {

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("untrackProduct");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("token", product.getLoginToken());
		in.addValue("site", product.getUrl());
		Map<String, Object> out = jdbcCall.execute(in);
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
	public Map<String, Object> getProduct(NewProductRequest product) {
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
