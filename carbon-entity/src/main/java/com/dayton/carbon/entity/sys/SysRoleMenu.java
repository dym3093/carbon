package com.dayton.carbon.entity.sys;

import com.dayton.carbon.entity.comm.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色与权限关系表（中间表）
 * @author Martin Deng
 * @since 2020-09-04 22:04
 */
@Data
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenu extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8002495367681808017L;

	/** 记录ID */
	private String id;
	/** 角色ID */
	private String roleId;
	/** 菜单ID */
	private String menuId;

	@Builder(toBuilder = true)
	public SysRoleMenu(String remark, Integer status, String createId, LocalDateTime createTime, String updateId, LocalDateTime updateTime, String id, String roleId, String menuId) {
		super(remark, status, createId, createTime, updateId, updateTime);
		this.id = id;
		this.roleId = roleId;
		this.menuId = menuId;
	}
}
