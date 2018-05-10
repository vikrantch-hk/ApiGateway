package com.hk.gateway.filter;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

// this filter is kept for future use
public class RoutingFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(RoutingFilter.class);

	@Override
	public boolean shouldFilter() {
		String requestURI = RequestContext.getCurrentContext().getRequest().getRequestURI();
		if (requestURI.contains("first-service"))
			return true;
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		try {
			String url = UriComponentsBuilder.fromHttpUrl("https://www.google.com")
					.path("/maps/search/?api=1&parameters").build().toUriString();
			ctx.setRouteHost(new URL(url));
		} catch (Exception e) {
			logger.error(" exception in RoutingFilter ");
		}
		return null;
	}

	@Override
	public String filterType() {
		return "route";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
