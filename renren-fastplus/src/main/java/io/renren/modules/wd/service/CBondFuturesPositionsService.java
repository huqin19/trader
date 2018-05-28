package io.renren.modules.wd.service;

import java.util.List;
import java.util.Map;
import io.renren.modules.wd.entity.CBondFuturesPositionsEntity;
/**
 * 万德数据库CBONDFUTURESPOSITIONS表
 * service
 * @author DHB
 * @date 2018/5/14
 */
public interface CBondFuturesPositionsService {
	/**
	 * 查询最近一条数据
	 * @param map
	 * @return
	 */
	CBondFuturesPositionsEntity queryFirst(Map<String, Object> map);
	/**
	 * 通过交易日期区间查询
	 * @return
	 */
	List<CBondFuturesPositionsEntity> queryList(Map<String, Object> map);
	/**
	 * 通过交易日期查询
	 * @return
	 */
	List<CBondFuturesPositionsEntity> queryByTdDate(Map<String, Object> map);
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	/**
	 * 保存
	 */
	void save(CBondFuturesPositionsEntity cBondFuturesPositionsEntity);
	/**
	 * 查询所有
	 * @param map
	 * @return
	 */
	List<CBondFuturesPositionsEntity> queryAll(Map<String, Object> map);
	
	/**
	 * 查询日期最近的
	 * @param map
	 * @return
	 */
	List<CBondFuturesPositionsEntity> queryLatest(Map<String, Object> map);
}