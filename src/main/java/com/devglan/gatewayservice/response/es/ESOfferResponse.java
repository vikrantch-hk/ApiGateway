package com.devglan.gatewayservice.response.es;

import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.devglan.gatewayservice.pojo.es.ESOfferAction;
import com.devglan.gatewayservice.pojo.es.ESOfferTrigger;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Mar 22, 2013
 * Time: 2:38:42 PM
 * <p/>
 * This response is intended to be kept only for internal purposes and not exposed for any API.
 */

public class ESOfferResponse extends BaseESResponse {

  private String nm;
  private List<String> rl_nm = new ArrayList<String>();
  private List<ESOfferAction> o_act = new ArrayList<ESOfferAction>();
  private ESOfferTrigger o_tr;
  private Long o_typ_id;
  private Date st_dt;
  private Date en_dt;
  private String trms;
  private String ccode;
  private Date cr_dt;
  private Long st_id;
  private Long al_tms_pr_usr;
  private List<Long> o_paymode_id = new ArrayList<Long>();
  private List<String> email_dom = new ArrayList<String>();
  private boolean usr_auth_enabled;
  private boolean retailStoreOnly;
  private Long o_img_id;
  private Long o_promo_id;
  private boolean displayOfferOnCouponPage;
  private Long displayOrder;
  private boolean hk_retail_offer;
  private String landing_page_url;

  public String getNm() {
    return nm;
  }

  public void setNm(String nm) {
    this.nm = nm;
  }

  public List<String> getRl_nm() {
    return rl_nm;
  }

  public void setRl_nm(List<String> rl_nm) {
    this.rl_nm = rl_nm;
  }

  public List<ESOfferAction> getO_act() {
    return o_act;
  }

  public void setO_act(List<ESOfferAction> o_act) {
    this.o_act = o_act;
  }

  public ESOfferTrigger getO_tr() {
    return o_tr;
  }

  public void setO_tr(ESOfferTrigger o_tr) {
    this.o_tr = o_tr;
  }

  public Long getO_typ_id() {
    return o_typ_id;
  }

  public void setO_typ_id(Long o_typ_id) {
    this.o_typ_id = o_typ_id;
  }

  public Date getSt_dt() {
    return st_dt;
  }

  public void setSt_dt(Date st_dt) {
    this.st_dt = st_dt;
  }

  public Date getEn_dt() {
    return en_dt;
  }

  public void setEn_dt(Date en_dt) {
    this.en_dt = en_dt;
  }

  public String getCcode() {
    return ccode;
  }

  public void setCcode(String ccode) {
    this.ccode = ccode;
  }

  public String getTrms() {
    return trms;
  }

  public void setTrms(String trms) {
    this.trms = trms;
  }

  public Date getCr_dt() {
    return cr_dt;
  }

  public void setCr_dt(Date cr_dt) {
    this.cr_dt = cr_dt;
  }

  public Long getAl_tms_pr_usr() {
    return al_tms_pr_usr;
  }

  public void setAl_tms_pr_usr(Long al_tms_pr_usr) {
    this.al_tms_pr_usr = al_tms_pr_usr;
  }

  public Long getSt_id() {
    return st_id;
  }

  public void setSt_id(Long st_id) {
    this.st_id = st_id;
  }

  public List<Long> getO_paymode_id() {
    return o_paymode_id;
  }

  public void setO_paymode_id(List<Long> o_paymode_id) {
    this.o_paymode_id = o_paymode_id;
  }

  public List<String> getEmail_dom() {
    return email_dom;
  }

  public void setEmail_dom(List<String> email_dom) {
    this.email_dom = email_dom;
  }

  public boolean isUsr_auth_enabled() {
    return usr_auth_enabled;
  }

  public void setUsr_auth_enabled(boolean usr_auth_enabled) {
    this.usr_auth_enabled = usr_auth_enabled;
  }

  public boolean isRetailStoreOnly() {
    return retailStoreOnly;
  }

  public void setRetailStoreOnly(boolean retailStoreOnly) {
    this.retailStoreOnly = retailStoreOnly;
  }

  public Long getO_img_id() {
    return o_img_id;
  }

  public void setO_img_id(Long o_img_id) {
    this.o_img_id = o_img_id;
  }

  public Long getO_promo_id() {
    return o_promo_id;
  }

  public void setO_promo_id(Long o_promo_id) {
    this.o_promo_id = o_promo_id;
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

  public boolean isHk_retail_offer() {
    return hk_retail_offer;
  }

  public void setHk_retail_offer(boolean hk_retail_offer) {
    this.hk_retail_offer = hk_retail_offer;
  }

  public String getLanding_page_url() {
    return landing_page_url;
  }

  public void setLanding_page_url(String landing_page_url) {
    this.landing_page_url = landing_page_url;
  }
}

