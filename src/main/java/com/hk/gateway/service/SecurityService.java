
package com.hk.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hk.gateway.client.SecurityFeignClient;
import com.hk.gateway.response.SecurityResponseObj;

@Service
@EnableScheduling
public class SecurityService {

	private static Logger logger = LoggerFactory.getLogger(SecurityService.class);
	private SecurityResponseObj securityResponseObj;
	// @Value
	private String applicationId = "e7778a70-4d30-11e8-bb97-b7e048038ba3";
	// @Value
	private String useHkSecurity = "true";

	@Autowired
	private SecurityFeignClient client;

	// 5 min fixed delay//initial delay 5s
	@Scheduled(fixedDelay = 5000, initialDelay = 5000)
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
		return client.getApplicationConfiguration(applicationId);
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
