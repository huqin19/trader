package io.renren.modules.generator.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 固收日报查询
 * 
 * @author milk
 * @email 
 * @date 2018-05-16 14:23:34
 */
//@TableName("")
public class NewspaperEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// ========== 1.银行间每日债券借贷 ========
	/*
	 * 日期 TRDDATE
	 */
	private String trddate;  //TTRD_CMDS_EXECUTIONREPORT
	
	/*
	 * 标的券代码 U_I_CODE
	 */
	private String uICode;  //TTRD_CMDS_EXECUTIONREPORT
	
	/*
	 * 标的券名称 B_NAME
	 */
	private String bName;  //TTRD_CMDS_EXECUTIONREPORT
	
	/*
	 * 加权平均费率(%)
	 */
	private String weightedAverageRate;  //TTRD_CMDS_EXECUTIONREPORT计算
	
	/*
	 * 成交量(亿元)
	 */
	private String volume;  //TTRD_CMDS_EXECUTIONREPORT计算
	
	
	// ========== 2.国债期货品种排名视图 ========
	/*
	 * 交易日 TRADE_DT
	 */
	private String tradeDt; //CBONDFUTURESPOSITIONS
	
	/*
	 * 合约 S_INFO_WINDCODE
	 */
	private String sInfoWindcode;
	
	/*
	 * 名次 FS_INFO_RANK
	 */
	private Integer fsInfoRank;
	
	/*
	 * 成交会员简称 CJMEM
	 */
	private String cJmem; 
	
	/*
	 * 成交量 CJL
	 */
	private BigDecimal cJl;
	
	/*
	 * 成交变化 CJLBH
	 */
	private BigDecimal cJlbh;
	
	/*
	 * 持买会员简称 CBMEM
	 */
	private String cBmem;

	/*
	 * 持买量 CBL
	 */
	private BigDecimal cBl;
	
	/*
	 * 持买量变化 CBLBH
	 */
	private BigDecimal cBlbh;
	
	/*
	 * 持卖会员简称 CSMEM
	 */
	private String cSmem;
	
	/*
	 * 持卖量 CSL
	 */
	private BigDecimal cSl;
	
	/*
	 * 持卖变化 CSLBH
	 */
	private BigDecimal cSlbh;
	
	// ========== 3.国债期货当日结算价视图 ========
	/*
	 * 交易日
	 */
	//private String TRADE_DT; //CBONDFUTURESEODPRICES
	
	/*
	 * 合约
	 */
	//private String S_INFO_WINDCODE; //CFUTURESDESCRIPTION
	
	/*
	 * 开盘价 S_DQ_OPEN
	 */
	private BigDecimal sDqOpen; //CBONDFUTURESEODPRICES
	
	/*
	 * 最高价 S_DQ_HIGH
	 */
	private BigDecimal sDqHigh; //CBONDFUTURESEODPRICES
	
	/*
	 * 最低价 S_DQ_LOW
	 */
	private BigDecimal sDqLow; //CBONDFUTURESEODPRICES
	
	/*
	 * 收盘价 S_DQ_CLOSE
	 */
	private BigDecimal sDqClose; //CBONDFUTURESEODPRICES
	
	/*
	 * 结算价 S_DQ_SETTLE
	 */
	private BigDecimal sDqSettle; //CBONDFUTURESEODPRICES
	
	/*
	 * 成交量 S_DQ_VOLUME
	 */
	private BigDecimal sDqVolume; //CBONDFUTURESEODPRICES
	
	/*
	 * 持仓量 S_DQ_OI
	 */
	private BigDecimal sDqOi; //CBONDFUTURESEODPRICES
	
	/*
	 * 涨跌(元)-涨跌幅% S_DQ_CHANGE
	 */
	private BigDecimal sDqChange; //CBONDFUTURESEODPRICES
	
	/*
	 * 最后交易日期 S_INFO_DELISTDATE
	 */
	private String sInfoDelistdate; //CFUTURESDESCRIPTION

	public String getTrddate() {
		return trddate;
	}

	public void setTrddate(String trddate) {
		this.trddate = trddate;
	}

	public String getuICode() {
		return uICode;
	}

	public void setuICode(String uICode) {
		this.uICode = uICode;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getWeightedAverageRate() {
		return weightedAverageRate;
	}

	public void setWeightedAverageRate(String weightedAverageRate) {
		this.weightedAverageRate = weightedAverageRate;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getTradeDt() {
		return tradeDt;
	}

	public void setTradeDt(String tradeDt) {
		this.tradeDt = tradeDt;
	}

	public String getsInfoWindcode() {
		return sInfoWindcode;
	}

	public void setsInfoWindcode(String sInfoWindcode) {
		this.sInfoWindcode = sInfoWindcode;
	}

	public Integer getFsInfoRank() {
		return fsInfoRank;
	}

	public void setFsInfoRank(Integer fsInfoRank) {
		this.fsInfoRank = fsInfoRank;
	}

	public String getcJmem() {
		return cJmem;
	}

	public void setcJmem(String cJmem) {
		this.cJmem = cJmem;
	}

	public BigDecimal getcJl() {
		return cJl;
	}

	public void setcJl(BigDecimal cJl) {
		this.cJl = cJl;
	}

	public BigDecimal getcJlbh() {
		return cJlbh;
	}

	public void setcJlbh(BigDecimal cJlbh) {
		this.cJlbh = cJlbh;
	}

	public String getcBmem() {
		return cBmem;
	}

	public void setcBmem(String cBmem) {
		this.cBmem = cBmem;
	}

	public BigDecimal getcBl() {
		return cBl;
	}

	public void setcBl(BigDecimal cBl) {
		this.cBl = cBl;
	}

	public BigDecimal getcBlbh() {
		return cBlbh;
	}

	public void setcBlbh(BigDecimal cBlbh) {
		this.cBlbh = cBlbh;
	}

	public String getcSmem() {
		return cSmem;
	}

	public void setcSmem(String cSmem) {
		this.cSmem = cSmem;
	}

	public BigDecimal getcSl() {
		return cSl;
	}

	public void setcSl(BigDecimal cSl) {
		this.cSl = cSl;
	}

	public BigDecimal getcSlbh() {
		return cSlbh;
	}

	public void setcSlbh(BigDecimal cSlbh) {
		this.cSlbh = cSlbh;
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

	public String getsInfoDelistdate() {
		return sInfoDelistdate;
	}

	public void setsInfoDelistdate(String sInfoDelistdate) {
		this.sInfoDelistdate = sInfoDelistdate;
	}
	
	//传值
	private String dateinfoS;
	private String dateinfoE;
	//券名称 || 合约名称
	private String sname;
	//类型   1 ：银行间每日债券借贷   2：国债期货当日结算价  3：国债期货品种排名
	private String stype;
	//某天
	private String paramdate;
	
	/*
	 * 合约 S_INFO_WINDCODE集合
	 */
	private List<String> sInfoWindcodes;

	public String getDateinfoS() {
		return dateinfoS;
	}

	public void setDateinfoS(String dateinfoS) {
		this.dateinfoS = dateinfoS;
	}

	public String getDateinfoE() {
		return dateinfoE;
	}

	public void setDateinfoE(String dateinfoE) {
		this.dateinfoE = dateinfoE;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getParamdate() {
		return paramdate;
	}

	public void setParamdate(String paramdate) {
		this.paramdate = paramdate;
	}

	public List<String> getsInfoWindcodes() {
		return sInfoWindcodes;
	}

	public void setsInfoWindcodes(List<String> sInfoWindcodes) {
		this.sInfoWindcodes = sInfoWindcodes;
	}

}
