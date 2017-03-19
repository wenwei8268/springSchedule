package com.johj.common.utils.datatype;
public class DoubleUtils {
	
	private DoubleUtils() {
    }

	/**
	 * Object转为Double
	 * @param value
	 * @return
	 */
    public static Double valueOf(Object value) {
    	Double res = null;
        if (value != null && value.toString().trim().length() > 0) {
            res = Double.valueOf(value.toString().trim());
        }
        return res;
    }
}
