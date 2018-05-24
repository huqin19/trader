package io.renren.modules.job.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 成员
 * @author DHB
 * @date 2018/5/9
 */
public class UsersEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long objectId;
	/**
	 * 成员名字
	 */
	private String name;
	/**
	 * 成员ID
	 */
	private String userid;
	private Integer status;
	private String creatorId;
	private Date createdTimestamp;
	private String lastModifierId;
	private Date modifiedTimestamp;
	/**
	 * 返回部门List
	 */
	private List<Integer> department;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public List<Integer> getDepartment() {
		return department;
	}
	public void setDepartment(List<Integer> department) {
		this.department = department;
	}
	public Long getObjectId() {
		return objectId;
	}
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public String getLastModifierId() {
		return lastModifierId;
	}
	public void setLastModifierId(String lastModifierId) {
		this.lastModifierId = lastModifierId;
	}
	public Date getModifiedTimestamp() {
		return modifiedTimestamp;
	}
	public void setModifiedTimestamp(Date modifiedTimestamp) {
		this.modifiedTimestamp = modifiedTimestamp;
	}
	
		
}
