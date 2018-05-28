package io.renren.modules.job.service;

import java.util.List;
import java.util.Map;

import io.renren.modules.job.entity.DepartmentEntity;
/**
 * @date 2018/5/22
 * @author DHB
 * 获取部门 service
 */
public interface DepartmentService {
	/**
	 * 插入记录
	 * @param department
	 */
	void save(DepartmentEntity department);
	/**
	 * 批量插入
	 * @param list
	 */
	void saveBatch(List<DepartmentEntity> list);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	DepartmentEntity queryObject(Integer id);
	/**
	 * 查询所有
	 * @return
	 */
	List<DepartmentEntity> queryAllDepartment();
	/**
	 * 根据日期区间查询
	 * @param map
	 * @return
	 */
	List<DepartmentEntity> queryByTime(Map<String, Object> map);
	/**
	 * 删除所有
	 */
	void deleteAll();
	/**
	 * 删除后插入
	 * @param list
	 */
	void deleteThenSave(List<DepartmentEntity> list);
	
}
