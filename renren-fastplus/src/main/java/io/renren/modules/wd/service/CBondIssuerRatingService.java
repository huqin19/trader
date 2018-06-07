package io.renren.modules.wd.service;

import java.util.List;
import java.util.Map;

import io.renren.modules.wd.entity.CBondFuturesEODPricesEntity;
import io.renren.modules.wd.entity.CBondFuturesPositionsEntity;
import io.renren.modules.wd.entity.CBondIssuerRatingEntity;
/**
 * 万德数据库表CBONDISSUERRATING
 * @author DHB
 * @date 2018年5月18日上午11:03:35
 */
public interface CBondIssuerRatingService {
	/**
	 * 查询所有
	 * @param map
	 * @return
	 */
	List<CBondIssuerRatingEntity> queryAll(Map<String, Object> map);
	/**
	 * 删除所有
	 */
	void deleteAll();
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	/**
	 * 保存
	 */
	void save(CBondIssuerRatingEntity cBondIssuerRatingEntity);
	/**
	 * 清空后保存
	 * @param list
	 */
	void deleteAllThenSave(List<CBondIssuerRatingEntity> list);
	/**
	 * 查询最近一条数据
	 * @param map
	 * @return
	 */
	CBondIssuerRatingEntity queryFirst(Map<String, Object> map);
	/**
	 * 查询所有日期最近的
	 * @param map
	 * @return
	 */
	List<CBondIssuerRatingEntity> queryLatest(Map<String, Object> map);
	/**
	 * 分页查询
	 * @return
	 */
	List<CBondIssuerRatingEntity> queryList(Map<String, Object> map);
	/**
	 * 批量插入
	 * @param list
	 */
	void saveBatch(List<CBondIssuerRatingEntity> list);
	/**
	 * 批量更新状态
	 */
	void updateStatus();
	/**
	 * 删除无效状态
	 */
	void deleteStatus();
	/**
	 * 批量插入
	 * @param list
	 */
	void firstSaveBatch(List<CBondIssuerRatingEntity> list);
}
