package com.johj.common.utils.datatype;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	/**
	 * 空格替换
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		Pattern p = Pattern.compile("\\s+|\t|\r|\n");
		Matcher m = p.matcher(str);
		return m.replaceAll(" ");
	}

	public static Long strToLong(String str){
		return Long.parseLong(str);
	}

    public static boolean isNotBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return false;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return true;
            }
        }
        return false;
    }
	
	
	public static boolean isEmpty(String str){
		if(null == str || "".equals(str)){
			return true;
		}
		return false;
	}
}
