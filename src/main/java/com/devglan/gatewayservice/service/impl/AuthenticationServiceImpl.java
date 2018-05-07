package com.devglan.gatewayservice.service.impl;

import java.util.StringTokenizer;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.devglan.gatewayservice.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Value("${third.party.api.access.user.name}")
	private String apiAccessUserName;

	@Value("${third.party.api.access.password}")
	private String apiAccessPassword;

	public boolean authenticate(String authCredentials) {

		if (null == authCredentials)
			return false;
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = DatatypeConverter.parseBase64Binary(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");

			final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
			final String username = tokenizer.nextToken();
			final String password = tokenizer.nextToken();
			return apiAccessUserName.equals(username) && apiAccessPassword.equals(password);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return false;

	}

}
