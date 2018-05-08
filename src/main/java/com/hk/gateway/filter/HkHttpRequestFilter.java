package com.hk.gateway.filter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hk.gateway.constant.RequestConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class HkHttpRequestFilter extends ZuulFilter {

	private static final String DEFAULT_ALLOWED_USER_AGENT = ".*";
	// @Value
	private String allowedUserAgent = "a";
	private static Logger logger = LoggerFactory.getLogger(HkHttpRequestFilter.class);

	public boolean shouldFilter() {
		return true;
	}

	public Object run() throws ZuulException {
		if (allowedUserAgent == null || allowedUserAgent.isEmpty()) {
			allowedUserAgent = DEFAULT_ALLOWED_USER_AGENT;
		}

		RequestContext context = RequestContext.getCurrentContext();
		ServletRequest request = context.getRequest();
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			// logger.info("REQUEST FILTER LOGGER FOR API : " +
			// httpServletRequest.getRequestURL().toString());
			if (!(httpServletRequest.getHeader("User-Agent") != null
					&& !httpServletRequest.getHeader("User-Agent").matches(allowedUserAgent))) {
				String headerUserId = httpServletRequest.getHeader(RequestConstants.USER_ID);
				String platformId = httpServletRequest.getHeader(RequestConstants.PLATFORM);

				if (headerUserId != null && platformId != null) {
					logger.error("NOTALLOWED_USER_AGENT_FROM_CLIENT FOR API : "
							+ httpServletRequest.getRequestURL().toString() + " |  FOR HEADER_USER_ID : " + headerUserId
							+ " | AUTH_USER_ID : " + headerUserId + " | PLATFORM : " + platformId + " | USER AGENT "
							+ httpServletRequest.getHeader("User-Agent"));
				} else {
					logger.error(
							"TOO_MANY_REQUESTS_FROM_CLIENT FOR API : " + httpServletRequest.getRequestURL().toString());
					logger.error("NOTALLOWED_USER_AGENT_FROM_CLIENT FOR API : "
							+ httpServletRequest.getRequestURL().toString() + " | USER AGENT "
							+ httpServletRequest.getHeader("User-Agent"));

				}
				throw new ZuulException("Forbidden", HttpServletResponse.SC_FORBIDDEN, "FORBIDDEN");

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
