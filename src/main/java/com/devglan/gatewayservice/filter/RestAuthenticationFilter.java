package com.devglan.gatewayservice.filter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.devglan.gatewayservice.service.AuthenticationService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class RestAuthenticationFilter extends ZuulFilter {

	public static final String AUTHENTICATION_HEADER = "Authorization";

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {

		RequestContext context = RequestContext.getCurrentContext();
		ServletRequest request = context.getRequest();
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			String authCredentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);

			boolean authenticationStatus = authenticationService.authenticate(authCredentials);

			if (!authenticationStatus) {
				throw new ZuulException("UnAuthorized", HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
			}
		}
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
