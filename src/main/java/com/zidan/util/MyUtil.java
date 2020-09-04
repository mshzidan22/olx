package com.zidan.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class  MyUtil {

	public static String getTimeFromNow(LocalDateTime adsTime) {
		LocalDateTime timeNow = LocalDateTime.now();
		long year= ChronoUnit.YEARS.between(adsTime, timeNow);
		long month= ChronoUnit.MONTHS.between(adsTime, timeNow);
		long day= ChronoUnit.DAYS.between(adsTime, timeNow);
		long hour= ChronoUnit.HOURS.between(adsTime, timeNow);
	    long min= ChronoUnit.MINUTES.between(adsTime, timeNow);
	    long sec= ChronoUnit.SECONDS.between(adsTime, timeNow);
	    return (year>1)? year + " years ago":(month>1)? month+" months ago":
	    (day>1)? day+" days ago":(hour>1)? hour+" Hours ago":(min>1)? min+" minutes ago" :sec + " second ago";
	}
}
