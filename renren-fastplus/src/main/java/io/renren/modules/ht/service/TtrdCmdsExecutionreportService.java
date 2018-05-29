package io.renren.modules.ht.service;

import java.util.List;
import java.util.Map;

import io.renren.modules.ht.entity.TtrdCmdsExecutionreportEntity;

/**
 * @author DHB
 * @date 2018年5月29日上午2:23:17
 * 恒泰TTRD_CMDS_EXECUTIONREPORT表
 */
public interface TtrdCmdsExecutionreportService {
	/**
	 * 查询所有
	 * @param map
	 * @return
	 */
	List<TtrdCmdsExecutionreportEntity> queryAll();
	/**
	 * 删除所有
	 */
	void deleteAll();
	/**
	 * 保存
	 */
	void save(TtrdCmdsExecutionreportEntity ttrdCmdsExecutionreportEntity);
	/**
	 * 清空后保存
	 * @param list
	 */
	void deleteAllThenSave(List<TtrdCmdsExecutionreportEntity> list);
	
	/**
	 * 查询总数
	 */
	int queryTotal();
}