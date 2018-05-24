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
import io.renren.common.utils.SpringContextUtils;
import io.renren.datasources.DataSourceNames;
import io.renren.datasources.DynamicDataSource;
import io.renren.modules.job.entity.SyncPushLogEntity;
import io.renren.modules.job.service.SyncPushLogService;
import io.renren.modules.wd.entity.CBondFuturesEODPricesEntity;
import io.renren.modules.wd.service.CBondFuturesEODPricesService;

/**
 * @author DHB
 * @date 2018年5月23日下午2:32:15
 * 万德数据库定时任务
 */
@Component("wddbTask")
public class WddbTask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	SyncPushLogService syncPushLogService = (SyncPushLogService) SpringContextUtils.getBean("syncPushLogService");
	@Autowired
	private CBondFuturesEODPricesService cBondFuturesEODPricesService;
	@Value("${spring.datasource.druid.wddb_source.url}")
	private String url;
	/**
	 * 表CBondFuturesEODPrices定时任务
	 * @param param
	 * 1,在主表查询更新今日日期下的数据
	 * 1.1，如果不为空就以插入本地数据库
	 * 1.2，如果为空就没有数据返回
	 */
	public void cBondFuturesEODPrices(String param) {
		logger.info("我是带参数的getDepartmentInformation方法，正在被执行，参数为：" + param);
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
		List<CBondFuturesEODPricesEntity> cbList = cBondFuturesEODPricesService.queryByTdDate(wdMap);
		DynamicDataSource.clearDataSource();
		DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
		if(cbList != null) {
			for(CBondFuturesEODPricesEntity cb : cbList) {
				System.out.println("==="+cb.getTradeDt()+"==="+cb.getsInfoWindcode());
				//cBondFuturesEODPricesService.save(cb);
			}
			log.setResult(1);
			log.setResultDesc("更新本地万德数据库表CBONDFUTURESEODPRICES成功！");
		}else {
			log.setResult(0);
			log.setResultDesc("没有查询到今日数据，更新本地万德数据库表CBONDFUTURESEODPRICES失败！");
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		/*if(cBondFuturesEODPricesService.queryFirst(zqMap) != null) {
			String dStart = cBondFuturesEODPricesService.queryFirst(zqMap).getTradeDt();
			//DynamicDataSource.clearDataSource();
			//DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);
			wdMap.put("dStart", "2014-09-09");
			List<CBondFuturesEODPricesEntity> cbList = cBondFuturesEODPricesService.queryList(wdMap);
			
			for(CBondFuturesEODPricesEntity cb : cbList) {
				System.out.println("====="+cb.getTradeDt()+"======");
			}
			
			
			//DynamicDataSource.clearDataSource();
			//DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			//for(CBondFuturesEODPricesEntity cb : cbList) {
				//cBondFuturesEODPricesService.save(cb);
			//}
			//DynamicDataSource.clearDataSource();
		}else {
			//DynamicDataSource.clearDataSource();
			//DynamicDataSource.setDataSource(DataSourceNames.WDDB_SOURCE);			
			wdMap.put("dStart", param);
			List<CBondFuturesEODPricesEntity> cbList = cBondFuturesEODPricesService.queryList(wdMap);
			//DynamicDataSource.clearDataSource();
			//DynamicDataSource.setDataSource(DataSourceNames.ZQDB_SOURCE);
			for(CBondFuturesEODPricesEntity cb : cbList) {
				cBondFuturesEODPricesService.save(cb);
			}
		}*/
		
	}
}
