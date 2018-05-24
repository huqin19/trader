package io.renren.modules.job.service;

import java.util.List;
import java.util.Map;

import io.renren.modules.job.entity.UserDepartmentEntity;

/**
 * @author DHB
 * @date 2018年5月23日上午12:33:05
 * 成员部门Service
 */
public interface UserDepartmentService {
	/**
	 * 插入记录
	 * @param department
	 */
	void save(UserDepartmentEntity userDept);
	/**
	 * 查询所有
	 * @return
	 */
	List<UserDepartmentEntity> queryAllUserDept();
	/**
	 * 根据日期区间查询
	 * @param map
	 * @return
	 */
	List<UserDepartmentEntity> queryByTime(Map<String, Object> map);
}
