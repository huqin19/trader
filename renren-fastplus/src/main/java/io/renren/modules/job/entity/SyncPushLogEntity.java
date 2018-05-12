package io.renren.modules.job.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 推送同步日志
 * @author DHB
 * @date 2018年5月9日
 */
public class SyncPushLogEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 日志id
	*/
	private Long id;
	/**
	 * 同步接口地址
	*/
	private String url;
	/**
	 * 同步接口名
	*/
	private String functionName;
	/**
	 * 同步参数
	*/
	private String param;
	/**
	 * 同步时间
	*/
	private Date createTime;
	/**
	 * 同步原因
	*/
	private String reason;
	/**
	 * 同步方式，0-定时，-1手动
	*/
	private Integer way;
	/**
	 * 同步结果，0-成功，-1失败
	*/
	private Integer result;
	/**
	 * 结果描述
	*/
	private String resultDesc;
	/**
	 * 备注
	*/
	private String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getWay() {
		return way;
	}
	public void setWay(Integer way) {
		this.way = way;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getResultDesc() {
		return resultDesc;
	}
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
