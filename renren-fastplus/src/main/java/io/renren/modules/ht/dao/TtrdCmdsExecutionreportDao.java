package io.renren.modules.ht.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.ht.entity.TtrdCmdsExecutionreportEntity;
import io.renren.modules.sys.dao.BaseDao;


/**
 * @author DHB
 * @date 2018年5月29日上午2:21:14
 * 表TTRD_CMDS_EXECUTIONREPORT
 */
@Mapper
public interface TtrdCmdsExecutionreportDao extends BaseDao<TtrdCmdsExecutionreportEntity> {
	void deleteAll();
	List<TtrdCmdsExecutionreportEntity> queryAll();
	TtrdCmdsExecutionreportEntity queryFirst(Map<String, Object> map);
	List<TtrdCmdsExecutionreportEntity> queryLatest(Map<String, Object> map);
	void updateStatus();
	void deleteStatus();
}
