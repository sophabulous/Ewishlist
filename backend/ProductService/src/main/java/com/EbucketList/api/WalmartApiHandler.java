package com.EbucketList.api;

import com.EbucketList.api.models.WalmartResponse;
import com.EbucketList.api.dao.WalmartDao;
import io.swagger.model.Product;

import java.net.MalformedURLException;

public class WalmartApiHandler implements APIHandler {

    WalmartDao walmartDao = new WalmartDao();
    WalmartResponse walmartResponse = null;

    /**
     * Get the price for an walmartResponse.
     */
    public Double getPrice(String url) throws MalformedURLException {
        if(walmartResponse == null){
            walmartResponse = walmartDao.getItem(url);
        }
        return walmartResponse.getSalePrice();
    }

    /**
     * Get the product details.
     */
    public Product getProduct(String url) throws MalformedURLException{
        if(walmartResponse == null){
            walmartResponse = walmartDao.getItem(url);
        }
        Product product = new Product();
        product.setItemId(walmartResponse.getItemId());
        product.setName(walmartResponse.getName());
        product.setLongDescription(walmartResponse.getLongDescription());
        product.setSalePrice(walmartResponse.getSalePrice());
        product.setUpc(walmartResponse.getUpc());
        product.setThumbnailImage(walmartResponse.getThumbnailImage());

        return product;
    }

}
