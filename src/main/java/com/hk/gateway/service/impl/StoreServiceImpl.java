package com.hk.gateway.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.gateway.constant.EnumStoreType;
import com.hk.gateway.constant.StoreConfigKeys;
import com.hk.gateway.constant.StoreConstants;
import com.hk.gateway.pojo.Store;
import com.hk.gateway.pojo.StoreConfigValues;
import com.hk.gateway.repo.StoreConfigValuesRepo;
import com.hk.gateway.repo.StoreRepo;
import com.hk.gateway.service.StoreService;

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
					&& !StoreConfigKeys.FALLBACK_LOCATIONS.equals(configKey)
					&& !StoreConfigKeys.DEFAULT_KAA_LOGIN.equals(configKey)) {
				storeId = StoreConstants.MASTER_B2B_STORE_ID;
			}
		}
		StoreConfigValues storeConfigValues = (StoreConfigValues) storeConfigValuesRepo
				.findByConfigKeyAndStoreId(configKey, storeId);
		// added for store id other than 7 and 13 to pick this config key
		if (StringUtils.isEmpty(storeConfigValues.getConfigValue())
				&& StoreConfigKeys.DEFAULT_KAA_LOGIN.equals(configKey)) {
			storeId = StoreConstants.MASTER_B2B_STORE_ID;
			storeConfigValues = (StoreConfigValues) storeConfigValuesRepo.findByConfigKeyAndStoreId(configKey, storeId);
		}
		return storeConfigValues.getConfigValue();
	}

}
