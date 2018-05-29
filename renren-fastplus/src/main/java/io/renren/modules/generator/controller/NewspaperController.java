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
    public R list(@RequestParam Map<String, Object> params){
		//Query query = new Query(new HashMap<String, Object>());
		List<NewspaperEntity> newspaperList = newspaperService.queryZQJDList(new HashMap<String, Object>());

        return R.ok().put("newspaperList", newspaperList);
    }


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
