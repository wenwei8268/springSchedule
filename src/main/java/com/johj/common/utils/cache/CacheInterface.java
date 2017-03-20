package com.johj.common.utils.cache;


/**
 * 
 *
 */
public interface CacheInterface {

	/**
	 * 从指定名称的缓存里获取指定key的value
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Object get(String cacheName, String key);

	/**
	 * 把key-value放入到指定名称的缓存中
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public void set(String cacheName, String key, Object value);

	/**
	 * 判断指定缓存名称中是否包含指定key
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public boolean contain(String cacheName, String key);

	/**
	 * 清除缓存中的所有数据
	 * 
	 */
	public void clearAll();
	
	/**
	 * 从指定名称的缓存中删除指定的key
	 * 
	 * @param cacheName
	 * @param key
	 */
	public void remove(String cacheName, String key);
	
}
