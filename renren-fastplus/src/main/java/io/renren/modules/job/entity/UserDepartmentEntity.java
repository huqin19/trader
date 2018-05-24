package io.renren.modules.job.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author DHB
 * @date 2018年5月23日上午12:22:12
 * 成员部门
 */
public class UserDepartmentEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long objectId;
	private String depId;
	private String userId;
	private Date  createdTimestamp;
	public Long getObjectId() {
		return objectId;
	}
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	
	
}
