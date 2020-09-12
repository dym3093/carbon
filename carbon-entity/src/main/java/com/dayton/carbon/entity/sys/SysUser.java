package com.dayton.carbon.entity.sys;

import com.dayton.carbon.entity.comm.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统用户表（基础表）
 * @author Martin Deng
 * @since 2020-09-03 22:15
 */
@Data
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

	@Builder
	public SysUser(String remark, Integer status, String createId,
	               LocalDateTime createTime, String updateId,
	               LocalDateTime updateTime, String id, String userName,
	               String password) {
		super(remark, status, createId, createTime, updateId, updateTime);
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public String toString() {
		// 返回JSON格式的内容
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
