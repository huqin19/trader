package io.renren.modules.ht.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.ht.entity.TBNDEntity;
import io.renren.modules.sys.dao.BaseDao;


/**
*@author DHB
*@version 2018年5月18日下午12:42:35
*恒泰数据库TBND表
*/
@Mapper
public interface TBNDDao extends BaseDao<TBNDEntity>{
	void deleteAll();
	List<TBNDEntity> queryAll(Map<String, Object> map);
	TBNDEntity queryFirst(Map<String, Object> map);
	List<TBNDEntity> queryLatest(Map<String, Object> map);
	
}
