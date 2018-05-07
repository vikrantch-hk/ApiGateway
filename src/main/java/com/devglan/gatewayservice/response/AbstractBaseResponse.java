package com.devglan.gatewayservice.response;


import java.util.List;

import com.devglan.gatewayservice.constant.DtoJsonConstants;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Apr 11, 2013
 * Time: 7:58:56 PM
 */
public abstract class AbstractBaseResponse extends AbstractGenericResponse {

  protected Long storeId;


  protected AbstractBaseResponse(Long storeId) {
    this.storeId = storeId;
  }

  protected AbstractBaseResponse() {
  }

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }


  @Override
  protected List<String> getKeys() {
    List<String> keyList = super.getKeys();

    keyList.add(DtoJsonConstants.STORE_ID);
    return keyList;
  }

  @Override
  protected List<Object> getValues() {
    List<Object> valueList = super.getValues();

    valueList.add(this.storeId);
    return valueList;
  }
}
