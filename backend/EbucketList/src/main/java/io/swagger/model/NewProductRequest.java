package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.LoginToken;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NewProductRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-30T16:11:09.391Z")

public class NewProductRequest   {
  @JsonProperty("url")
  private String url = null;

  @JsonProperty("loginToken")
  private LoginToken loginToken = null;

  public NewProductRequest url(String url) {
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

  public NewProductRequest loginToken(LoginToken loginToken) {
    this.loginToken = loginToken;
    return this;
  }

  /**
   * Get loginToken
   * @return loginToken
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LoginToken getLoginToken() {
    return loginToken;
  }

  public void setLoginToken(LoginToken loginToken) {
    this.loginToken = loginToken;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewProductRequest newProductRequest = (NewProductRequest) o;
    return Objects.equals(this.url, newProductRequest.url) &&
        Objects.equals(this.loginToken, newProductRequest.loginToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url, loginToken);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewProductRequest {\n");
    
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    loginToken: ").append(toIndentedString(loginToken)).append("\n");
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

