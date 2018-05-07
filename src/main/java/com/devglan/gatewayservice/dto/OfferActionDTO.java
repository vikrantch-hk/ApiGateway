package com.devglan.gatewayservice.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.devglan.gatewayservice.pojo.es.ESOfferAction;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Jul 23, 2013
 * Time: 1:58:08 PM
 */
public class OfferActionDTO implements Serializable {

    private Long id;
    private Long storeId;
    private Long rank;
    private Long offerActionTypeId;
    private double discount;
    private Long discountMaxLimit;
    private Date cashBackExpiryDate;
    private int counter;
    List<Long> includedVariants;
//  private Long includingExpressionId;
//  private Long excludingExpressionId;

    public OfferActionDTO(ESOfferAction offerAction) {
        if (offerAction != null) {
            this.counter = offerAction.getCtr() != null ? offerAction.getCtr() : -1;
            this.id = offerAction.getId();
            this.storeId = offerAction.getSt_id();
            this.offerActionTypeId = offerAction.getO_act_typ_id();
            if (offerAction.getDscnt() != null) {
                this.discount = offerAction.getDscnt();
            }
            this.discountMaxLimit = offerAction.getDscnt_max_lim();
            this.cashBackExpiryDate = offerAction.getCsh_bck_exp_dt();
            this.rank = offerAction.getRank();
            this.includedVariants = offerAction.getInc_var();
        }
    }

  /* private Long applicableOnExpressionId;
private int maxFreeVariants;*/

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

    public OfferActionDTO() {

    }

/*
  public OfferActionDTO(OfferAction offerAction) {
    this.id = offerAction.getId();
    this.storeId = offerAction.getStoreId();
    this.offerActionTypeId = offerAction.getOfferActionTypeId();
    this.discount = offerAction.getDiscount();
    this.discountMaxLimit = offerAction.getDiscountMaxLimit();
    this.cashBackExpiryDate = offerAction.getCashBackExpiryDate();
    this.includingExpressionId = offerAction.getIncludingExpressionId();
    this.excludingExpressionId = offerAction.getExcludingExpressionId();
    */
/*this.applicableOnExpressionId = offerAction.getApplicableOnExpressionId();
    this.maxFreeVariants = offerAction.getMaxFreeVariants();*//*

  }
*/

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfferActionTypeId() {
        return offerActionTypeId;
    }

    public void setOfferActionTypeId(Long offerActionTypeId) {
        this.offerActionTypeId = offerActionTypeId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Long getDiscountMaxLimit() {
        return discountMaxLimit;
    }

    public void setDiscountMaxLimit(Long discountMaxLimit) {
        this.discountMaxLimit = discountMaxLimit;
    }

    public Date getCashBackExpiryDate() {
        return cashBackExpiryDate;
    }

    public void setCashBackExpiryDate(Date cashBackExpiryDate) {
        this.cashBackExpiryDate = cashBackExpiryDate;
    }
/*
  public Long getApplicableOnExpressionId() {
    return applicableOnExpressionId;
  }

  public void setApplicableOnExpressionId(Long applicableOnExpressionId) {
    this.applicableOnExpressionId = applicableOnExpressionId;
  }

  public int getMaxFreeVariants() {
    return maxFreeVariants;
  }

  public void setMaxFreeVariants(int maxFreeVariants) {
    this.maxFreeVariants = maxFreeVariants;
  }*/

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<Long> getIncludedVariants() {
        return includedVariants;
    }

    public void setIncludedVariants(List<Long> includedVariants) {
        this.includedVariants = includedVariants;
    }
}
