package com.devglan.gatewayservice.dto;

import com.devglan.gatewayservice.constant.UserConstants;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Mar 18, 2013
 * Time: 5:52:15 PM
 */
public class UserSubscriptionBooleanDTO {

  private Boolean smsSubscribed;
  private Boolean emailSubScribed;

  public UserSubscriptionBooleanDTO() {
  }

  public UserSubscriptionBooleanDTO(int boolBitset) {
    this.smsSubscribed = boolBitset % UserConstants.SUBSCRIBE_SMS == 0;
    this.emailSubScribed = boolBitset % UserConstants.SUBSCRIBE_EMAIL == 0;
  }

  public int generateBoolBitset() {
    int boolBitset = 1;

    boolBitset = this.smsSubscribed != null && this.smsSubscribed ? boolBitset * UserConstants.SUBSCRIBE_SMS : boolBitset;
    boolBitset = this.emailSubScribed != null && this.emailSubScribed ? boolBitset * UserConstants.SUBSCRIBE_EMAIL : boolBitset;
    return boolBitset;
  }

  public Boolean getSmsSubscribed() {
    return smsSubscribed;
  }

  public void setSmsSubscribed(Boolean smsSubscribed) {
    this.smsSubscribed = smsSubscribed;
  }

  public Boolean getEmailSubScribed() {
    return emailSubScribed;
  }

  public void setEmailSubScribed(Boolean emailSubScribed) {
    this.emailSubScribed = emailSubScribed;
  }
}
