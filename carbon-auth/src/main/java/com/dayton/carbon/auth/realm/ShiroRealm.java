package com.dayton.carbon.auth.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Shiro权限匹配和账号密码匹配
 * @author Martin Deng
 * @since 2020-09-04 22:08
 */
public class ShiroRealm extends AuthorizingRealm{

	/**
	 * 授权权限 <br/>
	 * 用户进行前线验证时Shiro会去换种中找，如果查找不到，会执行这个方法去查权限，并放入缓存中
	 * @param principals
	 * @return org.apache.shiro.authz.AuthorizationInfo
	 * @author Martin Deng
	 * @since 2020/9/4 22:09
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		return null;
	}

}
