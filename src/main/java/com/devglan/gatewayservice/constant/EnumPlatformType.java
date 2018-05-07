package com.devglan.gatewayservice.constant;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Abhay Bhatia
 * Date: 11/14/14
 * Time: 1:34 PM
 */
public enum EnumPlatformType {

  DESKTOP(1L, "Desktop", "D"),
  MOBILEWEB(2L, "MobileWeb", "M"),
  ANDROID(3L, "Android", "A"),
  IOS(4L, "iOS", "I");

  private String name;
  private String shortCode;
  private Long id;

  EnumPlatformType(Long id, String name, String shortCode) {
    this.id = id;
    this.name = name;
    this.shortCode = shortCode;
  }

  public static List<Long> getPlatformIdsSupportingPacks() {
    return Arrays.asList(DESKTOP.getId(), MOBILEWEB.getId(), ANDROID.getId(), IOS.getId());
  }

  public static List<Long> getAppPlatFormIds() {
    return Arrays.asList(ANDROID.getId(), IOS.getId());
  }

  public static EnumPlatformType getEnumPlatformTypeById(Long id) {
    if (id == null) {
      return null;
    }
    for (EnumPlatformType enumPlatformType : EnumPlatformType.values()) {
      if (enumPlatformType.getId().equals(id)) {
        return enumPlatformType;
      }
    }
    return null;
  }

  public static EnumPlatformType getEnumPlatformTypeByName(String name) {
    if (StringUtils.isBlank(name)) {
      return null;
    }
    for (EnumPlatformType enumPlatformType : EnumPlatformType.values()) {
      if (enumPlatformType.getName().equalsIgnoreCase(name)) {
        return enumPlatformType;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public Long getId() {
    return id;
  }

  public String getShortCode() {
    return shortCode;
  }
}
