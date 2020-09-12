package com.dayton.carbon.entity.sys;

import com.dayton.carbon.entity.comm.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 权限表（基础表）
 * @author Martin Deng
 * @since 2020-09-04 22:02
 */
@Data
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 2521744826018872969L;

	/** 记录ID */
	private String id;
	/** 菜单名称 */
	private String menuName;

	@Builder
	public SysMenu(String remark, Integer status, String createId, LocalDateTime createTime, String updateId, LocalDateTime updateTime, String id, String menuName) {
		super(remark, status, createId, createTime, updateId, updateTime);
		this.id = id;
		this.menuName = menuName;
	}
}
