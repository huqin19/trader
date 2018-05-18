package io.renren.modules.job.entity;

import java.io.Serializable;
/**
 * 部门成员
 * @author Administrator
 * @date 2018/5/17
 */
public class DepartmentUserEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 部门ID
	 */
	private Integer departmentId;
	/**
	 * 成员ID
	 */
	private String userId;
	
	public DepartmentUserEntity(Integer departmentId, String userId) {
		this.departmentId = departmentId;
		this.userId = userId;
	}
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
	@Override
	public String toString() {
		return "DepartmentUserEntity [departmentId=" + departmentId + ", userId=" + userId + "]";
	}
	
}
