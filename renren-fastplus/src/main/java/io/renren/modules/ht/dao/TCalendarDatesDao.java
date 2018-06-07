package io.renren.modules.ht.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.ht.entity.TCalendarDatesEntity;
import io.renren.modules.sys.dao.BaseDao;
/**
 * 恒泰数据库TCALENDAR_DATES表
 * @author DHB
 * @date 2018/5/15
 */
@Mapper
public interface TCalendarDatesDao extends BaseDao<TCalendarDatesEntity>{
	List<TCalendarDatesEntity> queryCode(Map<String, Object> map);
	void deleteAll();
	List<TCalendarDatesEntity> queryAll(Map<String, Object> map);
	void updateStatus();
	void deleteStatus();
}
