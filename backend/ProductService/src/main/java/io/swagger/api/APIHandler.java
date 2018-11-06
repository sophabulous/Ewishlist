package com.spacewhales.EbucketList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public abstract class APIHandler {
	
	/**
	 * Creates new API handler. 
	 */
	public APIHandler() {
	}
	
	/**
	 * Get the price for an item. 
	 */
	public abstract String getPrice(String productID);
	
	/**
	 * Get the product details.
	 */
	public abstract String getProduct(String productID);
	
}
