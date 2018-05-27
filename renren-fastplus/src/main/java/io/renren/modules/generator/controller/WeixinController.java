package io.renren.modules.generator.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;
import io.renren.modules.generator.entity.WeixinEntity;
import io.renren.modules.generator.service.WeixinService;
import io.renren.modules.sys.entity.SysRoleEntity;



/**
 * 微信推送
 *
 * @author milk
 * @email 
 * @date 2018-05-16 14:23:34
 */
@RestController
@RequestMapping("generator/weixin")
public class WeixinController {
    @Autowired
    private WeixinService weixinService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:weixin:list")
    public R list(@RequestParam Map<String, Object> params){
		//Query query = new Query(new HashMap<String, Object>());
		List<WeixinEntity> weixinList = weixinService.queryList(new HashMap<String, Object>());

        return R.ok().put("weixinList", weixinList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{weixinId}")
    @RequiresPermissions("generator:weixin:info")
    public R info(@PathVariable("weixinId") Long weixinId){
    	WeixinEntity weixin = null;
		//WeixinEntity weixin = weixinService.selectById(weixinId);

        return R.ok().put("weixin", weixin);
    }

    /**
     * 推送消息
     */
    @RequestMapping("/send")
    @RequiresPermissions("generator:weixin:send")
    public R sendMessage(@RequestBody WeixinEntity weixinEntity){
		//weixinService.insert(weixin);
    	if(null != weixinEntity) {
    		if(null != weixinEntity.getTitleArr() && weixinEntity.getTitleArr().length > 0) {
    			for(String x:weixinEntity.getTitleArr()) {
    				System.out.println(x);
    			}
    		}
    		if(null != weixinEntity.getNewtreeName() && weixinEntity.getNewtreeName().length > 0) {
    			for(String y:weixinEntity.getNewtreeName()) {
    				System.out.println(y);
    			}
    		}
    	}
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:weixin:update")
    public R update(@RequestBody WeixinEntity weixin){
		//weixinService.updateById(weixin);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:weixin:delete")
    public R delete(@RequestBody Long[] weixinIds){
		//weixinService.deleteBatchIds(Arrays.asList(weixinIds));

        return R.ok();
    }

}
