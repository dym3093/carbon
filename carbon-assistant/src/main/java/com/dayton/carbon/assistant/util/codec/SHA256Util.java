package com.dayton.carbon.assistant.util.codec;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Sha-256加密工具
 * @author Martin Deng
 * @since 2020-09-02 22:36
 */
public enum  SHA256Util {

	/** 唯一枚举元素，作为单例 */
	UNIQUE_INSTANCE;

	/**
	 * 获取线程安全单例
	 * @return com.dayton.carbon.assistant.util.codec.SHA256Util
	 * @author Martin Deng
	 * @since 2020/9/2 22:42
	 */
	public static SHA256Util instance(){
		return UNIQUE_INSTANCE;
	}

	/** 加密算法 */
	public static final String HASH_ALGORITHM_NAME = "SHA-256";
	/** 循环次数 */
	public static final int HASH_ITERATIONS = 15;

	public static String sha256(String password, String salt){
		return  new SimpleHash(HASH_ALGORITHM_NAME, password, salt,
				HASH_ITERATIONS).toString();
	}
}
