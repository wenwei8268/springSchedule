
package com.johj.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class PropertiesUtils {

	private PropertiesUtils() {

	}

	/**
	 * Properties文件读取
	 * @Description: Properties文件读取
	 * @param filePath
	 * @return
	 *
	 */
	public static Properties loadProperties(String filePath) {
		InputStream inputStream = PropertiesUtils.class.getResourceAsStream(filePath);
		Properties p = new Properties();
		try {
			p.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}

}
