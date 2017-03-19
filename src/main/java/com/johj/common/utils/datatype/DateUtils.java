
package com.johj.common.utils.datatype;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static Date now() {
		long t0 = System.currentTimeMillis();
		Date now = new Date(t0);
		return now;
	}
	
	/**
	 * 返回指定格式的时间字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		String str = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		str = sdf.format(date);
		
		return str;
	}
	
	/**
	 * 返回默认格式的时间字符串，默认格式为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 */
	public static String defaultFormatDate(Date date) {
		String str = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		str = sdf.format(date);
		
		return str;
	}
	
	/**
	 * 比较两个日期大小
	 * <br>
	 * 0：相等、正数date1大、负数date2大
	 * @param date1
	 * @param date2
	 * @return 时间差
	 */
	public static long compare(Date date1, Date date2) {
		if(date1 == null && date2 == null) {
			return 0;
		}
		if(date1 == null) {
			return -1;
		}
		if(date2 == null) {
			return 1;
		}
		return date1.getTime() - date2.getTime();
	}
	
}