package io.renren.modules.job.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.common.sequence.Sequence;
import io.renren.modules.job.dao.UserDepartmentDao;
import io.renren.modules.job.entity.DepartmentEntity;
import io.renren.modules.job.entity.UserDepartmentEntity;
import io.renren.modules.job.service.UserDepartmentService;

/**
 * @author DHB
 * @date 2018年5月23日上午12:42:54
 * 
 */
@Service("userDepartmentService")
public class UserDepartmentServiceImpl implements UserDepartmentService{
	@Autowired
	private  UserDepartmentDao userDepartmentDao;
	Sequence sequence = new Sequence(0, 0);
	
	@Override
	@Transactional
	public void save(UserDepartmentEntity userDept) {
		Long objectId = sequence.nextId();
		userDept.setObjectId(objectId);
		userDept.setCreatedTimestamp(new Date());
		userDepartmentDao.save(userDept);
	}

	@Override
	public List<UserDepartmentEntity> queryAllUserDept() {
		return userDepartmentDao.queryAllUserDept();
	}

	@Override
	public List<UserDepartmentEntity> queryByTime(Map<String, Object> map) {
		return userDepartmentDao.queryByTime(map);
	}

	@Override
	@Transactional
	public void saveBatch(List<UserDepartmentEntity> list) {
		for(UserDepartmentEntity userDept : list) {
			Long objectId = sequence.nextId();
			userDept.setObjectId(objectId);
			userDept.setCreatedTimestamp(new Date());
		}
		userDepartmentDao.saveBatch(list);
	}

	@Override
	public void deleteAll() {
		userDepartmentDao.deleteAll();
	}

	@Override
	@Transactional
	public void deleteThenSave(List<UserDepartmentEntity> list) {
		userDepartmentDao.deleteAll();
		for(UserDepartmentEntity userDept : list) {
			Long objectId = sequence.nextId();
			userDept.setObjectId(objectId);
			userDept.setCreatedTimestamp(new Date());
		}
		userDepartmentDao.saveBatch(list);
	}
	
}
