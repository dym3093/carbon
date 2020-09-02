package com.dayton.carbon.auth.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常
 * @author Martin Deng
 * @since 2020-09-02 22:25
 */
@ControllerAdvice
public class CarbonShiroException {

	/**
	 * 处理Shiro权限拦截异常 <br/>
	 * 如果返回JSON数据格式请加上@ResponseBody注解
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 * @author Martin Deng
	 * @since 2020/9/2 22:27
	 */
	@ResponseBody
	@ExceptionHandler(value = AuthorizationException.class)
	public Map<String, Object> defaultErrorHandler(){
		Map<String, Object> map = new HashMap<>();
		map.put("403", "权限不足");
		return map;
	}

}
