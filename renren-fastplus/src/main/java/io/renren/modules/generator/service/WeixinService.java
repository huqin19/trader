package io.renren.modules.generator.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import io.renren.modules.generator.entity.WeixinEntity;
import io.renren.modules.generator.entity.ZqSheetsEntity;

/**
 * 微信推送
 *
 * @author milk
 * @email 
 * @date 2018-05-16 14:23:34
 */
public interface WeixinService {

	/**
	 * 查询人员ztree
	 */
	List<WeixinEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询日报ztree
	 */
	List<WeixinEntity> querySheetList(Map<String, Object> map);
	
	/**
	 * 根据主键查询日报
	 * @param objectId
	 * @return
	 */
	public ZqSheetsEntity queryZqSheetsObject(BigDecimal objectId);
}

