package io.renren.modules.job.service;

import java.util.List;
import java.util.Map;

import io.renren.modules.job.entity.UsersEntity;
/**
 * @date 2018/5/22
 * @author DHB
 * 获取用户 service
 */
public interface UsersService {
	/**
	 * 插入记录
	 * @param department
	 */
	void save(UsersEntity user);
	/**
	 * 批量插入
	 * @param list
	 */
	void saveBatch(List<UsersEntity> list);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	UsersEntity queryObject(String userid);
	/**
	 * 查询所有
	 * @return
	 */
	List<UsersEntity> queryAllUsers();
	/**
	 * 根据日期区间查询
	 * @param map
	 * @return
	 */
	List<UsersEntity> queryByTime(Map<String, Object> map);
}
