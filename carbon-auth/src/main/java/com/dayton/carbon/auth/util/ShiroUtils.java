package com.dayton.carbon.auth.util;

import com.dayton.carbon.assistant.util.spring.SpringUtil;
import com.dayton.carbon.entity.sys.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;

import java.util.Collection;
import java.util.Objects;

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

	private static RedisSessionDAO redisSessionDAO = SpringUtil.getBean(RedisSessionDAO.class);

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

	/**
	 * 获取当前用户信息
	 * @return com.dayton.carbon.entity.sys.SysUser
	 * @author Martin Deng
	 * @since 2020/9/3 22:33
	 */
	public static SysUser getUserInfo(){
		return (SysUser) SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 删除用户缓存
	 * @param userName          用户名
	 * @param isRemoveSession  是否删除Session
	 * @author Martin Deng
	 * @since 2020/9/3 22:34
	 */
	public static void deleteCache(String userName, boolean isRemoveSession){
		// 从缓存中获取session
		Session session = null;
		Collection<Session> sessions = redisSessionDAO.getActiveSessions();
		SysUser sysUser;
		Object attribute = null;
		for (Session sessionInfo : sessions) {
			// 遍历Session，找到该用户名称对应的Session
			attribute = sessionInfo.getAttribute(DefaultSubjectContext
					.PRINCIPALS_SESSION_KEY);
			if (null == attribute){
				continue;
			}
			// 转换
			sysUser = (SysUser) ((SimplePrincipalCollection)attribute)
					.getPrimaryPrincipal();
			if (null == sysUser){
				continue;
			}
			if (Objects.equals(sysUser.getUserName(), userName)){
				session = sessionInfo;
				break;
			}
		}
		if (session == null){
			return;
		}
		// 删除 session
		if (isRemoveSession){
			redisSessionDAO.delete(session);
		}
		// 删除cache，在访问受限接口时会重新授权
		DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils
				.getSecurityManager();
		Authenticator authenticator = securityManager.getAuthenticator();
		((LogoutAware)authenticator).onLogout((SimplePrincipalCollection)attribute);
	}

}
