package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

        @JsonProperty("itemId")
        long itemId;

        @JsonProperty("parentItemId")
        long parentItemId;

        @JsonProperty("name")
        String name;

        @JsonProperty("salePrice")
        double salePrice;

        @JsonProperty("upc")
        long upc;

        @JsonProperty("longDescription")
        String longDescription;

        @JsonProperty("brandName")
        String brandName;

        @JsonProperty("thumbnailImage")
        String thumbnailImage;

        @JsonProperty("productTrackingUrl")
        String productTrackingUrl;


        public long getItemId() {
                return itemId;
        }

        public void setItemId(long itemId) {
                this.itemId = itemId;
        }

        public long getParentItemId() {
                return parentItemId;
        }

        public void setParentItemId(long parentItemId) {
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

        public long getUpc() {
                return upc;
        }

        public void setUpc(long upc) {
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
}

