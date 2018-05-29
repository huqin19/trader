package io.renren.modules.ht.service;

import java.util.List;
import java.util.Map;

import io.renren.modules.ht.entity.TBNDEntity;

/**
*@author DHB
*@version 2018年5月18日上午11:13:07
*恒泰TBND表
*/
public interface TBNDService {
	/**
	 * 查询所有
	 * @param map
	 * @return
	 */
	List<TBNDEntity> queryAll(Map<String, Object> map);
	/**
	 * 删除所有
	 */
	void deleteAll();
	/**
	 * 保存
	 */
	void save(TBNDEntity tBNDEntity);
	/**
	 * 清空后保存
	 * @param list
	 */
	void deleteAllThenSave(List<TBNDEntity> list);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	/**
	 * 查询最近一条数据
	 * @param map
	 * @return
	 */
	TBNDEntity queryFirst(Map<String, Object> map);
	/**
	 * 查询所有日期最近的
	 * @param map
	 * @return
	 */
	List<TBNDEntity> queryLatest(Map<String, Object> map);
}
