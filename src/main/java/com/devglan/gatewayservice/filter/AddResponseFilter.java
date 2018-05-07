package com.devglan.gatewayservice.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.util.StreamUtils;

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
		return true;
	}

	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		InputStream stream = context.getResponseDataStream();
		String body;
		/*try {
			body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
		//	context.setResponseBody("Modified via setResponseBody(): " + body);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		return null;
	}
}
