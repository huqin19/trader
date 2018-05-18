package io.renren.modules.wd.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 万德数据库CBONDFUTURESEODPRICES表
 * @author DHB
 * @date 2018/5/14
 */
public class CBondFuturesEODPricesEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	//object_id
	private String objectId;
	//s_info_windcode
	private String sInfoWindcode;
	//trade_dt
	private String tradeDt;
	//s_dq_presettle
	private BigDecimal dDqPresettle;
	//s_dq_open
	private BigDecimal sDqOpen; 
	//s_dq_high
	private BigDecimal sDqHigh;
	//s_dq_low
	private BigDecimal sDqLow;
	//s_dq_close
	private BigDecimal sDqClose;
	//s_dq_settle
	private BigDecimal sDqSettle;
	//s_dq_volume
	private BigDecimal sDqVolume;
	//s_dq_amount
	private BigDecimal sDqAmount;
	//s_dq_oi
	private BigDecimal sDqOi;
	//s_dq_change
	private BigDecimal sDqChange;
	//opdate
	private String opdate;
	//opmode
	private String opmode;
	//imp_date
	private String impDate;
	//imp_time
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
	public BigDecimal getdDqPresettle() {
		return dDqPresettle;
	}
	public void setdDqPresettle(BigDecimal dDqPresettle) {
		this.dDqPresettle = dDqPresettle;
	}
	public BigDecimal getsDqOpen() {
		return sDqOpen;
	}
	public void setsDqOpen(BigDecimal sDqOpen) {
		this.sDqOpen = sDqOpen;
	}
	public BigDecimal getsDqHigh() {
		return sDqHigh;
	}
	public void setsDqHigh(BigDecimal sDqHigh) {
		this.sDqHigh = sDqHigh;
	}
	public BigDecimal getsDqLow() {
		return sDqLow;
	}
	public void setsDqLow(BigDecimal sDqLow) {
		this.sDqLow = sDqLow;
	}
	public BigDecimal getsDqClose() {
		return sDqClose;
	}
	public void setsDqClose(BigDecimal sDqClose) {
		this.sDqClose = sDqClose;
	}
	public BigDecimal getsDqSettle() {
		return sDqSettle;
	}
	public void setsDqSettle(BigDecimal sDqSettle) {
		this.sDqSettle = sDqSettle;
	}
	public BigDecimal getsDqVolume() {
		return sDqVolume;
	}
	public void setsDqVolume(BigDecimal sDqVolume) {
		this.sDqVolume = sDqVolume;
	}
	public BigDecimal getsDqAmount() {
		return sDqAmount;
	}
	public void setsDqAmount(BigDecimal sDqAmount) {
		this.sDqAmount = sDqAmount;
	}
	public BigDecimal getsDqOi() {
		return sDqOi;
	}
	public void setsDqOi(BigDecimal sDqOi) {
		this.sDqOi = sDqOi;
	}
	public BigDecimal getsDqChange() {
		return sDqChange;
	}
	public void setsDqChange(BigDecimal sDqChange) {
		this.sDqChange = sDqChange;
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
		return "CBondFuturesEODPricesEntity [objectId=" + objectId + ", sInfoWindcode=" + sInfoWindcode + ", tradeDt="
				+ tradeDt + ", dDqPresettle=" + dDqPresettle + ", sDqOpen=" + sDqOpen + ", sDqHigh=" + sDqHigh
				+ ", sDqLow=" + sDqLow + ", sDqClose=" + sDqClose + ", sDqSettle=" + sDqSettle + ", sDqVolume="
				+ sDqVolume + ", sDqAmount=" + sDqAmount + ", sDqOi=" + sDqOi + ", sDqChange=" + sDqChange + ", opdate="
				+ opdate + ", opmode=" + opmode + ", impDate=" + impDate + ", impTime=" + impTime + "]";
	}
	
}
