package io.renren.modules.job.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.job.entity.DepartmentEntity;
import io.renren.modules.job.entity.ResultEntity;
import io.renren.modules.job.entity.SyncPushLogEntity;
import io.renren.modules.job.entity.UsersEntity;
import io.renren.modules.job.service.SyncPushLogService;
import io.renren.modules.job.utils.GsonUtil;
import io.renren.modules.job.utils.HttpClientUtils;
/**
 * 定时任务接受用户
 * @author DHB
 * @date 2018年5月4日 
 */
@Component("getUserTask")
public class GetUserTask {
	//接口名称
	@Value("${api.request-user.name}")
	private String name;
	//请求URL
	@Value("${api.request-user.url}")
	private String url;
	//参数名
	@Value("${api.request-user.param-name}")
	private String paramName;
	private Logger logger = LoggerFactory.getLogger(getClass());
	//获取spring bean
	SyncPushLogService syncPushLogService = (SyncPushLogService) SpringContextUtils.getBean("syncPushLogService");
	/**
	 * 1.2接口返回
	 * @param param 入参
	 * 将返回数据日志记录数据库
	 * 1，如果返回结果为空，获取成员信息失败
	 * 2，如果返回结果状态为1，获取成员信息成功，否则就失败
	 */
	public void getUserInformation(String param) {	
		logger.info("我是带参数的getUserInformation方法，正在被执行，参数为：" + param);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put(paramName, param);
		//发送get请求
		//String strResult = HttpClientUtils.doGet(url, paramMap, true);
		String strResult = "{\"cause\": null,\"code\":0,\"obj\":[{\"name\":\"张亮\",\"userid\":\"zhangliang\",\"department\":[10]},{\"name\":\"蔡瀚廷\",\"userid\":\"caihanting\",\"department\": [2,22]}],\"msg\":\"成功！\"}";
		ResultEntity<List<UsersEntity>> result = GsonUtil.fromJsonArray(strResult, UsersEntity.class);
		List<UsersEntity> list = result.getObj();
		for(UsersEntity d : list ) {
			System.out.println(d.getUserid()+"----"+d.getName()+"----");
			List<Integer> inList = d.getDepartment();
			for(Integer i : inList) {
				System.out.println(i+"================");
			}
	}
		//数据库保存执行记录
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName(name);
		log.setParam(paramName + "=" + param);
		log.setCreateTime(new Date());
		log.setReason("需要使用" + name + "接口，用以获取数据");
		//同步方式，0-定时，-1手动 ???
		log.setWay(-1);
		if(result == null) {
			log.setResult(0);
			log.setResultDesc("获取接口返回失败,数据为空！");
		}else {			
			//状态码为0，获取成功
			if(result.getCode() == 0) {
				log.setResult(1);
				log.setResultDesc("code:" + result.getCode() + ";" + "获取成员信息成功！");
			}else {
				log.setResult(0);
				log.setResultDesc("code:" + result.getCode() + ";" + "获取成员信息失败！  "+result.getMsg());
			}
		}			
		log.setRemark("remark");
		syncPushLogService.save(log);
	}
}
