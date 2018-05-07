package com.devglan.gatewayservice.constant;

/**
 * @author Rimal
 */
public class Keys {

	public static final String shippingFreeAfter = "shippingFreeAfter";
	public static final String shippingCharges = "shippingCharges";
	public static final String imageDistributionDomainPrefix = "imageDistributionDomainPrefix";
	public static final String imageDistributionDomainSuffix = "imageDistributionDomainSuffix";
	public static final String imageUploads = "imageUploads";
	public static final String noOfImagesInRepositorySubDir = "noOfImagesInRepositorySubDir";

	// cod variables
	public static final String codRoute = "codRoute";
	public static final String codMinAmount = "codMinAmount";
	public static final String codMaxAmount = "codMaxAmount";
	public static final String codCharges = "codCharges";
	public static final String codFreeAfter = "codFreeAfter";
	public static final String thresholdEmi = "thresholdEmi";

	public static final String projectEnv = "project.env";
	public static final String esName = "project.esName";
	public static final String prodEnv = "prod";
	public static final String devEnv = "dev";
	public static final String stagingEnv = "staging";
	public static final String neoEnv = "neo";
	public static final String qaEnv = "qa";

	// email variables
	public static final String hkNoReplyEmail = "hkNoReplyEmail";
	public static final String hkNoReplyName = "hkNoReplyName";
	public static final String hkContactEmail = "hkContactEmail";
	public static final String hkContactName = "hkContactName";
	public static final String hkReplyEmail = "hkReplyEmail";
	public static final String hkReplyName = "hkReplyName";
	public static final String userEmailLinksRedirectDomain = "userEmailLinksRedirectDomain";

	public static final String applicableForRetailFulfillment = "applicableForRetailFulfillment";
	public static final String locationStartTime = "locationStartTime";
	public static final String locationEndTime = "locationEndTime";

	// contact us variables
	public static final String hkCustCareNumber = "hkCustCareNo";
	// header variables
	public static final String hkLogo = "hkLogo";
	public static final String footerLogo = "footerLogo";

	// mailbolt variables
	public static final String notifyMeSecondsToWaitBeforeResendingNotificationForAVariant = "notifyMeSecondsToWaitBeforeResendingNotificationForAVariant";
	public static final String notifyMeBufferRate = "notifyMeBufferRate";
	public static final String notifyMeConversionRate = "notifyMeConversionRate";
	public static final String notifyMeLatestRequestsStillConvertibleThresholdDays = "notifyMeLatestRequestsStillConvertibleThresholdDays";
	public static final String productReminderBufferDays = "productReminderBufferDays";
	public static final String productUpperThresholdReminderDays = "productUpperThresholdReminderDays";
	public static final String productLowerThresholdReminderDays = "productLowerThresholdReminderDays";
	public static final String mailboltBaseUrl = "mailboltBaseUrl";
	public static final String recordDataOnPersona = "recordDataOnPersona";
	public static final String personaServiceBaseUrl = "personaServiceBaseUrl";

	public static final String jarvisBaseUrl = "jarvisBaseUrl";

	// shipping label
	public static final String shippingLabelDir = "shippingLabelDir";

	// pay
	public static final String payNative = "payNative";
	public static final String isJmsOn = "isJmsOn";
	public static final String codCountAllowed = "codCountAllowed";

	// gosf start time
	public static final String gosfStartTime = "gosfStartTime";
	public static final String gosfEndTime = "gosfEndTime";
	public static final String gosfEnabled = "gosfEnabled";

	// cod flip order start and end timings
	public static final String codFlipTillShipOrderStartTime = "codFlipTillShipOrderStartTime";
	public static final String codFlipTillShipOrderEndTime = "codFlipTillShipOrderEndTime";
	public static final String codFlipEnabled = "codFlipEnabled";

	public static final String hKHeaderTabsUrl = "hKHeaderTabsUrl";

	public static final String hkConsultUrl = "hkConsultUrl";

	// connecto/personalization
	public static final String connectoReadKey = "connectoReadKey";
	public static final String noOfDaysForTrendingProducts = "noOfDaysForTrendingProducts";

	// bright
	public static final String brightlifecareRestUrl = "brightlifecareRestUrl";
	public static final String sendOprToBright = "sendOprToBright";

	// contentful
	public static final String contentfulSecretKey = "contentfulSecretKey";
	public static final String contentfulCareerSpaceKey = "contentfulCareerSpaceKey";

	// mobile Signup
	public static final String showMobileVerificationInApp = "showMobileVerificationInApp";

	// third party
	public static final String nudgespotApiBaseUrl = "nudgespotApiBaseUrl";
	public static final String facebookGraphApiBaseUrl = "facebookGraphApiBaseUrl";
	public static final String googleApisBaseUrl = "googleApisBaseUrl";

	public static final String SAP_SECRET_KEY = "SAP_SECRET_KEY";
	public static final String SAP_ACCESS_KEY = "SAP_ACCESS_KEY";
	public static final String awsSqsEndpoint = "awsSqsEndpoint";
	public static final String awsHKMPSalesOrder = "awsHKMPSalesOrder";
	public static final String awsHKB2CSalesOrder = "awsHKB2CSalesOrder";
	public static final String awsBPCMasterQueue = "awsBPCMasterQueue";

	public static final String retailOrderAcceptMailRecepients = "retailOrderAcceptMailRecepients";
	// hkSecurity
	public static final String APPLICATION_ID = "APPLICATION_ID";
	public static final String HKSECURITY_URL = "HKSECURITY_URL";
	public static final String CLIENT_ID = "CLIENT_ID";
	public static final String USE_HKSECURITY = "USE_HKSECURITY";
}
