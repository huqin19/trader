package io.renren.modules.wd.service;

import java.util.List;
import java.util.Map;

import io.renren.modules.wd.entity.CBondRatingEntity;
/**
 * 万德数据库CBONDRATING表
 * Service
 * @author DHB
 * @date 2018/5/15
 */
public interface CBondRatingService {
	/**
	 * 查询所有
	 * @param map
	 * @return
	 */
	List<CBondRatingEntity> queryAll(Map<String, Object> map);
	/**
	 * 删除所有
	 */
	void deleteAll();
	/**
	 * 保存
	 */
	void save(CBondRatingEntity cBondRatingEntity);
	/**
	 * 清空后保存
	 * @param list
	 */
	void deleteAllThenSave(List<CBondRatingEntity> list);
}