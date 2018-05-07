package com.devglan.gatewayservice.response;

import com.devglan.gatewayservice.constant.DtoJsonConstants;
import com.devglan.gatewayservice.pojo.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Vidur on 12/1/2015.
 */
public class SSOUserResponse extends UserResponse {

  private String authToken;
  private Date authExpiryDate;

  public SSOUserResponse(Long storeId) {
    super(storeId);
  }

  public SSOUserResponse(User user, Long storeId, String authToken, Date authExpiryDate) {
    super(user, storeId);
    this.authToken = authToken;
    this.authExpiryDate = authExpiryDate;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  public Date getAuthExpiryDate() {
    return authExpiryDate;
  }

  public void setAuthExpiryDate(Date authExpiryDate) {
    this.authExpiryDate = authExpiryDate;
  }

  @Override
  protected List<String> getKeys() {
    List<String> keyList = super.getKeys();
    keyList.add(DtoJsonConstants.AUTH_TOKEN);
    keyList.add(DtoJsonConstants.AUTH_EXPIRY_DATE);
    return keyList;
  }

  @Override
  protected List<Object> getValues() {
    List<Object> valueList = super.getValues();
    valueList.add(this.authToken);
    valueList.add(this.authExpiryDate);

    return valueList;
  }
}
