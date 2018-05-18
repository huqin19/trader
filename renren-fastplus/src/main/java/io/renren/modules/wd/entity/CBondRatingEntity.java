package io.renren.modules.wd.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 万德数据库CBONDRATING表
 * @author DHB
 * @date 2018/5/15
 */
public class CBondRatingEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String objectId;
	private String sInfoWindcode;
	private String annDt;
	private String bRateStyle;
	private String bInfoCreditrating;
	private String bInfoCreditratingagency;
	private String bInfoCreditratingexplain;
	private String bInfoPrecreditrating;
	private String bCreditratingChange;
	private String annDt2;
	private Timestamp opdate;
	private String opmode;
	private String impDate;
	private String impTime;
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getsInfoWindcode() {
		return sInfoWindcode;
	}
	public void setsInfoWindcode(String sInfoWindcode) {
		this.sInfoWindcode = sInfoWindcode;
	}
	public String getAnnDt() {
		return annDt;
	}
	public void setAnnDt(String annDt) {
		this.annDt = annDt;
	}
	public String getbRateStyle() {
		return bRateStyle;
	}
	public void setbRateStyle(String bRateStyle) {
		this.bRateStyle = bRateStyle;
	}
	public String getbInfoCreditrating() {
		return bInfoCreditrating;
	}
	public void setbInfoCreditrating(String bInfoCreditrating) {
		this.bInfoCreditrating = bInfoCreditrating;
	}
	public String getbInfoCreditratingagency() {
		return bInfoCreditratingagency;
	}
	public void setbInfoCreditratingagency(String bInfoCreditratingagency) {
		this.bInfoCreditratingagency = bInfoCreditratingagency;
	}
	public String getbInfoCreditratingexplain() {
		return bInfoCreditratingexplain;
	}
	public void setbInfoCreditratingexplain(String bInfoCreditratingexplain) {
		this.bInfoCreditratingexplain = bInfoCreditratingexplain;
	}
	public String getbInfoPrecreditrating() {
		return bInfoPrecreditrating;
	}
	public void setbInfoPrecreditrating(String bInfoPrecreditrating) {
		this.bInfoPrecreditrating = bInfoPrecreditrating;
	}
	public String getbCreditratingChange() {
		return bCreditratingChange;
	}
	public void setbCreditratingChange(String bCreditratingChange) {
		this.bCreditratingChange = bCreditratingChange;
	}
	public String getAnnDt2() {
		return annDt2;
	}
	public void setAnnDt2(String annDt2) {
		this.annDt2 = annDt2;
	}
	public Timestamp getOpdate() {
		return opdate;
	}
	public void setOpdate(Timestamp opdate) {
		this.opdate = opdate;
	}
	public String getOpmode() {
		return opmode;
	}
	public void setOpmode(String opmode) {
		this.opmode = opmode;
	}
	public String getImpDate() {
		return impDate;
	}
	public void setImpDate(String impDate) {
		this.impDate = impDate;
	}
	public String getImpTime() {
		return impTime;
	}
	public void setImpTime(String impTime) {
		this.impTime = impTime;
	}
	@Override
	public String toString() {
		return "CBondRatingEntity [objectId=" + objectId + ", sInfoWindcode=" + sInfoWindcode + ", annDt=" + annDt
				+ ", bRateStyle=" + bRateStyle + ", bInfoCreditrating=" + bInfoCreditrating
				+ ", bInfoCreditratingagency=" + bInfoCreditratingagency + ", bInfoCreditratingexplain="
				+ bInfoCreditratingexplain + ", bInfoPrecreditrating=" + bInfoPrecreditrating + ", bCreditratingChange="
				+ bCreditratingChange + ", annDt2=" + annDt2 + ", opdate=" + opdate + ", opmode=" + opmode
				+ ", impDate=" + impDate + ", impTime=" + impTime + "]";
	}
}
