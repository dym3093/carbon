package com.dayton.carbon.entity.comm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Martin Deng
 * @since 2020-09-03 22:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class BaseEntity implements Serializable{

	/** 备注 */
	protected String remark;
	/** 记录状态 */
	protected Integer status;
	/** 记录创建人ID */
	protected String createId;
	/** 创建时间 */
	protected LocalDateTime createTime;
	/** 修改人ID */
	protected String updateId;
	/** 修改时间 */
	protected LocalDateTime updateTime;

}
