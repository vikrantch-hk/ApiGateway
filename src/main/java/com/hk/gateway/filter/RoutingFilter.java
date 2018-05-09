package com.hk.gateway.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;

public class RoutingFilter extends ZuulFilter {
	private ProxyRequestHelper helper;

	public RoutingFilter() {
		this(new ProxyRequestHelper());
	}

	public RoutingFilter(ProxyRequestHelper helper) {
		this.helper = helper;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		String requestURI = RequestContext.getCurrentContext().getRequest().getRequestURI();
		if (requestURI.contains("first-service"))
			return true;
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		MultiValueMap<String, String> headers = this.helper.buildZuulRequestHeaders(request);
		MultiValueMap<String, String> params = this.helper.buildZuulRequestQueryParams(request);
		// String verb = getVerb(request);
		InputStream requestEntity = getRequestBody(request);
		HttpClient httpclient = Vertx.factory.vertx().createHttpClient();

		String uri = this.helper.buildZuulRequestURI(request);

		try {
			HttpResponse response = forward(httpclient, "https://maps.googleapis.com/maps/api/js?key=YOUR_KEY&callback=myMap", uri, request, headers, params, requestEntity);
			setResponse(response);
		} catch (Exception ex) {
			context.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			context.set("error.exception", ex);
		}
		return null;
	}
	
	private HttpResponse forward(HttpClient httpclient, String verb, String uri, 
			   HttpServletRequest request, MultiValueMap<String, String> headers, 
			   MultiValueMap<String, String> params, InputStream requestEntity) 
			   throws Exception { 
			  Map<String, Object> info = this.helper.debug(verb, uri, headers, params, 
			    requestEntity); 
			  URL host = RequestContext.getCurrentContext().getRouteHost(); 
			  HttpHost httpHost = getHttpHost(host); 
			  uri = StringUtils.cleanPath(host.getPath() + uri); 
			  HttpRequest httpRequest; 
			  switch (verb.toUpperCase()) { 
			  case "POST": 
			   HttpPost httpPost = new HttpPost(uri + getQueryString()); 
			   httpRequest = httpPost; 
			   httpPost.setEntity(new InputStreamEntity(requestEntity, request 
			     .getContentLength())); 
			   break; 
			  case "PUT": 
			   HttpPut httpPut = new HttpPut(uri + getQueryString()); 
			   httpRequest = httpPut; 
			   httpPut.setEntity(new InputStreamEntity(requestEntity, request 
			     .getContentLength())); 
			   break; 
			  case "PATCH": 
			   HttpPatch httpPatch = new HttpPatch(uri + getQueryString()); 
			   httpRequest = httpPatch; 
			   httpPatch.setEntity(new InputStreamEntity(requestEntity, request 
			     .getContentLength())); 
			   break; 
			  default: 
			   httpRequest = new BasicHttpRequest(verb, uri + getQueryString()); 
			   log.debug(uri + getQueryString()); 
			  } 
			  try { 
			   httpRequest.setHeaders(convertHeaders(headers)); 
			   log.debug(httpHost.getHostName() + " " + httpHost.getPort() + " " 
			     + httpHost.getSchemeName()); 
			   HttpResponse zuulResponse = forwardRequest(httpclient, httpHost, httpRequest); 
			   this.helper.appendDebug(info, zuulResponse.getStatusLine().getStatusCode(), 
			     revertHeaders(zuulResponse.getAllHeaders())); 
			   return zuulResponse; 
			  } 
			  finally { 
			   // When HttpClient instance is no longer needed, 
			   // shut down the connection manager to ensure 
			   // immediate deallocation of all system resources 
			   // httpclient.getConnectionManager().shutdown(); 
			  } 
			 } 
	
	private String getQueryString() throws UnsupportedEncodingException { 
		  HttpServletRequest request = RequestContext.getCurrentContext().getRequest(); 
		  MultiValueMap<String, String> params=helper.buildZuulRequestQueryParams(request); 
		  StringBuilder query=new StringBuilder(); 
		  for (Map.Entry<String, List<String>> entry : params.entrySet()) { 
		   String key=URLEncoder.encode(entry.getKey(), "UTF-8"); 
		   for (String value : entry.getValue()) { 
		    query.append("&"); 
		    query.append(key); 
		    query.append("="); 
		    query.append(URLEncoder.encode(value, "UTF-8")); 
		   } 
		  } 
		  return (query.length()>0) ? "?" + query.substring(1) : ""; 
		 } 
	
	private HttpHost getHttpHost(URL host) { 
		  HttpHost httpHost = new HttpHost(host.getHost(), host.getPort(), 
		    host.getProtocol()); 
		  return httpHost; 
		 } 
		 

	private void setResponse(HttpResponse response) throws IOException {
		this.helper.setResponse(response.getStatusLine().getStatusCode(),
				response.getEntity() == null ? null : response.getEntity().getContent(),
				revertHeaders(response.getAllHeaders()));
	}

	private MultiValueMap<String, String> revertHeaders(Header[] headers) { 
		  MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>(); 
		  for (Header header : headers) { 
		   String name = header.getName(); 
		   if (!map.containsKey(name)) { 
		    map.put(name, new ArrayList<String>()); 
		   } 
		   map.get(name).add(header.getValue()); 
		  } 
		  return map; 
		 } 
	
	private InputStream getRequestBody(HttpServletRequest request) {
		InputStream requestEntity = null;
		try {
			requestEntity = request.getInputStream();
		} catch (IOException ex) {
			// no requestBody is ok.
		}
		return requestEntity;
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
