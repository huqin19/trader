package io.renren.modules.ht.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.modules.ht.dao.TtrdCmdsExecutionreportDao;
import io.renren.modules.ht.entity.TBNDEntity;
import io.renren.modules.ht.entity.TtrdCmdsExecutionreportEntity;
import io.renren.modules.ht.service.TtrdCmdsExecutionreportService;

/**
 * @author DHB
 * @date 2018年5月29日上午2:25:48
 *
 */
@Service("ttrdCmdsExecutionreportService")
public class TtrdCmdsExecutionreportServiceImpl implements TtrdCmdsExecutionreportService{
	@Autowired
	private TtrdCmdsExecutionreportDao ttrdCmdsExecutionreportDao;

	@Override
	public List<TtrdCmdsExecutionreportEntity> queryAll() {
		return ttrdCmdsExecutionreportDao.queryAll();
	}

	@Override
	public void deleteAll() {
		ttrdCmdsExecutionreportDao.deleteAll();
	}

	@Override
	@Transactional
	public void save(TtrdCmdsExecutionreportEntity ttrdCmdsExecutionreportEntity) {
		ttrdCmdsExecutionreportDao.save(ttrdCmdsExecutionreportEntity);
	}

	@Override
	@Transactional
	public void deleteAllThenSave(List<TtrdCmdsExecutionreportEntity> list) {
		ttrdCmdsExecutionreportDao.deleteAll();
		for(TtrdCmdsExecutionreportEntity tt : list) {
			ttrdCmdsExecutionreportDao.save(tt);
		}
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return ttrdCmdsExecutionreportDao.queryTotal(map);
	}

	@Override
	public TtrdCmdsExecutionreportEntity queryFirst(Map<String, Object> map) {
		return ttrdCmdsExecutionreportDao.queryFirst(map);
	}

	@Override
	public List<TtrdCmdsExecutionreportEntity> queryLatest(Map<String, Object> map) {
		return ttrdCmdsExecutionreportDao.queryLatest(map);
	}

	@Override
	public List<TtrdCmdsExecutionreportEntity> queryList(Map<String, Object> map) {
		return ttrdCmdsExecutionreportDao.queryList(map);
	}

	@Override
	@Transactional
	public void saveBatch(List<TtrdCmdsExecutionreportEntity> list) {
		for(TtrdCmdsExecutionreportEntity t : list) {
			t.setStatus(0);
		}
		ttrdCmdsExecutionreportDao.saveBatch(list);
	}

	@Override
	@Transactional
	public void updateStatus() {
		ttrdCmdsExecutionreportDao.updateStatus();
	}

	@Override
	@Transactional
	public void deleteStatus() {
		ttrdCmdsExecutionreportDao.deleteStatus();
	}

	@Override
	public void firstSaveBatch(List<TtrdCmdsExecutionreportEntity> list) {
		for(TtrdCmdsExecutionreportEntity t : list) {
			t.setStatus(1);
		}
		ttrdCmdsExecutionreportDao.saveBatch(list);
	}

	
}
