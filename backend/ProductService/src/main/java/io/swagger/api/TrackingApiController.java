package io.swagger.api;

import com.EbucketList.database.ProductJdbcDatabase;
import io.swagger.model.AddProductRequest;
import io.swagger.model.LoginToken;
import io.swagger.model.Product;
import io.swagger.model.ProductItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-01T17:36:03.863Z")

@Controller
public class TrackingApiController implements TrackingApi {

    private static final Logger log = LoggerFactory.getLogger(TrackingApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final ProductJdbcDatabase db = new ProductJdbcDatabase();

    @org.springframework.beans.factory.annotation.Autowired
    public TrackingApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    public ResponseEntity<ProductItem> addTrackedProduct(@ApiParam(value = "" ,required=true )  @Valid @RequestBody AddProductRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                if(! db.validateToken(body.getLoginToken())){
                    ProductItem productItem = null;
                    return new ResponseEntity<ProductItem>(productItem, HttpStatus.FORBIDDEN);
                }
                else{
                    db.trackProduct(body); //update to use new model
                    //TODO: update this to incorporate adding the product to the product table as well
                    WalmartApiHandler walmartApiHandler = new WalmartApiHandler();
                    double price = walmartApiHandler.getPrice(body.getUrl());
                    Product product = walmartApiHandler.getProduct(body.getUrl());
                    ProductItem  productItem = new ProductItem();
                    productItem.setCurrentPrice(product.getSalePrice());
                    productItem.setProductId(product.getItemId());
                    productItem.setProductName(product.getName());
                    productItem.setUrl(body.getUrl());
                    productItem.setVendor("Walmart");
                    return new ResponseEntity<ProductItem>(productItem, HttpStatus.ACCEPTED);
                }


            }
            catch (MalformedURLException me){
                log.error("url is not formatted properly");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ProductItem>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ProductItem>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ProductItem> deleteTrackedProduct(@ApiParam(value = "",required=true) @PathVariable("productId") Long productId, @Valid @RequestBody LoginToken token) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                if(! db.validateToken(token)){
                    ProductItem productItem = null;
                    return new ResponseEntity<ProductItem>(productItem, HttpStatus.FORBIDDEN);
                }
                else{
                    ProductItem productItem  = null; //TODO: Update this to fetch the actual product and return it once the fetch from db is implmented
                    db.untrackProduct(productId);
                    return new ResponseEntity<ProductItem>(productItem, HttpStatus.ACCEPTED);
                }

            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ProductItem>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ProductItem>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ProductItem> getTrackedProductInfo(@ApiParam(value = "",required=true) @PathVariable("productId") Long productId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginToken body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                if(! db.validateToken(body)){
                    ProductItem productItem = null;
                    return new ResponseEntity<ProductItem>(productItem, HttpStatus.FORBIDDEN);
                }
                else{
                    ProductItem productItem  = null; //TODO: Update this to fetch the actual product and return it once the fetch from db is implmented
                    return new ResponseEntity<ProductItem>(productItem, HttpStatus.ACCEPTED);
                }

            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ProductItem>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ProductItem>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<ProductItem>> getTrackedProducts(@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginToken body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                if(! db.validateToken(body)){
                    ProductItem productItem = null;
                    ArrayList<ProductItem> productList = null;
                    return new ResponseEntity<List<ProductItem>>(productList, HttpStatus.FORBIDDEN);
                }
                else{
                    ProductItem productItem  = null; //TODO: Update this to fetch the actual product and return it once the fetch from db is implmented
                    ArrayList<ProductItem> productList = null;
                    return new ResponseEntity<List<ProductItem>>(productList, HttpStatus.FORBIDDEN);
                }

            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<ProductItem>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<ProductItem>>(HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<Void> pingTracking() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ProductItem> updateProductItem(@ApiParam(value = "",required=true) @PathVariable("productId") Integer productId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody ProductItem body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ProductItem>(objectMapper.readValue("{  \"trackedTime\" : \"2000-01-23T04:56:07.000+00:00\",  \"productId\" : { },  \"vendor\" : \"vendor\",  \"currentPrice\" : 0.8008282,  \"trackedPrice\" : 6.0274563,  \"productName\" : \"productName\",  \"url\" : \"http://example.com/aeiou\"}", ProductItem.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ProductItem>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ProductItem>(HttpStatus.NOT_IMPLEMENTED);
    }

}
