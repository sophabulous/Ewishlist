/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.LoginToken;
import io.swagger.model.NewProductRequest;
import io.swagger.model.ProductItem;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-30T16:11:09.391Z")

@Api(value = "tracking", description = "the tracking API")
public interface TrackingApi {

    @ApiOperation(value = "Add a product to the user's wishlist", nickname = "addTrackedProduct", notes = "", response = ProductItem.class, tags={ "Tracking", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful", response = ProductItem.class),
        @ApiResponse(code = 401, message = "Invalid/expired login token"),
        @ApiResponse(code = 405, message = "Product could not be tracked") })
    @RequestMapping(value = "/tracking",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<ProductItem> addTrackedProduct(@ApiParam(value = "" ,required=true )  @Valid @RequestBody NewProductRequest body);


    @ApiOperation(value = "Delete a product from the user's wishlist", nickname = "deleteTrackedProduct", notes = "", response = ProductItem.class, tags={ "Tracking", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful", response = ProductItem.class),
        @ApiResponse(code = 401, message = "Invalid/expired login token"),
        @ApiResponse(code = 405, message = "Product could not be tracked") })
    @RequestMapping(value = "/tracking/{productId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<ProductItem> deleteTrackedProduct(@ApiParam(value = "",required=true) @PathVariable("productId") Integer productId);


    @ApiOperation(value = "Information about an item being tracked by the user defined by the login token", nickname = "getTrackedProductInfo", notes = "", response = ProductItem.class, tags={ "Tracking", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful", response = ProductItem.class),
        @ApiResponse(code = 401, message = "Invalid/expired login token"),
        @ApiResponse(code = 405, message = "Product does not exist") })
    @RequestMapping(value = "/tracking/{productId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<ProductItem> getTrackedProductInfo(@ApiParam(value = "",required=true) @PathVariable("productId") Integer productId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginToken body);


    @ApiOperation(value = "Get all product information for a user defined by their token", nickname = "getTrackedProducts", notes = "", response = ProductItem.class, responseContainer = "List", tags={ "Tracking", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful", response = ProductItem.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Invalid/expired login token"),
        @ApiResponse(code = 405, message = "Product does not exist") })
    @RequestMapping(value = "/tracking",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<ProductItem>> getTrackedProducts(@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginToken body);


    @ApiOperation(value = "Update a tracked product", nickname = "updateProductItem", notes = "", response = ProductItem.class, tags={ "Tracking", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful", response = ProductItem.class),
        @ApiResponse(code = 401, message = "Invalid/expired login token"),
        @ApiResponse(code = 405, message = "Could not update product item") })
    @RequestMapping(value = "/tracking/{productId}",
        method = RequestMethod.POST)
    ResponseEntity<ProductItem> updateProductItem(@ApiParam(value = "",required=true) @PathVariable("productId") Integer productId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody ProductItem body);

}
