package io.renren.modules.job.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.renren.datasources.DataSourceNames;
import io.renren.datasources.DynamicDataSource;
import io.renren.modules.ht.entity.TBNDEntity;
import io.renren.modules.ht.entity.TCalendarDatesEntity;
import io.renren.modules.ht.entity.TtrdCmdsExecutionreportEntity;
import io.renren.modules.ht.service.TBNDService;
import io.renren.modules.ht.service.TCalendarDatesService;
import io.renren.modules.ht.service.TtrdCmdsExecutionreportService;
import io.renren.modules.job.entity.SyncPushLogEntity;
import io.renren.modules.job.service.SyncPushLogService;

/**
 * @author DHB
 * @date 2018年5月24日下午3:29:49
 * 恒泰数据库定时任务
 */
@Component("htdbTask")
public class HtdbTask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SyncPushLogService syncPushLogService;
	@Autowired
	private TCalendarDatesService tCalendarDatesService;
	@Autowired
	private TBNDService tBNDService;
	@Autowired
	private TtrdCmdsExecutionreportService ttrdCmdsExecutionreportService;
	@Value("${spring.datasource.druid.htdb_source.url}")
	private String url;
	/**
	 * 表TCALENDAR_DATES定时任务
	 * @param param
	 * 查询所有数据并更新
	 * 1，查询恒泰数据库
	 * 2，删除本地表数据，插入本地表
	 */
	public void updateTCalendarDates(String param) {
		logger.info("我是带参数的tCalendarDates方法，正在被执行，参数为：" + param);
		Map<String, Object> zqMap = new HashMap<String, Object>();
		Map<String, Object> htMap = new HashMap<String, Object>();
		zqMap.put("tableName", "TCALENDAR_DATES");
		htMap.put("tableName", "XIR_MD.TCALENDAR_DATES");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryTotal,queryAll,deleteAllThenSave");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地恒泰TCALENDAR_DATES数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = tCalendarDatesService.queryTotal(zqMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.HTDB_SOURCE);
		List<TCalendarDatesEntity> list = tCalendarDatesService.queryAll(htMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {		
			tCalendarDatesService.deleteAllThenSave(list);
			if(before > 0) {
				log.setResult(1);
				int after = tCalendarDatesService.queryTotal(zqMap);
				log.setResultDesc("非首次更新本地"+ after +"条恒泰数据库表TCALENDAR_DATES记录成功！");
			}else {
				log.setResult(1);
				int after = tCalendarDatesService.queryTotal(zqMap);
				log.setResultDesc("首次更新本地"+ after +"条恒泰数据库表TCALENDAR_DATES记录成功！");
			}
		}else {
			log.setResult(0);
			log.setResultDesc("没有查询到数据，更新本地恒泰数据库表TCALENDAR_DATES失败！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
	/**
	 * 表TBND定时任务
	 * @param param
	 * 查询所有数据并更新
	 * 1，查询恒泰数据库
	 * 2，删除本地表数据，插入本地表
	 */
	public void updateTBND(String param) {
		logger.info("我是带参数的tBND方法，正在被执行，参数为：" + param);
		Map<String, Object> zqMap = new HashMap<String, Object>();
		Map<String, Object> htMap = new HashMap<String, Object>();
		zqMap.put("tableName", "TBND");
		htMap.put("tableName", "XIR_MD.TBND");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryTotal,queryAll,deleteAllThenSave");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地恒泰TBND数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = tBNDService.queryTotal(zqMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.HTDB_SOURCE);
		List<TBNDEntity> list = tBNDService.queryAll(htMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {		
			tBNDService.deleteAllThenSave(list);
			if(before > 0) {
				log.setResult(1);
				int after = tBNDService.queryTotal(zqMap);
				log.setResultDesc("非首次更新本地"+ after +"条恒泰数据库表TBND记录成功！");
			}else {
				log.setResult(1);
				int after = tBNDService.queryTotal(zqMap);
				log.setResultDesc("首次更新本地"+ after +"条恒泰数据库表TBND记录成功！");
			}
		}else {
			log.setResult(0);
			log.setResultDesc("没有查询到数据，更新本地恒泰数据库表TBND失败！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
	/**
	 * 表TTRD_CMDS_EXECUTIONREPORT定时任务
	 * @param param
	 * 查询所有数据并更新
	 * 1，查询恒泰数据库
	 * 2，删除本地表数据，插入本地表
	 */
	public void updateTtrdCmdsExecutionreport(String param) {
		logger.info("我是带参数的updateTtrdCmdsExecutionreport方法，正在被执行，参数为：" + param);
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryTotal,queryAll,deleteAllThenSave");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地恒泰TTRD_CMDS_EXECUTIONREPORT数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = ttrdCmdsExecutionreportService.queryTotal();
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.HTDB_SOURCE);
		List<TtrdCmdsExecutionreportEntity> list = ttrdCmdsExecutionreportService.queryAll();
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {		
			ttrdCmdsExecutionreportService.deleteAllThenSave(list);
			if(before > 0) {
				log.setResult(1);
				int after = ttrdCmdsExecutionreportService.queryTotal();
				log.setResultDesc("非首次更新本地"+ after +"条恒泰数据库表TTRD_CMDS_EXECUTIONREPORT记录成功！");
			}else {
				log.setResult(1);
				int after = ttrdCmdsExecutionreportService.queryTotal();
				log.setResultDesc("首次更新本地"+ after +"条恒泰数据库表TTRD_CMDS_EXECUTIONREPORT记录成功！");
			}
		}else {
			log.setResult(0);
			log.setResultDesc("没有查询到数据，更新本地恒泰数据库表TTRD_CMDS_EXECUTIONREPORT失败！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
}
