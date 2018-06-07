package io.renren.modules.wd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.modules.wd.dao.CBondIssuerRatingDao;
import io.renren.modules.wd.entity.CBondIssuerRatingEntity;
import io.renren.modules.wd.service.CBondIssuerRatingService;
/**
 * 万得数据库表CBONDISSUERRATING
 * @author DHB
 * @date 2018年5月18日上午11:03:35
 */
@Service("cBondIssuerRatingService")
public class CBondIssuerRatingServiceImpl implements CBondIssuerRatingService{
	@Autowired
	private CBondIssuerRatingDao cBondIssuerRatingDao;

	@Override
	public int queryTotal(Map<String, Object> map) {
		return cBondIssuerRatingDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(CBondIssuerRatingEntity cBondIssuerRatingEntity) {
		cBondIssuerRatingDao.save(cBondIssuerRatingEntity);
	}

	@Override
	public List<CBondIssuerRatingEntity> queryAll(Map<String, Object> map) {
		return cBondIssuerRatingDao.queryAll(map);
	}

	@Override
	public void deleteAll() {
		cBondIssuerRatingDao.deleteAll();
	}

	@Override
	@Transactional
	public void deleteAllThenSave(List<CBondIssuerRatingEntity> list) {
		cBondIssuerRatingDao.deleteAll();
		for(CBondIssuerRatingEntity cb : list) {
			cBondIssuerRatingDao.save(cb);
		}
	}

	@Override
	public CBondIssuerRatingEntity queryFirst(Map<String, Object> map) {
		return cBondIssuerRatingDao.queryFirst(map);
	}

	@Override
	public List<CBondIssuerRatingEntity> queryLatest(Map<String, Object> map) {
		return cBondIssuerRatingDao.queryLatest(map);
	}

	@Override
	public List<CBondIssuerRatingEntity> queryList(Map<String, Object> map) {
		return cBondIssuerRatingDao.queryList(map);
	}
	@Override
	@Transactional
	public void saveBatch(List<CBondIssuerRatingEntity> list) {
		for(CBondIssuerRatingEntity c : list) {
			c.setStatus(0);
		}
		cBondIssuerRatingDao.saveBatch(list);
	}

	@Override
	@Transactional
	public void updateStatus() {
		cBondIssuerRatingDao.updateStatus();
	}

	@Override
	@Transactional
	public void deleteStatus() {
		cBondIssuerRatingDao.deleteStatus();
	}

	@Override
	@Transactional
	public void firstSaveBatch(List<CBondIssuerRatingEntity> list) {
		for(CBondIssuerRatingEntity c : list) {
			c.setStatus(1);
		}
		cBondIssuerRatingDao.saveBatch(list);
	}
}
