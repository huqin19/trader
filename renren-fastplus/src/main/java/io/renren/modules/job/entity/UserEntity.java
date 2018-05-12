package io.renren.modules.job.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 返回成员
 * @author DHB
 * @date 2018/5/9
 */
public class UserEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String userid;
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
