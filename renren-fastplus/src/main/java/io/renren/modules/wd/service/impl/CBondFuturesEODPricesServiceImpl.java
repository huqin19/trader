package io.renren.modules.wd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.common.utils.DateUtils;
import io.renren.modules.wd.dao.CBondFuturesEODPricesDao;
import io.renren.modules.wd.entity.CBondFuturesEODPricesEntity;
import io.renren.modules.wd.entity.CFuturesDescriptionEntity;
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
	public List<CBondFuturesEODPricesEntity> queryList(Map<String, Object> map) {
		return cBondFuturesEODPricesDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return cBondFuturesEODPricesDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(CBondFuturesEODPricesEntity cBondFuturesEODPricesEntity) {
		cBondFuturesEODPricesDao.save(cBondFuturesEODPricesEntity);
	}

	@Override
	public CBondFuturesEODPricesEntity queryFirst(Map<String, Object> map) {
		return cBondFuturesEODPricesDao.queryFirst(map);
	}

	@Override
	public List<CBondFuturesEODPricesEntity> queryByTdDate(Map<String, Object> map) {
		return cBondFuturesEODPricesDao.queryByTdDate(map);
	}

	@Override
	public List<CBondFuturesEODPricesEntity> queryAll(Map<String, Object> map) {
		return cBondFuturesEODPricesDao.queryAll(map);
	}

	@Override
	public List<CBondFuturesEODPricesEntity> queryLatest(Map<String, Object> map) {
		return cBondFuturesEODPricesDao.queryLatest(map);
	}

	@Override
	@Transactional
	public void deleteAllThenSave(List<CBondFuturesEODPricesEntity> list) {
		cBondFuturesEODPricesDao.deleteAll();
		for(CBondFuturesEODPricesEntity cf : list) {
			cBondFuturesEODPricesDao.save(cf);
		}
	}

	@Override
	public void deleteAll() {
		cBondFuturesEODPricesDao.deleteAll();
	}
	
}
