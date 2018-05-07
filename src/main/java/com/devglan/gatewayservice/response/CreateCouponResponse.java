package com.devglan.gatewayservice.response;


import java.util.Date;
import java.util.List;

import com.devglan.gatewayservice.constant.DtoJsonConstants;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: 8/27/14
 * Time: 7:36 PM
 */
public class CreateCouponResponse extends AbstractBaseResponse {

  protected Long offerId;
  protected String offerName;
  protected Date endDt;
  protected Long timesAllowedPerUserId;
  protected String terms;
  protected String couponCode;
  protected String purchaseEnablingExpression;

  public CreateCouponResponse(Long storeId) {
    super(storeId);
  }

  public Long getOfferId() {
    return offerId;
  }

  public void setOfferId(Long offerId) {
    this.offerId = offerId;
  }

  public String getOfferName() {
    return offerName;
  }

  public void setOfferName(String offerName) {
    this.offerName = offerName;
  }

  public Date getEndDt() {
    return endDt;
  }

  public void setEndDt(Date endDt) {
    this.endDt = endDt;
  }

  public Long getTimesAllowedPerUserId() {
    return timesAllowedPerUserId;
  }

  public void setTimesAllowedPerUserId(Long timesAllowedPerUserId) {
    this.timesAllowedPerUserId = timesAllowedPerUserId;
  }

  public String getTerms() {
    return terms;
  }

  public void setTerms(String terms) {
    this.terms = terms;
  }

  public String getCouponCode() {
    return couponCode;
  }

  public void setCouponCode(String couponCode) {
    this.couponCode = couponCode;
  }

  public String getPurchaseEnablingExpression() {
    return purchaseEnablingExpression;
  }

  public void setPurchaseEnablingExpression(String purchaseEnablingExpression) {
    this.purchaseEnablingExpression = purchaseEnablingExpression;
  }

  @Override
  protected List<String> getKeys() {
    List<String> keys = super.getKeys();
    keys.add(DtoJsonConstants.ID);
    keys.add(DtoJsonConstants.NAME);
    keys.add(DtoJsonConstants.TERMS);
    keys.add(DtoJsonConstants.COUPON_CODE);
    keys.add(DtoJsonConstants.COUPON_PURCHASE_BEFORE_ENABLED);
    keys.add(DtoJsonConstants.OFFER_EXPIRY);
    return keys;
  }

  @Override
  protected List<Object> getValues() {
    List<Object> values = super.getValues();
    values.add(offerId);
    values.add(offerName);
    values.add(terms);
    values.add(couponCode);
    values.add(purchaseEnablingExpression);
    values.add(endDt);
    return values;
  }
}
