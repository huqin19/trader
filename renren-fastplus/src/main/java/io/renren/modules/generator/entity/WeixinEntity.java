package io.renren.modules.generator.entity;

import java.io.Serializable;

/**
 * 微信推送
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
	
}
