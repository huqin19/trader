package io.renren.modules.ht.service;

import java.util.List;

import io.renren.modules.ht.entity.TBNDEntity;

/**
*@author DHB
*@version 2018年5月18日上午11:13:07
*恒泰TBND表
*/
public interface TBNDService {
	/**
	 * 通过对象code查询
	 * @param 
	 * @return
	 */
	List<TBNDEntity> queryCode(String iCode);
	/**
	 * 保存
	 * @param tBNDEntity
	 */
	void save(TBNDEntity tBNDEntity);
}
