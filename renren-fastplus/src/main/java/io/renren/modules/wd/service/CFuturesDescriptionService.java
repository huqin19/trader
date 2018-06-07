package io.renren.modules.wd.service;

import java.util.List;
import java.util.Map;

import io.renren.modules.wd.entity.CBondFuturesEODPricesEntity;
import io.renren.modules.wd.entity.CBondRatingEntity;
import io.renren.modules.wd.entity.CFuturesDescriptionEntity;

/**
 * 万德数据库表CFUTURESDESCRIPTION
 * @author DHB
 * @date 2018/5/15
 */

public interface CFuturesDescriptionService {
	/**
	 * 查询所有
	 * @param map
	 * @return
	 */
	List<CFuturesDescriptionEntity> queryAll(Map<String, Object> map);
	/**
	 * 删除所有
	 */
	void deleteAll();
	/**
	 * 保存
	 */
	void save(CFuturesDescriptionEntity cFuturesDescriptionEntity);
	/**
	 * 清空后保存
	 * @param list
	 */
	void deleteAllThenSave(List<CFuturesDescriptionEntity> list);
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	/**
	 * 查询最近一条数据
	 * @param map
	 * @return
	 */
	CFuturesDescriptionEntity queryFirst(Map<String, Object> map);
	/**
	 * 查询所有日期最近的
	 * @param map
	 * @return
	 */
	List<CFuturesDescriptionEntity> queryLatest(Map<String, Object> map);
	/**
	 * 分页查询
	 * @return
	 */
	List<CFuturesDescriptionEntity> queryList(Map<String, Object> map);
	/**
	 * 批量插入
	 * @param list
	 */
	void saveBatch(List<CFuturesDescriptionEntity> list);
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
	void firstSaveBatch(List<CFuturesDescriptionEntity> list);
}
