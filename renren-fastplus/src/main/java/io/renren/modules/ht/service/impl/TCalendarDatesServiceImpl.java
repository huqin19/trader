package io.renren.modules.ht.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.modules.ht.dao.TCalendarDatesDao;
import io.renren.modules.ht.entity.TCalendarDatesEntity;
import io.renren.modules.ht.service.TCalendarDatesService;

@Service("tCalendarDatesService")
public class TCalendarDatesServiceImpl implements TCalendarDatesService{
	private TCalendarDatesDao tCalendarDatesDao;

	@Override
	public List<TCalendarDatesEntity> queryCode(Map<String, Object> map) {
		return tCalendarDatesDao.queryCode(map);
	}

	@Override
	public List<TCalendarDatesEntity> queryList(Map<String, Object> map) {
		return tCalendarDatesDao.queryList(map);
	}

	@Override
	public List<TCalendarDatesEntity> queryAll(Map<String, Object> map) {
		return tCalendarDatesDao.queryAll(map);
	}

	@Override
	public void deleteAll() {
		tCalendarDatesDao.deleteAll();
	}

	@Override
	@Transactional
	public void save(TCalendarDatesEntity tCalendarDates) {
		tCalendarDatesDao.save(tCalendarDates);
	}

	@Override
	@Transactional
	public void deleteAllThenSave(List<TCalendarDatesEntity> list) {
		tCalendarDatesDao.deleteAll();
		for(TCalendarDatesEntity tc : list) {
			tCalendarDatesDao.save(tc);
		}		
	}


}
