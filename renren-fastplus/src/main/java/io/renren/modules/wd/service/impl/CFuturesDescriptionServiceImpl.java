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
	public CFuturesDescriptionEntity queryObject(String objectId) {
		return cFuturesDescriptionDao.queryObject(objectId);
	}

	@Override
	public List<CFuturesDescriptionEntity> queryList(Map<String, Object> map) {
		return cFuturesDescriptionDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return cFuturesDescriptionDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(CFuturesDescriptionEntity cFuturesDescriptionEntity) {
		cFuturesDescriptionDao.save(cFuturesDescriptionEntity);
	}
	
}
