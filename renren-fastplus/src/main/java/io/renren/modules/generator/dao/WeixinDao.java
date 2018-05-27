package io.renren.modules.generator.dao;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.generator.entity.WeixinEntity;
import io.renren.modules.sys.dao.BaseDao;

/**
 * 微信推送
 * 
 * @author milk
 * @email 
 * @date 2018-05-16 14:23:34
 */
@Mapper
public interface WeixinDao extends BaseDao<WeixinEntity> {
	
	
}
