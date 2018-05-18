package io.renren.modules.wd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.modules.wd.dao.CBondFuturesEODPricesDao;
import io.renren.modules.wd.entity.CBondFuturesEODPricesEntity;
import io.renren.modules.wd.service.CBondFuturesEODPricesService;

/**
 * 万德数据库CBONDFUTURESEODPRICES表
 * @author DHB
 * @date 2018/5/14
 */
@Service("cBondFuturesEODPricesService")
public class CBondFuturesEODPricesServiceImpl implements CBondFuturesEODPricesService{
	@Autowired
	private CBondFuturesEODPricesDao cBondFuturesEODPricesDao;
	@Override
	public CBondFuturesEODPricesEntity queryObject(String objectId) {
		return cBondFuturesEODPricesDao.queryObject(objectId);
	}

	@Override
	public List<CBondFuturesEODPricesEntity> queryList(Map<String, Object> map) {
		return cBondFuturesEODPricesDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return cBondFuturesEODPricesDao.queryTotal();
	}

	@Override
	@Transactional
	public void save(CBondFuturesEODPricesEntity cBondFuturesEODPricesEntity) {
		cBondFuturesEODPricesDao.save(cBondFuturesEODPricesEntity);
	}

}
