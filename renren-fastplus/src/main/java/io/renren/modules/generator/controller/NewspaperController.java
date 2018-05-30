package io.renren.modules.generator.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return R.ok().put("newspaperList", newspaperList).put("newspaperEntity", newspaperEntity);
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
