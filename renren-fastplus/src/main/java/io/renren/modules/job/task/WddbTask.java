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

import io.renren.common.utils.DateUtils;
import io.renren.datasources.DataSourceNames;
import io.renren.datasources.DynamicDataSource;
import io.renren.modules.job.entity.SyncPushLogEntity;
import io.renren.modules.job.service.SyncPushLogService;
import io.renren.modules.wd.entity.CBondFuturesEODPricesEntity;
import io.renren.modules.wd.entity.CBondFuturesPositionsEntity;
import io.renren.modules.wd.entity.CBondIssuerRatingEntity;
import io.renren.modules.wd.entity.CBondRatingEntity;
import io.renren.modules.wd.entity.CFuturesDescriptionEntity;
import io.renren.modules.wd.service.CBondFuturesEODPricesService;
import io.renren.modules.wd.service.CBondFuturesPositionsService;
import io.renren.modules.wd.service.CBondIssuerRatingService;
import io.renren.modules.wd.service.CBondRatingService;
import io.renren.modules.wd.service.CFuturesDescriptionService;

/**
 * @author DHB
 * @date 2018年5月23日下午2:32:15
 * 万德数据库定时任务
 */
@Component("wddbTask")
public class WddbTask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SyncPushLogService syncPushLogService;
	@Autowired
	private CBondFuturesEODPricesService cBondFuturesEODPricesService;
	@Autowired
	private CBondFuturesPositionsService cBondFuturesPositionsService;
	@Autowired
	private CBondIssuerRatingService cBondIssuerRatingService;
	@Autowired
	private CBondRatingService cBondRatingService;
	@Autowired
	private CFuturesDescriptionService cFuturesDescriptionService;
	@Value("${spring.datasource.druid.wddb_source.url}")
	private String url;
	/**
	 * 表CBONDFUTURESEODPRICES定时任务
	 * @param param
	 * 1,在本地查询最近的数据trade_dt
	 * 1.1，如果不为空就以插入万德中大于这个日期的数据
	 * 1.2，如果为空就全量更新
	 */
	public void updateCBondFuturesEODPrices(String param) {
		logger.info("我是带参数的cBondFuturesEODPrices方法，正在被执行，参数为：" + param);
		Map<String, Object> zqMap = new HashMap<String, Object>();
		Map<String, Object> wdMap = new HashMap<String, Object>();
		zqMap.put("tableName", "CBONDFUTURESEODPRICES");
		wdMap.put("tableName", "wind_filesync.CBONDFUTURESEODPRICES");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryTotal,queryAll,deleteAllThenSave");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地万德CBONDFUTURESEODPRICES数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = cBondFuturesEODPricesService.queryTotal(zqMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		List<CBondFuturesEODPricesEntity> list = cBondFuturesEODPricesService.queryAll(wdMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {
			cBondFuturesEODPricesService.deleteAllThenSave(list);
			if(before > 0) {
				log.setResult(1);
				int after = cBondFuturesEODPricesService.queryTotal(zqMap);
				log.setResultDesc("非首次更新本地"+ after +"条万德数据库表CBONDFUTURESEODPRICES记录成功！");
			}else {
				log.setResult(1);
				int after = cBondFuturesEODPricesService.queryTotal(zqMap);
				log.setResultDesc("首次更新本地"+ after +"条万德数据库表CBONDFUTURESEODPRICES记录成功！");
			}			
		}else {
			log.setResult(0);
			log.setResultDesc("没有查询到数据，更新本地万德数据库表CBONDFUTURESEODPRICES失败！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
	/**
	 * 表CBONDFUTURESPOSITIONS定时任务
	 * @param param
	 * 1,在本地查询最近的数据trade_dt
	 * 1.1，如果不为空就以插入万德中大于这个日期的数据
	 * 1.2，如果为空就全量更新
	 */
	public void updateCBondFuturesPositions(String param) {
		logger.info("我是带参数的cBondFuturesPositions方法，正在被执行，参数为：" + param);
		Map<String, Object> zqMap = new HashMap<String, Object>();
		Map<String, Object> wdMap = new HashMap<String, Object>();
		zqMap.put("tableName", "CBONDFUTURESPOSITIONS");
		wdMap.put("tableName", "wind_filesync.CBONDFUTURESPOSITIONS");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryTotal,queryAll,deleteAllThenSave");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地万德CBONDFUTURESPOSITIONS数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = cBondFuturesPositionsService.queryTotal(zqMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		List<CBondFuturesPositionsEntity> list = cBondFuturesPositionsService.queryAll(wdMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {
			cBondFuturesPositionsService.deleteAllThenSave(list);
			if(before > 0) {
				log.setResult(1);
				int after = cBondFuturesPositionsService.queryTotal(zqMap);
				log.setResultDesc("非首次更新本地"+ after +"条万德数据库表CBONDFUTURESPOSITIONS记录成功！");
			}else {
				log.setResult(1);
				int after = cBondFuturesPositionsService.queryTotal(zqMap);
				log.setResultDesc("首次更新本地"+ after +"条万德数据库表CBONDFUTURESPOSITIONS记录成功！");
			}			
		}else {
			log.setResult(0);
			log.setResultDesc("没有查询到数据，更新本地万德数据库表CBONDFUTURESPOSITIONS失败！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
	/**
	 * 表CBONDISSUERRATING定时任务
	 * @param param
	 * 查询所有数据并更新
	 * 1，查询万德数据库
	 * 2，删除本地表数据，插入本地表
	 */
	public void updateCBondIssuerRating(String param) {
		logger.info("我是带参数的cBondIssuerRating方法，正在被执行，参数为：" + param);
		Map<String, Object> zqMap = new HashMap<String, Object>();
		Map<String, Object> wdMap = new HashMap<String, Object>();
		zqMap.put("tableName", "CBONDISSUERRATING");
		wdMap.put("tableName", "wind_filesync.CBONDISSUERRATING");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryTotal,queryAll,deleteAllThenSave");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地万德CBONDISSUERRATING数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = cBondIssuerRatingService.queryTotal(zqMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		List<CBondIssuerRatingEntity> list = cBondIssuerRatingService.queryAll(wdMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {
			cBondIssuerRatingService.deleteAllThenSave(list);
			if(before > 0) {
				log.setResult(1);
				int after = cBondIssuerRatingService.queryTotal(zqMap);
				log.setResultDesc("非首次更新本地"+ after +"条万德数据库表CBONDISSUERRATING记录成功！");
			}else {
				log.setResult(1);
				int after = cBondIssuerRatingService.queryTotal(zqMap);
				log.setResultDesc("首次更新本地"+ after +"条万德数据库表CBONDISSUERRATING记录成功！");
			}			
		}else {
			log.setResult(0);
			log.setResultDesc("没有查询到数据，更新本地万德数据库表CBONDISSUERRATING失败！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
	/**
	 * 表CBONDRATING定时任务
	 * @param param
	 * 查询所有数据并更新
	 * 1，查询万德数据库
	 * 2，删除本地表数据，插入本地表
	 */
	public void updateCBondRating(String param) {
		logger.info("我是带参数的cBondRating方法，正在被执行，参数为：" + param);
		Map<String, Object> zqMap = new HashMap<String, Object>();
		Map<String, Object> wdMap = new HashMap<String, Object>();
		zqMap.put("tableName", "CBONDRATING");
		wdMap.put("tableName", "wind_filesync.CBONDRATING");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryTotal,queryAll,deleteAllThenSave");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地万德CBONDRATING数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = cBondRatingService.queryTotal(zqMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		List<CBondRatingEntity> list = cBondRatingService.queryAll(wdMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {
			cBondRatingService.deleteAllThenSave(list);
			if(before > 0) {
				log.setResult(1);
				int after = cBondRatingService.queryTotal(zqMap);
				log.setResultDesc("非首次更新本地"+ after +"条万德数据库表CBONDRATING记录成功！");
			}else {
				log.setResult(1);
				int after = cBondRatingService.queryTotal(zqMap);
				log.setResultDesc("首次更新本地"+ after +"条万德数据库表CBONDRATING记录成功！");
			}			
		}else {
			log.setResult(0);
			log.setResultDesc("没有查询到数据，更新本地万德数据库表CBONDRATING失败！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
	/**
	 * 表CFUTURESDESCRIPTION定时任务
	 * @param param
	 * 查询所有数据并更新
	 * 1，查询万德数据库
	 * 2，删除本地表数据，插入本地表
	 */
	public void updateCFuturesDescription(String param) {
		logger.info("我是带参数的cFuturesDescription方法，正在被执行，参数为：" + param);
		Map<String, Object> zqMap = new HashMap<String, Object>();
		Map<String, Object> wdMap = new HashMap<String, Object>();
		zqMap.put("tableName", "CFUTURESDESCRIPTION");
		wdMap.put("tableName", "wind_filesync.CFUTURESDESCRIPTION");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryTotal,queryAll,deleteAllThenSave");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地万德CFUTURESDESCRIPTION数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = cFuturesDescriptionService.queryTotal(zqMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		List<CFuturesDescriptionEntity> list = cFuturesDescriptionService.queryAll(wdMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {
			cFuturesDescriptionService.deleteAllThenSave(list);
			if(before > 0) {
				log.setResult(1);
				int after = cFuturesDescriptionService.queryTotal(zqMap);
				log.setResultDesc("非首次更新本地"+ after +"条万德数据库表CFUTURESDESCRIPTION记录成功！");
			}else {
				log.setResult(1);
				int after = cFuturesDescriptionService.queryTotal(zqMap);
				log.setResultDesc("首次更新本地"+ after +"条万德数据库表CFUTURESDESCRIPTION记录成功！");
			}			
		}else {
			log.setResult(0);
			log.setResultDesc("没有查询到数据，更新本地万德数据库表CFUTURESDESCRIPTION失败！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
}
