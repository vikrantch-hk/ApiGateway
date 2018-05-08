package com.hk.gateway.service;

import com.hk.gateway.pojo.User;

public interface AgentHasUserService {
	public User getAgentMappedToUser(Long userId, Long mappingTypeId);

}
