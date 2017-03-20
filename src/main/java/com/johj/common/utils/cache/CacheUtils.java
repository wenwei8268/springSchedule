package com.johj.common.utils.cache;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

/**
 * 
 */
public class CacheUtils {

	private static final Logger logger = Logger.getLogger(CacheUtils.class);
	private static CacheInterface ehcache = EhCacheImpl.getInstance();
	private static transient ResourceBundle propertites = null;
	private static final String CACHE_TYPE_FILE_URL = "config.cachetype";
	private static final String CACHE_TYPE_EHCACHE = "ehcache";
	private static Map<String, String> propertitesMap = new ConcurrentHashMap<String, String>();
	
	/**
	 * 具体的缓存实现类配置在config.cachetype.propertites
	 * 
	 */
	static {
		propertites = ResourceBundle.getBundle(CACHE_TYPE_FILE_URL, Locale.getDefault());
		if(propertites == null) {
			throw new RuntimeException(CACHE_TYPE_FILE_URL + ".properties file not found error!");
		}
		for(String key:propertites.keySet()) {
			String value = propertites.getString(key);
			logger.info("catchType: " + key + " value: " + value);
			propertitesMap.put(key, value);
		}
	}

	/**
	 * 从指定的缓存中根据key获取value
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 * @throws OssException
	 */
	public static Object get(String cacheName, String key) {
		if(CACHE_TYPE_EHCACHE.equals(propertitesMap.get(cacheName))) {
			return ehcache.get(cacheName, key);
		}
		return null;
	}

	/**
	 * 把指定的key-value放入到指定名称的缓存中
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public static void put(String cacheName, String key, Object value) throws Exception {
		if(CACHE_TYPE_EHCACHE.equals(propertitesMap.get(cacheName))) {
			ehcache.set(cacheName, key, value);
		}
	}

	/**
	 * 判断指定名称的缓存中是否存在指定的key
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static boolean contain(String cacheName, String key) {
		if(CACHE_TYPE_EHCACHE.equals(propertitesMap.get(cacheName))) {
			return ehcache.contain(cacheName, key);
		}
		return false;
	}

	/**
	 * 清楚全部的缓存
	 * 
	 */
	public static void clearAll() {
		try {
			ehcache.clearAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 从指定名称的缓存中删除指定的key
	 * 
	 * @param cacheName
	 * @param key
	 */
	public static void remove(String cacheName, String key) {
		if(CACHE_TYPE_EHCACHE.equals(propertitesMap.get(cacheName))) {
			ehcache.remove(cacheName, key);
		}
	}

}
