package com.hk.gateway.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA. User: Vaibhav Date: May 30, 2013 Time: 2:08:15 PM
 */
public class RoleConstants {

	public static final String GUEST_USER = "GUEST_USER";
	public static final String GUEST_NUMBER_VERIFIED = "GUEST_NUMBER_VERIFIED";
	public static final String GUEST_NUMBER_UNVERIFIED = "GUEST_NUMBER_UNVERIFIED";
	public static final String HK_MERGED_USER = "HK_MERGED_USER";
	public static final String TEMP_USER = "TEMP_USER";
	public static final String HK_USER = "HK_USER";
	public static final String HK_UNVERIFIED = "HKUNVERIFIED";
	public static final String HK_DEACTIVATED = "HKDEACTIVE";
	public static final String HK_LOYALTY_USER = "HK_LOYALTY_USER";
	public static final String HK_DELETED = "HKDELETE";
	public static final String HK_BLOCKED = "HKBLOCKED";
	public static final String COD_BLOCKED = "CODBLOCKED";
	public static final String NEFT_USER = "NEFT_USER";
	public static final String B2B_MANAGER = "B2B_MANAGER";
	public static final String HK_CFA_USER = "HK_CFA_USER";
	public static final String EXPERT_BUYER = "EXPERT_BUYER";
	// new role for cfa store user
	public static final String HK_CFA_STORE_USER = "HK_CFA_STORE_USER";

	// for CFA
	public static final String ME = "ME";
	public static final String KAA = "KAA";
	public static final String RSM = "RSM";
	public static final String B2B_UNVERIFIED = "B2B_UNVERIFIED";
	public static final String B2B_EMPLOYEE = "B2B_EMPLOYEE";
	public static final String B2B_CS = "B2B_CS";
	public static final String B2B_BLOCKED_USER = "B2B_BLOCKED_USER";
	public static final String B2B_DUMMY_VERIFIED = "B2B_DUMMY_VERIFIED";

	// for event
	public static final String EVENT_MANAGER = "EVENT_MANAGER";
	public static final String EVENT_UNVERIFIED = "EVENT_UNVERIFIED";

	public static final String PHYSICAL_STORE_SS = "A. Physical Store SS";
	public static final String FREELANCER_SS = "B. Freelancer SS";
	public static final String GYM_OWNER_STOCKS = "C. Gym Owner-Stocks";
	public static final String TRAINER_STOCKS_SELLS = "D. Trainer- Stocks + Sells";
	public static final String TRAINER_ONLY_SELLS = "E. Trainer- Only Sells";
	public static final String SPORTS_GOODS_SHOP = "F. Sports goods shop";
	public static final String MEDICAL_OUTLETS = "G. Medical Outlets";
	public static final String SELF_SERVICE_MT_DEPARTMENTAL = "H. Self Service MT/Departmental";
	public static final String SIS_USER = "I. SIS User";
	public static final String STORE_MANAGER = "STORE_MANAGER";

	/* Return B2B user roles */
	public static List<String> getB2BAllowedUserRoles() {
		List<String> allowedRoles = new ArrayList<String>(0);
		allowedRoles.add(PHYSICAL_STORE_SS);
		allowedRoles.add(FREELANCER_SS);
		allowedRoles.add(GYM_OWNER_STOCKS);
		allowedRoles.add(TRAINER_STOCKS_SELLS);
		allowedRoles.add(TRAINER_ONLY_SELLS);
		allowedRoles.add(SPORTS_GOODS_SHOP);
		allowedRoles.add(MEDICAL_OUTLETS);
		allowedRoles.add(SELF_SERVICE_MT_DEPARTMENTAL);
		allowedRoles.add(SIS_USER);
		allowedRoles.add(B2B_UNVERIFIED);
		allowedRoles.add(EVENT_UNVERIFIED);
		return allowedRoles;
	}

	public static List<String> getB2BUserRoles() {
		List<String> b2bRoles = new ArrayList<String>();
		b2bRoles.add("'" + RoleConstants.PHYSICAL_STORE_SS + "'");
		b2bRoles.add("'" + RoleConstants.FREELANCER_SS + "'");
		b2bRoles.add("'" + RoleConstants.GYM_OWNER_STOCKS + "'");
		b2bRoles.add("'" + RoleConstants.TRAINER_STOCKS_SELLS + "'");
		b2bRoles.add("'" + RoleConstants.TRAINER_ONLY_SELLS + "'");
		b2bRoles.add("'" + RoleConstants.SPORTS_GOODS_SHOP + "'");
		b2bRoles.add("'" + RoleConstants.MEDICAL_OUTLETS + "'");
		b2bRoles.add("'" + RoleConstants.SELF_SERVICE_MT_DEPARTMENTAL + "'");
		return b2bRoles;
	}

	public static List<String> getB2BTrainerRoles() {
		List<String> b2bRoles = new ArrayList<String>();
		b2bRoles.add(RoleConstants.GYM_OWNER_STOCKS);
		b2bRoles.add(RoleConstants.TRAINER_STOCKS_SELLS);
		b2bRoles.add(RoleConstants.TRAINER_ONLY_SELLS);
		return b2bRoles;
	}

	public static List<String> getB2BSSRoutingEnabledRolesForSelfOrderPlacement() {
		List<String> b2bRoles = new ArrayList<String>();
		b2bRoles.add(RoleConstants.TRAINER_STOCKS_SELLS);
		b2bRoles.add(RoleConstants.TRAINER_ONLY_SELLS);
		return b2bRoles;
	}

	public static List<String> getB2BSsRoles() {
		List<String> b2bRoles = new ArrayList<String>();
		b2bRoles.add(RoleConstants.PHYSICAL_STORE_SS);
		b2bRoles.add(RoleConstants.FREELANCER_SS);
		return b2bRoles;
	}

	/* Return B2B Sales user roles */
	public static List<String> getB2BSalesRoles() {
		List<String> allowedRoles = new ArrayList<String>(0);
		allowedRoles.add(RSM);
		allowedRoles.add(KAA);
		allowedRoles.add(ME);
		allowedRoles.add(B2B_CS);
		allowedRoles.add(B2B_EMPLOYEE);
		return allowedRoles;
	}

	public static List<String> getTrainerSelfSignUpRoles() {
		List<String> trainerRoles = new ArrayList<String>();
		trainerRoles.add(B2B_DUMMY_VERIFIED);
		trainerRoles.add(TRAINER_ONLY_SELLS);
		return trainerRoles;
	}
}
