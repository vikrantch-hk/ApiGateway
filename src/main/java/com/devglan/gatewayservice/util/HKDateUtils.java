package com.devglan.gatewayservice.util;

import java.util.Date;

import org.joda.time.DateTimeZone;

public class HKDateUtils {
	
	/**
	   * @param date this date is always assumed to be in local time -  and no validation is made for that
	   * @return
	   */
	public static Date getGMTDate(Date date) {
	    if (date == null) {
	      return null;
	    }
	    DateTimeZone timezone = DateTimeZone.getDefault();
	    return new Date(timezone.convertLocalToUTC(date.getTime(), false));
	  }

}
