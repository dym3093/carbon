package com.dayton.carbon.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Martin Deng
 * @since 2020-09-04 22:56
 */
@Data
@Component
@ConfigurationProperties(prefix = SpecialRedisProperties.REDIS_PREFIX)
public class SpecialRedisProperties {

	public static final String REDIS_PREFIX = "spring.redis";

	/** 主机ip */
	private String host;
	/** 端口号 */
	private int port;
	/** 超时时间 */
	private int timeout;
	/** 密码 */
	private String password;

}
