package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils2.PageUtils;
import io.renren.modules.generator.entity.GoodsEntity;

import java.util.Map;

/**
 * 商品管理; InnoDB free: 4096 kB
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-16 14:23:34
 */
public interface GoodsService extends IService<GoodsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

