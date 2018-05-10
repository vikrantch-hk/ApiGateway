package com.hk.gateway.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hk.gateway.common.MultiReadHttpServletRequest;
import com.hk.gateway.constant.RequestConstants;
import com.hk.gateway.constant.RoleConstants;
import com.hk.gateway.pojo.User;
import com.hk.gateway.pojo.UserAuth;
import com.hk.gateway.security.Crypt;
import com.hk.gateway.service.AgentHasUserService;
import com.hk.gateway.service.SSOService;
import com.hk.gateway.service.UserService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * this filer checks if the request is authenticated or not there are 2 headers
 * - authorization and user id authorization is a combination of access token
 * and user id (encrypted) user id is the id of the user for which the data is
 * being requested/updated there are 3 checks in this filter - matching
 * authorization header ..i.e., matching access token with user 2nd check - user
 * id in authorization header matches with the user id in user id header - this
 * user id will be used to fetch/update all data related to user 3rd check - if
 * request method is POST, compare body-userId and authorization-userId
 */
public class SecureAuthFilter extends ZuulFilter {
	public static final String AUTHENTICATION_HEADER = "Authorization";

	public static final String UNAUTH_MSG_TEMPLETE = "REQUEST UNAUTHORIZED FOR USER ";

	private static Logger logger = LoggerFactory.getLogger(SecureAuthFilter.class);

	@Autowired
	private SSOService ssoService;
	@Autowired
	private UserService userService;
	@Autowired
	private AgentHasUserService agentHasUserService;

	public boolean shouldFilter() {
		String requestURI = RequestContext.getCurrentContext().getRequest().getRequestURI();
		return (requestURI.contains("/api/cart/v3/") || requestURI.contains("/api/user/v3/")
				|| requestURI.contains("/api/search/v3/") || requestURI.contains("/api/event/v3/")
				|| requestURI.contains("/api/b2b/user/v3/") || requestURI.contains("/api/user/account/v3/")
				|| requestURI.contains("/api/user/address/v3/") || requestURI.contains("/api/payment/v3/")
				|| requestURI.contains("/api/form/data/v3/") || requestURI.contains("/api/header/v3/")
				|| requestURI.contains("/api/app/v3/") || requestURI.contains("/api/order/v3/")
				|| requestURI.contains("/api/usergoal/v3/") || requestURI.contains("/api/userPoints/reward/v3/")
				|| requestURI.contains("/api/user/counselling/v3/") || requestURI.contains("/api/gym/v3/"));
	}

	public Object run() throws ZuulException {

		RequestContext context = RequestContext.getCurrentContext();
		ServletRequest request = context.getRequest();
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			MultiReadHttpServletRequest wrappedRequest = null;
			try {
				wrappedRequest = new MultiReadHttpServletRequest(httpServletRequest);
			} catch (IOException e1) {
				logger.error(" exception in MultiReadHttpServletRequest " + e1);
			}
			String authCredentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);
			String headerUserId = httpServletRequest.getHeader(RequestConstants.USER_ID);
			String platformId = httpServletRequest.getHeader(RequestConstants.PLATFORM);
			String version = httpServletRequest.getHeader(RequestConstants.HEADER_APP_VERSION_ID);

			// check if the auth token user mapping is correct
			boolean primaryCheck = false;
			// check if auth token and user header has not been manipulated
			boolean secondaryCheck = false;
			// check if body-userId is same as header-Auth-userId
			boolean userIdCheck = false;
			boolean authTokenExpired = false;
			Long userId = null;
			UserAuth userAuth = null;
			if (authCredentials != null && !authCredentials.equalsIgnoreCase("null") && headerUserId != null) {
				String authToken = null;
				try {
					authCredentials = Crypt.decrypt(authCredentials);
					userId = Long.parseLong(authCredentials.split("!!")[0]);
					authToken = authCredentials.split("!!")[1];
				} catch (Exception ex) {
					logger.error("Exception while decrypting auth header" + ex);
				}

				if (userId != null && authToken != null) {
					userAuth = ssoService.getUserAuthByToken(authToken);
					User agent = null;
					if (userAuth != null) {
						if (userAuth.getExpiryDate().after(new Date())) {
							Long userAuthUserId = userAuth.getUserId();
							if (userAuthUserId.equals(userId)) {
								primaryCheck = true;
							} else {
								agent = agentHasUserService.getAgentMappedToUser(userId, null);
								if (agent != null && agent.getId().equals(userAuthUserId)) {
									primaryCheck = true;
								} else {
									boolean isB2BEmployee = userService.userHasRole(userId, RoleConstants.B2B_EMPLOYEE);
									if (isB2BEmployee) {
										primaryCheck = true;
									}
								}
							}
						} else {
							authTokenExpired = true;
						}
					}
					if (primaryCheck) {
						if (String.valueOf(userId).equals(headerUserId)) {
							secondaryCheck = true;
						} else {
							if (agent == null) {
								agent = agentHasUserService.getAgentMappedToUser(Long.parseLong(headerUserId), null);
							}
							if (agent != null && agent.getId().equals(userId)) {
								secondaryCheck = true;
							} else {
								boolean isB2BEmployee = userService.userHasRole(userId, RoleConstants.B2B_EMPLOYEE);
								if (isB2BEmployee) {
									secondaryCheck = true;
								}
							}
						}
					}

					// for post request, check the userId body param with userId header param
					if (primaryCheck && secondaryCheck && httpServletRequest.getMethod().equalsIgnoreCase("POST")) {
						httpServletRequest = wrappedRequest;
						try {
							userIdCheck = compareHeaderUIdAndBodyUId(userId, httpServletRequest);
						} catch (Exception e) {
							logger.error("Error while checking body-userId with header-auth-userId for :" + userId);
						}
					} else if (httpServletRequest.getMethod().equalsIgnoreCase("GET")) {
						userIdCheck = true;
					}
				}
			}

			if (primaryCheck && secondaryCheck && userIdCheck) {
			} else {
				String COMMON_LOGS = " | For API : " + httpServletRequest.getRequestURL().toString()
						+ " | HEADER_USER_ID : " + headerUserId + " | AUTH_USER_ID : " + userId + " | PLATFORM : "
						+ platformId + "| APP_VERSION : " + version;

				if (!primaryCheck) {
					if (authTokenExpired) {
						logger.error(UNAUTH_MSG_TEMPLETE + " | USER_AUTH_TOKEN_EXPIRED | AUTH_TOKEN_EXPIRY DATE : "
								+ userAuth.getExpiryDate() + COMMON_LOGS);
					} else {
						logger.error(UNAUTH_MSG_TEMPLETE + " | USER_AUTH_MISMATCH | USER_AUTH_TOKEN_FROM_CLIENT : "
								+ authCredentials + COMMON_LOGS);
					}
				} else if (!secondaryCheck) {
					logger.error(UNAUTH_MSG_TEMPLETE + " | AUTH_USER_ID & HEADER_USER_ID MISMATCH  " + COMMON_LOGS);
				} else {
					try {
						logger.error(UNAUTH_MSG_TEMPLETE + "| BODY_USER_ID & HEADER_USER_ID MISMATCH  | BODY : "
								+ IOUtils.toString(httpServletRequest.getInputStream()) + COMMON_LOGS);
					} catch (IOException e) {
						logger.error(" exception in IOUtils.toString " + e);
					}
				}
				throw new ZuulException("Unauthorized", HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
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

	private boolean compareHeaderUIdAndBodyUId(Long headerUserId, HttpServletRequest httpServletRequest)
			throws IOException, JSONException {
		String httpRequestBody = IOUtils.toString(httpServletRequest.getInputStream());
		String bodyUserId = null;
		try {
			JSONObject jsonObject = new JSONObject(httpRequestBody);
			bodyUserId = jsonObject.get("userId").toString();
		} catch (Exception e) {
			logger.error(UNAUTH_MSG_TEMPLETE
					+ " | NOT GETTING USER_ID IN POST BODY ERROR WHILE PARSING REQUEST | BODY : " + httpRequestBody
					+ " | For API : " + httpServletRequest.getRequestURL() + "  For User-Id[HEADER] : " + headerUserId);
		}
		if (bodyUserId != null) {
			if (String.valueOf(headerUserId).equals(bodyUserId)) {
				return true;
			} else {
				User agent = agentHasUserService.getAgentMappedToUser(Long.parseLong(bodyUserId), null);
				return agent != null && agent.getId().equals(headerUserId)
						|| userService.userHasRole(headerUserId, RoleConstants.B2B_EMPLOYEE);
			}
		}
		return false;
	}

}
