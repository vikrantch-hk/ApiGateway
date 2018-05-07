package com.devglan.gatewayservice.response;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.devglan.gatewayservice.constant.DtoJsonConstants;
import com.devglan.gatewayservice.pojo.User;

/**
 * Created by ankita.ghosh on 5/24/2016.
 */
public class UserInfoResponse extends AbstractBaseResponse {


  private String login;
  private String name;
  private String gender;
  private String contactNumber;
  private Date authExpiryDate;

  public UserInfoResponse(Long storeId) {
    super(storeId);
  }

  public UserInfoResponse(User user, Long storeId, Date authExpiryDate) {
    super(storeId);
    this.name = user.getName();
    this.login = user.getLogin();
    this.gender = user.getGender();
    this.contactNumber = user.getContactNumber();
    this.authExpiryDate = authExpiryDate;
  }


  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public Date getAuthExpiryDate() {
    return authExpiryDate;
  }

  public void setAuthExpiryDate(Date authExpiryDate) {
    this.authExpiryDate = authExpiryDate;
  }

  @Override
  protected List<String> getKeys() {
    List<String> keys = super.getKeys();
    keys.add(DtoJsonConstants.NAME);
    keys.add(DtoJsonConstants.LOGIN);
    keys.add(DtoJsonConstants.GENDER);
    keys.add(DtoJsonConstants.CONTACT_NUMBER);
    keys.add(DtoJsonConstants.AUTH_EXPIRY_DATE);
    return keys;

  }

  @Override
  protected List<Object> getValues() {
    List<Object> values = super.getValues();
    values.add(this.name);
    values.add(this.login);
    values.add(this.gender);
    values.add(this.contactNumber);
    values.add(this.authExpiryDate);
    return values;

  }
}
