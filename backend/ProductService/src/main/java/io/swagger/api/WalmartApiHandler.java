package io.swagger.api;

import io.swagger.api.APIHandler;
import io.swagger.dao.WalmartDao;
import io.swagger.model.Item;
import io.swagger.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;

public class WalmartApiHandler implements APIHandler {

    WalmartDao walmartDao = new WalmartDao();
    Item item = null;

    /**
     * Get the price for an item.
     */
    public Double getPrice(String url) throws MalformedURLException {
        if(item == null){
            item = walmartDao.getItem(url);
        }
        return item.getSalePrice();
    }

    /**
     * Get the product details.
     */
    public Product getProduct(String url) throws MalformedURLException{
        if(item == null){
            item = walmartDao.getItem(url);
        }
        Product product = new Product();
        product.setItemId(item.getItemId());
        product.setName(item.getName());
        product.setLongDescription(item.getLongDescription());
        product.setSalePrice(item.getSalePrice());
        product.setUpc(item.getUpc());
        product.setThumbnailImage(item.getThumbnailImage());

        return product;
    }

}
