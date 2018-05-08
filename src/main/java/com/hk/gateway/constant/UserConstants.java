package com.hk.gateway.constant;

/**
 * @author Rimal
 */
public class UserConstants {

	public static final int ACTIVATION_LINK_EXPIRY_DAYS = 100;
	public static final int FORGOT_PASSWORD_LINK_EXPIRY_DAYS = 1;
	public static final int MAX_USER_NAME_LIMIT = 80;

	// prime numbers for bool bitset
	public static final int SUBSCRIBE_SMS = 2;
	public static final int SUBSCRIBE_EMAIL = 3;

	// Regex for masking email
	public static final String MASK_LOGIN_REGEX = "(?<=.{3}).(?=[^@]*?.{1}@)";
	public static final String MASK_LOGIN_SUBSTITUTION = "x";

	public static final Long DEFAULT_AUTH_EXPIRATION_DAYS = 365L;

	// for default user name
	public static final String USER = "User";
}
