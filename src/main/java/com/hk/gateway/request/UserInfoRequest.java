package com.hk.gateway.request;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ankita.ghosh on 5/24/2016.
 */
public class UserInfoRequest extends AbstractBaseRequest {

	private static Logger logger = LoggerFactory.getLogger(UserInfoRequest.class);
	private String authToken;

	@Override
	public boolean validate() {
		boolean valid = super.validate();

		if (valid && StringUtils.isBlank(this.authToken)) {
			logger.error("Error validating " + this.getClass().getSimpleName() + " auth token cannot be null");
			valid = false;
		}

		return valid;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
}
