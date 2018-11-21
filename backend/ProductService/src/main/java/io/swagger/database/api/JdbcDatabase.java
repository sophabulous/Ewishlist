package io.swagger.database.api;

import io.swagger.model.LoginToken;
import io.swagger.model.ProductItem;
import io.swagger.model.ProductRequest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public interface JdbcDatabase {

    public void insertProduct(ProductItem product);
    /**
     * add product to wishlist
     *
     * @param productReq
     */
    public void trackProduct(ProductRequest productReq);

    /**
     * delete product to wishlist
     *
     * @param product
     */
    public void untrackProduct(ProductRequest product);

    /**
     * update product price
     *
     * @param product
     */
    public void updatePrice(ProductItem product);
    /**
     * getProduct info
     *
     * @param product
     */
    public ProductItem getProduct(ProductRequest product);
    /**
     * get Wishlist
     *
     * @param token
     */
    public List<String> getWishlist(LoginToken token) ;

    /**
     * @return JdbcTemplate
     */
    public JdbcTemplate getJdbcTemplate();

    /**
     * @return DataSource
     */
    public DataSource getDataSource();

}
