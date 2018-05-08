package com.hk.gateway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hk.gateway.pojo.Role;
import com.hk.gateway.repo.RoleRepo;
import com.hk.gateway.service.RoleService;

public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepo repo;

	@Override
	public Role getRoleByName(String roleName) {
		return repo.findById(roleName).get();
	}

}
