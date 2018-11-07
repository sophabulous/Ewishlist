package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.tomcat.jni.Time;

public class TrackProductResponse {

    @JsonProperty("productId")
    private String productId;

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("currentPrice")
    private double currentPrice;

    @JsonProperty("trackedPrice")
    private double originalPrice;

    @JsonProperty("trackedTime")
    private Time trackedTime;

    @JsonProperty("vendor")
    private String vendor;

    @JsonProperty("url")
    private String url;



    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Time getTrackedTime() {
        return trackedTime;
    }

    public void setTrackedTime(Time trackedTime) {
        this.trackedTime = trackedTime;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
