package com.devglan.gatewayservice.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devglan.gatewayservice.constant.EnumStoreType;
import com.devglan.gatewayservice.constant.StoreConfigKeys;
import com.devglan.gatewayservice.constant.StoreConstants;
import com.devglan.gatewayservice.pojo.Store;
import com.devglan.gatewayservice.pojo.StoreConfigValues;
import com.devglan.gatewayservice.repo.StoreConfigValuesRepo;
import com.devglan.gatewayservice.repo.StoreRepo;
import com.devglan.gatewayservice.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepo repo;
	@Autowired
	private StoreConfigValuesRepo storeConfigValuesRepo;
	
	@Override
	public String getStoreConfigValue(Long storeId, String configKey) {

		Store store = repo.findById(storeId).get();
		if (EnumStoreType.CFA.getId().equals(store.getStoreType())) {
			if (!StoreConfigKeys.STORE_LOCATION_CODE.equals(configKey)
					&& !StoreConfigKeys.FALLBACK_LOCATIONS.equals(configKey) && !StoreConfigKeys.DEFAULT_KAA_LOGIN.equals(configKey)) {
				storeId = StoreConstants.MASTER_B2B_STORE_ID;
			}
		}
		StoreConfigValues storeConfigValues = (StoreConfigValues) storeConfigValuesRepo.findByConfigKeyAndStoreId(configKey, storeId);
		//added for store id other than 7 and 13 to pick this config key
		if(StringUtils.isEmpty(storeConfigValues.getConfigValue()) && StoreConfigKeys.DEFAULT_KAA_LOGIN.equals(configKey)){
			storeId = StoreConstants.MASTER_B2B_STORE_ID;
			storeConfigValues = (StoreConfigValues) storeConfigValuesRepo.findByConfigKeyAndStoreId(configKey, storeId);
		}
		return storeConfigValues.getConfigValue();
	}

}
