package com.spacewhales.EbucketList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public interface APIHandler {

	/**
	 * Get the price for an item. 
	 */
	public abstract Double getPrice(String productID);

	/**
	 * Get the product details.
	 */
	public abstract String getProduct(String productID);
	
}
