package com.dayton.carbon.auth.config;

import com.dayton.carbon.assistant.util.codec.SHA256Util;
import com.dayton.carbon.auth.properties.SpecialRedisProperties;
import com.dayton.carbon.auth.realm.ShiroRealm;
import com.dayton.carbon.auth.realm.ShiroSessionManger;
import com.dayton.carbon.auth.util.ShiroSessionIdGenerator;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置类
 * @author Martin Deng
 * @since 2020-09-04 22:12
 */
@Configuration
public class ShiroConfig {

	/** 缓存中key */
	private static final String CACHE_KEY = "shiro:cache:";
	/** session key */
	private static final String SESSION_KEY = "shiro:session:";
	/** 失效时间 */
	private static final int EXPIRE = 3600000;
	/** session离得实体类id标识 */
	public static final String PRINCIPAL_ID_FIELD_NAME = "userId";
	@Resource
	private SpecialRedisProperties redis;

	/**
	 * 开启Shiro-aop注解支持 <br/>
	 * 使用代理方式，所以需要开启代码支持
	 * @param securityManager	 SecurityManager
	 * @return org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
	 * @author Martin Deng
	 * @since 2020/9/4 22:24
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

	/**
	 * Shiro基础配置
	 * @param securityManager SecurityManager
	 * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
	 * @author Martin Deng
	 * @since 2020/9/4 22:33
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactory(SecurityManager
			                                                 securityManager){
		ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
		factoryBean.setSecurityManager(securityManager);
		// 过滤属性配置
		Map<String, String> filterChain = new LinkedHashMap<>();
		// 注意：过滤器配置顺序不能颠倒
		// 配置不会被拦截的链接
		filterChain.put("/static/**", "anon");
		filterChain.put("/userLogin/**", "anon");
		filterChain.put("/**", "authc");
		// 配置shiro默认登录界面地址，前后端分离中，登录界面跳转应由前端路由控制，后台仅返回json数据
		factoryBean.setLoginUrl("/userLogin/unauth");
		factoryBean.setFilterChainDefinitionMap(filterChain);
		return factoryBean;
	}

	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 自定义Session管理
		securityManager.setSessionManager(sessionManager());
		// 自定义cache管理
		securityManager.setCacheManager(cacheManger());
		// 自定义Realm认证
		securityManager.setRealm(shiroRealm());
		return securityManager;
	}

	/**
	 * 身份验证器
	 * @return org.apache.shiro.realm.Realm
	 * @author Martin Deng
	 * @since 2020/9/4 22:42
	 */
	@Bean
	public Realm shiroRealm() {
		ShiroRealm shiroRealm = new ShiroRealm();
		shiroRealm.setCredentialsMatcher(hashedCredentialMatcher());
		return shiroRealm;
	}

	/**
	 * 凭证匹配器 <br/>
	 * 将密码校验交给 Shiro 中的SimpleAuthenticationInfo进行处理，在这里做匹配配置
	 * @return org.apache.shiro.authc.credential.CredentialsMatcher
	 * @author Martin Deng
	 * @since 2020/9/4 22:43
	 */
	@Bean
	public CredentialsMatcher hashedCredentialMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		// 散列算法：这里使用SHA256算法
		matcher.setHashAlgorithmName(SHA256Util.HASH_ALGORITHM_NAME);
		// 散列次数：比如散列两次，相当于md5(md5(""));
		matcher.setHashIterations(SHA256Util.HASH_ITERATIONS);
		return matcher;
	}

	/**
	 * 配置 Redis 管理器
	 * 使用的是shiro-redis开源插件
	 * @return org.crazycake.shiro.RedisManager
	 * @author Martin Deng
	 * @since 2020/9/4 23:04
	 */
	@Bean
	public RedisManager redisManager(){
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(redis.getHost());
		redisManager.setTimeout(redis.getTimeout());
		redisManager.setPassword(redis.getPassword());
		return redisManager;
	}

	/**
	 * 配置Cache管理器 <br/>
	 * 用于往Redis存储权限和角色标识 <br/>
	 * 使用的是shiro-redis开源插件
	 * @return org.apache.shiro.cache.CacheManager
	 * @author Martin Deng
	 * @since 2020/9/4 23:07
	 */
	@Bean
	public CacheManager cacheManger() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		redisCacheManager.setKeyPrefix(CACHE_KEY);
		// 配置缓存的话要求方在session里面的实体类必须有个id标识
		redisCacheManager.setPrincipalIdFieldName(PRINCIPAL_ID_FIELD_NAME);
		return redisCacheManager;
	}

	/**
	 * SessionId生成器
	 * @return com.dayton.carbon.auth.util.ShiroSessionIdGenerator
	 * @author Martin Deng
	 * @since 2020/9/4 23:08
	 */
	@Bean
	public ShiroSessionIdGenerator sessionIdGenerator(){
		return  new ShiroSessionIdGenerator();
	}

	/**
	 * 配置RedisSessionDAO
	 * 使用的是shiro-redis开源插件
	 * @return org.crazycake.shiro.RedisSessionDAO
	 * @author Martin Deng
	 * @since 2020/9/4 23:10
	 */
	public RedisSessionDAO redisSessionDAO(){
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());
		redisSessionDAO.setKeyPrefix(SESSION_KEY);
		redisSessionDAO.setExpire(EXPIRE);
		return redisSessionDAO;
	}

	/**
	 * 配置Session管理器
	 * @return org.apache.shiro.session.mgt.SessionManager
	 * @author Martin Deng
	 * @since 2020/9/4 23:10
	 */
	@Bean
	public SessionManager sessionManager() {
		ShiroSessionManger shiroSessionManger = new ShiroSessionManger();
		shiroSessionManger.setSessionDAO(redisSessionDAO());
		return shiroSessionManger;
	}

}
