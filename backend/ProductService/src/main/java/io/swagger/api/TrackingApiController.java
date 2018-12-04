package io.swagger.api;

import io.swagger.database.api.JdbcDatabase;
import io.swagger.model.ProductRequest;
import io.swagger.model.LoginToken;
import io.swagger.model.Product;
import io.swagger.model.ProductItem;
import com.fasterxml.jackson.databind.ObjectMapper;

import batch.BatchProductUpdate;
import external.api.UserServiceAPI;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-01T17:36:03.863Z")
@CrossOrigin(origins = "http://localhost:8080")
@Controller
public class TrackingApiController implements TrackingApi {
	private static final Logger log = LoggerFactory.getLogger(TrackingApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	private UserServiceAPI userServiceApi;
	
	@Autowired
	private JdbcDatabase db ;

	@Autowired
	APIHandler walmartApiHandler;

	@Autowired
	BatchProductUpdate batch;
	
	@Autowired
	public TrackingApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<ProductItem> addTrackedProduct(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ProductRequest body) {
		log.info("adding product: " + body);
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				if(!userServiceApi.validateToken(body.getLoginToken())){
					log.info("add product: not authenticated");
					ProductItem productItem = null;
					return new ResponseEntity<ProductItem>(HttpStatus.FORBIDDEN);
				} else {
					log.info("add product: authenticated");
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
		log.info("deleting product: " + body);
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			if (!userServiceApi.validateToken(body.getLoginToken())) {
				log.info("delete product: not authenticated");
				ProductItem productItem = null;
				return new ResponseEntity<ProductItem>(HttpStatus.FORBIDDEN);
			} else {
				log.info("delete product: authenticated");
				db.untrackProduct(body);
				return new ResponseEntity<ProductItem>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<ProductItem>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Void> executeBatch(@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginToken body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			if(!userServiceApi.validateAdmin(body)) {
				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
			} else {
				batch.updateAllProducts(db, walmartApiHandler);
				batch.sendEmailsForUpdatedProducts(db);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<ProductItem> getTrackedProductInfo(@ApiParam(value = "",required=true) @PathVariable("productId") String  productId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody ProductRequest body) {
		log.info("get tracked product info: " + body);
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			if (!userServiceApi.validateToken(body.getLoginToken())) {
				log.info("get tracked product info: not authenticated");
				return new ResponseEntity<ProductItem>(HttpStatus.FORBIDDEN);
			} else {
				log.info("get tracked product info: authenticated");
				ProductItem item;
				try {
					item = db.getProduct(body);
				} catch (IOException e) {
					log.info("Product not in database: " + body.getUrl());
					return new ResponseEntity<ProductItem>(HttpStatus.METHOD_NOT_ALLOWED);

				}
				return new ResponseEntity<ProductItem>(item, HttpStatus.OK);
			}
		}
		return new ResponseEntity<ProductItem>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<ProductItem>> getTrackedProducts(@ApiParam(value = "", required = true) @Valid @RequestBody LoginToken body) {
		log.info("getting all tracked product: " + body);
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			if (!userServiceApi.validateToken(body)) {
				log.info("getting all tracked products: not authenticated");
				return new ResponseEntity<List<ProductItem>>(HttpStatus.FORBIDDEN);
			} else {
				log.info("getting all tracked product: authenticated");
				List<String> productList = db.getWishlist(body);
				List<ProductItem> products = new ArrayList<ProductItem>();
				for (String url : productList) {
					ProductRequest productRequest = new ProductRequest();
					productRequest.setUrl(url);
					productRequest.setLoginToken(body);
					ProductItem prodItem;
					try {
						prodItem = db.getProduct(productRequest);
						products.add(prodItem);
					} catch (IOException e) {
						log.info("Product not in database: " + productRequest.getUrl());
						return new ResponseEntity<List<ProductItem>>(HttpStatus.METHOD_NOT_ALLOWED);

					}
				}
				return new ResponseEntity<List<ProductItem>>(products, HttpStatus.OK);
			}
		}

		return new ResponseEntity<List<ProductItem>>(HttpStatus.BAD_REQUEST);
	}


	public ResponseEntity<Void> pingTracking() {
		log.info("pinged");
		String accept = request.getHeader("Accept");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	//assume can only update price
	public ResponseEntity<ProductItem> updateProductItem(@ApiParam(value = "",required=true) @PathVariable("productId") String productId, @RequestParam("price") Double price, @ApiParam(value = "" ,required=true )  @Valid @RequestBody ProductRequest productRequest) {
		log.info("update product item: " + productRequest);
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			if (!userServiceApi.validateToken(productRequest.getLoginToken())) {
				log.info("update product: not authenticated");
				return new ResponseEntity<ProductItem>(HttpStatus.FORBIDDEN);
			} else {
				log.info("update product: authenticated");
				ProductItem prodItem;
				try {
					prodItem = db.getProduct(productRequest);
				} catch (IOException e) {
					log.info("Product not in database: " + productRequest.getUrl());
					return new ResponseEntity<ProductItem>(HttpStatus.METHOD_NOT_ALLOWED);

				}
				prodItem.setTrackedPrice(prodItem.getCurrentPrice());
				prodItem.setCurrentPrice(price);
				db.updatePrice(prodItem);
				return new ResponseEntity<ProductItem>(prodItem, HttpStatus.OK);
			}
		}
		return new ResponseEntity<ProductItem>(HttpStatus.BAD_REQUEST);
	}

}

