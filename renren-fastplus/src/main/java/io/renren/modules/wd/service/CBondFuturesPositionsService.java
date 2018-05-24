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
	 * 通过对象ID查询
	 * @param objectId
	 * @return
	 */
	CBondFuturesPositionsEntity queryObject(String objectId);
	/**
	 * 通过交易日期查询
	 * @return
	 */
	List<CBondFuturesPositionsEntity> queryList(Map<String, Object> map);
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	/**
	 * 保存
	 */
	void save(CBondFuturesPositionsEntity cBondFuturesPositionsEntity);
}
