package io.renren.modules.wd.service;

import java.util.List;
import java.util.Map;

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
}
