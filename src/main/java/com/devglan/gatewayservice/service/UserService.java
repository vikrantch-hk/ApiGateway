package com.devglan.gatewayservice.service;

public interface UserService {
	
	public boolean userHasRole(Long userId, String roleName);

}
