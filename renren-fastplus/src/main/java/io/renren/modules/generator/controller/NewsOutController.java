package io.renren.modules.generator.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		if((newspaperEntity.getStype() != null && newspaperEntity.getStype().equals("1"))
				||
		   (newspaperEntity.getStype() != null && newspaperEntity.getStype().equals("2"))  ) {
			//银行间每日债券借贷
			listZQJD = newspaperService.queryZQJDList(newspaperEntity);
			//国债期货当日结算价
			listVCBONDFUTURESEODPRICES = newspaperService.queryVCBONDFUTURESEODPRICESList(newspaperEntity);
			sInfoWindcodes = newspaperService.queryHYNameOfVCBONDFUTURESEODPRICES(newspaperEntity);
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
        return R.ok().put("listZQJD", listZQJD).put("listVCBONDFUTURESEODPRICES", listVCBONDFUTURESEODPRICES)
        		.put("listVCBONDFUTURESPOSITIONSD", listVCBONDFUTURESPOSITIONSD).put("newspaperEntity", newspaperEntity)
        		.put("dateLists", dateLists)
        		.put("priceLists", priceLists)
        		.put("namehy", namehy);
    }


}
