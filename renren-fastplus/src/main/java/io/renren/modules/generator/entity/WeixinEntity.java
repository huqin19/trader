package io.renren.modules.generator.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 微信推送-树状结构model
 * 
 * @author milk
 * @email 
 * @date 2018-05-16 14:23:34
 */
//@TableName("")
public class WeixinEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	
	private String pId;
	
	private String name;

	private String open;
	
	private String checked;
	
	private String isleaf; //是否是人员  1:是  0：不是
	
	private String[] titleArr;
	private String[] newtreeName;
	private String sheetDate; //推送的日报日期
	
	/**
	 * spring bean名称
	 */
	private String beanName;
	
	/**
	 * 方法名
	 */
	private String methodName;
	
	/**
	 * 参数
	 */
	private String params;
	
	/**
	 * cron表达式
	 */
	private String cronExpression;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 应用编号
	 */
	private String appId; 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}

	public String[] getTitleArr() {
		return titleArr;
	}

	public void setTitleArr(String[] titleArr) {
		this.titleArr = titleArr;
	}

	public String[] getNewtreeName() {
		return newtreeName;
	}

	public void setNewtreeName(String[] newtreeName) {
		this.newtreeName = newtreeName;
	}

	public String getSheetDate() {
		return sheetDate;
	}

	public void setSheetDate(String sheetDate) {
		this.sheetDate = sheetDate;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}
