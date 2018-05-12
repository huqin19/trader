package io.renren.modules.job.entity;

import java.io.Serializable;

public class InvalidEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 没有成功的用户
	 */
	private String invaliduser;
	/**
	 * 没有成功的部门
	 */
	private String invalidparty;
	private String invalidtag;
	public String getInvaliduser() {
		return invaliduser;
	}
	public void setInvaliduser(String invaliduser) {
		this.invaliduser = invaliduser;
	}
	public String getInvalidparty() {
		return invalidparty;
	}
	public void setInvalidparty(String invalidparty) {
		this.invalidparty = invalidparty;
	}
	public String getInvalidtag() {
		return invalidtag;
	}
	public void setInvalidtag(String invalidtag) {
		this.invalidtag = invalidtag;
	}
	
	
}
