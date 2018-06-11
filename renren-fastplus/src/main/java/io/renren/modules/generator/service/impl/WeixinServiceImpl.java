package io.renren.modules.generator.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.common.sequence.Sequence;
import io.renren.common.utils.Constant.ScheduleStatus;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.generator.dao.WeixinDao;
import io.renren.modules.generator.dao.ZqJobAttachDao;
import io.renren.modules.generator.dao.ZqSheetsDao;
import io.renren.modules.generator.entity.WeixinEntity;
import io.renren.modules.generator.entity.ZqJobAttachEntity;
import io.renren.modules.generator.entity.ZqSheetsEntity;
import io.renren.modules.generator.service.WeixinService;
import io.renren.modules.job.dao.ScheduleJobDao;
import io.renren.modules.job.entity.ScheduleJobEntity;
import io.renren.modules.job.utils.ScheduleUtils;


@Service("weixinService")
public class WeixinServiceImpl implements WeixinService {

	@Autowired
	private WeixinDao weixinDao;
	
	@Autowired
	private ZqSheetsDao zqSheetsDao;
	
	@Autowired
	private ZqJobAttachDao zqJobAttachDao;
	
	@Autowired
    private Scheduler scheduler;
	
	@Autowired
	private ScheduleJobDao schedulerJobDao;
	
	Sequence sequence = new Sequence(0, 0);
	
    @Override
    public List<WeixinEntity> queryList(Map<String, Object> map) {
    	return weixinDao.queryList(map);
    }

	@Override
	public List<WeixinEntity> querySheetList(Map<String, Object> map) {
		return zqSheetsDao.querySheetList(map);
	}

	@Override
	public ZqSheetsEntity queryZqSheetsObject(BigDecimal objectId) {
		return zqSheetsDao.queryObject(objectId);
	}

	@Override
	@Transactional
	public String submitMessage(WeixinEntity weixinEntity) {
		String result = "";
		if(null != weixinEntity) {
			ScheduleJobEntity scheduleJob = new ScheduleJobEntity();
			scheduleJob.setBeanName(weixinEntity.getBeanName());
			scheduleJob.setCreateTime(new Date());
			scheduleJob.setCronExpression(weixinEntity.getCronExpression());
			//生成主键
			Long jobId = sequence.nextId();
			scheduleJob.setJobId(jobId);
			scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
			scheduleJob.setMethodName(weixinEntity.getMethodName());
			scheduleJob.setParams(weixinEntity.getParams());
			scheduleJob.setRemark(weixinEntity.getRemark());
			ValidatorUtils.validateEntity(scheduleJob);
			//保存定时附加信息表
			ZqJobAttachEntity zqJobAttachEntity = new ZqJobAttachEntity();
			//生成主键
			Long objectId = sequence.nextId();
			zqJobAttachEntity.setObjectId(objectId);
			Timestamp d = new Timestamp(System.currentTimeMillis()); 
			zqJobAttachEntity.setCreatedTimestamp(d);
			zqJobAttachEntity.setJobId(jobId);
			String sheetIds = "";
			if(null != weixinEntity.getTitleArr() && weixinEntity.getTitleArr().length > 0) {
				for(String t : weixinEntity.getTitleArr()) {
					sheetIds += "|" + t;
				}
			}
			String userIds = "";
			if(null != weixinEntity.getNewtreeName() && weixinEntity.getNewtreeName().length > 0) {
				for(String t : weixinEntity.getNewtreeName()) {
					userIds += "|" + t;
				}
			}
			zqJobAttachEntity.setSheetId(sheetIds);
			zqJobAttachEntity.setUserId(userIds);
			zqJobAttachEntity.setStatus(new BigDecimal(1));
			zqJobAttachDao.save(zqJobAttachEntity);
			schedulerJobDao.save(scheduleJob);
	        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
		}
		return null;
	}

}
