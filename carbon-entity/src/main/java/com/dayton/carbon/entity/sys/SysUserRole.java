package com.dayton.carbon.entity.sys;

import com.dayton.carbon.entity.comm.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Martin Deng
 * @since 2020-09-04 22:05
 */
@Data
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
public class SysUserRole extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -5718427365124770427L;

	/** 记录ID */
	private String id;
	/** 用户ID */
	private String userId;
	/** 角色ID */
	private String roleId;

	@Builder
	public SysUserRole(String remark, Integer status, String createId, LocalDateTime createTime, String updateId, LocalDateTime updateTime, String id, String userId, String roleId) {
		super(remark, status, createId, createTime, updateId, updateTime);
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
	}
}
