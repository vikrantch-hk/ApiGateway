package com.devglan.gatewayservice.constant;

/**
 * @author Rimal
 */
public enum ImageAlignmentEnum {

  Left("lt"),
  Center("c");

  private String suffix;

  ImageAlignmentEnum(String suffix) {
    this.suffix = suffix;
  }

  public String getSuffix() {
    return suffix;
  }

}
