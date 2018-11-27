package io.swagger.api;

import io.swagger.database.api.JdbcDatabase;
import io.swagger.model.ProductRequest;
import io.swagger.model.LoginToken;
import io.swagger.model.Product;
import io.swagger.model.ProductItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-01T17:36:03.863Z")


@Controller
public class TrackingApiController implements TrackingApi {

    @Value( "${users.service.url}" )
    private String userServiceUrl;

    private static final Logger log = LoggerFactory.getLogger(TrackingApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private JdbcDatabase db ;

    @Autowired
    APIHandler walmartApiHandler;

    @Autowired
    public TrackingApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
     /*uses the user service to authenticate user token*/
    private boolean validateToken(LoginToken loginToken){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(userServiceUrl, loginToken, String.class);
        int status = response.getStatusCode().value();
        if(status == 200){
            return true;
        }
        else{
            return false;
        }
    }


    public ResponseEntity<ProductItem> addTrackedProduct(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ProductRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                if(!validateToken(body.getLoginToken())){
                    ProductItem productItem = null;
                    return new ResponseEntity<ProductItem>(HttpStatus.FORBIDDEN);
                }
                else{
                    double price = walmartApiHandler.getPrice(body.getUrl());
                    Product product = walmartApiHandler.getProduct(body.getUrl());
                    ProductItem  productItem = new ProductItem();
                    productItem.setCurrentPrice(product.getSalePrice());
                    productItem.setProductId(new Long(product.getItemId()).toString());
                    productItem.setProductName(product.getName());
                    productItem.setUrl(body.getUrl());
                    productItem.setVendor("Walmart");
                    db.insertProduct(productItem);
                    ProductRequest productRequest= new ProductRequest();
                    db.trackProduct(body);
                    return new ResponseEntity<ProductItem>(productItem, HttpStatus.OK);
                }
            }
            catch (MalformedURLException me) {
                log.error("url is not formatted properly");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<ProductItem>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ProductItem> deleteTrackedProduct(@ApiParam(value = "",required=true) @PathVariable("productId") String productId, @Valid @RequestBody ProductRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            if(!validateToken(body.getLoginToken())){
                ProductItem productItem = null;
                return new ResponseEntity<ProductItem>(HttpStatus.FORBIDDEN);
            }
            else{
                db.untrackProduct(body);
                return new ResponseEntity<ProductItem>(HttpStatus.OK);
            }
        }

        return new ResponseEntity<ProductItem>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ProductItem> getTrackedProductInfo(@ApiParam(value = "",required=true) @PathVariable("productId") String  productId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody ProductRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")){
                if(! validateToken(body.getLoginToken())){
                    return new ResponseEntity<ProductItem>(HttpStatus.FORBIDDEN);
                }
                else {
                    ProductItem item = db.getProduct(body);
                    return new ResponseEntity<ProductItem>(item, HttpStatus.OK);
                }
        }
        return new ResponseEntity<ProductItem>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<ProductItem>> getTrackedProducts(@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginToken body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            if(! validateToken(body)){
                return new ResponseEntity<List<ProductItem>>(HttpStatus.FORBIDDEN);
            }
            else{
                List<String> productList = db.getWishlist(body);
                List<ProductItem> products = new ArrayList<ProductItem>();
                for(String url : productList){
                    ProductItem item = new ProductItem();
                    item.setUrl(url);
                    products.add(item);
                }
                return new ResponseEntity<List<ProductItem>>(products, HttpStatus.OK);
            }
        }

        return new ResponseEntity<List<ProductItem>>(HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<Void> pingTracking() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //assume can only update price
    public ResponseEntity<ProductItem> updateProductItem(@ApiParam(value = "",required=true) @PathVariable("productId") String productId, @RequestParam("price") Double price, @ApiParam(value = "" ,required=true )  @Valid @RequestBody ProductRequest productRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            if(! validateToken(productRequest.getLoginToken())){
                return new ResponseEntity<ProductItem>(HttpStatus.FORBIDDEN);
            }
            else{
                ProductItem prodItem = db.getProduct(productRequest);
                prodItem.setCurrentPrice(price);
                db.updatePrice(prodItem);
                return new ResponseEntity<ProductItem>(prodItem, HttpStatus.OK);
            }
        }
        return new ResponseEntity<ProductItem>(HttpStatus.BAD_REQUEST);
    }

}
