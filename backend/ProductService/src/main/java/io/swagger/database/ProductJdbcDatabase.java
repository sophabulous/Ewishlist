package io.swagger.database;

import io.swagger.database.api.JdbcDatabase;
import io.swagger.model.LoginToken;
import io.swagger.model.ProductItem;
import io.swagger.model.ProductRequest;
import io.swagger.model.UserEmailItem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Configuration
@Component
public class ProductJdbcDatabase implements JdbcDatabase {

	@Value("${DB_URL}")
	String dbUrl;

	@Value("${DB_USER:username}")
	String dbUsername;

	@Value("${DB_PWD:password}")
	String dbPassword;

	private DriverManagerDataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public ProductJdbcDatabase() {

	}

	@PostConstruct
	public void init(){
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		// replace with local database
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
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
	 * @param productReq
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
	public void untrackProduct(ProductRequest product) {
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
	 * @throws IOException 
	 */
	public ProductItem getProduct(ProductRequest product) throws IOException {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("getProduct");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("site", product.getUrl());
		Map<String, Object> out = jdbcCall.execute(in);

		boolean status = (boolean) out.get("status");
		if (!status) {
			throw new IOException("Product not in database");
		}
		ProductItem pi = new ProductItem();
		pi.setProductName((String) out.get("product_name"));
		pi.setUrl(product.getUrl());
		Float price = (Float) out.get("current_price");
		Float y_price = (Float) out.get("yesterday_price");
		pi.setTrackedPrice(y_price.doubleValue());
		pi.setCurrentPrice(price.doubleValue());
		
		return pi;

	}

	/**
	 * get Wishlist
	 *
	 * @param token
	 */
	public List<String> getWishlist(LoginToken token) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("getUserId");
		MapSqlParameterSource in = new MapSqlParameterSource().addValue("session_token", token.getSessionToken());
		Map<String, Object> out = jdbcCall.execute(in);
		Integer user_id = (Integer) out.get("user_id");
		List<String> products = this.jdbcTemplate.query(
				"SELECT site FROM public.wishlist WHERE user_id = "+user_id+";",
				new RowMapper<String>() {
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString("site");
					}
				});
		return products;

	}

	public Stream<ProductItem> getAllProducts()
	{
		SqlRowSet r = jdbcTemplate.queryForRowSet("SELECT * FROM getAllProducts();");
		ArrayList<ProductItem> l = new ArrayList<ProductItem>();
		
		while (r.next())
		{
			ProductItem i = new ProductItem();

			i.setCurrentPrice(r.getDouble("current_price"));
			i.setUrl(r.getString("site"));

			l.add(i);
		}
		
		return l.stream();
	}
	
	public Stream<UserEmailItem> getUsersToNotify()
	{
		 SqlRowSet r = jdbcTemplate.queryForRowSet("SELECT * FROM getUsersToNotify();");
		 ArrayList<UserEmailItem> l = new ArrayList<UserEmailItem>();
		 
		 while (r.next())
		 {
			 l.add(new UserEmailItem(r.getString("user_name"), r.getString("email")));
		 }
		 
		 return l.stream();
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
