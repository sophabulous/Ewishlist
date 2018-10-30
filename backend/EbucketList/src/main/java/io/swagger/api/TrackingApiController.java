package io.swagger.api;

import io.swagger.model.LoginToken;
import io.swagger.model.NewProductRequest;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-30T16:11:09.391Z")

@Controller
public class TrackingApiController implements TrackingApi {

    private static final Logger log = LoggerFactory.getLogger(TrackingApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TrackingApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ProductItem> addTrackedProduct(@ApiParam(value = "" ,required=true )  @Valid @RequestBody NewProductRequest body) {
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

    public ResponseEntity<ProductItem> deleteTrackedProduct(@ApiParam(value = "",required=true) @PathVariable("productId") Integer productId) {
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

    public ResponseEntity<ProductItem> getTrackedProductInfo(@ApiParam(value = "",required=true) @PathVariable("productId") Integer productId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginToken body) {
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

    public ResponseEntity<List<ProductItem>> getTrackedProducts(@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginToken body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<ProductItem>>(objectMapper.readValue("[ {  \"trackedTime\" : \"2000-01-23T04:56:07.000+00:00\",  \"productId\" : { },  \"vendor\" : \"vendor\",  \"currentPrice\" : 0.8008282,  \"trackedPrice\" : 6.0274563,  \"productName\" : \"productName\",  \"url\" : \"http://example.com/aeiou\"}, {  \"trackedTime\" : \"2000-01-23T04:56:07.000+00:00\",  \"productId\" : { },  \"vendor\" : \"vendor\",  \"currentPrice\" : 0.8008282,  \"trackedPrice\" : 6.0274563,  \"productName\" : \"productName\",  \"url\" : \"http://example.com/aeiou\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<ProductItem>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<ProductItem>>(HttpStatus.NOT_IMPLEMENTED);
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
