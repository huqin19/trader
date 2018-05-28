package io.renren.modules.job.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.common.sequence.Sequence;
import io.renren.modules.job.dao.UsersDao;
import io.renren.modules.job.entity.UsersEntity;
import io.renren.modules.job.service.UsersService;

@Service("usersService")
public class UsersServiceImpl implements UsersService{
	@Autowired
	private UsersDao usersDao;
	
	Sequence sequence = new Sequence(0, 0);
	@Override
	@Transactional
	public void save(UsersEntity user) {
		Long objectId = sequence.nextId();
		user.setObjectId(objectId);
		user.setCreatedTimestamp(new Date());
		usersDao.save(user);
	}

	@Override
	public UsersEntity queryObject(String userid) {
		return usersDao.queryObject(userid);
	}

	@Override
	@Transactional
	public void saveBatch(List<UsersEntity> list) {
		for(UsersEntity user : list) {
			Long objectId = sequence.nextId();
			user.setObjectId(objectId);
			user.setCreatedTimestamp(new Date());
		}
		usersDao.saveBatch(list);
	}

	@Override
	public List<UsersEntity> queryAllUsers() {
		return usersDao.queryAllUsers();
	}

	@Override
	public List<UsersEntity> queryByTime(Map<String, Object> map) {
		return usersDao.queryByTime(map);
	}

	@Override
	public void deleteAll() {
		usersDao.deleteAll();
	}

	@Override
	@Transactional
	public void deleteThenSave(List<UsersEntity> list) {
		usersDao.deleteAll();
		for(UsersEntity user : list) {
			Long objectId = sequence.nextId();
			user.setObjectId(objectId);
			user.setCreatedTimestamp(new Date());
			usersDao.save(user);
		}
		
	}
}
