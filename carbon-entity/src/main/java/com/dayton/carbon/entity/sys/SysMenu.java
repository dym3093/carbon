package com.dayton.carbon.entity.sys;

import com.dayton.carbon.entity.comm.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

/**
 * 权限表（基础表）
 * @author Martin Deng
 * @since 2020-09-04 22:02
 */
@Data
@Builder
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends BaseEntity implements Serializable {
}
