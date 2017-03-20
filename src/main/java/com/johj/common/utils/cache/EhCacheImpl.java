package com.johj.common.utils.cache;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

/**
 * 
 *
 */
public class EhCacheImpl implements CacheInterface {

	private static final Logger logger = Logger.getLogger(EhCacheImpl.class);
	private static final String RESOURCE_FILE_PATHNAME = "config/ehcache.xml";
	private static EhCacheImpl ehCacheImpl = null;

	private CacheManager cacheManager;
	
	public static CacheInterface getInstance() {
		if(ehCacheImpl == null) {
			synchronized (EhCacheImpl.class) {
				if(ehCacheImpl == null) {
					ehCacheImpl = new EhCacheImpl();
				}
			}
		}
		return ehCacheImpl;
	}

	private EhCacheImpl() {
		try {
			URL url = EhCacheImpl.class.getClassLoader().getResource(RESOURCE_FILE_PATHNAME);
			cacheManager = new CacheManager(url);
		} catch (Exception e) {
			logger.error("ehcache缓存初始化失败", e);
		}
	}

	@Override
	public Object get(String cacheName, String key) {
		Cache cache = cacheManager.getCache(cacheName);
		Element element = cache.get(key);
		if (element != null) {
			return element.getObjectValue();
		} else {
			return null;
		}
		
	}

	@Override
	public void set(String cacheName, String key, Object valueObj) {
		Cache cache = cacheManager.getCache(cacheName);
		Element element = new Element(key, valueObj);
		cache.put(element);
	}

	@Override
	public boolean contain(String cacheName, String key) {
		Cache cache = cacheManager.getCache(cacheName);
		return cache.get(key) != null;
	}

	@Override
	public void clearAll() {
		cacheManager.clearAll();
	}

	@Override
	public void remove(String cacheName, String key) {
		Cache cache = cacheManager.getCache(cacheName);
		cache.remove(key);
	}

}