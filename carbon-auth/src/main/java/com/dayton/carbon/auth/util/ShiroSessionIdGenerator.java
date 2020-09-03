package com.dayton.carbon.auth.util;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**
 * 自定义SessionId生成器
 * @author Martin Deng
 * @since 2020-09-03 22:28
 */
public class ShiroSessionIdGenerator implements SessionIdGenerator{

	/**
	 * 自定义SessionId生成器
	 * @param session	session
	 * @return java.io.Serializable
	 * @author Martin Deng
	 * @since 2020/9/3 22:30
	 */
	@Override
	public Serializable generateId(Session session) {
		Serializable sessionId = new JavaUuidSessionIdGenerator().generateId
				(session);
		return String.format("login_token_%s", sessionId);
	}

}
