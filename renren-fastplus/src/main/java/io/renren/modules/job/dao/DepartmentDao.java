package io.renren.modules.job.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.job.entity.DepartmentEntity;
import io.renren.modules.sys.dao.BaseDao;
/**
 * 
 * @author DHB
 * @date 2018年5月23日上午12:26:18
 * 部门DAO
 */
@Mapper
public interface DepartmentDao extends BaseDao<DepartmentEntity>{
	List<DepartmentEntity> queryAllDepartment();
	List<DepartmentEntity> queryByTime(Map<String, Object> map);
}
