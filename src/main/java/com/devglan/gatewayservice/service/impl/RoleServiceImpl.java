package com.devglan.gatewayservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.devglan.gatewayservice.pojo.Role;
import com.devglan.gatewayservice.repo.RoleRepo;
import com.devglan.gatewayservice.service.RoleService;

public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepo repo;

	@Override
	public Role getRoleByName(String roleName) {
		return repo.findById(roleName).get();
	}

}
