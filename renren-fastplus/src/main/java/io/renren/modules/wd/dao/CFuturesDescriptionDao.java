package io.renren.modules.wd.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.modules.wd.entity.CFuturesDescriptionEntity;

@Mapper
public interface CFuturesDescriptionDao extends BaseDao<CFuturesDescriptionEntity>{
	void deleteAll();
	List<CFuturesDescriptionEntity> queryAll(Map<String, Object> map);
}
