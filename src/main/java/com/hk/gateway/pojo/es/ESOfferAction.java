package com.hk.gateway.pojo.es;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ESOfferAction implements Serializable {
	private Long id;
	private Long rank;
	private Long o_act_typ_id;
	private Double dscnt;

	private Long dscnt_max_lim;
	private Date csh_bck_exp_dt;
	private Long st_id;
	private List<Long> inc_br = new ArrayList<Long>();
	private List<Long> inc_pro = new ArrayList<Long>();
	private List<Long> inc_var = new ArrayList<Long>();
	private List<Long> inc_plt = new ArrayList<Long>();
	private List<String> inc_cat = new ArrayList<String>();
	private Double inc_min_pr;
	private Double inc_max_pr;
	private List<Long> exc_br = new ArrayList<Long>();
	private List<Long> exc_pro = new ArrayList<Long>();
	private List<Long> exc_var = new ArrayList<Long>();
	private List<Long> exc_plt = new ArrayList<Long>();
	private List<String> exc_cat = new ArrayList<String>();
	private Double exc_min_pr;
	private Double exc_max_pr;
	private List<ESOfferFreebie> freebs = new ArrayList<ESOfferFreebie>();
	private Integer ctr;

	public Integer getCtr() {
		return ctr;
	}

	public void setCtr(Integer ctr) {
		this.ctr = ctr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getO_act_typ_id() {
		return o_act_typ_id;
	}

	public void setO_act_typ_id(Long o_act_typ_id) {
		this.o_act_typ_id = o_act_typ_id;
	}

	public Double getDscnt() {
		return dscnt;
	}

	public void setDscnt(Double dscnt) {
		this.dscnt = dscnt;
	}

	public Long getDscnt_max_lim() {
		return dscnt_max_lim;
	}

	public void setDscnt_max_lim(Long dscnt_max_lim) {
		this.dscnt_max_lim = dscnt_max_lim;
	}

	public Date getCsh_bck_exp_dt() {
		return csh_bck_exp_dt;
	}

	public void setCsh_bck_exp_dt(Date csh_bck_exp_dt) {
		this.csh_bck_exp_dt = csh_bck_exp_dt;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public Long getSt_id() {
		return st_id;
	}

	public void setSt_id(Long st_id) {
		this.st_id = st_id;
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

	public List<Long> getExc_plt() {
		return exc_plt;
	}

	public void setExc_plt(List<Long> exc_plt) {
		this.exc_plt = exc_plt;
	}

	public List<Long> getExc_var() {
		return exc_var;
	}

	public void setExc_var(List<Long> exc_var) {
		this.exc_var = exc_var;
	}

	public List<Long> getInc_var() {
		return inc_var;
	}

	public void setInc_var(List<Long> inc_var) {
		this.inc_var = inc_var;
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

	public List<ESOfferFreebie> getFreebs() {
		return freebs;
	}

	public void setFreebs(List<ESOfferFreebie> freebs) {
		this.freebs = freebs;
	}
}
