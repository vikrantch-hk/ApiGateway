package com.devglan.gatewayservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.devglan.gatewayservice.pojo.AgentHasUser;
import com.devglan.gatewayservice.pojo.User;
import com.devglan.gatewayservice.repo.AgentHasUserRepo;
import com.devglan.gatewayservice.repo.UserRepo;
import com.devglan.gatewayservice.service.AgentHasUserService;
import com.devglan.gatewayservice.service.UserService;


@Service
public class AgentHasUserServiceImpl implements AgentHasUserService{

	@Autowired
	private UserService userService;

	@Autowired
	private AgentHasUserRepo repo;
	
	@Autowired
	private UserRepo userRepo;


	@Override
	public User getAgentMappedToUser(Long userId, Long mappingTypeId){
		AgentHasUser ahu;
		if (mappingTypeId != null) {
			ahu = repo.findByUserIdAndTypeAndActive(userId, mappingTypeId,true);
		} else {
			ahu = repo.findByUserIdAndActive(userId, true);
		}
		if (ahu != null) {
			return userRepo.findById(ahu.getAgentId()).get();
		}
		return null;
	}

}
