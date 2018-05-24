package io.renren.modules.ht.entity;

import java.io.Serializable;

/**
 * 恒泰数据库TCALENDAR_DATES表
 * @author DHB
 * @date 2018/5/15
 */
public class TCalendarDatesEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String calCode;
	private String 	calDay;
	private Integer calFlag;
	public String getCalCode() {
		return calCode;
	}
	public void setCalCode(String calCode) {
		this.calCode = calCode;
	}
	public String getCalDay() {
		return calDay;
	}
	public void setCalDay(String calDay) {
		this.calDay = calDay;
	}
	public Integer getCalFlag() {
		return calFlag;
	}
	public void setCalFlag(Integer calFlag) {
		this.calFlag = calFlag;
	}
	@Override
	public String toString() {
		return "TCalendarDates [calCode=" + calCode + ", calDay=" + calDay + ", calFlag=" + calFlag + "]";
	}
	
}
