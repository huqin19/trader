package io.renren.modules.job.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 部门成员
 * @author Administrator
 * @date 2018/5/17
 */
public class DepartmentUserEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long objectId;
	/**
	 * 部门ID
	 */
	private Integer departmentId;
	/**
	 * 成员ID
	 */
	private String userId;
	private Date createdTimestamp;
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getObjectId() {
		return objectId;
	}
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}	
}
