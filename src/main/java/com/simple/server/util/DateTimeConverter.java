package com.simple.server.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

import com.simple.server.config.AppConfig;


public class DateTimeConverter {
		
	public final static String NAV_DEFAULT_DATE = "1753-01-01 00:00:00";
	public final static String NAV_DEFAULT_TIME = "000000";
		
	private static final DateTimeFormatter DATE_FORMATTER =  
		    new DateTimeFormatterBuilder()
		        .append(null, new DateTimeParser[]{
		                DateTimeFormat.forPattern("dd/MM/yyyy").getParser(),
		                DateTimeFormat.forPattern("dd.MM.yyyy").getParser(),
		                DateTimeFormat.forPattern("dd-MM-yyyy").getParser(),
		                DateTimeFormat.forPattern("yyyy/MM/dd").getParser(),
		                DateTimeFormat.forPattern("yyyy.MM.dd").getParser(),
		                DateTimeFormat.forPattern("yyyy-MM-dd").getParser(),
		                DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss").getParser(),
		                DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss").getParser(),
		                DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").getParser(),
		                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss").getParser(),
		                DateTimeFormat.forPattern("yyyy.MM.dd HH:mm:ss").getParser(),
		                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").getParser(),
		                DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss.SSSz").getParser(),
		                DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss.SSSz").getParser(),		                										   
		                DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss.SSSz").getParser(),
		                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss.SSSz").getParser(),
		                DateTimeFormat.forPattern("yyyy.MM.dd HH:mm:ss.SSSz").getParser(),
		                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSz").getParser(),
		                DateTimeFormat.forPattern("HH:mm:ss").getParser(),
		                DateTimeFormat.forPattern("HHmmss").getParser(),
		                DateTimeFormat.forPattern("HH-mm-ss").getParser()		                		                
		        })
		        .toFormatter();
		
	
	public static Date add(Date since, int days, int hours, int minutes){
		Calendar cal = Calendar.getInstance();
		cal.setTime(since);
		if(days != 0)
			cal.add(Calendar.DAY_OF_MONTH, days);
		if(hours != 0)
			cal.add(Calendar.HOUR, hours);
		if(minutes != 0)
			cal.add(Calendar.MINUTE, minutes);
		Date dt = cal.getTime();
		return dt;
	}
	
	public static String getCurDate(){		
		return new SimpleDateFormat(AppConfig.DATEFORMAT).format(Calendar.getInstance().getTime());
	}
	
	
	public static String format(DateTime date){
		return new SimpleDateFormat(AppConfig.DATEFORMAT).format(date);
	}
	
	public static String add2CurDate(int days, int hours, int minutes){	
		Calendar cal = Calendar.getInstance();		
		cal.add(Calendar.DAY_OF_MONTH, days);
		cal.add(Calendar.HOUR_OF_DAY, hours);
		cal.add(Calendar.MINUTE, minutes);
		
		return new SimpleDateFormat(AppConfig.SIMPLE_DATEFORMAT).format(cal.getTime());
	}
	
	public static String dateToNavFormat(String sDate){
		LocalDate localDate = DATE_FORMATTER.parseLocalDate(sDate);
		DateTime dateTime = new DateTime(localDate.getYear(),localDate.getMonthOfYear(),localDate.getDayOfMonth(),0,0,0);		
		return dateTime.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));				
	}
	
	public static String timeToNavFormat(String sTime){
		LocalTime localTime = DATE_FORMATTER.parseLocalTime(sTime);
		DateTime dateTime = null;
		if(localTime.toString(DateTimeFormat.forPattern("HHmmss")).equals(NAV_DEFAULT_TIME)){
			dateTime = new DateTime(1753, 1, 1, localTime.getHourOfDay(),localTime.getMinuteOfHour(),localTime.getSecondOfMinute());					
		}
		else{			
			dateTime = new DateTime(1754, 1, 1, localTime.getHourOfDay(),localTime.getMinuteOfHour(),localTime.getSecondOfMinute());
		}
		return dateTime.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	public static DateTime createCurrentDateTime(){
		return new DateTime();
	}
	
}
