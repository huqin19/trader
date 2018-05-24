package io.renren.modules.ht.dao;

import java.util.List;

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
	List<TBNDEntity> queryCode(String iCode);
}
