package io.renren.modules.wd.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 万德数据库CBONDISSUERRATING表
 * @author DHB
 * @date 2018/5/14
 */
public class CBondIssuerRatingEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String objectId;
	private String sInfoCompname;
	private String annDt;
	private String bRateStyle;
	private String bInfoCreditrating;
	private Integer bRateRatingoutlook;
	private String bInfoCreditratingagency;
	private String sInfoCompcode;
	private String bInfoCreditratingexplain;
	private String bInfoPrecreditrating;
	private String bCreditratingChange;
	private Integer bInfoIssuerratetype;
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
	public String getsInfoCompname() {
		return sInfoCompname;
	}
	public void setsInfoCompname(String sInfoCompname) {
		this.sInfoCompname = sInfoCompname;
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
	public Integer getbRateRatingoutlook() {
		return bRateRatingoutlook;
	}
	public void setbRateRatingoutlook(Integer bRateRatingoutlook) {
		this.bRateRatingoutlook = bRateRatingoutlook;
	}
	public String getbInfoCreditratingagency() {
		return bInfoCreditratingagency;
	}
	public void setbInfoCreditratingagency(String bInfoCreditratingagency) {
		this.bInfoCreditratingagency = bInfoCreditratingagency;
	}
	public String getsInfoCompcode() {
		return sInfoCompcode;
	}
	public void setsInfoCompcode(String sInfoCompcode) {
		this.sInfoCompcode = sInfoCompcode;
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
	public Integer getbInfoIssuerratetype() {
		return bInfoIssuerratetype;
	}
	public void setbInfoIssuerratetype(Integer bInfoIssuerratetype) {
		this.bInfoIssuerratetype = bInfoIssuerratetype;
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
		return "CBondIssuerRatingEntity [objectId=" + objectId + ", sInfoCompname=" + sInfoCompname + ", annDt=" + annDt
				+ ", bRateStyle=" + bRateStyle + ", bInfoCreditrating=" + bInfoCreditrating + ", bRateRatingoutlook="
				+ bRateRatingoutlook + ", bInfoCreditratingagency=" + bInfoCreditratingagency + ", sInfoCompcode="
				+ sInfoCompcode + ", bInfoCreditratingexplain=" + bInfoCreditratingexplain + ", bInfoPrecreditrating="
				+ bInfoPrecreditrating + ", bCreditratingChange=" + bCreditratingChange + ", bInfoIssuerratetype="
				+ bInfoIssuerratetype + ", annDt2=" + annDt2 + ", opdate=" + opdate + ", opmode=" + opmode
				+ ", impDate=" + impDate + ", impTime=" + impTime + "]";
	}
	
}