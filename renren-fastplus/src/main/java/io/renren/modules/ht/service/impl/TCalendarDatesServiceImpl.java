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
	public List<TCalendarDatesEntity> queryCode(String calCode) {
		return tCalendarDatesDao.queryCode(calCode);
	}

	@Override
	public List<TCalendarDatesEntity> queryList(Map<String, Object> map) {
		return tCalendarDatesDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return tCalendarDatesDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(TCalendarDatesEntity tCalendarDates) {
		tCalendarDatesDao.save(tCalendarDates);
	}
}
