package com.johj.common.utils.datatype;
public class LongUtils {

	private LongUtils() {
	}

	/**
	 * Object 转换为 Long
	 * 
	 * @param value
	 * @return
	 */
	public static Long valueOf(Object value) {
		Long res = null;
		if (value != null && value.toString().trim().length() > 0) {
			res = Long.valueOf(value.toString().trim());
		}
		return res;
	}

}
