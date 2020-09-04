package com.dayton.carbon.entity.sys;

import com.dayton.carbon.entity.comm.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

/**
 * 角色与权限关系表（中间表）
 * @author Martin Deng
 * @since 2020-09-04 22:04
 */
@Data
@Builder
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenu extends BaseEntity implements Serializable {
}
