package io.renren.modules.ht.service;

import java.util.List;
import java.util.Map;

import io.renren.modules.ht.entity.TCalendarDatesEntity;
import io.renren.modules.wd.entity.CBondFuturesEODPricesEntity;
/**
 * 恒泰数据库TCALENDAR_DATES表
 * @author DHB
 * @date 2018/5/15
 */
public interface TCalendarDatesService {
	/**
	 * 通过对象code查询
	 * @param 
	 * @return
	 */
	List<TCalendarDatesEntity> queryCode(Map<String, Object> map);
	/**
	 * 分页查询
	 * @return
	 */
	List<TCalendarDatesEntity> queryList(Map<String, Object> map);
	/**
	 * 查询所有
	 * @param map
	 * @return
	 */
	List<TCalendarDatesEntity> queryAll(Map<String, Object> map);
	/**
	 * 删除所有
	 */
	void deleteAll();
	/**
	 * 保存
	 */
	void save(TCalendarDatesEntity tCalendarDates);
	
	/**
	 * 清空后保存
	 * @param list
	 */
	void deleteAllThenSave(List<TCalendarDatesEntity> list);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	/**
	 * 批量插入
	 * @param list
	 */
	void saveBatch(List<TCalendarDatesEntity> list);
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
	void firstSaveBatch(List<TCalendarDatesEntity> list);
}
