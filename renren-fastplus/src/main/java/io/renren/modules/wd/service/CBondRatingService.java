package io.renren.modules.wd.service;

import java.util.List;
import java.util.Map;

import io.renren.modules.wd.entity.CBondIssuerRatingEntity;
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
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	/**
	 * 查询最近一条数据
	 * @param map
	 * @return
	 */
	CBondRatingEntity queryFirst(Map<String, Object> map);
	/**
	 * 查询所有日期最近的
	 * @param map
	 * @return
	 */
	List<CBondRatingEntity> queryLatest(Map<String, Object> map);
	/**
	 * 分页查询
	 * @return
	 */
	List<CBondRatingEntity> queryList(Map<String, Object> map);
}
