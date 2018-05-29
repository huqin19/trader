package io.renren.modules.wd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.modules.wd.dao.CBondFuturesPositionsDao;
import io.renren.modules.wd.entity.CBondFuturesPositionsEntity;
import io.renren.modules.wd.entity.CFuturesDescriptionEntity;
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
	public CBondFuturesPositionsEntity queryFirst(Map<String, Object> map) {
		return cBondFuturesPositionsDao.queryFirst(map);
	}

	@Override
	public List<CBondFuturesPositionsEntity> queryList(Map<String, Object> map) {
		return cBondFuturesPositionsDao.queryList(map);
	}

	@Override
	public List<CBondFuturesPositionsEntity> queryByTdDate(Map<String, Object> map) {
		return cBondFuturesPositionsDao.queryByTdDate(map);
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

	@Override
	public List<CBondFuturesPositionsEntity> queryAll(Map<String, Object> map) {
		return cBondFuturesPositionsDao.queryAll(map);
	}

	@Override
	public List<CBondFuturesPositionsEntity> queryLatest(Map<String, Object> map) {
		return cBondFuturesPositionsDao.queryLatest(map);
	}

	@Override
	public void deleteAll() {
		cBondFuturesPositionsDao.deleteAll();
	}

	@Override
	@Transactional
	public void deleteAllThenSave(List<CBondFuturesPositionsEntity> list) {
		cBondFuturesPositionsDao.deleteAll();
		for(CBondFuturesPositionsEntity cd : list) {
			cBondFuturesPositionsDao.save(cd);
		}
	}


}
