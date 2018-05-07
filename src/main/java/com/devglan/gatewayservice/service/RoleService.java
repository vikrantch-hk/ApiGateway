package com.devglan.gatewayservice.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.devglan.gatewayservice.pojo.Role;
import com.devglan.gatewayservice.repo.RoleRepo;

public interface RoleService {

	
	public Role getRoleByName(String roleName);
}
