package com.devglan.gatewayservice.common;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.collections.iterators.IteratorEnumeration;
import org.apache.commons.lang.StringUtils;

import com.devglan.gatewayservice.constant.EnumPlatformType;
import com.devglan.gatewayservice.constant.RequestConstants;

/**
 * Created by mohammed.danish on 11/2/2016.
 */
public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

  private String _body;


  public MultiReadHttpServletRequest(HttpServletRequest request) throws IOException {
    super(request);
    _body = "";
    BufferedReader bufferedReader = request.getReader();
    String line;
    while ((line = bufferedReader.readLine()) != null){
      _body += line;
    }
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(_body.getBytes());
    return new ServletInputStream() {
      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public void setReadListener(ReadListener readListener) {

      }

      public int read() throws IOException {
        return byteArrayInputStream.read();
      }
    };
  }

  @Override
  public BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(this.getInputStream()));
  }

  @Override
  public String getHeader(String name) {
    if(RequestConstants.HEADER_APP_VERSION_ID.equals(name) && StringUtils.isNotBlank(super.getHeader(name))) {
      return getVersionHeader(name);
    }
    return super.getHeader(name);
  }

  @Override
  public Enumeration<String> getHeaders(String name) {
    if(RequestConstants.HEADER_APP_VERSION_ID.equals(name) && StringUtils.isNotBlank(super.getHeader(name))) {
      String versionString = getVersionHeader(name);
        Set<String> versionSet = new HashSet<>();
        versionSet.add(versionString);
        return new IteratorEnumeration(versionSet.iterator());
      }
    return super.getHeaders(name);
  }

  private String getVersionHeader(String name){
    Long platformId = (super.getHeader(RequestConstants.PLATFORM) != null) ? Long.valueOf(super.getHeader(RequestConstants.PLATFORM)) : null;
    if (platformId != null && EnumPlatformType.IOS.getId().equals(platformId)) {
      String versionString = super.getHeader(name);
      if (versionString.contains(".")) {
        versionString = versionString.replace(".", "");
        return versionString;
      }
    }
    return  super.getHeader(name);
  }
}