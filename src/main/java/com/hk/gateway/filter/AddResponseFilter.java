package com.hk.gateway.filter;

import java.util.ArrayList;
import java.util.List;

import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class AddResponseFilter extends ZuulFilter {

	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 999;
	}

	public boolean shouldFilter() {
		RequestContext context = RequestContext.getCurrentContext();
		if ((context.get("forward.to") != null) && context.get("forward.to").toString().contains("hystrix")) {
			return true;
		}
		return false;
	}

	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		List<Pair<String, String>> filteredResponseHeaders = new ArrayList<>();

		/*
		 * List<Pair<String, String>> zuulResponseHeaders =
		 * context.getZuulResponseHeaders(); if (zuulResponseHeaders != null) { for
		 * (Pair<String, String> it : zuulResponseHeaders) { if
		 * (!it.first().contains("X-")) { Pair<String, String> pair = new
		 * Pair<>(it.first(), it.second()); filteredResponseHeaders.add(pair); } } }
		 */
		Pair<String, String> pair = new Pair<>("Content-Type", "text/event-stream");
		filteredResponseHeaders.add(pair);
		context.put("zuulResponseHeaders", filteredResponseHeaders);

		return null;
	}
}
