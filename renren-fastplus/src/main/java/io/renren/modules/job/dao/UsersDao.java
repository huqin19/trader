package io.renren.modules.job.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.job.entity.UsersEntity;
import io.renren.modules.sys.dao.BaseDao;

@Mapper
public interface UsersDao extends BaseDao<UsersEntity>{
	List<UsersEntity> queryAllUsers();
	List<UsersEntity> queryByTime(Map<String, Object> map);
	void deleteAll();
}
