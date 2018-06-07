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
	 * 
	 * @param param
	 *            查询所有数据并更新 1，查询衡泰数据库 2，删除本地表数据，插入本地表
	 */
	public void updateTCalendarDates(String param) {
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
		log.setWay(0);
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
				if (list != null && list.size() > 0) {
					int pointsDataLimit = 400;// 限制条数
					Integer size = list.size();
					if (pointsDataLimit < size) {// 判断是否有必要分批
						int part = size / pointsDataLimit;// 分批数
						for (int i = 0; i < part; i++) {
							List<TCalendarDatesEntity> listPage = list.subList(0, pointsDataLimit);
							tCalendarDatesService.saveBatch(listPage);
							list.subList(0, pointsDataLimit).clear();
						}
						if (!list.isEmpty()) {
							tCalendarDatesService.saveBatch(list);// 最后剩下的数据
						}
					} else {
						tCalendarDatesService.saveBatch(list);
					}
				} else {
					System.out.println("没有数据!!!");
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
				list = tCalendarDatesService.queryList(htMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
				if (list != null && list.size() > 0) {
					int pointsDataLimit = 400;
					Integer size = list.size();
					if (pointsDataLimit < size) {
						int part = size / pointsDataLimit;
						for (int i = 0; i < part; i++) {
							List<TCalendarDatesEntity> listPage = list.subList(0, pointsDataLimit);
							tCalendarDatesService.firstSaveBatch(listPage);
							list.subList(0, pointsDataLimit).clear();
						}
						if (!list.isEmpty()) {
							tCalendarDatesService.firstSaveBatch(list);
						}
					} else {
						tCalendarDatesService.firstSaveBatch(list);
					}
				} else {
					System.out.println("没有数据!!!");
				}
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
		}	
		DynamicDataSource.clearDataSource();
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
	 * 
	 * @param param
	 *  1,在本地查询最近的数据IMP_TIME 1.1，如果不为空就以插入衡泰中大于这个日期的数据 1.2，如果为空就全量更新
	 */
	public void updateTBND(String param) {		
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
		log.setWay(0);
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
				if (list != null && list.size() > 0) {
					int pointsDataLimit = 400;// 限制条数
					Integer size = list.size();
					if (pointsDataLimit < size) {// 判断是否有必要分批
						int part = size / pointsDataLimit;// 分批数
						for (int i = 0; i < part; i++) {
							List<TBNDEntity> listPage = list.subList(0, pointsDataLimit);
							tBNDService.saveBatch(listPage);
							list.subList(0, pointsDataLimit).clear();
						}
						if (!list.isEmpty()) {
							tBNDService.saveBatch(list);// 最后剩下的数据
						}
					} else {
						tBNDService.saveBatch(list);
					}
				} else {
					System.out.println("没有数据!!!");
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
				list = tBNDService.queryList(htMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
				if (list != null && list.size() > 0) {
					int pointsDataLimit = 400;
					Integer size = list.size();
					if (pointsDataLimit < size) {
						int part = size / pointsDataLimit;
						for (int i = 0; i < part; i++) {
							List<TBNDEntity> listPage = list.subList(0, pointsDataLimit);
							tBNDService.firstSaveBatch(listPage);
							list.subList(0, pointsDataLimit).clear();
						}
						if (!list.isEmpty()) {
							tBNDService.firstSaveBatch(list);
						}
					} else {
						tBNDService.firstSaveBatch(list);
					}
				} else {
					System.out.println("没有数据!!!");
				}
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
		}	
		DynamicDataSource.clearDataSource();
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
	 * 
	 * @param param
	 * 1,在本地查询最近的数据trddate,trdtime 1.1，如果不为空就以插入衡泰中大于这个日期的数据
	 * 1.2，如果为空就全量更新
	 */
	public void updateTtrdCmdsExecutionreport(String param) {
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
		log.setWay(0);
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
				if (list != null && list.size() > 0) {
					int pointsDataLimit = 400;// 限制条数
					Integer size = list.size();
					if (pointsDataLimit < size) {// 判断是否有必要分批
						int part = size / pointsDataLimit;// 分批数
						for (int i = 0; i < part; i++) {
							List<TtrdCmdsExecutionreportEntity> listPage = list.subList(0, pointsDataLimit);
							ttrdCmdsExecutionreportService.saveBatch(listPage);
							list.subList(0, pointsDataLimit).clear();
						}
						if (!list.isEmpty()) {
							ttrdCmdsExecutionreportService.saveBatch(list);// 最后剩下的数据
						}
					} else {
						ttrdCmdsExecutionreportService.saveBatch(list);
					}
				} else {
					System.out.println("没有数据!!!");
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
				if (list != null && list.size() > 0) {
					int pointsDataLimit = 400;
					Integer size = list.size();
					if (pointsDataLimit < size) {
						int part = size / pointsDataLimit;
						for (int i = 0; i < part; i++) {
							List<TtrdCmdsExecutionreportEntity> listPage = list.subList(0, pointsDataLimit);
							ttrdCmdsExecutionreportService.firstSaveBatch(listPage);
							list.subList(0, pointsDataLimit).clear();
						}
						if (!list.isEmpty()) {
							ttrdCmdsExecutionreportService.firstSaveBatch(list);
						}
					} else {
						ttrdCmdsExecutionreportService.firstSaveBatch(list);
					}
				} else {
					System.out.println("没有数据!!!");
				}
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
		}	
		DynamicDataSource.clearDataSource();
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
