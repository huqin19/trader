package io.renren.modules.generator.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.generator.entity.NewspaperEntity;
import io.renren.modules.sys.dao.BaseDao;

/**
 * 固收日报查询
 * 
 * @author milk
 * @email 
 * @date 2018-05-16 14:23:34
 */
@Mapper
public interface NewspaperDao extends BaseDao<NewspaperEntity> {
	
	List<NewspaperEntity> queryZQJDList(NewspaperEntity newspaperEntity);
	
	List<NewspaperEntity> queryVCBONDFUTURESPOSITIONSDList(NewspaperEntity newspaperEntity);
	
	List<NewspaperEntity> queryVCBONDFUTURESEODPRICESList(NewspaperEntity newspaperEntity);
	
	/**
	 * 国债期货当日结算价 某年+某个合约 的折线图
	 */
	List<NewspaperEntity> queryVCBONDFUTURESEODPRICESListByYYYYMM(NewspaperEntity newspaperEntity);
	
	/**
	 * 获取 国债期货品种排名 合约名称 
	 */
	List<String> queryHYNameOfVCBONDFUTURESPOSITIONSD(NewspaperEntity newspaperEntity);
	
	/**
	 * 获取 国债期货当日结算价 合约名称 
	 */
	List<String> queryHYNameOfVCBONDFUTURESEODPRICES(NewspaperEntity newspaperEntity);
	
}
