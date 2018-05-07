package com.devglan.gatewayservice.request;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Vidur on 11/19/2015.
 */
public class SSOUserRequest extends AbstractBaseAuthRequest {

  private static Logger logger = LoggerFactory.getLogger(SSOUserRequest.class);
  private String ssoAuthToken;

  @Override
  public boolean validate() {
    boolean valid = super.validate();

    if (valid && StringUtils.isBlank(this.ssoAuthToken)) {
      logger.error("Error validating " + this.getClass().getSimpleName() + " access token cannot be null");
      valid = false;
    }

    return valid;
  }

  public String getSsoAuthToken() {
    return ssoAuthToken;
  }

  public void setSsoAuthToken(String ssoAuthToken) {
    this.ssoAuthToken = ssoAuthToken;
  }
}
