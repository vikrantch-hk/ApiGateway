package com.devglan.gatewayservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devglan.gatewayservice.pojo.Role;
import com.devglan.gatewayservice.pojo.Store;
import com.devglan.gatewayservice.pojo.StoreConfigValues;
import com.devglan.gatewayservice.pojo.User;

public interface UserRepo extends JpaRepository<User, Long>{

}
