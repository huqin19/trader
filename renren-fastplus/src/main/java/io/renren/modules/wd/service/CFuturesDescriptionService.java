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
	 * 通过对象ID查询表内容
	 * @param objectId
	 * @return
	 */
	CFuturesDescriptionEntity queryObject(String objectId);
	/**
	 * 通过日期查询
	 * @return
	 */
	List<CFuturesDescriptionEntity> queryList(Map<String, Object> map);
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	/**
	 * 保存
	 */
	void save(CFuturesDescriptionEntity cFuturesDescriptionEntity);
}
