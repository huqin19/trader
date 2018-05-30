package io.renren.modules.generator.controller;

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
		List<NewspaperEntity> listZQJD = null, listVCBONDFUTURESEODPRICES=null,listVCBONDFUTURESPOSITIONSD=null;
		if(newspaperEntity.getStype() != null && newspaperEntity.getStype().equals("1")) {
			//银行间每日债券借贷
			listZQJD = newspaperService.queryZQJDList(newspaperEntity);
		}
		if(newspaperEntity.getStype() != null && newspaperEntity.getStype().equals("2")) {
			//国债期货当日结算价
			listVCBONDFUTURESEODPRICES = newspaperService.queryVCBONDFUTURESEODPRICESList(newspaperEntity);
		}
		if(newspaperEntity.getStype() != null && newspaperEntity.getStype().equals("3")) {
			//国债期货品种排名 
			listVCBONDFUTURESPOSITIONSD = newspaperService.queryVCBONDFUTURESPOSITIONSDList(newspaperEntity);
		}
        return R.ok().put("listZQJD", listZQJD).put("listVCBONDFUTURESEODPRICES", listVCBONDFUTURESEODPRICES).put("listVCBONDFUTURESPOSITIONSD", listVCBONDFUTURESPOSITIONSD).put("newspaperEntity", newspaperEntity);
    }


}
