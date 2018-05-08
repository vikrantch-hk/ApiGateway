package com.hk.gateway.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hk.gateway.pojo.UserAuth;

public interface UserAuthRepo extends JpaRepository<UserAuth, Long> {
	public UserAuth findByAuthToken(String authToken);
}
