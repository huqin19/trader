package io.renren.modules.generator.controller;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.ExcelUtils;
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
		    	List<String> listName = new ArrayList<>();
		        listName.add("委托日期");
		        listName.add("标的券代码");
		        listName.add("标的券名称 ");
		        listName.add("加权平均费率(%)");
		        listName.add("成交量(亿元)");
		        List<String> listId = new ArrayList<>();
		        listId.add("trddate");
		        listId.add("uICode");
		        listId.add("bName");
		        listId.add("weightedAverageRate");
		        listId.add("volume");
		        List<Map<String,Object>> exportList = new ArrayList<>();
		        for (int t = 0; t < newspaperList.size(); t ++){
		            Map<String,Object> map = new HashMap<>();
		            map.put("trddate",newspaperList.get(t).getTrddate());
		            map.put("uICode",newspaperList.get(t).getuICode());
		            map.put("bName",newspaperList.get(t).getbName());
		            map.put("weightedAverageRate",newspaperList.get(t).getWeightedAverageRate());
		            map.put("volume",newspaperList.get(t).getVolume());
		            exportList.add(map);
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
		        ByteArrayOutputStream out = ExcelUtils.exportExcel("银行间每日债券借贷",
		        		listName,listId, exportList);
		        byte [] bytes = out.toByteArray();
		        resEntity = new ResponseEntity<byte[]>(bytes,head,HttpStatus.OK);
				return resEntity;
			}
			if("2".equals(newspaperEntity.getStype())) {//国债期货当日结算价
				newspaperList = newspaperService.queryVCBONDFUTURESEODPRICESList(newspaperEntity);
		    	List<String> listName = new ArrayList<>();
		    	listName.add("交易日");
		    	listName.add("合约");
		        listName.add("开盘价");
		        listName.add("最高价");
		        listName.add("最低价 ");
		        listName.add("收盘价");
		        listName.add("结算价");
		        listName.add("成交量");
		        listName.add("持仓量");
		        listName.add("涨跌(元)-涨跌幅%");
		        listName.add("最后交易日期");
		        List<String> listId = new ArrayList<>();
		        listId.add("tradeDt");
		        listId.add("sInfoWindcode");
		        listId.add("sDqOpen");
		        listId.add("sDqHigh");
		        listId.add("sDqLow");
		        listId.add("sDqClose");
		        listId.add("sDqSettle");
		        listId.add("sDqVolume");
		        listId.add("sDqOi");
		        listId.add("sDqChange");
		        listId.add("sInfoDelistdate");
		        List<Map<String,Object>> exportList = new ArrayList<>();
		        for (int t = 0; t < newspaperList.size(); t ++){
		            Map<String,Object> map = new HashMap<>();
		            map.put("tradeDt",newspaperList.get(t).getTradeDt());
		            map.put("sInfoWindcode",newspaperList.get(t).getsInfoWindcode());
		            map.put("sDqOpen",newspaperList.get(t).getsDqOpen());
		            map.put("sDqHigh",newspaperList.get(t).getsDqHigh());
		            map.put("sDqLow",newspaperList.get(t).getsDqLow());
		            map.put("sDqClose",newspaperList.get(t).getsDqClose());
		            map.put("sDqSettle",newspaperList.get(t).getsDqSettle());
		            map.put("sDqVolume",newspaperList.get(t).getsDqVolume());
		            map.put("sDqOi",newspaperList.get(t).getsDqOi());
		            map.put("sDqChange",newspaperList.get(t).getsDqChange());
		            map.put("sInfoDelistdate",newspaperList.get(t).getsInfoDelistdate());
		            exportList.add(map);
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
		        ByteArrayOutputStream out = ExcelUtils.exportExcel("国债期货当日结算价",
		        		listName,listId, exportList);
		        byte [] bytes = out.toByteArray();
		        resEntity = new ResponseEntity<byte[]>(bytes,head,HttpStatus.OK);
			}
			if("3".equals(newspaperEntity.getStype())) {//国债期货品种排名 
				newspaperList = newspaperService.queryVCBONDFUTURESPOSITIONSDList(newspaperEntity);
		    	List<String> listName = new ArrayList<>();	
		        listName.add("日期");
		        listName.add("合约");
		        listName.add("名次 ");
		        listName.add("会员简称");
		        listName.add("成交量");
		        listName.add("比上交易日增减");
		        listName.add("名次 ");
		        listName.add("会员简称");
		        listName.add("持买单量");
		        listName.add("比上交易日增减");
		        listName.add("名次 ");
		        listName.add("会员简称");
		        listName.add("持卖单量");
		        listName.add("比上交易日增减 ");
		        List<String> listId = new ArrayList<>();	        
		        listId.add("tradeDt");
		        listId.add("sInfoWindcode");
		        listId.add("fsInfoRank");
		        listId.add("cJmem");
		        listId.add("cJl");
		        listId.add("cJlbh");
		        listId.add("fsInfoRank");
		        listId.add("cBmem");
		        listId.add("cBl");
		        listId.add("cBlbh");
		        listId.add("fsInfoRank");
		        listId.add("cSmem");
		        listId.add("cSl");
		        listId.add("cSlbh");
		        List<Map<String,Object>> exportList = new ArrayList<>();
		        for (int t = 0; t < newspaperList.size(); t ++){
		            Map<String,Object> map = new HashMap<>();		           
		            map.put("tradeDt",newspaperList.get(t).getTradeDt());
		            map.put("sInfoWindcode",newspaperList.get(t).getsInfoWindcode());
		            map.put("fsInfoRank",newspaperList.get(t).getFsInfoRank());
		            map.put("cJmem",newspaperList.get(t).getcJmem());
		            map.put("cJl",newspaperList.get(t).getcJl());
		            map.put("cJlbh",newspaperList.get(t).getcJlbh());
		            map.put("fsInfoRank",newspaperList.get(t).getFsInfoRank());
		            map.put("cBmem",newspaperList.get(t).getcBmem());
		            map.put("cBl",newspaperList.get(t).getcBl());
		            map.put("cBlbh",newspaperList.get(t).getcBlbh());
		            map.put("fsInfoRank",newspaperList.get(t).getFsInfoRank());
		            map.put("cSmem",newspaperList.get(t).getcSmem());
		            map.put("cSl",newspaperList.get(t).getcSl());
		            map.put("cSlbh",newspaperList.get(t).getcSlbh());
		            exportList.add(map);
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
		        ByteArrayOutputStream out = ExcelUtils.exportExcel("国债期货品种排名",
		        		listName,listId, exportList);
		        byte [] bytes = out.toByteArray();
		        resEntity = new ResponseEntity<byte[]>(bytes,head,HttpStatus.OK);
			}
		}
		return resEntity;
    }	

}
