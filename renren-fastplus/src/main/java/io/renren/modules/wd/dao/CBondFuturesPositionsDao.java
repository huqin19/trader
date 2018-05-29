package io.renren.modules.wd.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.modules.wd.entity.CBondFuturesPositionsEntity;

@Mapper
public interface CBondFuturesPositionsDao extends BaseDao<CBondFuturesPositionsEntity>{
	CBondFuturesPositionsEntity queryFirst(Map<String, Object> map);
	List<CBondFuturesPositionsEntity> queryByTdDate(Map<String, Object> map);
	List<CBondFuturesPositionsEntity> queryAll(Map<String, Object> map);
	List<CBondFuturesPositionsEntity> queryLatest(Map<String, Object> map);
	void deleteAll();
}
