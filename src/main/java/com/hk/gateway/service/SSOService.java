package com.hk.gateway.service;

import com.hk.gateway.pojo.UserAuth;

/**
 * Created by Vidur on 11/18/2015.
 */
public interface SSOService {

	/*
	 * public SSOUserResponse getSSOUserResponse(SSOUserRequest ssoUserRequest);
	 * 
	 *//**
		 * @param user
		 * @param authExpirationDate
		 * @param updateToken
		 *            - to regenerate token --> used in case of password change so that
		 *            token is updated according to new password
		 * @return
		 */
	/*
	 * public UserAuth createUpdateAuthToken(User user, Date authExpirationDate,
	 * boolean updateToken);
	 * 
	 *//**
		 * this api will return user basic info based on user auth token(used in SSO)
		 *
		 * @param userInfoRequest
		 * @return
		 *//*
			 * public UserInfoResponse getUserResponseByAuthToken(UserInfoRequest
			 * userInfoRequest);
			 */

	public UserAuth getUserAuthByToken(String authToken);

	/*
	 * public UserAuthTokenResponse getAuthTokenResponseForUser(Long userId, Long
	 * storeId);
	 * 
	 * public UserAuth getAuthTokenForUser(Long userId);
	 */
}
