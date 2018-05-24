package io.renren.modules.job.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.common.sequence.Sequence;
import io.renren.modules.job.dao.SyncPushLogDao;
import io.renren.modules.job.entity.SyncPushLogEntity;
import io.renren.modules.job.service.SyncPushLogService;

@Service("syncPushLogService")
public class SyncPushServiceImpl implements SyncPushLogService{
	@Autowired
	private SyncPushLogDao syncPushLogDao;
	
	Sequence sequence = new Sequence(0, 0);

	@Override
	public SyncPushLogEntity queryObject(Long id) {
		return syncPushLogDao.queryObject(id);
	}

	@Override
	public List<SyncPushLogEntity> queryList(Map<String, Object> map) {
		return syncPushLogDao.queryList(map);
	}

	@Override
	@Transactional
	public void save(SyncPushLogEntity log) {
		Long id = sequence.nextId();
		log.setId(id);
		log.setCreateTime(new Date());
		syncPushLogDao.save(log);
	}

	
}
