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
	 * 通过对象ID查询表内容
	 * @param objectId
	 * @return
	 */
	CBondFuturesEODPricesEntity queryObject(String objectId);
	/**
	 * 通过交易日期查询
	 * @return
	 */
	List<CBondFuturesEODPricesEntity> queryList(Map<String, Object> map);
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	/**
	 * 保存
	 */
	void save(CBondFuturesEODPricesEntity cBondFuturesEODPricesEntity);
	
}
