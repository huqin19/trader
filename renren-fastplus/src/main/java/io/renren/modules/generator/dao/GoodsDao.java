package io.renren.modules.generator.dao;

import io.renren.modules.generator.entity.GoodsEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品管理; InnoDB free: 4096 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-16 14:23:34
 */
@Mapper
public interface GoodsDao extends BaseMapper<GoodsEntity> {
	
}
