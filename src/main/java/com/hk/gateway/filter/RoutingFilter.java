package com.hk.gateway.filter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class RoutingFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		 String requestURI = RequestContext.getCurrentContext().getRequest().getRequestURI();
		 if(requestURI.contains("first-service"))
		return true;
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		 RequestContext ctx = RequestContext.getCurrentContext();
	       // ctx.setSendZuulResponse(false);
	        String modifiedPath = "/hello2";
	        try {
	        	String url = UriComponentsBuilder.fromHttpUrl("https://www.google.com").path("/maps/search/?api=1&parameters").build()
                        .toUriString();
				//ctx.setRouteHost(new URL(url));
				ctx.set("requestURI",url);
				//ctx.setResponseStatusCode(org.apache.http.HttpStatus.SC_USE_PROXY);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       // ctx.put(FilterConstants.REQUEST_URI_KEY, "https://www.google.com/maps/search/?api=1&parameters");
	       /*ctx.put(FilterConstants.PROXY_KEY, "https://www.google.com/maps/search/?api=1&parameters");
	       ctx.setResponseStatusCode(org.apache.http.HttpStatus.SC_USE_PROXY);
	        ctx.getResponse().sendRedirect("https://www.google.com/maps/search/?api=1&parameters");
	        ctx.setRouteHost(new URL("https://www.google.com/maps/search/?api=1&parameters"));*/
	    
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
