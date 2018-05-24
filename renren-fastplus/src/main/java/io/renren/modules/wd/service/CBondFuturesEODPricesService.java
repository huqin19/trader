package io.renren.modules.wd.service;

import java.util.List;
import java.util.Map;

import io.renren.modules.wd.entity.CBondFuturesEODPricesEntity;
/**
 * 万德数据库CBONDFUTURESEODPRICES表
 * service
 * @author DHB
 * @date 2018/5/14
 */
public interface CBondFuturesEODPricesService {
	/**
	 * 查询最近一条数据
	 * @param map
	 * @return
	 */
	CBondFuturesEODPricesEntity queryFirst(Map<String, Object> map);
	/**
	 * 通过交易日期区间查询
	 * @return
	 */
	List<CBondFuturesEODPricesEntity> queryList(Map<String, Object> map);
	/**
	 * 通过交易日期查询
	 * @return
	 */
	List<CBondFuturesEODPricesEntity> queryByTdDate(Map<String, Object> map);
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	/**
	 * 保存
	 */
	void save(CBondFuturesEODPricesEntity cBondFuturesEODPricesEntity);
	
}
