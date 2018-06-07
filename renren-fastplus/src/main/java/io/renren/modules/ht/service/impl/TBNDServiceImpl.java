package io.renren.modules.ht.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.modules.ht.dao.TBNDDao;
import io.renren.modules.ht.entity.TBNDEntity;
import io.renren.modules.ht.service.TBNDService;

/**
*@author DHB
*@version 2018年5月18日下午12:46:42
*恒泰TBND表
*/
@Service("tBNDService")
public class TBNDServiceImpl implements TBNDService{
	@Autowired
	private TBNDDao tBNDao;

	@Override
	@Transactional
	public void save(TBNDEntity tBNDEntity) {
		tBNDao.save(tBNDEntity);
	}

	@Override
	public List<TBNDEntity> queryAll(Map<String, Object> map) {
		return tBNDao.queryAll(map);
	}

	@Override
	public void deleteAll() {
		tBNDao.deleteAll();
	}

	@Override
	@Transactional
	public void deleteAllThenSave(List<TBNDEntity> list) {
		tBNDao.deleteAll();
		for(TBNDEntity tb : list) {
			tBNDao.save(tb);
			
		}
	}
	@Override
	public int queryTotal(Map<String, Object> map) {
		return tBNDao.queryTotal(map);
	}
	@Override
	public TBNDEntity queryFirst(Map<String, Object> map) {
		return tBNDao.queryFirst(map);
	}
	@Override
	public List<TBNDEntity> queryLatest(Map<String, Object> map) {
		return tBNDao.queryLatest(map);
	}

	@Override
	@Transactional
	public void saveBatch(List<TBNDEntity> list) {
		for(TBNDEntity t : list) {
			t.setStatus(0);
		}
		tBNDao.saveBatch(list);
	}

	@Override
	@Transactional
	public void updateStatus() {
		tBNDao.updateStatus();
	}

	@Override
	@Transactional
	public void deleteStatus() {
		tBNDao.deleteStatus();
	}

	@Override
	@Transactional
	public void firstSaveBatch(List<TBNDEntity> list) {
		for(TBNDEntity t : list) {
			t.setStatus(1);
		}
		tBNDao.saveBatch(list);
	}
	@Override
	public List<TBNDEntity> queryList(Map<String, Object> map) {
		return tBNDao.queryList(map);
	}
}
