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
	 * 通过对象ID查询表内容
	 * @param objectId
	 * @return
	 */
	CBondRatingEntity queryObject(String objectId);
	/**
	 * 通过交易日期查询
	 * @return
	 */
	List<CBondRatingEntity> queryList(Map<String, Object> map);
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	/**
	 * 保存
	 */
	void save(CBondRatingEntity cBondRatingEntity);
}
