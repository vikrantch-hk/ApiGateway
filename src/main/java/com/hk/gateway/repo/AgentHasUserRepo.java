package com.hk.gateway.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hk.gateway.pojo.AgentHasUser;

public interface AgentHasUserRepo extends JpaRepository<AgentHasUser, Integer> {

	public AgentHasUser findByUserIdAndTypeAndActive(Long userId, Long type, Boolean active);

	public AgentHasUser findByUserIdAndActive(Long userId, Boolean active);

}
