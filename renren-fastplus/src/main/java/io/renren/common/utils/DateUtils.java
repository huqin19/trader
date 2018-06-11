package io.renren.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 日期处理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public final static String NUM_PATTERN = "yyyyMMdd";
	public final static String DATE_JOB = "yyyy/MM/dd HH:mm:ss";
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }
	public static String format(String dateStr,String after,String before) {
		if(dateStr != null && dateStr.trim() != ""){
			Date date =null;
			SimpleDateFormat dfAfter = new SimpleDateFormat(after);
			SimpleDateFormat dfBefore = new SimpleDateFormat(before);
			try {
				date = dfAfter.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return dfBefore.format(date);
		}			
		return null;		
	}
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    public static String timeStampFormat(Timestamp time, String pattern) {
        if(time != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(time);
        }
        return null;
    }
    
    public static void main(String[] args) {
    	String s = format("20170501",NUM_PATTERN,DATE_PATTERN);
    	System.out.println(s+ "======");
	}
}
