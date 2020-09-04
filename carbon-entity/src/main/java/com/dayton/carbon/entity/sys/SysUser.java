package com.dayton.carbon.entity.sys;

import com.dayton.carbon.entity.comm.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.Tolerate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 系统用户表（基础表）
 * @author Martin Deng
 * @since 2020-09-03 22:15
 */
@Data
@Builder
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 529414741501491192L;

	/** id */
	private String id;
	/** 用户名 */
	private String userName;
	/** 密码 */
	private String password;

	@Tolerate
	public SysUser() {}

	@Override
	public String toString() {
		// 返回JSON格式的内容
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
