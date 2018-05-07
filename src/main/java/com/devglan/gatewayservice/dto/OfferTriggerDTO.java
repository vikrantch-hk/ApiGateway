package com.devglan.gatewayservice.dto;


import java.io.Serializable;

import com.devglan.gatewayservice.pojo.es.ESOfferTrigger;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Jul 23, 2013
 * Time: 1:58:17 PM
 */
public class OfferTriggerDTO implements Serializable {

  private Long id;
  private Long storeId;
//  private Long includingExpressionId;
//  private Long excludingExpressionId;
  private Integer minQty;
  private Integer minAmount;
  private Integer maxQty;
  private Integer maxAmount;


  public OfferTriggerDTO() {

  }

/*
  public OfferTriggerDTO(OfferTrigger offerTrigger) {
    this.id = offerTrigger.getId();
    this.storeId = offerTrigger.getStoreId();
    this.includingExpressionId = offerTrigger.getIncludingExpressionId();
    this.excludingExpressionId = offerTrigger.getExcludingExpressionId();
    this.minAmount = offerTrigger.getMinAmount();
    this.maxAmount = offerTrigger.getMaxAmount();
    this.maxQty = offerTrigger.getMaxQty();
    this.minQty = offerTrigger.getMinQty();


  }
*/

  public OfferTriggerDTO(ESOfferTrigger offerTrigger) {
    if (offerTrigger != null) {
      this.id = offerTrigger.getId();
      this.storeId = offerTrigger.getSt_id();
      this.maxAmount = offerTrigger.getMax_amt() == null ? null : offerTrigger.getMax_amt().intValue();
      this.minAmount = offerTrigger.getMin_amt() == null ? null : offerTrigger.getMin_amt().intValue();
      this.maxQty = offerTrigger.getMax_qty() == null ? null : offerTrigger.getMax_qty().intValue();
      this.minQty = offerTrigger.getMin_qty() == null ? null : offerTrigger.getMin_qty().intValue();
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
/*
  public Long getIncludingExpressionId() {
    return includingExpressionId;
  }

  public void setIncludingExpressionId(Long includingExpressionId) {
    this.includingExpressionId = includingExpressionId;
  }

  public Long getExcludingExpressionId() {
    return excludingExpressionId;
  }

  public void setExcludingExpressionId(Long excludingExpressionId) {
    this.excludingExpressionId = excludingExpressionId;
  }
*/


  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }


  public Integer getMinQty() {
    return minQty;
  }

  public void setMinQty(Integer minQty) {
    this.minQty = minQty;
  }

  public Integer getMinAmount() {
    return minAmount;
  }

  public void setMinAmount(Integer minAmount) {
    this.minAmount = minAmount;
  }

  public Integer getMaxQty() {
    return maxQty;
  }

  public void setMaxQty(Integer maxQty) {
    this.maxQty = maxQty;
  }

  public Integer getMaxAmount() {
    return maxAmount;
  }

  public void setMaxAmount(Integer maxAmount) {
    this.maxAmount = maxAmount;
  }
}
