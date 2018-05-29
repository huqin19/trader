package io.renren.modules.wd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.modules.wd.dao.CBondRatingDao;
import io.renren.modules.wd.entity.CBondRatingEntity;
import io.renren.modules.wd.service.CBondRatingService;

/**
 * 万德数据库CBONDRATING表
 * @author DHB
 * @date 2018/5/15
 */
@Service("cBondRatingService")
public class CBondRatingServiceImpl implements CBondRatingService{
	@Autowired
	private CBondRatingDao cBondRatingDao;

	@Override
	public List<CBondRatingEntity> queryAll(Map<String, Object> map) {
		return cBondRatingDao.queryAll(map);
	}

	@Override
	public void deleteAll() {
		cBondRatingDao.deleteAll();
	}

	@Override
	@Transactional
	public void save(CBondRatingEntity cBondRatingEntity) {
		cBondRatingDao.save(cBondRatingEntity);
	}

	@Override
	@Transactional
	public void deleteAllThenSave(List<CBondRatingEntity> list) {
		cBondRatingDao.deleteAll();
		for(CBondRatingEntity cb : list) {
			cBondRatingDao.save(cb);
		}
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return cBondRatingDao.queryTotal(map);
	}

	@Override
	public CBondRatingEntity queryFirst(Map<String, Object> map) {
		return cBondRatingDao.queryFirst(map);
	}

	@Override
	public List<CBondRatingEntity> queryLatest(Map<String, Object> map) {
		return cBondRatingDao.queryLatest(map);
	}
	
}
