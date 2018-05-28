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
		Map<String, Object> wdMap = new HashMap<String, Object>();
		Map<String,Object> zqMap = new HashMap<String,Object>();
		zqMap.put("tableName","CBONDFUTURESEODPRICES");				
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryFirst,queryLatest,save");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地万德CBONDFUTURESEODPRICES数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		String latest = cBondFuturesEODPricesService.queryFirst(zqMap).getTradeDt();
		DynamicDataSource.clearDataSource();				
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		wdMap.put("tableName", "wind_filesync.CBONDFUTURESEODPRICES");
		wdMap.put("latest", latest);
		List<CBondFuturesEODPricesEntity> list = cBondFuturesEODPricesService.queryLatest(wdMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {
			for(CBondFuturesEODPricesEntity cb : list) {
				cBondFuturesEODPricesService.save(cb);
			}
			log.setResult(1);
			log.setResultDesc("更新本地万德数据库表CBONDFUTURESEODPRICES成功！");
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
		Map<String, Object> wdMap = new HashMap<String, Object>();
		Map<String,Object> zqMap = new HashMap<String,Object>();
		zqMap.put("tableName","CBONDFUTURESPOSITIONS");				
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryFirst,queryLatest,save");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地万德CBONDFUTURESPOSITIONS数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		String latest = cBondFuturesPositionsService.queryFirst(zqMap).getTradeDt();
		DynamicDataSource.clearDataSource();				
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		wdMap.put("tableName", "wind_filesync.CBONDFUTURESPOSITIONS");
		wdMap.put("latest", latest);
		List<CBondFuturesPositionsEntity> list = cBondFuturesPositionsService.queryLatest(wdMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {
			for(CBondFuturesPositionsEntity cb : list) {
				cBondFuturesPositionsService.save(cb);
			}
			log.setResult(1);
			log.setResultDesc("更新本地万德数据库表CBONDFUTURESPOSITIONS成功！");
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
		Map<String, Object> wdMap = new HashMap<String, Object>();
		wdMap.put("tableName", "CBONDISSUERRATING");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryAll,deleteAllThenSave");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地万德CBONDISSUERRATING数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		List<CBondIssuerRatingEntity> list = cBondIssuerRatingService.queryAll(wdMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {		
			cBondIssuerRatingService.deleteAllThenSave(list);
			log.setResult(1);
			log.setResultDesc("更新本地万德数据库表CBONDISSUERRATING成功！");
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
		Map<String, Object> wdMap = new HashMap<String, Object>();
		wdMap.put("tableName", "CBONDRATING");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryAll,deleteAllThenSave");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地万德CBONDRATING数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		List<CBondRatingEntity> list = cBondRatingService.queryAll(wdMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {		
			cBondRatingService.deleteAllThenSave(list);
			log.setResult(1);
			log.setResultDesc("更新本地万德数据库表CBONDRATING成功！");
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
		Map<String, Object> wdMap = new HashMap<String, Object>();
		wdMap.put("tableName", "wind_filesync.CFUTURESDESCRIPTION");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryAll,deleteAllThenSave");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地万德CFUTURESDESCRIPTION数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		List<CFuturesDescriptionEntity> list = cFuturesDescriptionService.queryAll(wdMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {		
			cFuturesDescriptionService.deleteAllThenSave(list);
			log.setResult(1);
			log.setResultDesc("更新本地万德数据库表CFUTURESDESCRIPTION成功！");
		}else {
			log.setResult(0);
			log.setResultDesc("没有查询到数据，更新本地万德数据库表CFUTURESDESCRIPTION失败！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
}
