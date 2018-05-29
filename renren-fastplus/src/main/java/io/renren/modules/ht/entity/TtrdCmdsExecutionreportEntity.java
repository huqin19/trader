package io.renren.modules.ht.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author DHB
 * @date 2018年5月29日上午2:07:17
 *
 */
public class TtrdCmdsExecutionreportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal epid;
	private String trddate;
	private String trdtime;
	private String execid;
	private String trdtype;
	private Integer marketindicator;
	private String dirction;
	private String iCode;
	private String aType;
	private String mType;
	private String iName;
	private String uICode;
	private String uAType;
	private String uMType;
	private String uIName;
	private BigDecimal price;
	private BigDecimal ytm;
	private BigDecimal trdfv;
	private BigDecimal trdcashamt;
	private BigDecimal uClearPrice;
	private BigDecimal uClearPrice2;
	private BigDecimal uYtm;
	private BigDecimal uYtm2;
	private Integer tradelimitdays;
	private BigDecimal settlcurramt;
	private String settlcurramt2;
	private String exectype;
	private Integer fraTradefwddays;
	private Integer bndTrdtype;
	private String irsBenchmark2;
	private BigDecimal irsBenchmarkspread2;
	private String irsBenchmark1;
	private BigDecimal irsBenchmarkspread1;
	private BigDecimal irsRate;
	private BigDecimal principal;
	
	public BigDecimal getEpid() {
		return epid;
	}
	public void setEpid(BigDecimal epid) {
		this.epid = epid;
	}
	public String getTrddate() {
		return trddate;
	}
	public void setTrddate(String trddate) {
		this.trddate = trddate;
	}
	public String getTrdtime() {
		return trdtime;
	}
	public void setTrdtime(String trdtime) {
		this.trdtime = trdtime;
	}
	public String getExecid() {
		return execid;
	}
	public void setExecid(String execid) {
		this.execid = execid;
	}
	public String getTrdtype() {
		return trdtype;
	}
	public void setTrdtype(String trdtype) {
		this.trdtype = trdtype;
	}
	public Integer getMarketindicator() {
		return marketindicator;
	}
	public void setMarketindicator(Integer marketindicator) {
		this.marketindicator = marketindicator;
	}
	public String getDirction() {
		return dirction;
	}
	public void setDirction(String dirction) {
		this.dirction = dirction;
	}
	public String getiCode() {
		return iCode;
	}
	public void setiCode(String iCode) {
		this.iCode = iCode;
	}
	public String getaType() {
		return aType;
	}
	public void setaType(String aType) {
		this.aType = aType;
	}
	public String getmType() {
		return mType;
	}
	public void setmType(String mType) {
		this.mType = mType;
	}
	public String getiName() {
		return iName;
	}
	public void setiName(String iName) {
		this.iName = iName;
	}
	public String getuICode() {
		return uICode;
	}
	public void setuICode(String uICode) {
		this.uICode = uICode;
	}
	public String getuAType() {
		return uAType;
	}
	public void setuAType(String uAType) {
		this.uAType = uAType;
	}
	public String getuMType() {
		return uMType;
	}
	public void setuMType(String uMType) {
		this.uMType = uMType;
	}
	public String getuIName() {
		return uIName;
	}
	public void setuIName(String uIName) {
		this.uIName = uIName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getYtm() {
		return ytm;
	}
	public void setYtm(BigDecimal ytm) {
		this.ytm = ytm;
	}
	public BigDecimal getTrdfv() {
		return trdfv;
	}
	public void setTrdfv(BigDecimal trdfv) {
		this.trdfv = trdfv;
	}
	public BigDecimal getTrdcashamt() {
		return trdcashamt;
	}
	public void setTrdcashamt(BigDecimal trdcashamt) {
		this.trdcashamt = trdcashamt;
	}
	public BigDecimal getuClearPrice() {
		return uClearPrice;
	}
	public void setuClearPrice(BigDecimal uClearPrice) {
		this.uClearPrice = uClearPrice;
	}
	public BigDecimal getuClearPrice2() {
		return uClearPrice2;
	}
	public void setuClearPrice2(BigDecimal uClearPrice2) {
		this.uClearPrice2 = uClearPrice2;
	}
	public BigDecimal getuYtm() {
		return uYtm;
	}
	public void setuYtm(BigDecimal uYtm) {
		this.uYtm = uYtm;
	}
	public BigDecimal getuYtm2() {
		return uYtm2;
	}
	public void setuYtm2(BigDecimal uYtm2) {
		this.uYtm2 = uYtm2;
	}
	public Integer getTradelimitdays() {
		return tradelimitdays;
	}
	public void setTradelimitdays(Integer tradelimitdays) {
		this.tradelimitdays = tradelimitdays;
	}
	public BigDecimal getSettlcurramt() {
		return settlcurramt;
	}
	public void setSettlcurramt(BigDecimal settlcurramt) {
		this.settlcurramt = settlcurramt;
	}
	public String getSettlcurramt2() {
		return settlcurramt2;
	}
	public void setSettlcurramt2(String settlcurramt2) {
		this.settlcurramt2 = settlcurramt2;
	}
	public String getExectype() {
		return exectype;
	}
	public void setExectype(String exectype) {
		this.exectype = exectype;
	}
	public Integer getFraTradefwddays() {
		return fraTradefwddays;
	}
	public void setFraTradefwddays(Integer fraTradefwddays) {
		this.fraTradefwddays = fraTradefwddays;
	}
	public Integer getBndTrdtype() {
		return bndTrdtype;
	}
	public void setBndTrdtype(Integer bndTrdtype) {
		this.bndTrdtype = bndTrdtype;
	}
	public String getIrsBenchmark2() {
		return irsBenchmark2;
	}
	public void setIrsBenchmark2(String irsBenchmark2) {
		this.irsBenchmark2 = irsBenchmark2;
	}
	public BigDecimal getIrsBenchmarkspread2() {
		return irsBenchmarkspread2;
	}
	public void setIrsBenchmarkspread2(BigDecimal irsBenchmarkspread2) {
		this.irsBenchmarkspread2 = irsBenchmarkspread2;
	}
	public String getIrsBenchmark1() {
		return irsBenchmark1;
	}
	public void setIrsBenchmark1(String irsBenchmark1) {
		this.irsBenchmark1 = irsBenchmark1;
	}
	public BigDecimal getIrsBenchmarkspread1() {
		return irsBenchmarkspread1;
	}
	public void setIrsBenchmarkspread1(BigDecimal irsBenchmarkspread1) {
		this.irsBenchmarkspread1 = irsBenchmarkspread1;
	}
	public BigDecimal getIrsRate() {
		return irsRate;
	}
	public void setIrsRate(BigDecimal irsRate) {
		this.irsRate = irsRate;
	}
	public BigDecimal getPrincipal() {
		return principal;
	}
	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}
}
