package com.devglan.gatewayservice.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devglan.gatewayservice.exception.InvalidParameterException;
import com.devglan.gatewayservice.pojo.UserAuth;
import com.devglan.gatewayservice.repo.UserAuthRepo;
import com.devglan.gatewayservice.service.SSOService;

@Service
public class SSOServiceImpl implements SSOService {

	@Autowired
	private UserAuthRepo repo;

	@Override
	//@Transactional
	public UserAuth getUserAuthByToken(String authToken) {
		if (StringUtils.isBlank(authToken)) {
			throw new InvalidParameterException("ACCESS_TOKEN_CANNOT_BE_BLANK");
		}

		UserAuth userAuth = repo.findByAuthToken(authToken);
		return userAuth;

	}

}
