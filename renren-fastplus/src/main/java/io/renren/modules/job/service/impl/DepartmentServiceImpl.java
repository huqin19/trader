package io.renren.modules.job.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.common.sequence.Sequence;
import io.renren.modules.job.dao.DepartmentDao;
import io.renren.modules.job.entity.DepartmentEntity;
import io.renren.modules.job.service.DepartmentService;
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	private DepartmentDao departmentDao;
	Sequence sequence = new Sequence(0, 0);
	
	@Override
	@Transactional
	public void saveBatch(List<DepartmentEntity> list) {
		for(DepartmentEntity department : list) {
			Long objectId = sequence.nextId();
			department.setObjectId(objectId);
			department.setStatus(0);
			department.setCreatedTimestamp(new Date());
		}
		departmentDao.saveBatch(list);
	}
	@Override
	public DepartmentEntity queryObject(Integer id) {
		return departmentDao.queryObject(id);	
	}
	@Override
	public List<DepartmentEntity> queryAllDepartment() {
		return departmentDao.queryAllDepartment();
	}
	@Override
	public List<DepartmentEntity> queryByTime(Map<String, Object> map) {
		return departmentDao.queryByTime(map);
	}
	@Override
	@Transactional
	public void save(DepartmentEntity department) {
		Long objectId = sequence.nextId();
		department.setObjectId(objectId);
		department.setCreatedTimestamp(new Date());
		departmentDao.save(department);
	}
	@Override
	public void deleteAll() {
		departmentDao.deleteAll();
	}
	@Override
	@Transactional
	public void deleteThenSave(List<DepartmentEntity> list) {
		departmentDao.deleteAll();
		for(DepartmentEntity department : list) {
			Long objectId = sequence.nextId();
			department.setObjectId(objectId);
			department.setCreatedTimestamp(new Date());
			departmentDao.save(department);
		}
		
	}
	@Override
	@Transactional
	public void updateStatus() {
		departmentDao.updateStatus();
	}
	@Override
	@Transactional
	public void deleteStatus() {
		departmentDao.deleteStatus();
	}
	@Override
	@Transactional
	public void firstSaveBatch(List<DepartmentEntity> list) {
		for(DepartmentEntity department : list) {
			Long objectId = sequence.nextId();
			department.setObjectId(objectId);
			department.setStatus(1);
			department.setCreatedTimestamp(new Date());
		}
		departmentDao.saveBatch(list);
	}
	@Override
	public int queryTotal(Map<String, Object> map) {
		return departmentDao.queryTotal(map);
	}

}
