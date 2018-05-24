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
	 * 1,在主表查询更新今日日期下的数据
	 * 1.1，如果不为空就以插入本地数据库
	 * 1.2，如果为空就没有数据返回
	 */
	public void cBondFuturesEODPrices(String param) {
		logger.info("我是带参数的cBondFuturesEODPrices方法，正在被执行，参数为：" + param);
		Map<String, Object> wdMap = new HashMap<String, Object>();
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("save");
		log.setParam(param);
		log.setWay(0);
		log.setReason("用以更新本地万德CBONDFUTURESEODPRICES数据库表！");
		String trderDt = DateUtils.format(new Date()).toString();
		wdMap.put("tableName", "wind_filesync.CBONDFUTURESEODPRICES");
		wdMap.put("tradeDt", trderDt);
		System.out.println(trderDt+"============");
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		List<CBondFuturesEODPricesEntity> list = cBondFuturesEODPricesService.queryByTdDate(wdMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {
			for(CBondFuturesEODPricesEntity cb : list) {
				System.out.println("==="+cb.getTradeDt()+"==="+cb.getsInfoWindcode());
				cBondFuturesEODPricesService.save(cb);
			}
			log.setResult(1);
			log.setResultDesc("更新本地万德数据库表CBONDFUTURESEODPRICES成功！");
		}else {
			log.setResult(0);
			log.setResultDesc("没有查询到今日数据，更新本地万德数据库表CBONDFUTURESEODPRICES失败！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
	/**
	 * 表CBONDFUTURESPOSITIONS定时任务
	 * @param param
	 * 1,在主表查询更新今日日期下的数据
	 * 1.1，如果不为空就以插入本地数据库
	 * 1.2，如果为空就没有数据返回
	 */
	public void cBondFuturesPositions(String param) {
		logger.info("我是带参数的cBondFuturesPositions方法，正在被执行，参数为：" + param);
		Map<String, Object> wdMap = new HashMap<String, Object>();
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryByTdDate,save");
		log.setParam(param);
		log.setWay(0);
		log.setReason("用以更新本地万德CBONDFUTURESPOSITIONS数据库表！");
		String trderDt = DateUtils.format(new Date()).toString();
		wdMap.put("tableName", "wind_filesync.CBONDFUTURESPOSITIONS");
		wdMap.put("tradeDt", trderDt);
		System.out.println(trderDt+"============");
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		List<CBondFuturesPositionsEntity> list = cBondFuturesPositionsService.queryByTdDate(wdMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(list != null && list.size() != 0) {
			for(CBondFuturesPositionsEntity cb : list) {
				System.out.println("==="+cb.getTradeDt()+"==="+cb.getsInfoWindcode());
				cBondFuturesPositionsService.save(cb);
			}
			log.setResult(1);
			log.setResultDesc("更新本地万德数据库表CBONDFUTURESPOSITIONS成功！");
		}else {
			log.setResult(0);
			log.setResultDesc("没有查询到今日数据，更新本地万德数据库表CBONDFUTURESPOSITIONS失败！");
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
	public void cBondIssuerRating(String param) {
		logger.info("我是带参数的cBondIssuerRating方法，正在被执行，参数为：" + param);
		Map<String, Object> wdMap = new HashMap<String, Object>();
		wdMap.put("tableName", "wind_filesync.CBONDISSUERRATING");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryAll,deleteAllThenSave");
		log.setParam(param);
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
	public void cBondRating(String param) {
		logger.info("我是带参数的cBondRating方法，正在被执行，参数为：" + param);
		Map<String, Object> wdMap = new HashMap<String, Object>();
		wdMap.put("tableName", "wind_filesync.CBONDRATING");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryAll,deleteAllThenSave");
		log.setParam(param);
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
	public void cFuturesDescription(String param) {
		logger.info("我是带参数的cFuturesDescription方法，正在被执行，参数为：" + param);
		Map<String, Object> wdMap = new HashMap<String, Object>();
		wdMap.put("tableName", "wind_filesync.CFUTURESDESCRIPTION");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryAll,deleteAllThenSave");
		log.setParam(param);
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
