package com.hk.gateway.dto;

import java.util.Date;
import java.util.List;

import com.hk.gateway.constant.DtoJsonConstants;
import com.hk.gateway.response.CreateCouponResponse;
import com.hk.gateway.util.HKDateUtils;

/**
 * Created by IntelliJ IDEA. User: Vaibhav Date: Jul 23, 2013 Time: 1:17:36 PM
 */
public class UserCouponDto extends OfferDTO {
	public UserCouponDto() {
	}

	public UserCouponDto(OfferDTO offerDTO) {
		this.couponCode = offerDTO.getCouponCode();
		this.endDt = offerDTO.getEndDt();
		this.terms = offerDTO.getTerms();
		this.id = offerDTO.getId();
		this.name = offerDTO.getName();
		this.image = offerDTO.getImage();
	}

	public UserCouponDto(OfferDTO offerDTO, String code, Date end_dt) {
		this.couponCode = code;
		this.endDt = end_dt;
		this.terms = offerDTO.getTerms();
		this.id = offerDTO.getId();
		this.name = offerDTO.getName();
		this.image = offerDTO.getImage();
	}

	public UserCouponDto(CreateCouponResponse createCouponResponse) {
		this.couponCode = createCouponResponse.getCouponCode();
		this.endDt = HKDateUtils.getGMTDate(createCouponResponse.getEndDt());
		this.terms = createCouponResponse.getTerms();
		this.id = createCouponResponse.getOfferId();
		this.name = createCouponResponse.getOfferName();
	}

	@Override
	protected List<String> getKeys() {
		List<String> keys = super.getKeys();
		keys.add(DtoJsonConstants.OFFER_EXPIRY);

		return keys;
	}

	@Override
	protected List<Object> getValues() {
		List<Object> values = super.getValues();
		values.add(this.endDt);

		return values;
	}
}
