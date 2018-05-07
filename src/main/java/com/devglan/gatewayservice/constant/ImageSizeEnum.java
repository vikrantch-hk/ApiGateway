package com.devglan.gatewayservice.constant;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Apr 10, 2013
 * Time: 1:52:59 AM
 */
public enum ImageSizeEnum {

  DoubleExtraLargeSize(960, "xxl"),
  ExtraLargeSize(640, "xl"),
  LargeSize(480, "l"),
  MediumSize(320, "m"),
  SmallSize(180, "s"),
  ExtraSmallSize(160, "xs"),
  DoubleExtraSmallSize(120, "xxs"),
  TinySize(100, "t"),
  ExtraTinySize(70, "xt"),
  DoubleExtraTinySize(40, "xxt"),
  Original(null, "o");

  private Integer dimension;
  private String suffix;

  ImageSizeEnum(Integer dimension, String suffix) {
    this.dimension = dimension;
    this.suffix = suffix;
  }

  public Integer getDimension() {
    return dimension;
  }

  public String getSuffix() {
    return suffix;
  }

}