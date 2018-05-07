package com.devglan.gatewayservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devglan.gatewayservice.pojo.Role;
import com.devglan.gatewayservice.pojo.Store;
import com.devglan.gatewayservice.pojo.StoreConfigValues;

public interface StoreConfigValuesRepo extends JpaRepository<StoreConfigValues, Long>{
	
	public StoreConfigValues findByConfigKeyAndStoreId(String configKey, Long storeId);

}
