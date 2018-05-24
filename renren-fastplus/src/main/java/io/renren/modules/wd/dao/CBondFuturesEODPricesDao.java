package io.renren.modules.wd.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.modules.wd.entity.CBondFuturesEODPricesEntity;
/**
 * 万德数据库CBONDFUTURESEODPRICES表
 * service
 * @author DHB
 * @date 2018/5/14
 */
@Mapper
public interface CBondFuturesEODPricesDao extends BaseDao<CBondFuturesEODPricesEntity>{
	CBondFuturesEODPricesEntity queryFirst(Map<String, Object> map);
	List<CBondFuturesEODPricesEntity> queryByTdDate(Map<String, Object> map);
}
