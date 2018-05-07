package com.devglan.gatewayservice.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.devglan.gatewayservice.constant.DtoJsonConstants;
import com.devglan.gatewayservice.pojo.es.ESOfferAction;
import com.devglan.gatewayservice.response.JSONObject;
import com.devglan.gatewayservice.response.es.ESOfferResponse;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Jul 23, 2013
 * Time: 1:17:36 PM
 */
public class OfferDTO extends JSONObject {

  protected Long id;
  protected Long storeId;
  protected String name;
  protected Long offerTypeId;
  protected Date startDt;
  protected Date endDt;
  protected int offerPaymentModeId;
  protected Long timesAllowedPerUserId;
  protected String terms;
  protected List<OfferActionDTO> offerActionDTO = new ArrayList<OfferActionDTO>();
  protected OfferTriggerDTO offerTriggerDTO;
  protected String couponCode;
  private boolean displayOfferOnCouponPage;
  private boolean userAuthEnabled;
  private boolean retailStoreOnly;
  protected ImageResponseDto image;
  protected Long offerPromoId;
  private Long displayOrder;
  private Long countUserId;
  private String endDateString;
  private boolean hkRetailOffer; //only applicable for retail orders placed on  hk.com
  private String landingPageUrl;

  protected Set<String> offerRoles = new HashSet<String>(0);


  public OfferDTO() {

  }

  public OfferDTO(ESOfferResponse offerResponse) {
    this.id = offerResponse.getId();
    this.storeId = offerResponse.getStoreId();
    this.name = offerResponse.getNm();
    this.offerTypeId = offerResponse.getO_typ_id();
    this.startDt = offerResponse.getSt_dt();
    this.endDt = offerResponse.getEn_dt();
    List<Long> paymentModes = offerResponse.getO_paymode_id();
    if (paymentModes != null && !paymentModes.isEmpty()) {
      this.offerPaymentModeId = paymentModes.get(0).intValue();
    }
    this.terms = offerResponse.getTrms();
    for (ESOfferAction esOfferAction : offerResponse.getO_act()) {
      OfferActionDTO actionDTO = new OfferActionDTO(esOfferAction);
      this.offerActionDTO.add(actionDTO);
    }
//    this.offerActionDTO = new OfferActionDTO(offerResponse.getO_act());
    this.offerTriggerDTO = new OfferTriggerDTO(offerResponse.getO_tr());
    this.offerRoles = new HashSet<String>(offerResponse.getRl_nm());
    this.couponCode = offerResponse.getCcode();
    this.timesAllowedPerUserId = offerResponse.getAl_tms_pr_usr();
    this.userAuthEnabled = offerResponse.isUsr_auth_enabled();
    this.retailStoreOnly = offerResponse.isRetailStoreOnly();
    this.offerPromoId = offerResponse.getO_promo_id();
    this.displayOfferOnCouponPage = offerResponse.isDisplayOfferOnCouponPage();
    this.displayOrder = offerResponse.getDisplayOrder();
    this.hkRetailOffer = offerResponse.isHk_retail_offer();
    this.landingPageUrl = offerResponse.getLanding_page_url();
  }

/*
  public OfferDTO(Offer offer) {

    this.id = offer.getId();
    this.storeId = offer.getStoreId();
    this.name = offer.getName();
    this.offerTypeId = offer.getOfferTypeId();
    this.startDt = offer.getStartDt();
    this.endDt = offer.getEndDt();
    this.offerPaymentModeId = offer.getOfferPaymentModeId();
    this.terms = offer.getTerms();

    this.offerActionDTO = new OfferActionDTO(offer.getOfferAction());
    this.offerTriggerDTO = new OfferTriggerDTO(offer.getOfferTrigger());
    this.timesAllowedPerUserId = offer.getAllowedTimesPerUserId();
    if (offer.getRoles() != null) {
      for (Role role : offer.getRoles()) {
        String roleName = role.getName();
        offerRoles.add(roleName);
      }
    }
  }
*/

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getOfferTypeId() {
    return offerTypeId;
  }

  public void setOfferTypeId(Long offerTypeId) {
    this.offerTypeId = offerTypeId;
  }

  public Date getStartDt() {
    return startDt;
  }

  public void setStartDt(Date startDt) {
    this.startDt = startDt;
  }

  public Date getEndDt() {
    return endDt;
  }

  public void setEndDt(Date endDt) {
    this.endDt = endDt;
  }

  public int getOfferPaymentModeId() {
    return offerPaymentModeId;
  }

  public void setOfferPaymentModeId(int offerPaymentModeId) {
    this.offerPaymentModeId = offerPaymentModeId;
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

  public List<OfferActionDTO> getOfferActionDTO() {
    return offerActionDTO;
  }

  public void setOfferActionDTO(List<OfferActionDTO> offerActionDTO) {
    this.offerActionDTO = offerActionDTO;
  }

  public OfferTriggerDTO getOfferTriggerDTO() {
    return offerTriggerDTO;
  }

  public void setOfferTriggerDTO(OfferTriggerDTO offerTriggerDTO) {
    this.offerTriggerDTO = offerTriggerDTO;
  }

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  public Set<String> getOfferRoles() {
    return offerRoles;
  }

  public void setOfferRoles(Set<String> offerRoles) {
    this.offerRoles = offerRoles;
  }

  public String getCouponCode() {
    return couponCode;
  }

  public void setCouponCode(String couponCode) {
    this.couponCode = couponCode;
  }

  public boolean isUserAuthEnabled() {
    return userAuthEnabled;
  }

  public void setUserAuthEnabled(boolean userAuthEnabled) {
    this.userAuthEnabled = userAuthEnabled;
  }

  public boolean isRetailStoreOnly() {
    return retailStoreOnly;
  }

  public void setRetailStoreOnly(boolean retailStoreOnly) {
    this.retailStoreOnly = retailStoreOnly;
  }

  public ImageResponseDto getImage() {
    return image;
  }

  public void setImage(ImageResponseDto image) {
    this.image = image;
  }

  public Long getOfferPromoId() {
    return offerPromoId;
  }

  public void setOfferPromoId(Long offerPromoId) {
    this.offerPromoId = offerPromoId;
  }

  public boolean isDisplayOfferOnCouponPage() {
    return displayOfferOnCouponPage;
  }

  public void setDisplayOfferOnCouponPage(boolean displayOfferOnCouponPage) {
    this.displayOfferOnCouponPage = displayOfferOnCouponPage;
  }

  public Long getDisplayOrder() {
    return displayOrder;
  }

  public void setDisplayOrder(Long displayOrder) {
    this.displayOrder = displayOrder;
  }

  public Long getCountUserId() {
    return countUserId;
  }

  public void setCountUserId(Long countUserId) {
    this.countUserId = countUserId;
  }

  public String getEndDateString() {
    return endDateString;
  }

  public void setEndDateString(String endDateString) {
    this.endDateString = endDateString;
  }

  public boolean isHkRetailOffer() {
    return hkRetailOffer;
  }

  public void setHkRetailOffer(boolean hkRetailOffer) {
    this.hkRetailOffer = hkRetailOffer;
  }

  public String getLandingPageUrl() {
    return landingPageUrl;
  }

  public void setLandingPageUrl(String landingPageUrl) {
    this.landingPageUrl = landingPageUrl;
  }

  @Override
  protected List<String> getKeys() {
    List<String> keys = new ArrayList<String>();
    keys.add(DtoJsonConstants.ID);
    keys.add(DtoJsonConstants.NAME);
    keys.add(DtoJsonConstants.TERMS);
    keys.add(DtoJsonConstants.COUPON_CODE);
    keys.add(DtoJsonConstants.END_DATE);
    keys.add(DtoJsonConstants.OFFER_ACTIONS);
    keys.add(DtoJsonConstants.OFFER_TRIGGER);
    keys.add(DtoJsonConstants.IMAGE);
    keys.add(DtoJsonConstants.DISPLAY_ORDER);
    keys.add(DtoJsonConstants.COUPON_APPLICABLE);
    keys.add(DtoJsonConstants.COUNT);
    keys.add(DtoJsonConstants.END_DATE_COUPON);
    keys.add(DtoJsonConstants.LANDING_PAGE);
    return keys;
  }

  @Override
  protected List<Object> getValues() {
    List<Object> values = new ArrayList<Object>();

    values.add(this.id);
    values.add(this.name);
    /*values.add(this.paymentTypeId);*/
    values.add(this.terms);
    values.add(this.couponCode);
    values.add(this.endDt);
    values.add(this.offerActionDTO);
    values.add(this.offerTriggerDTO);
    values.add(this.image);
    values.add(this.displayOrder);
    values.add(this.displayOfferOnCouponPage);
    values.add(this.countUserId);
    values.add(this.endDateString);
    values.add(this.landingPageUrl);
    return values;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    OfferDTO offerDTO = (OfferDTO) o;

    return new EqualsBuilder()
        .append(id, offerDTO.id)
        .append(name, offerDTO.name)
        .append(offerTypeId, offerDTO.offerTypeId)
        .append(couponCode, offerDTO.couponCode)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(id)
        .append(name)
        .append(offerTypeId)
        .append(couponCode)
        .toHashCode();
  }
}
