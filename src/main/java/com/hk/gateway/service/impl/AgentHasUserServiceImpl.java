package com.hk.gateway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.gateway.pojo.AgentHasUser;
import com.hk.gateway.pojo.User;
import com.hk.gateway.repo.AgentHasUserRepo;
import com.hk.gateway.repo.UserRepo;
import com.hk.gateway.service.AgentHasUserService;
import com.hk.gateway.service.UserService;

@Service
public class AgentHasUserServiceImpl implements AgentHasUserService {

	@Autowired
	private UserService userService;

	@Autowired
	private AgentHasUserRepo repo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public User getAgentMappedToUser(Long userId, Long mappingTypeId) {
		AgentHasUser ahu;
		if (mappingTypeId != null) {
			ahu = repo.findByUserIdAndTypeAndActive(userId, mappingTypeId, true);
		} else {
			ahu = repo.findByUserIdAndActive(userId, true);
		}
		if (ahu != null) {
			return userRepo.findById(ahu.getAgentId()).get();
		}
		return null;
	}

}
