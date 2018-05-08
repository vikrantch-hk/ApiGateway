package com.hk.gateway.constant;

/**
 * Created by IntelliJ IDEA. User: Vaibhav Date: Apr 8, 2013 Time: 1:49:21 PM
 */
public class StoreConstants {

	public final static Long DEFAULT_STORE_ID = 1L;
	public final static Long LOYALTY_STORE_ID = 4L;
	public final static Long MUSCLEBLAZE_STORE_ID = 9L;

	public static final String DEFAULT_PASSWORD_SALT = "";
	public static final int DEFAULT_HASH_ITERATIONS = 1;

	public static final Long SYSTEM_USER_ID = -2L;
	public static final String AQUA_VENDOR_CODE = "AQUA";
	public static final Long AQUA_VENDOR_ID = 1L;

	public static final Long DEFAULT_PACK_IMAGE_ID = 120892L;
	public final static Long FITNESPRO_2_STORE_ID = 13L;

	public static final String BRIGHT_VENDOR_CODE = "BLC";
	public static final Long BRIGHT_VENDOR_ID = 291L;

	public static final Long AQUAMARINE_VENDOR_ID = 250L;
	public static final String AQUAMARINE_VENDOR_CODE = "AQHK";

	public static final Long BRIGHT_LIFECARE_VENDOR_ID = 393L;
	public static final String BRIGHT_LIFECARE_VENDOR_CODE = "BLE";

	// emails, sms config will be used for all B2B stores
	public final static Long MASTER_B2B_STORE_ID = 7L; // DELHI CFA

	public final static int MAX_ALLOWED_FAILURE_OTP_ATTEMPTS = 5;

	// added for sending authenticate no change sms only for orders having MB
	// products
	public final static Long MB_BRAND_ID = 539L;

}
