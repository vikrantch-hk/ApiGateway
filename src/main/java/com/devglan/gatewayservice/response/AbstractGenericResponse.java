package com.devglan.gatewayservice.response;


import java.util.ArrayList;
import java.util.List;

import com.devglan.gatewayservice.constant.DtoJsonConstants;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Jun 17, 2013
 * Time: 2:48:29 PM
 */
public abstract class AbstractGenericResponse extends JSONObject {

  protected boolean exception = false;
  protected List<String> messages = new ArrayList<String>();


  public boolean isException() {
    return exception;
  }

  public AbstractGenericResponse setException(boolean exception) {
    this.exception = exception;
    return this;
  }

  public AbstractGenericResponse addMessage(String message) {
    this.messages.add(message);
    return this;
  }

  public AbstractGenericResponse addMessages(List<String> messages) {
    this.messages.addAll(messages);
    return this;
  }

  public List<String> getMessages() {
    return messages;
  }


  @Override
  protected List<String> getKeys() {
    List<String> keys = new ArrayList<String>(0);
    keys.add(DtoJsonConstants.MESSAGES);
    keys.add(DtoJsonConstants.EXCEPTION);

    return keys;
  }

  @Override
  protected List<Object> getValues() {
    List<Object> values = new ArrayList<Object>(0);
    values.add(this.messages);
    values.add(this.exception);
    return values;
  }
}
