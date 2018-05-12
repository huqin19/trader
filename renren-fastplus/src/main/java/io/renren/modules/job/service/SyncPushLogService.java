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
	 * 查询推送同步日志列表
	 */
	List<SyncPushLogEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存定时任务日志
	 */
	void save(SyncPushLogEntity log);
	
	/**
	 * 根据id删除定时任务日志
	 */
	void delete(Long id);
	
	/**
	 * 根据id批量删除定时任务日志
	 */
	void deleteBatch(Long[] ids);
}
