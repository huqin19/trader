package io.renren.modules.generator.controller;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.DateUtils;
import io.renren.common.utils.ExcelUtil;
import io.renren.common.utils.R;
import io.renren.modules.api.annotation.AuthIgnore;
import io.renren.modules.generator.entity.NewspaperEntity;
import io.renren.modules.generator.service.NewspaperService;



/**
 * 固收日报查询
 *
 * @author milk
 * @email 
 * @date 2018-05-16 14:23:34
 */
@RestController
@RequestMapping("/api")
public class NewsOutController {
    @Autowired
    private NewspaperService newspaperService;

    /**
     * 日报-列表
     */
    @AuthIgnore
    @RequestMapping("/look")
    //@RequiresPermissions("generator:newspaper:look")
    public R look(@RequestBody NewspaperEntity newspaperEntity){
    	String[] dateLists = null;
    	BigDecimal[] priceLists = null;
    	String namehy = "";
		List<NewspaperEntity> listZQJD = null, listVCBONDFUTURESEODPRICES=null,listVCBONDFUTURESPOSITIONSD=null;
		List<String> sInfoWindcodes = new ArrayList<String>();
		BigDecimal minvalue = null;
		BigDecimal maxvalue = null;
		if((newspaperEntity.getStype() != null && newspaperEntity.getStype().equals("1"))
				||
		   (newspaperEntity.getStype() != null && newspaperEntity.getStype().equals("2"))  ) {
			//银行间每日债券借贷
			listZQJD = newspaperService.queryZQJDList(newspaperEntity);
			//国债期货当日结算价
			listVCBONDFUTURESEODPRICES = newspaperService.queryVCBONDFUTURESEODPRICESList(newspaperEntity);
			sInfoWindcodes = newspaperService.queryHYNameOfVCBONDFUTURESEODPRICES(newspaperEntity);
			//查折线图
			if(null != sInfoWindcodes && sInfoWindcodes.size() > 0 && null != newspaperEntity.getParamdate()) {
				NewspaperEntity pa = new NewspaperEntity();
				namehy = sInfoWindcodes.get(0);
				pa.setsInfoWindcode(sInfoWindcodes.get(0));
				pa.setParamyear(newspaperEntity.getParamdate().substring(0, 4));
				List<NewspaperEntity> list = newspaperService.queryVCBONDFUTURESEODPRICESListByYYYYMM(pa);
				List<String> dateList = new ArrayList<String>();
				List<BigDecimal> priceList = new ArrayList<BigDecimal>();
				if(null != list &&  list.size() > 0) {
					for(NewspaperEntity x:list) {
						dateList.add(x.getTradeDt());
						priceList.add(x.getsDqSettle());
						if(null == maxvalue) {
							maxvalue = x.getsDqSettle();
						}else if(x.getsDqSettle().compareTo(maxvalue) == 1) {
							maxvalue = x.getsDqSettle();
						}
						if(null == minvalue) {
							minvalue = x.getsDqSettle();
						}else if(minvalue.compareTo(x.getsDqSettle()) == 1) {
							minvalue = x.getsDqSettle();
						}
					}
				}
				dateLists = dateList.toArray(new String[dateList.size()]);
				priceLists = priceList.toArray(new BigDecimal[priceList.size()]);
			}
			
		}
		if(newspaperEntity.getStype() != null && newspaperEntity.getStype().equals("3")) {
			//国债期货品种排名 
			listVCBONDFUTURESPOSITIONSD = newspaperService.queryVCBONDFUTURESPOSITIONSDList(newspaperEntity);
			sInfoWindcodes = newspaperService.queryHYNameOfVCBONDFUTURESPOSITIONSD(newspaperEntity);
		}
		if(null != newspaperEntity) {
			newspaperEntity.setsInfoWindcodes(sInfoWindcodes);
		}
		
		String tmp = newspaperEntity.getParamdate();
		if(null != tmp && tmp.length() > 0) {
			tmp = tmp.replaceAll("-", "");
			newspaperEntity.setParamdate(tmp.substring(0, 4) + "-" + tmp.substring(4, 6) + "-" + tmp.substring(6, 8));
		}
        return R.ok().put("listZQJD", listZQJD).put("listVCBONDFUTURESEODPRICES", listVCBONDFUTURESEODPRICES)
        		.put("listVCBONDFUTURESPOSITIONSD", listVCBONDFUTURESPOSITIONSD).put("newspaperEntity", newspaperEntity)
        		.put("dateLists", dateLists)
        		.put("priceLists", priceLists)
        		.put("namehy", namehy)
        		.put("maxvalue", maxvalue)
        		.put("minvalue", minvalue);
    }
    
    @AuthIgnore
    @RequestMapping("/export")
    public ResponseEntity<byte[]> export(@ModelAttribute NewspaperEntity newspaperEntity ){
		List<NewspaperEntity> newspaperList = null;
		ResponseEntity<byte[]> resEntity = null;
		if(null != newspaperEntity && null != newspaperEntity.getStype()) {
			if("1".equals(newspaperEntity.getStype())) {//银行间每日债券借贷
				newspaperList = newspaperService.queryZQJDList(newspaperEntity);
		        List<Integer[]> mergeList = null;
		        Integer[] mergeColume = null;
		        Integer[] mergeRow = {0};
		        Integer[] fontColor = null;
		    	List<String> headName = new ArrayList<>();
		    	headName.add("委托日期");
		    	headName.add("标的券代码");
		        headName.add("标的券名称 ");
		        headName.add("加权平均费率(%)");
		        headName.add("成交量(亿元)");
		        List<List<String>> headsList = new ArrayList<>();
		        headsList.add(headName);
		        List<List<Object>> dtoList = new ArrayList<>();
		        for (int t = 0; t < newspaperList.size(); t ++){
		        	List<Object> dataList = new ArrayList<>();
		        	dataList.add(newspaperList.get(t).getTrddate());
		        	dataList.add(newspaperList.get(t).getuICode());
		        	dataList.add(newspaperList.get(t).getbName());
		        	dataList.add(newspaperList.get(t).getWeightedAverageRate());
		        	dataList.add(newspaperList.get(t).getVolume());
		        	dtoList.add(dataList);
		        }      
		        HttpHeaders head = new HttpHeaders();
		        String fileName = null;
		        try {
		        	fileName = URLEncoder.encode("银行间每日债券借贷.xls", "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		        head.setContentDispositionFormData("attachment", fileName);
		        head.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		        ByteArrayOutputStream out = ExcelUtil.export("银行间每日债券借贷",
		        		headsList, dtoList, mergeList,mergeColume,mergeRow,fontColor);
		        byte [] bytes = out.toByteArray();
		        resEntity = new ResponseEntity<byte[]>(bytes,head,HttpStatus.OK);
				return resEntity;
			}
			if("2".equals(newspaperEntity.getStype())) {//国债期货当日结算价
				newspaperList = newspaperService.queryVCBONDFUTURESEODPRICESList(newspaperEntity);
		        List<Integer[]> mergeList = null;
		        Integer[] mergeColume = null;
		        Integer[] mergeRow = {0};
		        Integer[] fontColor = {9};
		    	List<String> headName = new ArrayList<>();
		    	headName.add("交易日");
		    	headName.add("合约");
		    	headName.add("开盘价");
		    	headName.add("最高价");
		    	headName.add("最低价 ");
		    	headName.add("收盘价");
		    	headName.add("结算价");
		    	headName.add("成交量");
		    	headName.add("持仓量");
		    	headName.add("涨跌(元)-涨跌幅%");
		    	headName.add("最后交易日期");
		        List<List<String>> headsList = new ArrayList<>();
		        headsList.add(headName);
		        List<List<Object>> dtoList = new ArrayList<>();		        
		        for (int t = 0; t < newspaperList.size(); t ++){
		        	List<Object> dataList = new ArrayList<>();
		        	dataList.add(DateUtils.format(newspaperList.get(t).getTradeDt(),DateUtils.NUM_PATTERN,
							DateUtils.DATE_PATTERN));
		        	dataList.add(newspaperList.get(t).getsInfoWindcode());
		        	dataList.add(newspaperList.get(t).getsDqOpen());
		        	dataList.add(newspaperList.get(t).getsDqHigh());
		        	dataList.add(newspaperList.get(t).getsDqLow());
		        	dataList.add(newspaperList.get(t).getsDqClose());
		        	dataList.add(newspaperList.get(t).getsDqSettle());
		        	dataList.add(newspaperList.get(t).getsDqVolume());
		        	dataList.add(newspaperList.get(t).getsDqOi());
		        	dataList.add(newspaperList.get(t).getsDqChange());
		        	dataList.add(newspaperList.get(t).getsInfoDelistdate());
		        	dtoList.add(dataList);
		        }      
		        HttpHeaders head = new HttpHeaders();
		        String fileName = null;
		        try {
		        	fileName = URLEncoder.encode("国债期货当日结算价.xls", "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		        head.setContentDispositionFormData("attachment", fileName);
		        head.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		        ByteArrayOutputStream out = ExcelUtil.export("国债期货当日结算价",
		        		headsList, dtoList, mergeList,mergeColume,mergeRow,fontColor);
		        byte [] bytes = out.toByteArray();
		        resEntity = new ResponseEntity<byte[]>(bytes,head,HttpStatus.OK);
			}
			if("3".equals(newspaperEntity.getStype())) {//国债期货品种排名 
				newspaperList = newspaperService.queryVCBONDFUTURESPOSITIONSDList(newspaperEntity);
		        List<Integer[]> mergeList = new ArrayList<>();
		        Integer[] merge = {1,3,3,3};
		        mergeList.add(merge);
		        Integer[] mergeColume = {0};
		        Integer[] mergeRow = {0};
		        Integer[] fontColor = {5,9,13};
		        List<String> firstHeadName = new ArrayList<>();
		        firstHeadName.add("");
		        firstHeadName.add("成交量排名");
		        firstHeadName.add("持买量排名");
		        firstHeadName.add("持卖量排名");
		        List<String> secondHeadName = new ArrayList<>();		    	
		        secondHeadName.add("日期");
		        secondHeadName.add("合约");
		        secondHeadName.add("名次 ");
		        secondHeadName.add("会员简称");
		        secondHeadName.add("成交量");
		        secondHeadName.add("比上交易日增减");
		        secondHeadName.add("名次 ");
		        secondHeadName.add("会员简称");
		        secondHeadName.add("持买单量");
		        secondHeadName.add("比上交易日增减");
		        secondHeadName.add("名次 ");
		        secondHeadName.add("会员简称");
		        secondHeadName.add("持卖单量");
		        secondHeadName.add("比上交易日增减 ");        
		        List<List<String>> headsList = new ArrayList<>();
		        headsList.add(firstHeadName);
		        headsList.add(secondHeadName);
		        List<List<Object>> dtoList = new ArrayList<>();	
		        for (int t = 0; t < newspaperList.size(); t ++){
		        	List<Object> dataList = new ArrayList<>();		           
		        	dataList.add(DateUtils.format(newspaperList.get(t).getTradeDt(),DateUtils.NUM_PATTERN,
							DateUtils.DATE_PATTERN));
		        	dataList.add(newspaperList.get(t).getsInfoWindcode());
		            dataList.add(newspaperList.get(t).getFsInfoRank());
		            dataList.add(newspaperList.get(t).getcJmem());
		            dataList.add(newspaperList.get(t).getcJl());
		            dataList.add(newspaperList.get(t).getcJlbh());
		            dataList.add(newspaperList.get(t).getFsInfoRank());
		            dataList.add(newspaperList.get(t).getcBmem());
		            dataList.add(newspaperList.get(t).getcBl());
		            dataList.add(newspaperList.get(t).getcBlbh());
		            dataList.add(newspaperList.get(t).getFsInfoRank());
		            dataList.add(newspaperList.get(t).getcSmem());
		            dataList.add(newspaperList.get(t).getcSl());
		            dataList.add(newspaperList.get(t).getcSlbh());
		            dtoList.add(dataList);
		        }      
		        HttpHeaders head = new HttpHeaders();
		        String fileName = null;
		        try {
		        	fileName = URLEncoder.encode("国债期货品种排名.xls", "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		        head.setContentDispositionFormData("attachment", fileName);
		        head.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		        ByteArrayOutputStream out = ExcelUtil.export("国债期货品种排名",
		        		headsList, dtoList, mergeList,mergeColume,mergeRow,fontColor);
		        byte [] bytes = out.toByteArray();
		        resEntity = new ResponseEntity<byte[]>(bytes,head,HttpStatus.OK);
			}
		}
		return resEntity;
    }	

}
