
package com.johj.common.utils;

import java.lang.reflect.Field;


public class ReflectUtils {


	public static boolean checkObjIsNull(Object bean){
		if(bean == null){
			return true;
		}
		try {
		Class<?> clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {  
			field.setAccessible(true);//值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。值为 false 则指示反射的对象应该实施 Java 语言访问检查。
			//System.out.println(field.get(bean));
				if(field.get(bean) != null){
					return false;
				}
		}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return true;
	}
}
