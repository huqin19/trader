package io.renren.modules.job.task;

import java.sql.Timestamp;
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
		int total = tCalendarDatesService.queryTotal(htMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {		
			tCalendarDatesService.deleteAllThenSave(list);
			if(before > 0) {
				log.setResult(1);
				int after = tCalendarDatesService.queryTotal(zqMap);
				int failed = total - after;
				int sucess = after - before;
				log.setResultDesc("成功："+sucess+"，失败："+failed+"。非首次更新本地恒泰数据库表TCALENDAR_DATES！");
			}else {
				log.setResult(1);
				int after = tCalendarDatesService.queryTotal(zqMap);
				int failed = total - after;
				int sucess = after - before;
				log.setResultDesc("成功："+sucess+"，失败："+failed+"。首次更新本地恒泰数据库表TCALENDAR_DATES！");
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
	 * 1,在本地查询最近的数据IMP_TIME
	 * 1.1，如果不为空就以插入恒泰中大于这个日期的数据
	 * 1.2，如果为空就全量更新
	 */
	public void updateTBND(String param) {
		logger.info("我是带参数的tBND方法，正在被执行，参数为：" + param);
		Map<String, Object> zqMap = new HashMap<String, Object>();
		Map<String, Object> htMap = new HashMap<String, Object>();
		zqMap.put("tableName", "TBND");
		htMap.put("tableName", "XIR_MD.TBND");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryTotal,queryFirst,queryLatest,save");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地恒泰TBND数据库表！");			
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = tBNDService.queryTotal(zqMap);
		String latest = null;
		if(before > 0) {
			latest = tBNDService.queryFirst(zqMap).getImpTime();
			htMap.put("latest", latest);
		}
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.HTDB_SOURCE);
		List<TBNDEntity> list = tBNDService.queryLatest(htMap);
		int total = tBNDService.queryTotal(htMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {
			for(TBNDEntity tb : list) {
				tBNDService.save(tb);
			}
			int after = tBNDService.queryTotal(zqMap);
			int success = after - before;
			int failed = list.size() - success;
			if(before > 0) {
				log.setResult(1);
				log.setResultDesc("成功："+success+"，失败："+failed+"。非首次更新本地恒泰数据库表TBND！");
			}else {
				log.setResult(1);
				log.setResultDesc("成功："+success+"，失败："+failed+"。首次更新本地恒泰数据库表TBND！");
			}			
		}else if (before == total){
			log.setResult(0);
			log.setResultDesc("更新0条数据，本地和恒泰数据库表TBND一致！");
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
	 * 1,在本地查询最近的数据trddate,trdtime
	 * 1.1，如果不为空就以插入恒泰中大于这个日期的数据
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
		log.setFunctionName("queryTotal,queryFirst,queryLatest,save");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地恒泰TTRD_CMDS_EXECUTIONREPORT数据库表！");			
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = ttrdCmdsExecutionreportService.queryTotal();
		System.out.println(before+"=================");
		TtrdCmdsExecutionreportEntity tce = null;
		if(before > 0) {
			tce = ttrdCmdsExecutionreportService.queryFirst(zqMap);			
			String latestDate = tce.getTrddate();
			String latestTime = tce.getTrdtime();
			htMap.put("latestDate", latestDate);
			htMap.put("latestTime", latestTime);
		}
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.HTDB_SOURCE);
		List<TtrdCmdsExecutionreportEntity> list = ttrdCmdsExecutionreportService.queryLatest(htMap);
		int total = ttrdCmdsExecutionreportService.queryTotal();
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {
			for(TtrdCmdsExecutionreportEntity tt : list) {
				ttrdCmdsExecutionreportService.save(tt);
			}
			int after = ttrdCmdsExecutionreportService.queryTotal();
			int success = after - before;
			int failed = list.size() - success;
			if(before > 0) {
				log.setResult(1);
				log.setResultDesc("成功："+success+"，失败："+failed+"。非首次更新本地恒泰数据库表TTRD_CMDS_EXECUTIONREPORT！");
			}else {
				log.setResult(1);
				log.setResultDesc("成功："+success+"，失败："+failed+"。首次更新本地恒泰数据库表TTRD_CMDS_EXECUTIONREPORT！");
			}			
		}else if (before == total){
			log.setResult(0);
			log.setResultDesc("更新0条数据，本地和恒泰数据库表TTRD_CMDS_EXECUTIONREPORT一致！");
		}else {
			log.setResult(0);
			log.setResultDesc("没有查询到数据，更新本地恒泰数据库表TTRD_CMDS_EXECUTIONREPORT失败！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
}
