package io.renren.modules.job.entity;

import java.io.Serializable;

/**
 * @author DHB
 * @date 2018年5月28日下午8:47:00
 *
 */
public class Content implements Serializable{

	private static final long serialVersionUID = 1L;
	private String description;
	private String 	picurl;
	private String title;
	private String url;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
	
}
