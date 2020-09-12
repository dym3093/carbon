package com.dayton.carbon.entity.sys;

import com.dayton.carbon.entity.comm.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色表（基础表）
 * @author Martin Deng
 * @since 2020-09-04 22:04
 */
@Data
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -994808078681301428L;

	/** 记录ID */
	private String id;
	/** 角色名称 */
	private String roleName;

	@Builder(toBuilder = true)
	public SysRole(String remark, Integer status, String createId, LocalDateTime createTime, String updateId, LocalDateTime updateTime, String id, String roleName) {
		super(remark, status, createId, createTime, updateId, updateTime);
		this.id = id;
		this.roleName = roleName;
	}
}
