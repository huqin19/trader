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
	 * 1,首次同步 status=1
	 * 2,非首次同步 
	 * 2.1,先将status=0的数据删除
	 * 2.2,本轮同步、本轮同步的数据status=0，
	 * 2.3,同步完成后将status=1的改为status=0、将status=0的置为1
	 */
	public void updateCBondFuturesEODPrices(String param, Integer way) {
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
		log.setWay(way);
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
				if(list != null && list.size() > 0) {
					int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE").toString());
					List<CBondFuturesEODPricesEntity> saveList = new ArrayList<CBondFuturesEODPricesEntity>();
					for(int i = 0; i < list.size(); i++) {
						if(i > 0 && i%loopSize == 0) {
							cBondFuturesEODPricesService.saveBatch(saveList);
							saveList.clear();
							saveList = new ArrayList<CBondFuturesEODPricesEntity>();
						}
						saveList.add(list.get(i));
					}
					if(saveList.size() > 0) {
						cBondFuturesEODPricesService.saveBatch(saveList);
					}
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
				if(list != null && list.size() > 0) {
					int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE").toString());
					List<CBondFuturesEODPricesEntity> saveList = new ArrayList<CBondFuturesEODPricesEntity>();
					for(int i = 0; i < list.size(); i++) {
						if(i > 0 && i%loopSize == 0) {
							cBondFuturesEODPricesService.firstSaveBatch(saveList);
							saveList.clear();
							saveList = new ArrayList<CBondFuturesEODPricesEntity>();
						}
						saveList.add(list.get(i));
					}
					if(saveList.size() > 0) {
						cBondFuturesEODPricesService.firstSaveBatch(saveList);
					}
				}	
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
		}	
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
	 * 1,首次同步 status=1
	 * 2,非首次同步 
	 * 2.1,先将status=0的数据删除
	 * 2.2,本轮同步、本轮同步的数据status=0，
	 * 2.3,同步完成后将status=1的改为status=0、将status=0的置为1
	 */
	public void updateCBondFuturesPositions(String param, Integer way) {
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
		log.setWay(way);
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
				if(list != null && list.size() > 0) {
					int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE").toString());
					List<CBondFuturesPositionsEntity> saveList = new ArrayList<CBondFuturesPositionsEntity>();
					for(int i = 0; i < list.size(); i++) {
						if(i > 0 && i%loopSize == 0) {
							cBondFuturesPositionsService.saveBatch(saveList);
							saveList.clear();
							saveList = new ArrayList<CBondFuturesPositionsEntity>();
						}
						saveList.add(list.get(i));
					}
					if(saveList.size() > 0) {
						cBondFuturesPositionsService.saveBatch(saveList);
					}
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
				if(list != null && list.size() > 0) {
					int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE").toString());
					List<CBondFuturesPositionsEntity> saveList = new ArrayList<CBondFuturesPositionsEntity>();
					for(int i = 0; i < list.size(); i++) {
						if(i > 0 && i%loopSize == 0) {
							cBondFuturesPositionsService.firstSaveBatch(saveList);
							saveList.clear();
							saveList = new ArrayList<CBondFuturesPositionsEntity>();
						}
						saveList.add(list.get(i));
					}
					if(saveList.size() > 0) {
						cBondFuturesPositionsService.firstSaveBatch(saveList);
					}
				}
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
		}	
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
	 * 1,首次同步 status=1
	 * 2,非首次同步 
	 * 2.1,先将status=0的数据删除
	 * 2.2,本轮同步、本轮同步的数据status=0，
	 * 2.3,同步完成后将status=1的改为status=0、将status=0的置为1
	 */
	public void updateCBondIssuerRating(String param, Integer way) {
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
		log.setWay(way);
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
				if(list != null && list.size() > 0) {
					int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE").toString());
					List<CBondIssuerRatingEntity> saveList = new ArrayList<CBondIssuerRatingEntity>();
					for(int i = 0; i < list.size(); i++) {
						if(i > 0 && i%loopSize == 0) {
							cBondIssuerRatingService.saveBatch(saveList);
							saveList.clear();
							saveList = new ArrayList<CBondIssuerRatingEntity>();
						}
						saveList.add(list.get(i));
					}
					if(saveList.size() > 0) {
						cBondIssuerRatingService.saveBatch(saveList);
					}
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
				if(list != null && list.size() > 0) {
					int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE").toString());
					List<CBondIssuerRatingEntity> saveList = new ArrayList<CBondIssuerRatingEntity>();
					for(int i = 0; i < list.size(); i++) {
						if(i > 0 && i%loopSize == 0) {
							cBondIssuerRatingService.firstSaveBatch(saveList);
							saveList.clear();
							saveList = new ArrayList<CBondIssuerRatingEntity>();
						}
						saveList.add(list.get(i));
					}
					if(saveList.size() > 0) {
						cBondIssuerRatingService.firstSaveBatch(saveList);
					}
				}	
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
		}	
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
	 * 1,首次同步 status=1
	 * 2,非首次同步 
	 * 2.1,先将status=0的数据删除
	 * 2.2,本轮同步、本轮同步的数据status=0，
	 * 2.3,同步完成后将status=1的改为status=0、将status=0的置为1
	 */
	public void updateCBondRating(String param, Integer way) {
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
		log.setWay(way);
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
				if(list != null && list.size() > 0) {
					int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE").toString());
					List<CBondRatingEntity> saveList = new ArrayList<CBondRatingEntity>();
					for(int i = 0; i < list.size(); i++) {
						if(i > 0 && i%loopSize == 0) {
							cBondRatingService.saveBatch(saveList);
							saveList.clear();
							saveList = new ArrayList<CBondRatingEntity>();
						}
						saveList.add(list.get(i));
					}
					if(saveList.size() > 0) {
						cBondRatingService.saveBatch(saveList);
					}
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
				if(list != null && list.size() > 0) {
					int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE").toString());
					List<CBondRatingEntity> saveList = new ArrayList<CBondRatingEntity>();
					for(int i = 0; i < list.size(); i++) {
						if(i > 0 && i%loopSize == 0) {
							cBondRatingService.firstSaveBatch(saveList);
							saveList.clear();
							saveList = new ArrayList<CBondRatingEntity>();
						}
						saveList.add(list.get(i));
					}
					if(saveList.size() > 0) {
						cBondRatingService.firstSaveBatch(saveList);
					}
				}	
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
		}	
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
	 * 1,首次同步 status=1
	 * 2,非首次同步 
	 * 2.1,先将status=0的数据删除
	 * 2.2,本轮同步、本轮同步的数据status=0，
	 * 2.3,同步完成后将status=1的改为status=0、将status=0的置为1
	 */
	public void updateCFuturesDescription(String param, Integer way) {
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
		log.setWay(way);
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
				if(list != null && list.size() > 0) {
					int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE").toString());
					List<CFuturesDescriptionEntity> saveList = new ArrayList<CFuturesDescriptionEntity>();
					for(int i = 0; i < list.size(); i++) {
						if(i > 0 && i%loopSize == 0) {
							cFuturesDescriptionService.saveBatch(saveList);
							saveList.clear();
							saveList = new ArrayList<CFuturesDescriptionEntity>();
						}
						saveList.add(list.get(i));
					}
					if(saveList.size() > 0) {
						cFuturesDescriptionService.saveBatch(saveList);
					}
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
				if(list != null && list.size() > 0) {
					int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE").toString());
					List<CFuturesDescriptionEntity> saveList = new ArrayList<CFuturesDescriptionEntity>();
					for(int i = 0; i < list.size(); i++) {
						if(i > 0 && i%loopSize == 0) {
							cFuturesDescriptionService.firstSaveBatch(saveList);
							saveList.clear();
							saveList = new ArrayList<CFuturesDescriptionEntity>();
						}
						saveList.add(list.get(i));
					}
					if(saveList.size() > 0) {
						cFuturesDescriptionService.firstSaveBatch(saveList);
					}
				}
				DynamicDataSource.clearDataSource();
				list.clear();
				offset = offset + limit;
			} while (offset < total);
		}	
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
