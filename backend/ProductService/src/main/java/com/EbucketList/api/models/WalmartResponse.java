package com.EbucketList.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WalmartResponse {

        @JsonProperty("itemId")
        String itemId;

        @JsonProperty("parentItemId")
        String parentItemId;

        @JsonProperty("name")
        String name;

        @JsonProperty("salePrice")
        double salePrice;

        @JsonProperty("upc")
        String upc;

        @JsonProperty("longDescription")
        String longDescription;

        @JsonProperty("brandName")
        String brandName;

        @JsonProperty("thumbnailImage")
        String thumbnailImage;

        @JsonProperty("productTrackingUrl")
        String productTrackingUrl;


        public String getItemId() {
                return itemId;
        }

        public void setItemId(String itemId) {
                this.itemId = itemId;
        }

        public String getParentItemId() {
                return parentItemId;
        }

        public void setParentItemId(String parentItemId) {
                this.parentItemId = parentItemId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public double getSalePrice() {
                return salePrice;
        }

        public void setSalePrice(double salePrice) {
                this.salePrice = salePrice;
        }

        public String getUpc() {
                return upc;
        }

        public void setUpc(String upc) {
                this.upc = upc;
        }

        public String getLongDescription() {
                return longDescription;
        }

        public void setLongDescription(String longDescription) {
                this.longDescription = longDescription;
        }

        public String getBrandName() {
                return brandName;
        }

        public void setBrandName(String brandName) {
                this.brandName = brandName;
        }

        public String getThumbnailImage() {
                return thumbnailImage;
        }

        public void setThumbnailImage(String thumbnailImage) {
                this.thumbnailImage = thumbnailImage;
        }

        public String getProductTrackingUrl() {
                return productTrackingUrl;
        }

        public void setProductTrackingUrl(String productTrackingUrl) {
                this.productTrackingUrl = productTrackingUrl;
        }

        @Override
        public String toString(){
                return "itemid: " + itemId +"\n" +
                        "parentItemId: " + parentItemId + "\n" +
                        "name: " + name + "\n" +
                        "salePrice: " +  salePrice + "\n" +
                        "upc: " +  upc.toString() + "\n" +
                        "longDescription: " + longDescription + "\n" +
                        "brandName: " + brandName + "\n" +
                        "thumbnailImage: " + thumbnailImage + "\n";
        }
}

