package io.renren.modules.generator.service;

import java.util.List;

import io.renren.modules.generator.entity.NewspaperEntity;

/**
 * 微信推送
 *
 * @author milk
 * @email 
 * @date 2018-05-16 14:23:34
 */
public interface NewspaperService {

	/**
	 * 银行间每日借贷 查询
	 */
	List<NewspaperEntity> queryZQJDList(NewspaperEntity newspaperEntity);
	
	/**
	 * 国债期货品种排名 查询
	 */
	List<NewspaperEntity> queryVCBONDFUTURESPOSITIONSDList(NewspaperEntity newspaperEntity);
	
	/**
	 * 国债期货当日结算价 查询
	 */
	List<NewspaperEntity> queryVCBONDFUTURESEODPRICESList(NewspaperEntity newspaperEntity);
}

