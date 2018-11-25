package io.swagger.api;

import com.EbucketList.api.models.WalmartResponse;
import io.swagger.dao.dao.EcommerceDao;
import io.swagger.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

@Component
@Configurable
@Service
public class WalmartApiHandler implements APIHandler {

    @Autowired
    EcommerceDao walmartDao;

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
