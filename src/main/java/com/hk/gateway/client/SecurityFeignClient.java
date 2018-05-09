package com.hk.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.gateway.fallback.HystrixFallbackConfiguration;
import com.hk.gateway.response.SecurityResponseObj;

@FeignClient(name = "security-service", fallback=HystrixFallbackConfiguration.class) // Service Id
public interface SecurityFeignClient {
	@RequestMapping(path = "hkSecurity/getApplicationConfiguration/{applicationId}", produces = "application/json", method = RequestMethod.GET)
	public SecurityResponseObj getApplicationConfiguration(@PathVariable(value = "applicationId") String applicationId);
}
