package com.devglan.gatewayservice.response;


import java.util.Date;
import java.util.List;

import com.devglan.gatewayservice.constant.DtoJsonConstants;

/**
 * Created by Vidur on 8/31/2016.
 */
public class UserAuthTokenResponse extends AbstractBaseResponse {

  private Long userId;
  private String encryptedToken;
  private Date expiryDate;

  public UserAuthTokenResponse(Long storeId) {
    super(storeId);
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getEncryptedToken() {
    return encryptedToken;
  }

  public void setEncryptedToken(String encryptedToken) {
    this.encryptedToken = encryptedToken;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  @Override
  protected List<String> getKeys() {
    List<String> keyList = super.getKeys();
    keyList.add(DtoJsonConstants.USER_ID);
    keyList.add(DtoJsonConstants.AUTH_TOKEN);
    keyList.add(DtoJsonConstants.AUTH_EXPIRY_DATE);
    return keyList;
  }

  @Override
  protected List<Object> getValues() {
    List<Object> valueList = super.getValues();
    valueList.add(this.userId);
    valueList.add(this.encryptedToken);
    valueList.add(this.expiryDate);

    return valueList;
  }
}
