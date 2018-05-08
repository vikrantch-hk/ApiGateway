package com.hk.gateway.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hk.gateway.pojo.Role;

public interface RoleRepo extends JpaRepository<Role, String> {

}
