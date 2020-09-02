package com.dayton.carbon.assistant.util.auth;

import com.dayton.carbon.assistant.util.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.crazycake.shiro.RedisSessionDAO;

/**
 * Shrio工具类
 * @author Martin Deng
 * @since 2020-09-02 23:10
 */
@Slf4j
public enum  ShiroUtils {

	/** 唯一枚举元素，作为单例 */
	UNIQUE_INSTANCE;

	/**
	 * 获取线程安全单例
	 * @return com.dayton.carbon.assistant.util.auth.ShiroUtils
	 * @author Martin Deng
	 * @since 2020/9/2 23:12
	 */
	public static ShiroUtils instance(){
		return UNIQUE_INSTANCE;
	}

	private static RedisSessionDAO redisSessionDAO =
			SpringUtil.getBean(RedisSessionDAO.class);

	/**
	 * 获取当前用户Session
	 * @return org.apache.shiro.session.Session
	 * @author Martin Deng
	 * @since 2020/9/2 23:19
	 */
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * 用户登出
	 * @author Martin Deng
	 * @since 2020/9/2 23:20
	 */
	public static void logout(){
		SecurityUtils.getSubject().logout();
	}

}
