package io.renren.modules.generator.dao;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.generator.entity.ZqJobAttachEntity;
import io.renren.modules.sys.dao.BaseDao;

/**
 * 定时附加信息表 DAO
 * 
 * @author huqin
 * @email qin.hu@newtouch.cn
 * @date 2018-06-08 08:51:32
 */
@Mapper
public interface ZqJobAttachDao extends BaseDao<ZqJobAttachEntity> {
	
	
}
