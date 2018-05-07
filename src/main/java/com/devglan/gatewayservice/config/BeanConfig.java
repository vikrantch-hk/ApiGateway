package com.devglan.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devglan.gatewayservice.filter.AddResponseFilter;
import com.devglan.gatewayservice.filter.HKSecurityFilter;
import com.devglan.gatewayservice.filter.HkHttpRequestFilter;
import com.devglan.gatewayservice.filter.SecureAuthFilter;

@Configuration
public class BeanConfig {

    @Bean
    public AddResponseFilter addRequestHeaderFilter(){
        return new AddResponseFilter();
    }
    
    @Bean
    public HKSecurityFilter addHKSecurityFilter(){
        return new HKSecurityFilter();
    }
    
    @Bean
    public SecureAuthFilter addSecureAuthFilter(){
        return new SecureAuthFilter();
    }
    
    @Bean
    public HkHttpRequestFilter addHkHttpRequestFilter(){
        return new HkHttpRequestFilter();
    }
}
