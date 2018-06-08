package io.renren.modules.generator.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.generator.entity.WeixinEntity;
import io.renren.modules.generator.entity.ZqSheetsEntity;
import io.renren.modules.sys.dao.BaseDao;

/**
 * 报表 DAO
 * 
 * @author huqin
 * @email qin.hu@newtouch.cn
 * @date 2018-06-08 08:51:32
 */
@Mapper
public interface ZqSheetsDao extends BaseDao<ZqSheetsEntity> {
	
	List<WeixinEntity> querySheetList(Map<String, Object> map);
}
