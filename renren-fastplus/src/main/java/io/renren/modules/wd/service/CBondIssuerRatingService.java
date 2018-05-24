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
	 * 通过对象ID查询
	 * @param objectId
	 * @return
	 */
	CBondIssuerRatingEntity queryObject(String objectId);
	/**
	 * 通过交易日期查询
	 * @return
	 */
	List<CBondIssuerRatingEntity> queryList(Map<String, Object> map);
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	/**
	 * 保存
	 */
	void save(CBondIssuerRatingEntity cBondIssuerRatingEntity);
}
