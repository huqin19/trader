package io.renren.modules.job.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 成员
 * @author DHB
 * @date 2018/5/9
 */
public class UsersEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 成员名字
	 */
	private String name;
	/**
	 * 成员ID
	 */
	private String userid;
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

		
}
