package io.renren.modules.wd.service;

import java.util.List;
import java.util.Map;

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
}
