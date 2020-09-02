package com.dayton.carbon.assistant.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring上下文工具类
 * @author Martin Deng
 * @since 2020-09-02 23:07
 */
@Component
public class SpringUtil implements ApplicationContextAware{

	private static ApplicationContext context;

	/**
	 * Spring在bean初始化后会判断是不是ApplicationContextAware的子类
	 * @param applicationContext	application上下文
	 * @author Martin Deng
	 * @since 2020/9/2 23:08
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}

	/**
	 * 通过bean类类型返回指定的Bean
	 * @param beanClass	Bean的类类型
	 * @return T
	 * @author Martin Deng
	 * @since 2020/9/2 23:09
	 */
	public static<T> T getBean(Class<T> beanClass){
		return context.getBean(beanClass);
	}

}
