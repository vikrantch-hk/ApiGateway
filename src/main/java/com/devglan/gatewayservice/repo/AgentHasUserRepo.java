package com.devglan.gatewayservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devglan.gatewayservice.pojo.AgentHasUser;
import com.devglan.gatewayservice.pojo.Role;
import com.devglan.gatewayservice.pojo.Store;
import com.devglan.gatewayservice.pojo.StoreConfigValues;
import com.devglan.gatewayservice.pojo.User;

public interface AgentHasUserRepo extends JpaRepository<AgentHasUser, Integer>{
	
	public AgentHasUser findByUserIdAndTypeAndActive(Long userId, Long type, Boolean active);
	
	public AgentHasUser findByUserIdAndActive(Long userId, Boolean active);

}
