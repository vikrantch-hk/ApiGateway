package com.hk.gateway.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.gateway.exception.InvalidParameterException;
import com.hk.gateway.pojo.UserAuth;
import com.hk.gateway.repo.UserAuthRepo;
import com.hk.gateway.service.SSOService;

@Service
public class SSOServiceImpl implements SSOService {

	@Autowired
	private UserAuthRepo repo;

	@Override
	public UserAuth getUserAuthByToken(String authToken) {
		if (StringUtils.isBlank(authToken)) {
			throw new InvalidParameterException("ACCESS_TOKEN_CANNOT_BE_BLANK");
		}

		UserAuth userAuth = repo.findByAuthToken(authToken);
		return userAuth;

	}

}
