package com.johj.common.utils.datatype;


public class IntegerUtils {

	private IntegerUtils() {
	}

	/**
	 * Object 转换为 Integer
	 * @param value
	 * @return
	 */
	public static Integer valueOf(Object value) {
		Integer res = null;
		if (value != null && value.toString().trim().length() > 0) {
			res = Integer.valueOf(value.toString().trim());
		}
		return res;
	}

}
