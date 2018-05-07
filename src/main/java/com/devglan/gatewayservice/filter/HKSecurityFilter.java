package com.devglan.gatewayservice.filter;

import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.devglan.gatewayservice.config.SecurityResponseObj;
import com.devglan.gatewayservice.config.SecurityService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class HKSecurityFilter extends ZuulFilter {
	
	private static Logger logger = LoggerFactory.getLogger(HKSecurityFilter.class);
	public static final String AUTHENTICATION_HEADER = "Authorization";
	@Autowired
	private SecurityService securityService;

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	public boolean shouldFilter() {
		return true;
	}

	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		ServletRequest request = context.getRequest();
		//ServletResponse response = context.getResponse();
		if (securityService == null) {
			ServletContext servletContext = request.getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			securityService = webApplicationContext.getBean(SecurityService.class);
		}

		if (request instanceof HttpServletRequest) {

			if (!securityService.checkUseHkSecurity()) {
               return null;
			}
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			String clientId = httpServletRequest.getHeader("clientId");
			String clientIP = httpServletRequest.getHeader("X-FORWARDED-FOR");
			if (clientIP == null || "".equals(clientIP)) {
				clientIP = request.getRemoteAddr();
			}
			String clientAPI = httpServletRequest.getRequestURL().toString();
			String authCredentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);
			if (authCredentials != null) {
				return null;
			}
			SecurityResponseObj securityResponseObj = null;
			securityResponseObj = securityService.getSecurityResponseObj();

			if (securityResponseObj != null) {
				int endIndex = clientAPI.indexOf("?");
				if (endIndex < 0) {
					endIndex = clientAPI.length();
				}
				String clientAPITrimmed = clientAPI.substring(clientAPI.indexOf("/first-service/"), endIndex);
				String clientAPIArray[] = clientAPITrimmed.split("/");
				int clientAPIArrayLength = clientAPIArray.length;
				Map<Integer, Set<String>> nonStarOpenApiMap = (Map<Integer, Set<String>>) securityResponseObj
						.getNonStarOpenApiMap();
				boolean checkNonStarOpenApi = false;
				checkNonStarOpenApi = checkClientApiInNonStarOpenApi(clientId, clientIP, clientAPI, clientAPITrimmed,
						clientAPIArrayLength, nonStarOpenApiMap);

				if (checkNonStarOpenApi) {
					return null;
				}

				Map<Integer, Set<String>> starOpenApiMap = (Map<Integer, Set<String>>) securityResponseObj
						.getStarOpenApiMap();
				boolean checkStarOpenApi = false;
				checkStarOpenApi = checkWithStarOpenApi(clientId, clientIP, clientAPI, clientAPIArray,
						clientAPIArrayLength, starOpenApiMap);
				if (checkStarOpenApi) {
				}

				if (clientId == null) {
					logger.info("CLIENT IP : " + clientIP + "=== clientId is NULL Request UNAUTHORIZED for url : === "
							+ clientAPI);
					setError(context);
					return null;
				}

				Map<String, Set<String>> clientIdAndClientIPsMapping = (Map<String, Set<String>>) securityResponseObj
						.getClientIdAndClientIPsMapping();
				if (clientIdAndClientIPsMapping != null) {
					boolean clientIPcheck = false;
					clientIPcheck = checkClientIP(clientId, clientIP, clientAPI, clientIdAndClientIPsMapping);
					if (clientIPcheck == false) {
						logger.info("Invalid IP : " + clientIP + "=== clientId : " + clientId
								+ "=== clientIP does not exists in security === Request UNAUTHORIZED for url : === "
								+ clientAPI);
						setError(context);
						return null;
					}
				}

				Map<String, Map<Integer, Set<String>>> nonStarClientIdAndClientAPIsMapping = (Map<String, Map<Integer, Set<String>>>) securityResponseObj
						.getNonStarClientIdAndClientAPIsMapping();
				if (nonStarClientIdAndClientAPIsMapping != null && nonStarClientIdAndClientAPIsMapping.size() > 0) {
					boolean checkNonStarInternalAPI = false;
					checkNonStarInternalAPI = checkNonStarInternalAPI(clientId, clientIP, clientAPI, clientAPITrimmed,
							clientAPIArrayLength, nonStarClientIdAndClientAPIsMapping);
					if (checkNonStarInternalAPI) {
						return null;
					}
				}
				Map<String, Map<Integer, Set<String>>> starClientIdAndClientAPIsMapping = (Map<String, Map<Integer, Set<String>>>) securityResponseObj
						.getStarClientIdAndClientAPIsMapping();
				if (starClientIdAndClientAPIsMapping != null && starClientIdAndClientAPIsMapping.size() > 0) {
					boolean checkStarInternalApi = false;
					checkStarInternalApi = checkStarInternalApi(clientId, clientIP, clientAPI, clientAPIArray,
							clientAPIArrayLength, starClientIdAndClientAPIsMapping);
					if (checkStarInternalApi) {
						return null;
					}

				}
				logger.info("clientId : " + clientId + "=== clientIp : " + clientIP
						+ "=== Request UNAUTHORIZED for url : === " + clientAPI);
				setError(context);
				return null;

			} else {
				logger.info("clientId : " + clientId + "=== clientIp : " + clientIP
						+ "=== securityResponseObj is NULL Request UNAUTHORIZED for url : === " + clientAPI);
				setError(context);
				return null;
			}
		}

		return null;
	}
	
	private boolean checkClientApiInNonStarOpenApi(String clientId, String clientIP, String clientAPI,
			String clientAPITrimmed, int clientAPIArrayLength, Map<Integer, Set<String>> nonStarOpenApiMap)
			 {
		if (nonStarOpenApiMap != null && nonStarOpenApiMap.size() > 0) {
			Set<String> nonStarOpenApiSet = nonStarOpenApiMap.get(clientAPIArrayLength);
			if (nonStarOpenApiSet != null && nonStarOpenApiSet.size() > 0
					&& nonStarOpenApiSet.contains(clientAPITrimmed)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkWithStarOpenApi(String clientId, String clientIP, String clientAPI, String[] clientAPIArray,
			int clientAPIArrayLength, Map<Integer, Set<String>> starOpenApiMap)  {
		if (starOpenApiMap != null && starOpenApiMap.size() > 0) {
			Set<String> starOpenApiSet = starOpenApiMap.get(clientAPIArrayLength);
			if (starOpenApiSet != null && starOpenApiSet.size() > 0) {
				int count = 0;
				for (String starOpenApi : starOpenApiSet) {
					String starOpenApiArray[] = starOpenApi.split("/");
					count = 0;
					for (int i = 0; i < clientAPIArrayLength; i++) {
						if (clientAPIArray[i].equalsIgnoreCase(starOpenApiArray[i])
								|| starOpenApiArray[i].equals("*")) {
							count++;
							continue;
						} else {
							break;
						}
					}
					if (count == clientAPIArrayLength) {
					return true;
				}
				}
				
			}
		}
		return false;
	}
	
	private void setError(RequestContext context) throws ZuulException {
		throw new ZuulException("Forbidden", HttpServletResponse.SC_FORBIDDEN, "FORBIDDEN");
	}
	
	private boolean checkClientIP(String clientId, String clientIP, String clientAPI,
			Map<String, Set<String>> clientIdAndClientIPsMapping) {
		Set<String> clientIps = clientIdAndClientIPsMapping.get(clientId);

		String clientIPArray[] = clientIP.split(",");
		int clientIPArrayLength = clientIPArray.length;
		if (clientIps != null && clientIps.size() > 0) {
			for (int i = 0; i < clientIPArrayLength; i++) {
				String clientIP3=clientIPArray[i].trim();
				int endIndex=clientIP3.lastIndexOf(".");
				clientIP3=clientIP3.substring(0, endIndex);
				if (clientIps.contains(clientIP3)) {
					return true;
				}
			}
		}
		return false;

	}
	
	private boolean checkNonStarInternalAPI(String clientId, String clientIP, String clientAPI, String clientAPITrimmed,
			int clientAPIArrayLength, Map<String, Map<Integer, Set<String>>> nonStarClientIdAndClientAPIsMapping)
			{
		Map<Integer, Set<String>> nonStarClientAPIMap = nonStarClientIdAndClientAPIsMapping.get(clientId);
		if (nonStarClientAPIMap != null && nonStarClientAPIMap.size() > 0) {
			Set<String> nonStarClientAPISet = nonStarClientAPIMap.get(clientAPIArrayLength);
			if (nonStarClientAPISet != null && nonStarClientAPISet.size() > 0
					&& nonStarClientAPISet.contains(clientAPITrimmed)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkStarInternalApi(String clientId, String clientIP, String clientAPI, String[] clientAPIArray,
			int clientAPIArrayLength, Map<String, Map<Integer, Set<String>>> starClientIdAndClientAPIsMapping)
			 {
		Map<Integer, Set<String>> starClientAPIMap = starClientIdAndClientAPIsMapping.get(clientId);
		if (starClientAPIMap != null && starClientAPIMap.size() > 0) {
			Set<String> starClientAPISet = starClientAPIMap.get(clientAPIArrayLength);
			if (starClientAPISet == null) {
				return false;
			}
			int count = 0;
			for (String starClientAPI : starClientAPISet) {
				String starClientAPIArray[] = starClientAPI.split("/");
				count = 0;
				for (int i = 0; i < clientAPIArrayLength; i++) {
					if (clientAPIArray[i].equalsIgnoreCase(starClientAPIArray[i])
							|| starClientAPIArray[i].equals("*")) {
						count++;
						continue;
					} else {
						break;
					}
				}
				if (count == clientAPIArrayLength) {
				return true;
			}
			}
			
		}
		return false;
	}

	
}
