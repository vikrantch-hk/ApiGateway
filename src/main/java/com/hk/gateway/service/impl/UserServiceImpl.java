package com.hk.gateway.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.gateway.exception.InvalidParameterException;
import com.hk.gateway.pojo.User;
import com.hk.gateway.repo.UserRepo;
import com.hk.gateway.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;

	@Override
	public boolean userHasRole(Long userId, String roleName) {
		if (null == userId) {
			throw new InvalidParameterException("USER_ID_CANNOT_BE_BLANK");
		}
		if (null == roleName) {
			throw new InvalidParameterException("ROLE_NAME_CANNOT_BE_BLANK");
		}

		User user = repo.findById(userId).get();
		Set<String> userRoles = user.getRoleStrings();
		return userRoles != null && userRoles.contains(roleName);
	}

}
