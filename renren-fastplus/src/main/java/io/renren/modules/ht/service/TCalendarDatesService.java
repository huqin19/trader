package io.renren.modules.ht.service;

import java.util.List;
import java.util.Map;

import io.renren.modules.ht.entity.TCalendarDatesEntity;
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
	 * 通过日期查询
	 * @param 
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
}
