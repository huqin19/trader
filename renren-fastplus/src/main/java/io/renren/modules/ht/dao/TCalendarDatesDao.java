package io.renren.modules.ht.dao;

import java.util.List;

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
	List<TCalendarDatesEntity> queryCode(String calCode);
}
