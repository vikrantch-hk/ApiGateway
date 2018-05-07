package com.devglan.gatewayservice.response;

import com.devglan.gatewayservice.constant.DtoJsonConstants;
import com.devglan.gatewayservice.dto.UserCouponDto;
import com.devglan.gatewayservice.dto.UserSubscriptionBooleanDTO;
import com.devglan.gatewayservice.pojo.Permission;
import com.devglan.gatewayservice.pojo.Role;
import com.devglan.gatewayservice.pojo.User;
import com.devglan.gatewayservice.service.RoleService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Apr 25, 2013
 * Time: 5:56:08 PM
 */
public class UserResponse extends AbstractBaseResponse {
	
	@Autowired
	private RoleService roleService;

  protected Long id;
  protected String login;
  protected String email;
  protected String name;
  protected Date birthDate;
  protected String gender;

  protected Set<String> roles = new HashSet<String>();
  protected Set<String> permissions = new HashSet<String>();
  protected List<UserCouponDto> userCoupons = new ArrayList<UserCouponDto>();

  protected Boolean smsSubscribed;
  protected Boolean emailSubscribed;

  protected String contactNumber;
  protected boolean isNumberVerified;
  protected boolean showGBJProfile;
  protected boolean isOtpValid;
  protected Long goalId;

  protected String cityNameOfUser;

  public UserResponse() {
  }

  public UserResponse(Long storeId) {
    super(storeId);
  }

  public UserResponse(User user, Long storeId) {
    super(storeId);
    this.id = user.getId();
    this.login = user.getLogin();
    this.email = user.getEmail();
    this.name = user.getName();
    this.birthDate = user.getBirthDate();
    this.gender = user.getGender();
    this.roles = user.getRoleStrings();

    UserSubscriptionBooleanDTO userSubscriptionBooleanDTO = new UserSubscriptionBooleanDTO(user.getSubscribedMask());
    this.smsSubscribed = userSubscriptionBooleanDTO.getSmsSubscribed();
    this.emailSubscribed = userSubscriptionBooleanDTO.getEmailSubScribed();

    for (String roleStr : roles) {
      Role roleVO = roleService.getRoleByName(roleStr);
      for (Permission permissionVO : roleVO.getPermissions()) {
        permissions.add(permissionVO.getName());
      }
    }

    this.contactNumber = user.getContactNumber();
    this.isNumberVerified = user.isNumberVerified();
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Long getGoalId() {
    return goalId;
  }

  public void setGoalId(Long goalId) {
    this.goalId = goalId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Set<String> getRoles() {
    return roles;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }

  public Boolean isSmsSubscribed() {
    return smsSubscribed;
  }

  public void setSmsSubscribed(Boolean smsSubscribed) {
    this.smsSubscribed = smsSubscribed;
  }

  public Boolean isEmailSubscribed() {
    return emailSubscribed;
  }

  public void setEmailSubscribed(Boolean emailSubscribed) {
    this.emailSubscribed = emailSubscribed;
  }

  public List<UserCouponDto> getUserCoupons() {
    return userCoupons;
  }

  public void setUserCoupons(List<UserCouponDto> userCoupons) {
    this.userCoupons = userCoupons;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public boolean isNumberVerified() {
    return isNumberVerified;
  }

  public void setIsNumberVerified(boolean isNumberVerified) {
    this.isNumberVerified = isNumberVerified;
  }

  public boolean isShowGBJProfile() {
    return showGBJProfile;
  }

  public void setShowGBJProfile(boolean showGBJProfile) {
    this.showGBJProfile = showGBJProfile;
  }

  public boolean isOtpValid() {
    return isOtpValid;
  }

  public void setOtpValid(boolean isOtpValid) {
    this.isOtpValid = isOtpValid;
  }

    public String getCityNameOfUser() {
        return cityNameOfUser;
    }

    public void setCityNameOfUser(String cityNameOfUser) {
        this.cityNameOfUser = cityNameOfUser;
    }

  @Override
  protected List<String> getKeys() {
    List<String> keyList = super.getKeys();
    keyList.add(DtoJsonConstants.ID);
    keyList.add(DtoJsonConstants.EMAIL);
    keyList.add(DtoJsonConstants.NAME);
    keyList.add(DtoJsonConstants.LOGIN);
    keyList.add(DtoJsonConstants.BIRTH_DATE);
    keyList.add(DtoJsonConstants.GENDER);
    keyList.add(DtoJsonConstants.ROLES);
    keyList.add(DtoJsonConstants.PERMISSIONS);
    keyList.add(DtoJsonConstants.USER_COUPONS);
    keyList.add(DtoJsonConstants.SMS_SUBSCRIBED);
    keyList.add(DtoJsonConstants.EMAIL_SUBSCRIBED);
    keyList.add(DtoJsonConstants.CONTACT_NUMBER);
    keyList.add(DtoJsonConstants.IS_NUMBER_VERIFIED);
    keyList.add(DtoJsonConstants.PASSWORD);
    keyList.add(DtoJsonConstants.SHOW_GBJ_PROFILE);
    keyList.add(DtoJsonConstants.VALID_OTP);
    keyList.add(DtoJsonConstants.GOAL_ID);
    keyList.add(DtoJsonConstants.CITY_NAME);
    return keyList;
  }

  @Override
  protected List<Object> getValues() {
    List<Object> valueList = super.getValues();
    valueList.add(this.id);
    valueList.add(this.email);
    valueList.add(this.name);
    valueList.add(this.login);
    valueList.add(this.birthDate);
    valueList.add(this.gender);
    valueList.add(this.roles);
    valueList.add(this.permissions);
    valueList.add(this.userCoupons);
    valueList.add(this.smsSubscribed);
    valueList.add(this.emailSubscribed);
    valueList.add(this.contactNumber);
    valueList.add(this.isNumberVerified);
    valueList.add(null); // don't change this value -- password should not be passed in response -- this is only to give support to older version of android/ios
    valueList.add(this.showGBJProfile);
    valueList.add(this.isOtpValid);
    valueList.add(this.goalId);
    valueList.add(this.cityNameOfUser);
    return valueList;
  }


}
