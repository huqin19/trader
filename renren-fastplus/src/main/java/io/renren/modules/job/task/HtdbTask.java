package io.renren.modules.job.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.renren.common.utils.ReadYml;
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
 * @date 2018年5月24日下午3:29:49 衡泰数据库定时任务
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
	 * 1,首次同步 status=1
	 * 2,非首次同步 
	 * 2.1,先将status=0的数据删除
	 * 2.2,本轮同步、本轮同步的数据status=0，
	 * 2.3,同步完成后将status=1的改为status=0、将status=0的置为1
	 */
	public void updateTCalendarDates(String param, Integer way) {
		logger.info("我是带参数的tCalendarDates方法，正在被执行，参数为：" + param);
		Map<String, Object> zqMap = new HashMap<String, Object>();
		Map<String, Object> htMap = new HashMap<String, Object>();
		zqMap.put("tableName", "TCALENDAR_DATES");
		htMap.put("tableName", "XIR_MD.TCALENDAR_DATES");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryList,saveBatch,updateStatus,deleteStatus");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(way);
		log.setReason("用以更新本地衡泰TCALENDAR_DATES数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = tCalendarDatesService.queryTotal(zqMap);
		List<TCalendarDatesEntity> list = null;
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.HTDB_SOURCE);
		Integer total = tCalendarDatesService.queryTotal(htMap);
		DynamicDataSource.clearDataSource();
		Integer offset = 0;
		Integer limit = 100000;
		htMap.put("limit", limit);
		if(before > 0) {
			DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			tCalendarDatesService.deleteStatus();
			DynamicDataSource.clearDataSource();
			do {
				htMap.put("offset", offset);
				DynamicDataSource.setDataSource(DataSourceNames.HTDB_SOURCE);
				list = tCalendarDatesService.queryList(htMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);				
				if(list != null && list.size() > 0) {
					int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE"));
					List<TCalendarDatesEntity> saveList = new ArrayList<TCalendarDatesEntity>();
					for(int i = 0; i < list.size(); i++) {
						if(i > 0 && i%loopSize == 0) {
							tCalendarDatesService.saveBatch(saveList);
							saveList.clear();
							saveList = new ArrayList<TCalendarDatesEntity>();
						}
						saveList.add(list.get(i));
					}
					if(saveList.size() > 0) {
						tCalendarDatesService.saveBatch(saveList);
					}
				}
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
			DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			tCalendarDatesService.updateStatus();
			DynamicDataSource.clearDataSource();
		}else {
			do {
				htMap.put("offset", offset);
				DynamicDataSource.setDataSource(DataSourceNames.HTDB_SOURCE);
				list = tCalendarDatesService.queryList(htMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
				if(list != null && list.size() > 0) {
					int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE"));
					List<TCalendarDatesEntity> saveList = new ArrayList<TCalendarDatesEntity>();
					for(int i = 0; i < list.size(); i++) {
						if(i > 0 && i%loopSize == 0) {
							tCalendarDatesService.saveBatch(saveList);
							saveList.clear();
							saveList = new ArrayList<TCalendarDatesEntity>();
						}
						saveList.add(list.get(i));
					}
					if(saveList.size() > 0) {
						tCalendarDatesService.saveBatch(saveList);
					}
				}
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
		}	
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		zqMap.put("status", 1);
		int after = tCalendarDatesService.queryTotal(zqMap);
		int success = after;
		int failed = total - after;
		if (before > 0) {
			log.setResult(1);
			log.setResultDesc("成功：" + success + "，失败：" + failed + "。非首次更新本地衡泰数据库表TCALENDAR_DATES！");
		} else {
			log.setResult(1);
			log.setResultDesc("成功：" + success + "，失败：" + failed + "。首次更新本地衡泰数据库表TCALENDAR_DATES！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}

	/**
	 * 表TBND定时任务
	 * @param param
	 * 1,首次同步 status=1
	 * 2,非首次同步 
	 * 2.1,先将status=0的数据删除
	 * 2.2,本轮同步、本轮同步的数据status=0，
	 * 2.3,同步完成后将status=1的改为status=0、将status=0的置为1
	 */
	public void updateTBND(String param, Integer way) {		
		logger.info("我是带参数的tBND方法，正在被执行，参数为：" + param);
		Map<String, Object> zqMap = new HashMap<String, Object>();
		Map<String, Object> htMap = new HashMap<String, Object>();
		zqMap.put("tableName", "TBND");
		htMap.put("tableName", "XIR_MD.TBND");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryList,saveBatch,updateStatus,deleteStatus");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(way);
		log.setReason("用以更新本地衡泰TBND数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = tBNDService.queryTotal(zqMap);
		List<TBNDEntity> list = null;
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.HTDB_SOURCE);
		Integer total = tBNDService.queryTotal(htMap);
		DynamicDataSource.clearDataSource();
		Integer offset = 0;
		Integer limit = 100000;
		htMap.put("limit", limit);
		if(before > 0) {
			DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			tBNDService.deleteStatus();
			DynamicDataSource.clearDataSource();
			do {
				htMap.put("offset", offset);
				DynamicDataSource.setDataSource(DataSourceNames.HTDB_SOURCE);
				list = tBNDService.queryList(htMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);			
				if(list != null && list.size() > 0) {
					int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE"));
					List<TBNDEntity> saveList = new ArrayList<TBNDEntity>();
					for(int i = 0; i < list.size(); i++) {
						if(i > 0 && i%loopSize == 0) {
							tBNDService.saveBatch(saveList);
							saveList.clear();
							saveList = new ArrayList<TBNDEntity>();
						}
						saveList.add(list.get(i));
					}
					if(saveList.size() > 0) {
						tBNDService.saveBatch(saveList);
					}
				}
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
			DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			tBNDService.updateStatus();
			DynamicDataSource.clearDataSource();
		}else {
			do {
				htMap.put("offset", offset);
				DynamicDataSource.setDataSource(DataSourceNames.HTDB_SOURCE);
				list = tBNDService.queryList(htMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
				if(list != null && list.size() > 0) {
					int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE"));
					List<TBNDEntity> saveList = new ArrayList<TBNDEntity>();
					for(int i = 0; i < list.size(); i++) {
						if(i > 0 && i%loopSize == 0) {
							tBNDService.saveBatch(saveList);
							saveList.clear();
							saveList = new ArrayList<TBNDEntity>();
						}
						saveList.add(list.get(i));
					}
					if(saveList.size() > 0) {
						tBNDService.saveBatch(saveList);
					}
				}
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
		}	
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		zqMap.put("status", 1);
		int after = tBNDService.queryTotal(zqMap);
		int success = after;
		int failed = total - after;
		if (before > 0) {
			log.setResult(1);
			log.setResultDesc("成功：" + success + "，失败：" + failed + "。非首次更新本地衡泰数据库表TBND！");
		} else {
			log.setResult(1);
			log.setResultDesc("成功：" + success + "，失败：" + failed + "。首次更新本地衡泰数据库表TBND！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}

	/**
	 * 表TTRD_CMDS_EXECUTIONREPORT定时任务
	 * @param param
	 * 1,首次同步 status=1
	 * 2,非首次同步 
	 * 2.1,先将status=0的数据删除
	 * 2.2,本轮同步、本轮同步的数据status=0，
	 * 2.3,同步完成后将status=1的改为status=0、将status=0的置为1
	 */
	public void updateTtrdCmdsExecutionreport(String param, Integer way) {
		logger.info("我是带参数的updateTtrdCmdsExecutionreport方法，正在被执行，参数为：" + param);
		Map<String, Object> zqMap = new HashMap<String, Object>();
		Map<String, Object> htMap = new HashMap<String, Object>();
		zqMap.put("tableName", "TTRD_CMDS_EXECUTIONREPORT");
		htMap.put("tableName", "TTRD_CMDS_EXECUTIONREPORT");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryList,saveBatch,updateStatus,deleteStatus");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(way);
		log.setReason("用以更新本地衡泰TTRD_CMDS_EXECUTIONREPORT数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = ttrdCmdsExecutionreportService.queryTotal(zqMap);
		List<TtrdCmdsExecutionreportEntity> list = null;
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.HTDB_SOURCE);
		Integer total = ttrdCmdsExecutionreportService.queryTotal(htMap);
		DynamicDataSource.clearDataSource();
		Integer offset = 0;
		Integer limit = 100000;
		htMap.put("limit", limit);
		if(before > 0) {
			DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			ttrdCmdsExecutionreportService.deleteStatus();
			DynamicDataSource.clearDataSource();
			do {
				htMap.put("offset", offset);
				DynamicDataSource.setDataSource(DataSourceNames.HTDB_SOURCE);
				list = ttrdCmdsExecutionreportService.queryList(htMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);			
				if(list != null && list.size() > 0) {
					int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE"));
					List<TtrdCmdsExecutionreportEntity> saveList = new ArrayList<TtrdCmdsExecutionreportEntity>();
					for(int i = 0; i < list.size(); i++) {
						if(i > 0 && i%loopSize == 0) {
							ttrdCmdsExecutionreportService.saveBatch(saveList);
							saveList.clear();
							saveList = new ArrayList<TtrdCmdsExecutionreportEntity>();
						}
						saveList.add(list.get(i));
					}
					if(saveList.size() > 0) {
						ttrdCmdsExecutionreportService.saveBatch(saveList);
					}
				}
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
			DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			ttrdCmdsExecutionreportService.updateStatus();
			DynamicDataSource.clearDataSource();
		}else {
			do {
				htMap.put("offset", offset);
				DynamicDataSource.setDataSource(DataSourceNames.HTDB_SOURCE);
				list = ttrdCmdsExecutionreportService.queryList(htMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
				if(list != null && list.size() > 0) {
					int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE"));
					List<TtrdCmdsExecutionreportEntity> saveList = new ArrayList<TtrdCmdsExecutionreportEntity>();
					for(int i = 0; i < list.size(); i++) {
						if(i > 0 && i%loopSize == 0) {
							ttrdCmdsExecutionreportService.saveBatch(saveList);
							saveList.clear();
							saveList = new ArrayList<TtrdCmdsExecutionreportEntity>();
						}
						saveList.add(list.get(i));
					}
					if(saveList.size() > 0) {
						ttrdCmdsExecutionreportService.saveBatch(saveList);
					}
				}
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
		}	
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		zqMap.put("status", 1);
		int after = ttrdCmdsExecutionreportService.queryTotal(zqMap);
		int success = after;
		int failed = total - after;
		if (before > 0) {
			log.setResult(1);
			log.setResultDesc("成功：" + success + "，失败：" + failed + "。非首次更新本地衡泰数据库表TTRD_CMDS_EXECUTIONREPORT！");
		} else {
			log.setResult(1);
			log.setResultDesc("成功：" + success + "，失败：" + failed + "。首次更新本地衡泰数据库表TTRD_CMDS_EXECUTIONREPORT！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
}
