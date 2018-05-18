package io.renren.modules.wd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public CBondRatingEntity queryObject(String objectId) {
		return cBondRatingDao.queryObject(objectId);
	}

	@Override
	public List<CBondRatingEntity> queryList(Map<String, Object> map) {
		return cBondRatingDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return cBondRatingDao.queryTotal(map);
	}

	@Override
	public void save(CBondRatingEntity cBondRatingEntity) {
		cBondRatingDao.save(cBondRatingEntity);
	}
	
}
