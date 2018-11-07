package io.swagger.api;

import com.spacewhales.EbucketList.APIHandler;

public class WalmartApiHandler implements APIHandler {
    /**
     * Get the price for an item.
     */
    public Double getPrice(String productID){
        return (Double)(25.00);

    }

    /**
     * Get the product details.
     */
    public String getProduct(String productID){
        return "new watch";

    }

}
