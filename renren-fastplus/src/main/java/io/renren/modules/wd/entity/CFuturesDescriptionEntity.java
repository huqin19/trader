package io.renren.modules.wd.entity;
/**
 * 万德数据库表CFUTURESDESCRIPTION
 * @author DHB
 * @date 2018/5/15
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class CFuturesDescriptionEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String objectId;
	private String sInfoWindcode;
	private String sInfoCode;
	private String sInfoName;
	private String sInfoEname;
	private String fsInfoSccode;
	private Integer fsInfoType;
	private Integer fsInfoCctype;
	private String sInfoExchmarket;
	private String sInfoListdate;
	private String sInfoDelistdate;
	private String fsInfoDlmonth;
	private BigDecimal fsInfoLprice;
	private String fsInfoLtdldate;
	private Timestamp opdate;
	private String opmode ;
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
	public String getsInfoCode() {
		return sInfoCode;
	}
	public void setsInfoCode(String sInfoCode) {
		this.sInfoCode = sInfoCode;
	}
	public String getsInfoName() {
		return sInfoName;
	}
	public void setsInfoName(String sInfoName) {
		this.sInfoName = sInfoName;
	}
	public String getsInfoEname() {
		return sInfoEname;
	}
	public void setsInfoEname(String sInfoEname) {
		this.sInfoEname = sInfoEname;
	}
	public String getFsInfoSccode() {
		return fsInfoSccode;
	}
	public void setFsInfoSccode(String fsInfoSccode) {
		this.fsInfoSccode = fsInfoSccode;
	}
	public Integer getFsInfoType() {
		return fsInfoType;
	}
	public void setFsInfoType(Integer fsInfoType) {
		this.fsInfoType = fsInfoType;
	}
	public Integer getFsInfoCctype() {
		return fsInfoCctype;
	}
	public void setFsInfoCctype(Integer fsInfoCctype) {
		this.fsInfoCctype = fsInfoCctype;
	}
	public String getsInfoExchmarket() {
		return sInfoExchmarket;
	}
	public void setsInfoExchmarket(String sInfoExchmarket) {
		this.sInfoExchmarket = sInfoExchmarket;
	}
	public String getsInfoListdate() {
		return sInfoListdate;
	}
	public void setsInfoListdate(String sInfoListdate) {
		this.sInfoListdate = sInfoListdate;
	}
	public String getsInfoDelistdate() {
		return sInfoDelistdate;
	}
	public void setsInfoDelistdate(String sInfoDelistdate) {
		this.sInfoDelistdate = sInfoDelistdate;
	}
	public String getFsInfoDlmonth() {
		return fsInfoDlmonth;
	}
	public void setFsInfoDlmonth(String fsInfoDlmonth) {
		this.fsInfoDlmonth = fsInfoDlmonth;
	}
	public BigDecimal getFsInfoLprice() {
		return fsInfoLprice;
	}
	public void setFsInfoLprice(BigDecimal fsInfoLprice) {
		this.fsInfoLprice = fsInfoLprice;
	}
	public String getFsInfoLtdldate() {
		return fsInfoLtdldate;
	}
	public void setFsInfoLtdldate(String fsInfoLtdldate) {
		this.fsInfoLtdldate = fsInfoLtdldate;
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
		return "CFuturesDescriptionEntity [objectId=" + objectId + ", sInfoWindcode=" + sInfoWindcode + ", sInfoCode="
				+ sInfoCode + ", sInfoName=" + sInfoName + ", sInfoEname=" + sInfoEname + ", fsInfoSccode="
				+ fsInfoSccode + ", fsInfoType=" + fsInfoType + ", fsInfoCctype=" + fsInfoCctype + ", sInfoExchmarket="
				+ sInfoExchmarket + ", sInfoListdate=" + sInfoListdate + ", sInfoDelistdate=" + sInfoDelistdate
				+ ", fsInfoDlmonth=" + fsInfoDlmonth + ", fsInfoLprice=" + fsInfoLprice + ", fsInfoLtdldate="
				+ fsInfoLtdldate + ", opdate=" + opdate + ", opmode=" + opmode + ", impDate=" + impDate + ", impTime="
				+ impTime + "]";
	}
	
}
