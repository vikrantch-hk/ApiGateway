
package com.hk.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hk.gateway.client.SecurityFeignClient;
import com.hk.gateway.response.SecurityResponseObj;

@Service
public class SecurityService {

	private static Logger logger = LoggerFactory.getLogger(SecurityService.class);
	private SecurityResponseObj securityResponseObj;
	@Value("${apiApplicationId}")
	private String apiApplicationId;
	@Value("${useHkSecurity}")
	private String useHkSecurity;

	@Autowired
	private SecurityFeignClient client;

	public void reloadSecurityCacheOnCluster() {
		logger.info("Inside reloadSecurityCacheOnCluster");

		try {
			if (!checkUseHkSecurity()) {
				logger.info("HKSecurity disabled useHkSecurity : " + useHkSecurity);
				return;
			}
			logger.info("Going to fetch Security data");
			SecurityResponseObj response = getAppConfig();

			if (response != null) {
				securityResponseObj = response;
				logger.info("Security data received successfully");

			} else {
				logger.info("No Security data returned!");
			}
		} catch (Exception e) {
			logger.error("Exception while fetching Security data", e);
		}
	}

	@Cacheable("appConfig")
	private SecurityResponseObj getAppConfig() {
		return client.getApplicationConfiguration(apiApplicationId);
	}

	public SecurityResponseObj getSecurityResponseObj() {
		if (securityResponseObj == null) {
			reloadSecurityCacheOnCluster();
		}
		return securityResponseObj;
	}

	public boolean checkUseHkSecurity() {
		if (useHkSecurity.equals("true")) {
			return true;
		} else {
			return false;
		}

	}

}
