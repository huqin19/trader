package io.renren.modules.generator.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.generator.entity.NewspaperEntity;
import io.renren.modules.sys.dao.BaseDao;

/**
 * 固收日报查询
 * 
 * @author milk
 * @email 
 * @date 2018-05-16 14:23:34
 */
@Mapper
public interface NewspaperDao extends BaseDao<NewspaperEntity> {
	
	List<NewspaperEntity> queryZQJDList(Map<String, Object> map);
	
	List<NewspaperEntity> queryVCBONDFUTURESPOSITIONSDList(Map<String, Object> map);
	
	List<NewspaperEntity> queryVCBONDFUTURESEODPRICESList(Map<String, Object> map);
	
}
