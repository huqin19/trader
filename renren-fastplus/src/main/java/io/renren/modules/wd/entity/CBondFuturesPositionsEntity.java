package io.renren.modules.wd.entity;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 万德数据库CBONDFUTURESPOSITIONS
 * @author DHB
 * Last_update2018年5月18日上午10:59:25
 */
public class CBondFuturesPositionsEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String objectId;
	private String sInfoWindcode;
	private String tradeDt;
	private String fsInfoMembername;
	private String fsInfoType;
	private BigDecimal fsInfoPositionsnum;
	private Integer fsInfoRank;
	private BigDecimal sOiPositionsnumc;
	private String opdate;
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
	public String getTradeDt() {
		return tradeDt;
	}
	public void setTradeDt(String tradeDt) {
		this.tradeDt = tradeDt;
	}
	public String getFsInfoMembername() {
		return fsInfoMembername;
	}
	public void setFsInfoMembername(String fsInfoMembername) {
		this.fsInfoMembername = fsInfoMembername;
	}
	public String getFsInfoType() {
		return fsInfoType;
	}
	public void setFsInfoType(String fsInfoType) {
		this.fsInfoType = fsInfoType;
	}
	public BigDecimal getFsInfoPositionsnum() {
		return fsInfoPositionsnum;
	}
	public void setFsInfoPositionsnum(BigDecimal fsInfoPositionsnum) {
		this.fsInfoPositionsnum = fsInfoPositionsnum;
	}
	public Integer getFsInfoRank() {
		return fsInfoRank;
	}
	public void setFsInfoRank(Integer fsInfoRank) {
		this.fsInfoRank = fsInfoRank;
	}
	public BigDecimal getsOiPositionsnumc() {
		return sOiPositionsnumc;
	}
	public void setsOiPositionsnumc(BigDecimal sOiPositionsnumc) {
		this.sOiPositionsnumc = sOiPositionsnumc;
	}
	public String getOpdate() {
		return opdate;
	}
	public void setOpdate(String opdate) {
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
		return "CBondFuturesPositions [objectId=" + objectId + ", sInfoWindcode=" + sInfoWindcode + ", tradeDt="
				+ tradeDt + ", fsInfoMembername=" + fsInfoMembername + ", fsInfoType=" + fsInfoType
				+ ", fsInfoPositionsnum=" + fsInfoPositionsnum + ", fsInfoRank=" + fsInfoRank + ", sOiPositionsnumc="
				+ sOiPositionsnumc + ", opdate=" + opdate + ", opmode=" + opmode + ", impDate=" + impDate + ", impTime="
				+ impTime + "]";
	}

}
