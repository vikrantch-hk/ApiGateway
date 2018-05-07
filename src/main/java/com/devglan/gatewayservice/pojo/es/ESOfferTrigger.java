package com.devglan.gatewayservice.pojo.es;

import java.io.Serializable;
import java.lang.Long;
import java.util.ArrayList;
import java.util.List;

public class ESOfferTrigger implements Serializable {
  private Long id;
  private List<Long> inc_br = new ArrayList<Long>();
  private List<Long> inc_pro = new ArrayList<Long>();
  private List<Long> inc_var = new ArrayList<Long>();
  private List<Long> inc_plt = new ArrayList<Long>();
  private List<String> inc_cat = new ArrayList<String>();
  private Double inc_min_pr;
  private Double inc_max_pr;
  private Double user_inc_max_oc;
  private Double user_inc_min_oc;
  private List<Long> user_inc_br = new ArrayList<Long>();
  private List<String> user_inc_cat = new ArrayList<String>();

  private List<Long> exc_br = new ArrayList<Long>();
  private List<Long> exc_pro = new ArrayList<Long>();
  private List<Long> exc_var = new ArrayList<Long>();
  private List<Long> exc_plt = new ArrayList<Long>();
  private List<String> exc_cat = new ArrayList<String>();
  private Double exc_min_pr;
  private Double exc_max_pr;
  private Double user_exc_max_oc;
  private Double user_exc_min_oc;
  private List<Long> user_exc_br = new ArrayList<Long>();
  private List<String> user_exc_cat = new ArrayList<String>();

  private Long min_qty;
  private Double min_amt;
  private Long st_id;
  private Double max_amt;
  private Long max_qty;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Long> getInc_br() {
    return inc_br;
  }

  public void setInc_br(List<Long> inc_br) {
    this.inc_br = inc_br;
  }

  public List<Long> getInc_pro() {
    return inc_pro;
  }

  public void setInc_pro(List<Long> inc_pro) {
    this.inc_pro = inc_pro;
  }

  public List<Long> getInc_plt() {
    return inc_plt;
  }

  public void setInc_plt(List<Long> inc_plt) {
    this.inc_plt = inc_plt;
  }

  public List<String> getInc_cat() {
    return inc_cat;
  }

  public void setInc_cat(List<String> inc_cat) {
    this.inc_cat = inc_cat;
  }

  public Double getInc_min_pr() {
    return inc_min_pr;
  }

  public void setInc_min_pr(Double inc_min_pr) {
    this.inc_min_pr = inc_min_pr;
  }

  public Double getInc_max_pr() {
    return inc_max_pr;
  }

  public void setInc_max_pr(Double inc_max_pr) {
    this.inc_max_pr = inc_max_pr;
  }

  public List<Long> getExc_br() {
    return exc_br;
  }

  public void setExc_br(List<Long> exc_br) {
    this.exc_br = exc_br;
  }

  public List<Long> getExc_pro() {
    return exc_pro;
  }

  public void setExc_pro(List<Long> exc_pro) {
    this.exc_pro = exc_pro;
  }

  public List<Long> getInc_var() {
    return inc_var;
  }

  public void setInc_var(List<Long> inc_var) {
    this.inc_var = inc_var;
  }

  public List<Long> getExc_var() {
    return exc_var;
  }

  public void setExc_var(List<Long> exc_var) {
    this.exc_var = exc_var;
  }

  public List<Long> getExc_plt() {
    return exc_plt;
  }

  public void setExc_plt(List<Long> exc_plt) {
    this.exc_plt = exc_plt;
  }

  public List<String> getExc_cat() {
    return exc_cat;
  }

  public void setExc_cat(List<String> exc_cat) {
    this.exc_cat = exc_cat;
  }

  public Double getExc_min_pr() {
    return exc_min_pr;
  }

  public void setExc_min_pr(Double exc_min_pr) {
    this.exc_min_pr = exc_min_pr;
  }

  public Double getExc_max_pr() {
    return exc_max_pr;
  }

  public void setExc_max_pr(Double exc_max_pr) {
    this.exc_max_pr = exc_max_pr;
  }

  public Double getUser_inc_max_oc() {
    return user_inc_max_oc;
  }

  public void setUser_inc_max_oc(Double user_inc_max_oc) {
    this.user_inc_max_oc = user_inc_max_oc;
  }

  public Double getUser_inc_min_oc() {
    return user_inc_min_oc;
  }

  public void setUser_inc_min_oc(Double user_inc_min_oc) {
    this.user_inc_min_oc = user_inc_min_oc;
  }

  public List<Long> getUser_inc_br() {
    return user_inc_br;
  }

  public void setUser_inc_br(List<Long> user_inc_br) {
    this.user_inc_br = user_inc_br;
  }

  public List<String> getUser_inc_cat() {
    return user_inc_cat;
  }

  public void setUser_inc_cat(List<String> user_inc_cat) {
    this.user_inc_cat = user_inc_cat;
  }

  public Double getUser_exc_max_oc() {
    return user_exc_max_oc;
  }

  public void setUser_exc_max_oc(Double user_exc_max_oc) {
    this.user_exc_max_oc = user_exc_max_oc;
  }

  public Double getUser_exc_min_oc() {
    return user_exc_min_oc;
  }

  public void setUser_exc_min_oc(Double user_exc_min_oc) {
    this.user_exc_min_oc = user_exc_min_oc;
  }

  public List<Long> getUser_exc_br() {
    return user_exc_br;
  }

  public void setUser_exc_br(List<Long> user_exc_br) {
    this.user_exc_br = user_exc_br;
  }

  public List<String> getUser_exc_cat() {
    return user_exc_cat;
  }

  public void setUser_exc_cat(List<String> user_exc_cat) {
    this.user_exc_cat = user_exc_cat;
  }

  public Long getMin_qty() {
    return min_qty;
  }

  public void setMin_qty(Long min_qty) {
    this.min_qty = min_qty;
  }

  public Double getMin_amt() {
    return min_amt;
  }

  public void setMin_amt(Double min_amt) {
    this.min_amt = min_amt;
  }

  public Long getSt_id() {
    return st_id;
  }

  public void setSt_id(Long st_id) {
    this.st_id = st_id;
  }

  public Double getMax_amt() {
    return max_amt;
  }

  public void setMax_amt(Double max_amt) {
    this.max_amt = max_amt;
  }

  public Long getMax_qty() {
    return max_qty;
  }

  public void setMax_qty(Long max_qty) {
    this.max_qty = max_qty;
  }
}
