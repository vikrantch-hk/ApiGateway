package com.devglan.gatewayservice.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devglan.gatewayservice.constant.EnumPlatformType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Mar 22, 2013
 * Time: 2:56:53 PM
 */
public class AbstractBaseRequest implements Serializable {
  private static Logger logger = LoggerFactory.getLogger(AbstractBaseRequest.class);

  protected Long storeId;
  protected Long platformId;

  public AbstractBaseRequest() {
  }

  public AbstractBaseRequest(Long storeId) {
    this.storeId = storeId;
  }

  public AbstractBaseRequest(Long storeId,Long platformId) {
    this.storeId = storeId;
    this.platformId=platformId;
  }

  protected boolean validate() {
    boolean valid = true;
    if (storeId == null) {
      logger.error("Error validating " + this.getClass().getSimpleName() + " store id cannot be null");
      valid = false;
    }

    // if platform id is null, that is ok, but if it is an invalid id that is not ok
    if (valid && platformId != null && EnumPlatformType.getEnumPlatformTypeById(platformId) == null) {
      logger.error("Error validating " + this.getClass().getSimpleName() + " platform id " + platformId + "is invalid");
      valid = false;
    }

    return valid;
  }

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  public Long getPlatformId() {
    return platformId;
  }

  public void setPlatformId(Long platformId) {
    this.platformId = platformId;
  }

  protected Map<String, Object> getParameters() {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("storeId", storeId.toString());
    return params;
  }
}
