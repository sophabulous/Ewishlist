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
	 * add product to product table if it doesn't exist
	 * 
	 * @param product
	 */
	public void insertProduct(ProductItem product) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("insertProduct");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("site", product.getUrl());
		in.addValue("item_name", product.getProductName());
		in.addValue("price", product.getCurrentPrice());
		Map<String, Object> out = jdbcCall.execute(in);
	}
	/**
	 * add product to wishlist
	 * 
	 * @param product
	 */
	public void trackProduct(ProductRequest productReq) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("trackProduct");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("token", productReq.getLoginToken().getSessionToken());
		in.addValue("site", productReq.getUrl());
		Map<String, Object> out = jdbcCall.execute(in);
	}

	/**
	 * delete product to wishlist
	 * 
	 * @param productId
	 */
	public void untrackProduct(Long productId, ProductRequest product) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("untrackProduct");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("token", product.getLoginToken().getSessionToken());  //does this validatte the token? If so I already check if token is valid
		in.addValue("site", product.getUrl());
		Map<String, Object> out = jdbcCall.execute(in);
	}

	/**
	 * update product price
	 * 
	 * @param product
	 */
	public void updatePrice(ProductItem product) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("updateProduct");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("price", product.getCurrentPrice());
		in.addValue("site", product.getUrl());
		Map<String, Object> out = jdbcCall.execute(in);
	}

	/**
	 * getProduct info
	 * 
	 * @param product
	 */
	public Map<String, Object> getProduct(ProductRequest product) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("getProduct");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("token", product.getLoginToken());
		in.addValue("site", product.getUrl());
		Map<String, Object> out = jdbcCall.execute(in);		
		return out;

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
