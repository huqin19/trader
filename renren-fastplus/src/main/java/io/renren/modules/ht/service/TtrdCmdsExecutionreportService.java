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
	int queryTotal(Map<String, Object> map);
	/**
	 * 查询最近一条数据
	 * @param map
	 * @return
	 */
	TtrdCmdsExecutionreportEntity queryFirst(Map<String, Object> map);
	/**
	 * 查询所有日期最近的
	 * @param map
	 * @return
	 */
	List<TtrdCmdsExecutionreportEntity> queryLatest(Map<String, Object> map);
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	List<TtrdCmdsExecutionreportEntity> queryList(Map<String, Object> map);
	
	/**
	 * 批量插入
	 * @param list
	 */
	void saveBatch(List<TtrdCmdsExecutionreportEntity> list);
	/**
	 * 批量更新状态
	 */
	void updateStatus();
	/**
	 * 删除无效状态
	 */
	void deleteStatus();
	/**
	 * 批量插入
	 * @param list
	 */
	void firstSaveBatch(List<TtrdCmdsExecutionreportEntity> list);
}
