package io.renren.modules.job.entity;

import java.io.Serializable;

/**
 * 返回部门
 * @author DHB
 * @date 2018/5/9
 */	
public class DepartmentEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer id;
	private Integer orderNo;
	private Integer parentid;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	
	
}
