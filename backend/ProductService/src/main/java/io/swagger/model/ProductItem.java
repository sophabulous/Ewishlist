package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ProductItem
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-30T16:11:09.391Z")

public class ProductItem   {
  @JsonProperty("productId")
  private String productId = null;

  @JsonProperty("productName")
  private String productName = null;

  @JsonProperty("currentPrice")
  private Double currentPrice = null;

  @JsonProperty("trackedPrice")
  private Double trackedPrice = null;

  @JsonProperty("trackedTime")
  private OffsetDateTime trackedTime = null;

  @JsonProperty("vendor")
  private String vendor = null;

  @JsonProperty("url")
  private String url = null;


  /**
   * Get productId
   * @return productId
  **/
  @ApiModelProperty(value = "")


  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public ProductItem productName(String productName) {
    this.productName = productName;
    return this;
  }

  /**
   * Get productName
   * @return productName
  **/
  @ApiModelProperty(value = "")


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public ProductItem currentPrice(Double currentPrice) {
    this.currentPrice = currentPrice;
    return this;
  }

  /**
   * Get currentPrice
   * @return currentPrice
  **/
  @ApiModelProperty(value = "")


  public Double getCurrentPrice() {
    return currentPrice;
  }

  public void setCurrentPrice(Double currentPrice) {
    this.currentPrice = currentPrice;
  }

  public ProductItem trackedPrice(Double trackedPrice) {
    this.trackedPrice = trackedPrice;
    return this;
  }

  /**
   * Get trackedPrice
   * @return trackedPrice
  **/
  @ApiModelProperty(value = "")


  public Double getTrackedPrice() {
    return trackedPrice;
  }

  public void setTrackedPrice(Double trackedPrice) {
    this.trackedPrice = trackedPrice;
  }

  public ProductItem trackedTime(OffsetDateTime trackedTime) {
    this.trackedTime = trackedTime;
    return this;
  }

  /**
   * Get trackedTime
   * @return trackedTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getTrackedTime() {
    return trackedTime;
  }

  public void setTrackedTime(OffsetDateTime trackedTime) {
    this.trackedTime = trackedTime;
  }

  public ProductItem vendor(String vendor) {
    this.vendor = vendor;
    return this;
  }

  /**
   * Get vendor
   * @return vendor
  **/
  @ApiModelProperty(value = "")


  public String getVendor() {
    return vendor;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  public ProductItem url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Get url
   * @return url
  **/
  @ApiModelProperty(value = "")


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductItem productItem = (ProductItem) o;
    return Objects.equals(this.productId, productItem.productId) &&
        Objects.equals(this.productName, productItem.productName) &&
        Objects.equals(this.currentPrice, productItem.currentPrice) &&
        Objects.equals(this.trackedPrice, productItem.trackedPrice) &&
        Objects.equals(this.trackedTime, productItem.trackedTime) &&
        Objects.equals(this.vendor, productItem.vendor) &&
        Objects.equals(this.url, productItem.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, productName, currentPrice, trackedPrice, trackedTime, vendor, url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductItem {\n");
    
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    productName: ").append(toIndentedString(productName)).append("\n");
    sb.append("    currentPrice: ").append(toIndentedString(currentPrice)).append("\n");
    sb.append("    trackedPrice: ").append(toIndentedString(trackedPrice)).append("\n");
    sb.append("    trackedTime: ").append(toIndentedString(trackedTime)).append("\n");
    sb.append("    vendor: ").append(toIndentedString(vendor)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

