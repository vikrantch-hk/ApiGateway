package com.devglan.gatewayservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devglan.gatewayservice.pojo.Role;

public interface RoleRepo extends JpaRepository<Role, String>{

}
