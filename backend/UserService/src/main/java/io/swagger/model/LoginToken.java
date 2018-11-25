package io.swagger.model;

import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LoginToken
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-30T16:11:09.391Z")

public class LoginToken   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("sessionToken")
  private String sessionToken = null;

  @JsonProperty("expiryTime")
  private Date expiryTime = null;

  public LoginToken username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
  **/
  @ApiModelProperty(value = "")


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public LoginToken sessionToken(String sessionToken) {
    this.sessionToken = sessionToken;
    return this;
  }

  /**
   * Get sessionToken
   * @return sessionToken
  **/
  @ApiModelProperty(value = "")

  public String getSessionToken() {
    return sessionToken;
  }

  public void setSessionToken(String sessionToken) {
    this.sessionToken = sessionToken;
  }

  public LoginToken expiryTime(Date expiryTime) {
    this.expiryTime = expiryTime;
    return this;
  }

  /**
   * Get expiryTime
   * @return expiryTime
  **/
  @ApiModelProperty(value = "")

  @Valid
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "EST")
  public Date getExpiryTime() {
    return expiryTime;
  }

  public void setExpiryTime(Date expiryTime) {
    this.expiryTime = expiryTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginToken loginToken = (LoginToken) o;
    return Objects.equals(this.username, loginToken.username) &&
        Objects.equals(this.sessionToken, loginToken.sessionToken) &&
        Objects.equals(this.expiryTime, loginToken.expiryTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, sessionToken, expiryTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoginToken {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    sessionToken: ").append(toIndentedString(sessionToken)).append("\n");
    sb.append("    expiryTime: ").append(toIndentedString(expiryTime)).append("\n");
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

