package io.renren.modules.wd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.modules.wd.dao.CFuturesDescriptionDao;
import io.renren.modules.wd.entity.CFuturesDescriptionEntity;
import io.renren.modules.wd.service.CFuturesDescriptionService;
/**
 * 万德数据库表CFUTURESDESCRIPTION
 * @author DHB
 * @date 2018年5月18日上午11:00:58
 */
@Service("cFuturesDescriptionService")
public class CFuturesDescriptionServiceImpl implements CFuturesDescriptionService{
	@Autowired
	private CFuturesDescriptionDao cFuturesDescriptionDao;

	@Override
	public List<CFuturesDescriptionEntity> queryAll(Map<String, Object> map) {
		return cFuturesDescriptionDao.queryAll(map);
	}

	@Override
	public void deleteAll() {
		cFuturesDescriptionDao.deleteAll();
	}

	@Override
	@Transactional
	public void save(CFuturesDescriptionEntity cFuturesDescriptionEntity) {
		cFuturesDescriptionDao.save(cFuturesDescriptionEntity);
	}

	@Override
	@Transactional
	public void deleteAllThenSave(List<CFuturesDescriptionEntity> list) {
		cFuturesDescriptionDao.deleteAll();
		for(CFuturesDescriptionEntity cf : list) {
			cFuturesDescriptionDao.save(cf);
		}
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return cFuturesDescriptionDao.queryTotal(map);
	}

	@Override
	public CFuturesDescriptionEntity queryFirst(Map<String, Object> map) {
		return cFuturesDescriptionDao.queryFirst(map);
	}

	@Override
	public List<CFuturesDescriptionEntity> queryLatest(Map<String, Object> map) {
		return cFuturesDescriptionDao.queryLatest(map);
	}

	@Override
	public List<CFuturesDescriptionEntity> queryList(Map<String, Object> map) {
		return cFuturesDescriptionDao.queryList(map);
	}
}
