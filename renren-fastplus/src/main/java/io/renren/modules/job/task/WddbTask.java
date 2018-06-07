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
import io.renren.modules.ht.entity.TCalendarDatesEntity;
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
	 * 1,在本地查询最近的数据opdate
	 * 1.1，如果不为空就以插入万德中大于这个日期的数据
	 * 1.2，如果为空就全量更新
	 */
	public void updateCBondFuturesEODPrices(String param) {
		logger.info("我是带参数的cBondFuturesEODPrices方法，正在被执行，参数为：" + param);
		Map<String, Object> zqMap = new HashMap<String, Object>();
		Map<String, Object> wdMap = new HashMap<String, Object>();
		zqMap.put("tableName","CBONDFUTURESEODPRICES");
		wdMap.put("tableName","wind_filesync.CBONDFUTURESEODPRICES");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryList,saveBatch,updateStatus,deleteStatus");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地万得CBONDFUTURESEODPRICES数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = cBondFuturesEODPricesService.queryTotal(zqMap);
		List<CBondFuturesEODPricesEntity> list = null;
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		Integer total = cBondFuturesEODPricesService.queryTotal(wdMap);
		DynamicDataSource.clearDataSource();
		Integer offset = 0;
		Integer limit = 10000;
		wdMap.put("limit", limit);
		if(before > 0) {
			DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			cBondFuturesEODPricesService.deleteStatus();
			DynamicDataSource.clearDataSource();
			do {
				wdMap.put("offset", offset);
				DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
				list = cBondFuturesEODPricesService.queryList(wdMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
				if (list != null && list.size() > 0) {
					int pointsDataLimit = 400;// 限制条数
					Integer size = list.size();
					if (pointsDataLimit < size) {// 判断是否有必要分批
						int part = size / pointsDataLimit;// 分批数
						for (int i = 0; i < part; i++) {
							List<CBondFuturesEODPricesEntity> listPage = list.subList(0, pointsDataLimit);
							cBondFuturesEODPricesService.saveBatch(listPage);
							list.subList(0, pointsDataLimit).clear();
						}
						if (!list.isEmpty()) {
							cBondFuturesEODPricesService.saveBatch(list);// 最后剩下的数据
						}
					} else {
						cBondFuturesEODPricesService.saveBatch(list);
					}
				} else {
					System.out.println("没有数据!!!");
				}
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
			DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			cBondFuturesEODPricesService.updateStatus();
			DynamicDataSource.clearDataSource();
		}else {
			do {
				wdMap.put("offset", offset);
				DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
				list = cBondFuturesEODPricesService.queryList(wdMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
				if (list != null && list.size() > 0) {
					int pointsDataLimit = 400;
					Integer size = list.size();
					if (pointsDataLimit < size) {
						int part = size / pointsDataLimit;
						for (int i = 0; i < part; i++) {
							List<CBondFuturesEODPricesEntity> listPage = list.subList(0, pointsDataLimit);
							cBondFuturesEODPricesService.firstSaveBatch(listPage);
							list.subList(0, pointsDataLimit).clear();
						}
						if (!list.isEmpty()) {
							cBondFuturesEODPricesService.firstSaveBatch(list);
						}
					} else {
						cBondFuturesEODPricesService.firstSaveBatch(list);
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
		int after = cBondFuturesEODPricesService.queryTotal(zqMap);
		int success = after;
		int failed = total - after;
		if (before > 0) {
			log.setResult(1);
			log.setResultDesc("成功：" + success + "，失败：" + failed + "。非首次更新本地万得数据库表CBONDFUTURESEODPRICES！");
		} else {
			log.setResult(1);
			log.setResultDesc("成功：" + success + "，失败：" + failed + "。首次更新本地万得数据库表CBONDFUTURESEODPRICES！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
	/**
	 * 表CBONDFUTURESPOSITIONS定时任务
	 * @param param
	 * 1,在本地查询最近的数据opdate
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
		log.setFunctionName("queryList,saveBatch,updateStatus,deleteStatus");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地万得CBONDFUTURESPOSITIONS数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = cBondFuturesPositionsService.queryTotal(zqMap);
		List<CBondFuturesPositionsEntity> list = null;
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		Integer total = cBondFuturesPositionsService.queryTotal(wdMap);
		DynamicDataSource.clearDataSource();
		Integer offset = 0;
		Integer limit = 10000;
		wdMap.put("limit", limit);
		if(before > 0) {
			DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			cBondFuturesPositionsService.deleteStatus();
			DynamicDataSource.clearDataSource();
			do {
				wdMap.put("offset", offset);
				DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
				list = cBondFuturesPositionsService.queryList(wdMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
				if (list != null && list.size() > 0) {
					int pointsDataLimit = 400;// 限制条数
					Integer size = list.size();
					if (pointsDataLimit < size) {// 判断是否有必要分批
						int part = size / pointsDataLimit;// 分批数
						for (int i = 0; i < part; i++) {
							List<CBondFuturesPositionsEntity> listPage = list.subList(0, pointsDataLimit);
							cBondFuturesPositionsService.saveBatch(listPage);
							list.subList(0, pointsDataLimit).clear();
						}
						if (!list.isEmpty()) {
							cBondFuturesPositionsService.saveBatch(list);// 最后剩下的数据
						}
					} else {
						cBondFuturesPositionsService.saveBatch(list);
					}
				} else {
					System.out.println("没有数据!!!");
				}
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
			DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			cBondFuturesPositionsService.updateStatus();
			DynamicDataSource.clearDataSource();
		}else {
			do {
				wdMap.put("offset", offset);
				DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
				list = cBondFuturesPositionsService.queryList(wdMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
				if (list != null && list.size() > 0) {
					int pointsDataLimit = 400;
					Integer size = list.size();
					if (pointsDataLimit < size) {
						int part = size / pointsDataLimit;
						for (int i = 0; i < part; i++) {
							List<CBondFuturesPositionsEntity> listPage = list.subList(0, pointsDataLimit);
							cBondFuturesPositionsService.firstSaveBatch(listPage);
							list.subList(0, pointsDataLimit).clear();
						}
						if (!list.isEmpty()) {
							cBondFuturesPositionsService.firstSaveBatch(list);
						}
					} else {
						cBondFuturesPositionsService.firstSaveBatch(list);
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
		int after = cBondFuturesPositionsService.queryTotal(zqMap);
		int success = after;
		int failed = total - after;
		if (before > 0) {
			log.setResult(1);
			log.setResultDesc("成功：" + success + "，失败：" + failed + "。非首次更新本地万得数据库表CBONDFUTURESPOSITIONS！");
		} else {
			log.setResult(1);
			log.setResultDesc("成功：" + success + "，失败：" + failed + "。首次更新本地万得数据库表CBONDFUTURESPOSITIONS！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
	/**
	 * 表CBONDISSUERRATING定时任务
	 * @param param
	 * 1,在本地查询最近的数据opdate
	 * 1.1，如果不为空就以插入万德中大于这个日期的数据
	 * 1.2，如果为空就全量更新
	 */
	public void updateCBondIssuerRating(String param) {
		logger.info("我是带参数的cBondIssuerRating方法，正在被执行，参数为：" + param);
		Map<String, Object> zqMap = new HashMap<String, Object>();
		Map<String, Object> wdMap = new HashMap<String, Object>();
		zqMap.put("tableName", "CBONDISSUERRATING");
		wdMap.put("tableName", "wind_filesync.CBONDISSUERRATING");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryList,saveBatch,updateStatus,deleteStatus");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地万得CBONDISSUERRATING数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = cBondIssuerRatingService.queryTotal(zqMap);
		List<CBondIssuerRatingEntity> list = null;
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		Integer total = cBondIssuerRatingService.queryTotal(wdMap);
		DynamicDataSource.clearDataSource();
		Integer offset = 0;
		Integer limit = 10000;
		wdMap.put("limit", limit);
		if(before > 0) {
			DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			cBondIssuerRatingService.deleteStatus();
			DynamicDataSource.clearDataSource();
			do {
				wdMap.put("offset", offset);
				DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
				list = cBondIssuerRatingService.queryList(wdMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
				if (list != null && list.size() > 0) {
					int pointsDataLimit = 400;// 限制条数
					Integer size = list.size();
					if (pointsDataLimit < size) {// 判断是否有必要分批
						int part = size / pointsDataLimit;// 分批数
						for (int i = 0; i < part; i++) {
							List<CBondIssuerRatingEntity> listPage = list.subList(0, pointsDataLimit);
							cBondIssuerRatingService.saveBatch(listPage);
							list.subList(0, pointsDataLimit).clear();
						}
						if (!list.isEmpty()) {
							cBondIssuerRatingService.saveBatch(list);// 最后剩下的数据
						}
					} else {
						cBondIssuerRatingService.saveBatch(list);
					}
				} else {
					System.out.println("没有数据!!!");
				}
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
			DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			cBondIssuerRatingService.updateStatus();
			DynamicDataSource.clearDataSource();
		}else {
			do {
				wdMap.put("offset", offset);
				DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
				list = cBondIssuerRatingService.queryList(wdMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
				if (list != null && list.size() > 0) {
					int pointsDataLimit = 400;
					Integer size = list.size();
					if (pointsDataLimit < size) {
						int part = size / pointsDataLimit;
						for (int i = 0; i < part; i++) {
							List<CBondIssuerRatingEntity> listPage = list.subList(0, pointsDataLimit);
							cBondIssuerRatingService.firstSaveBatch(listPage);
							list.subList(0, pointsDataLimit).clear();
						}
						if (!list.isEmpty()) {
							cBondIssuerRatingService.firstSaveBatch(list);
						}
					} else {
						cBondIssuerRatingService.firstSaveBatch(list);
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
		int after = cBondIssuerRatingService.queryTotal(zqMap);
		int success = after;
		int failed = total - after;
		if (before > 0) {
			log.setResult(1);
			log.setResultDesc("成功：" + success + "，失败：" + failed + "。非首次更新本地万得数据库表CBONDISSUERRATING！");
		} else {
			log.setResult(1);
			log.setResultDesc("成功：" + success + "，失败：" + failed + "。首次更新本地万得数据库表CBONDISSUERRATING！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
	/**
	 * 表CBONDRATING定时任务
	 * @param param
	 * 1,在本地查询最近的数据opdate
	 * 1.1，如果不为空就以插入万德中大于这个日期的数据
	 * 1.2，如果为空就全量更新
	 */
	public void updateCBondRating(String param) {
		logger.info("我是带参数的cBondRating方法，正在被执行，参数为：" + param);
		Map<String, Object> zqMap = new HashMap<String, Object>();
		Map<String, Object> wdMap = new HashMap<String, Object>();
		zqMap.put("tableName", "CBONDRATING");
		wdMap.put("tableName", "wind_filesync.CBONDRATING");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryList,saveBatch,updateStatus,deleteStatus");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地万得CBONDRATING数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = cBondRatingService.queryTotal(zqMap);
		List<CBondRatingEntity> list = null;
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		Integer total = cBondRatingService.queryTotal(wdMap);
		DynamicDataSource.clearDataSource();
		Integer offset = 0;
		Integer limit = 10000;
		wdMap.put("limit", limit);
		if(before > 0) {
			DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			cBondRatingService.deleteStatus();
			DynamicDataSource.clearDataSource();
			do {
				wdMap.put("offset", offset);
				DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
				list = cBondRatingService.queryList(wdMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
				if (list != null && list.size() > 0) {
					int pointsDataLimit = 400;// 限制条数
					Integer size = list.size();
					if (pointsDataLimit < size) {// 判断是否有必要分批
						int part = size / pointsDataLimit;// 分批数
						for (int i = 0; i < part; i++) {
							List<CBondRatingEntity> listPage = list.subList(0, pointsDataLimit);
							cBondRatingService.saveBatch(listPage);
							list.subList(0, pointsDataLimit).clear();
						}
						if (!list.isEmpty()) {
							cBondRatingService.saveBatch(list);// 最后剩下的数据
						}
					} else {
						cBondRatingService.saveBatch(list);
					}
				} else {
					System.out.println("没有数据!!!");
				}
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
			DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			cBondRatingService.updateStatus();
			DynamicDataSource.clearDataSource();
		}else {
			do {
				wdMap.put("offset", offset);
				DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
				list = cBondRatingService.queryList(wdMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
				if (list != null && list.size() > 0) {
					int pointsDataLimit = 400;
					Integer size = list.size();
					if (pointsDataLimit < size) {
						int part = size / pointsDataLimit;
						for (int i = 0; i < part; i++) {
							List<CBondRatingEntity> listPage = list.subList(0, pointsDataLimit);
							cBondRatingService.firstSaveBatch(listPage);
							list.subList(0, pointsDataLimit).clear();
						}
						if (!list.isEmpty()) {
							cBondRatingService.firstSaveBatch(list);
						}
					} else {
						cBondRatingService.firstSaveBatch(list);
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
		int after = cBondRatingService.queryTotal(zqMap);
		int success = after;
		int failed = total - after;
		if (before > 0) {
			log.setResult(1);
			log.setResultDesc("成功：" + success + "，失败：" + failed + "。非首次更新本地万得数据库表CBONDRATING！");
		} else {
			log.setResult(1);
			log.setResultDesc("成功：" + success + "，失败：" + failed + "。首次更新本地万得数据库表CBONDRATING！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
	/**
	 * 表CFUTURESDESCRIPTION定时任务
	 * @param param
	 * 1,在本地查询最近的数据opdate
	 * 1.1，如果不为空就以插入万德中大于这个日期的数据
	 * 1.2，如果为空就全量更新
	 */
	public void updateCFuturesDescription(String param) {
		logger.info("我是带参数的cFuturesDescription方法，正在被执行，参数为：" + param);
		Map<String, Object> zqMap = new HashMap<String, Object>();
		Map<String, Object> wdMap = new HashMap<String, Object>();
		zqMap.put("tableName", "CFUTURESDESCRIPTION");
		wdMap.put("tableName", "wind_filesync.CFUTURESDESCRIPTION");
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName("queryList,saveBatch,updateStatus,deleteStatus");
		log.setParam(param);
		log.setCreateTime(new Date());
		log.setWay(0);
		log.setReason("用以更新本地万得CFUTURESDESCRIPTION数据库表！");
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		int before = cFuturesDescriptionService.queryTotal(zqMap);
		List<CFuturesDescriptionEntity> list = null;
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
		Integer total = cFuturesDescriptionService.queryTotal(wdMap);
		DynamicDataSource.clearDataSource();
		Integer offset = 0;
		Integer limit = 10000;
		wdMap.put("limit", limit);
		if(before > 0) {
			DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			cFuturesDescriptionService.deleteStatus();
			DynamicDataSource.clearDataSource();
			do {
				wdMap.put("offset", offset);
				DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
				list = cFuturesDescriptionService.queryList(wdMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
				if (list != null && list.size() > 0) {
					int pointsDataLimit = 400;// 限制条数
					Integer size = list.size();
					if (pointsDataLimit < size) {// 判断是否有必要分批
						int part = size / pointsDataLimit;// 分批数
						for (int i = 0; i < part; i++) {
							List<CFuturesDescriptionEntity> listPage = list.subList(0, pointsDataLimit);
							cFuturesDescriptionService.saveBatch(listPage);
							list.subList(0, pointsDataLimit).clear();
						}
						if (!list.isEmpty()) {
							cFuturesDescriptionService.saveBatch(list);// 最后剩下的数据
						}
					} else {
						cFuturesDescriptionService.saveBatch(list);
					}
				} else {
					System.out.println("没有数据!!!");
				}
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
			DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			cFuturesDescriptionService.updateStatus();
			DynamicDataSource.clearDataSource();
		}else {
			do {
				wdMap.put("offset", offset);
				DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
				list = cFuturesDescriptionService.queryList(wdMap);
				DynamicDataSource.clearDataSource();
				DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
				if (list != null && list.size() > 0) {
					int pointsDataLimit = 400;
					Integer size = list.size();
					if (pointsDataLimit < size) {
						int part = size / pointsDataLimit;
						for (int i = 0; i < part; i++) {
							List<CFuturesDescriptionEntity> listPage = list.subList(0, pointsDataLimit);
							cFuturesDescriptionService.firstSaveBatch(listPage);
							list.subList(0, pointsDataLimit).clear();
						}
						if (!list.isEmpty()) {
							cFuturesDescriptionService.firstSaveBatch(list);
						}
					} else {
						cFuturesDescriptionService.firstSaveBatch(list);
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
		int after = cFuturesDescriptionService.queryTotal(zqMap);
		int success = after;
		int failed = total - after;
		if (before > 0) {
			log.setResult(1);
			log.setResultDesc("成功：" + success + "，失败：" + failed + "。非首次更新本地万得数据库表CFUTURESDESCRIPTION！");
		} else {
			log.setResult(1);
			log.setResultDesc("成功：" + success + "，失败：" + failed + "。首次更新本地万得数据库表CFUTURESDESCRIPTION！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		DynamicDataSource.clearDataSource();
	}
}
