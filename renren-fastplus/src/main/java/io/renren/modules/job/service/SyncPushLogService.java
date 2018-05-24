package io.renren.modules.job.service;

import java.util.List;
import java.util.Map;
import io.renren.modules.job.entity.SyncPushLogEntity;

/**
 * 推送同步日志
 * @author DHB
 * @date 2018年5月9日
 */

public interface SyncPushLogService {
	/**
	 * 根据ID，查询推送同步日志
	 */
	SyncPushLogEntity queryObject(Long id);
	
	/**
	 * 根据日期查询推送同步日志列表
	 */
	List<SyncPushLogEntity> queryList(Map<String, Object> map);
	
	
	/**
	 * 保存定时任务日志
	 */
	void save(SyncPushLogEntity log);
	
}
