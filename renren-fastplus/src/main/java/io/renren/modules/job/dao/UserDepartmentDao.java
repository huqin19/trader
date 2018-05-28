package io.renren.modules.job.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import io.renren.modules.job.entity.UserDepartmentEntity;
import io.renren.modules.sys.dao.BaseDao;

/**
 * @author DHB
 * @date 2018年5月23日上午12:26:18
 * 成员部门DAO
 */
@Mapper
public interface UserDepartmentDao extends BaseDao<UserDepartmentEntity>{
	List<UserDepartmentEntity> queryAllUserDept();
	List<UserDepartmentEntity> queryByTime(Map<String, Object> map);
	void deleteAll();
}
