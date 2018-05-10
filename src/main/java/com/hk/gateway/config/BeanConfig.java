package com.hk.gateway.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hk.gateway.filter.HKSecurityFilter;
import com.hk.gateway.filter.RoutingFilter;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@Configuration
public class BeanConfig {

	@Bean
	public ServletRegistrationBean hystrixStreamServlet() {
		return new ServletRegistrationBean(new HystrixMetricsStreamServlet(), "/hystrix.stream");
	}

	/*
	 * @Bean public RoutingFilter addRoutingFilter() { return new RoutingFilter(); }
	 */

	@Bean
	public HKSecurityFilter addHKSecurityFilter() {
		return new HKSecurityFilter();
	}

	/*
	 * @Bean public SecureAuthFilter addSecureAuthFilter(){ return new
	 * SecureAuthFilter(); }
	 * 
	 * @Bean public HkHttpRequestFilter addHkHttpRequestFilter(){ return new
	 * HkHttpRequestFilter(); }
	 */}
