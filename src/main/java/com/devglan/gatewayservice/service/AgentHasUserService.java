package com.devglan.gatewayservice.service;

import com.devglan.gatewayservice.pojo.User;

public interface AgentHasUserService {
	public User getAgentMappedToUser(Long userId, Long mappingTypeId);

}
