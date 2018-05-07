package com.devglan.gatewayservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devglan.gatewayservice.pojo.Role;
import com.devglan.gatewayservice.pojo.Store;

public interface StoreRepo extends JpaRepository<Store, Long>{

}
