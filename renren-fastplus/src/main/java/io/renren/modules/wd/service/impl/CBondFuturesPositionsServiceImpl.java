package io.renren.modules.wd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.modules.wd.dao.CBondFuturesPositionsDao;
import io.renren.modules.wd.entity.CBondFuturesPositionsEntity;
import io.renren.modules.wd.service.CBondFuturesPositionsService;
/**
 * 万德数据库CBONDFUTURESPOSITIONS
 * @author DHB
 * Last_update2018年5月18日上午10:59:25
 */
@Service("cBondFuturesPositionsService")
public class CBondFuturesPositionsServiceImpl implements CBondFuturesPositionsService {
	@Autowired
	private CBondFuturesPositionsDao cBondFuturesPositionsDao;
	@Override
	public CBondFuturesPositionsEntity queryObject(String objectId) {
		return cBondFuturesPositionsDao.queryObject(objectId);
	}

	@Override
	public List<CBondFuturesPositionsEntity> queryList(Map<String, Object> map) {
		return cBondFuturesPositionsDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return cBondFuturesPositionsDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(CBondFuturesPositionsEntity cBondFuturesPositionsEntity) {
		cBondFuturesPositionsDao.save(cBondFuturesPositionsEntity);
	}

}
