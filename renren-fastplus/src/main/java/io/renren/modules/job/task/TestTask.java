package io.renren.modules.job.task;

import io.renren.datasources.DataSourceNames;
import io.renren.datasources.TestService;
import io.renren.datasources.annotation.DataSource;
import io.renren.modules.wd.entity.CBondFuturesEODPricesEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试定时任务(演示Demo，可删除)
 * 
 * testTask为spring bean的名称
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月30日 下午1:34:24
 */
@Component("testTask")
public class TestTask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TestService tsestService;
	
	
	@DataSource(name = DataSourceNames.WDDB_SOURCE)
	public void test(String params){
		logger.info("我是带参数的test方法，正在被执行，参数为：" + params);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tradeDt", "2017");
		List<CBondFuturesEODPricesEntity> cbList = tsestService.queryList(map);
		System.out.println("==================start");
		for(int i = 0;i <=  cbList.size();i++) {
			System.out.println("==================start2" + cbList.size());
			System.out.println(cbList.get(i).toString());
			System.out.println("==================end");
			tsestService.saveToLocal(cbList.get(i));
		}
/*		for(CBondFuturesEODPricesEntity cbb : cbList){

			
		}*/
		
	}
	
	
	public void test2(){
		logger.info("我是不带参数的test2方法，正在被执行");
	}
}
