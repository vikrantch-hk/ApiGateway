package com.devglan.gatewayservice.exception;

import org.apache.commons.lang.StringUtils;

/**
 * @author adlakha.vaibhav
 */
public class InvalidParameterException extends HKRuntimeException {

  public InvalidParameterException(String messageKey, Object... params) {
    super(messageKey, params);
  }

  @Override
  public String getMessage() {
    String message = super.getMessage();

    if (StringUtils.isBlank(message)) {
      message = "Parameter passed to method does not match data type or is invalid";
    }
    return message;
  }
}
