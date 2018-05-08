package com.hk.gateway.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hk.gateway.pojo.StoreConfigValues;

public interface StoreConfigValuesRepo extends JpaRepository<StoreConfigValues, Long> {

	public StoreConfigValues findByConfigKeyAndStoreId(String configKey, Long storeId);

}
