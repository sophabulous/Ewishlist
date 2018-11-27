package io.swagger.api;

import io.swagger.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;

public interface APIHandler {

	/**
	 * Get the price for an walmartResponse.
	 */
	public abstract Double getPrice(String url) throws MalformedURLException;

	/**
	 * Get the product details.
	 */
	public abstract Product getProduct(String url) throws MalformedURLException;
	
}
