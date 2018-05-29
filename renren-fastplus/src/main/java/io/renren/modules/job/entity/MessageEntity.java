package io.renren.modules.job.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author DHB
 * @date 2018年5月28日下午8:33:08
 *
 */
public class MessageEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String touser;
	private String toparty;
	private String no;
	private String msgtype;
	private List<String> content;
	private String sendType;
	private String safe;
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getToparty() {
		return toparty;
	}
	public void setToparty(String toparty) {
		this.toparty = toparty;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public String getSafe() {
		return safe;
	}
	public void setSafe(String safe) {
		this.safe = safe;
	}
	public List<String> getContent() {
		return content;
	}
	public void setContent(List<String> content) {
		this.content = content;
	}
	
}
