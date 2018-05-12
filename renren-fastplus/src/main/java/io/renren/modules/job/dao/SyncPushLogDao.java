package io.renren.modules.job.dao;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.job.entity.SyncPushLogEntity;
import io.renren.modules.sys.dao.BaseDao;
/**
 * 推送同步日志
 * @author DHB
 * @date 2018年5月9日
 */
@Mapper
public interface SyncPushLogDao extends BaseDao<SyncPushLogEntity>{

}
