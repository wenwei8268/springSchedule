
package com.johj.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanUtils implements ApplicationContextAware {

	private static BeanUtils me;
	private BeanFactory beanFactory;
	
	
	public void init() {
		me = this;
		me.beanFactory = this.beanFactory;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.beanFactory = applicationContext;
	}
	
	/**
	 * 根据beanName获取
	 * 
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		return me.beanFactory.getBean(name);
	}

}
