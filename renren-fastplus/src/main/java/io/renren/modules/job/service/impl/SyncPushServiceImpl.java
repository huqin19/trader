package io.renren.modules.job.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.modules.job.dao.SyncPushLogDao;
import io.renren.modules.job.entity.SyncPushLogEntity;
import io.renren.modules.job.service.SyncPushLogService;

@Service("syncPushLogService")
public class SyncPushServiceImpl implements SyncPushLogService{
	@Autowired
	private SyncPushLogDao syncPushLogDao;

	@Override
	public SyncPushLogEntity queryObject(Long id) {
		return syncPushLogDao.queryObject(id);
	}

	@Override
	public List<SyncPushLogEntity> queryList(Map<String, Object> map) {
		return syncPushLogDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return syncPushLogDao.queryTotal(map);
	}

	@Override
	public void save(SyncPushLogEntity log) {
		syncPushLogDao.save(log);
	}

	@Override
	public void delete(Long id) {
		syncPushLogDao.delete(id);	
	}

	@Override
	public void deleteBatch(Long[] ids) {
		syncPushLogDao.deleteBatch(ids);		
	}
	
}
