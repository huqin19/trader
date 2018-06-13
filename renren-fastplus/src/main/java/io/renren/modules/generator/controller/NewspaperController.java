package io.renren.modules.generator.controller;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.R;
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
@RequestMapping("generator/newspaper")
public class NewspaperController {
    @Autowired
    private NewspaperService newspaperService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:newspaper:list")
    public R list(@RequestBody NewspaperEntity newspaperEntity){
		List<NewspaperEntity> newspaperList = null;
		if(null != newspaperEntity && null != newspaperEntity.getStype()) {
			if("1".equals(newspaperEntity.getStype())) {//银行间每日债券借贷
				newspaperList = newspaperService.queryZQJDList(newspaperEntity);
			}
			if("2".equals(newspaperEntity.getStype())) {//国债期货当日结算价
				newspaperList = newspaperService.queryVCBONDFUTURESEODPRICESList(newspaperEntity);
			}
			if("3".equals(newspaperEntity.getStype())) {//国债期货品种排名 
				newspaperList = newspaperService.queryVCBONDFUTURESPOSITIONSDList(newspaperEntity);
			}
		}
		String tmp = newspaperEntity.getDateinfoS();
		if(null != tmp && tmp.length() > 0) {
			tmp = tmp.replaceAll("-", "");
			newspaperEntity.setDateinfoS(tmp.substring(0, 4) + "-" + tmp.substring(4, 6) + "-" + tmp.substring(6, 8));
		}
		tmp = newspaperEntity.getDateinfoE();
		if(null != tmp && tmp.length() > 0) {
			tmp = tmp.replaceAll("-", "");
			newspaperEntity.setDateinfoE(tmp.substring(0, 4) + "-" + tmp.substring(4, 6) + "-" + tmp.substring(6, 8));
		}
        return R.ok().put("newspaperList", newspaperList).put("newspaperEntity", newspaperEntity);
    }
   
    
    @RequestMapping("/export")
    @RequiresPermissions("generator:newspaper:list")
    public ResponseEntity<byte[]> export(@RequestBody NewspaperEntity newspaperEntity ){
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
    /**
     * 日报-列表
     */
//    @AuthIgnore
//    @RequestMapping("/look")
//    @RequiresPermissions("generator:newspaper:look")
//    public R look(@RequestBody NewspaperEntity newspaperEntity){
//		List<NewspaperEntity> listZQJD = null, listVCBONDFUTURESEODPRICES=null,listVCBONDFUTURESPOSITIONSD=null;
//		//银行间每日债券借贷
//		listZQJD = newspaperService.queryZQJDList(newspaperEntity);
//		//国债期货当日结算价
//		listVCBONDFUTURESEODPRICES = newspaperService.queryVCBONDFUTURESEODPRICESList(newspaperEntity);
//		//国债期货品种排名 
//		listVCBONDFUTURESPOSITIONSD = newspaperService.queryVCBONDFUTURESPOSITIONSDList(newspaperEntity);
//        return R.ok().put("listZQJD", listZQJD).put("listVCBONDFUTURESEODPRICES", listVCBONDFUTURESEODPRICES).put("listVCBONDFUTURESPOSITIONSD", listVCBONDFUTURESPOSITIONSD).put("newspaperEntity", newspaperEntity);
//    }


    /**
     * 信息
     */
    @RequestMapping("/info/{NewspaperId}")
    @RequiresPermissions("generator:Newspaper:info")
    public R info(@PathVariable("NewspaperId") Long NewspaperId){
    	NewspaperEntity newspaper = null;
		//NewspaperEntity Newspaper = NewspaperService.selectById(NewspaperId);

        return R.ok().put("newspaper", newspaper);
    }

    /**
     * 推送消息
     */
    @RequestMapping("/send")
    @RequiresPermissions("generator:newspaper:send")
    public R sendMessage(@RequestBody NewspaperEntity newspaperEntity){
		//NewspaperService.insert(Newspaper);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:Newspaper:update")
    public R update(@RequestBody NewspaperEntity newspaper){
		//NewspaperService.updateById(newspaper);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:Newspaper:delete")
    public R delete(@RequestBody Long[] NewspaperIds){
		//NewspaperService.deleteBatchIds(Arrays.asList(NewspaperIds));

        return R.ok();
    }

}
