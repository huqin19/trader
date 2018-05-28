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

import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.job.entity.DepartmentEntity;
import io.renren.modules.job.entity.ResultEntity;
import io.renren.modules.job.entity.SyncPushLogEntity;
import io.renren.modules.job.service.DepartmentService;
import io.renren.modules.job.service.SyncPushLogService;
import io.renren.common.utils.GsonUtils;
import io.renren.common.utils.HttpClientUtils;

/**
 * 定时任务接收部门信息
 * @author DHB
 * @date 2018年5月10日 
 */
@Component("getDepartment")
public class GetDepartmentTask {
	//接口名称
	@Value("${api.request-department.name}")
	private String name;
	//请求URL
	@Value("${api.request-department.url}")
	private String url;
	//参数名
	@Value("${api.request-department.param-name}")
	private String paramName;
	@Autowired
	private DepartmentService departmentService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	//获取spring bean
	SyncPushLogService syncPushLogService = (SyncPushLogService) SpringContextUtils.getBean("syncPushLogService");
	/**
	 * 1.1接口返回
	 * @param param 入参
	 * 将返回数据日志记录数据库
	 * 1，如果返回结果为空，获取部门信息失败
	 * 2，如果返回结果状态为1，获取部门信息成功，否则就失败
	 * 3,成功就讲数据存入department表
	 */
	public void getDepartmentInformation(String param) {
		logger.info("我是带参数的getDepartmentInformation方法，正在被执行，参数为：" + param);
		Map<String, String> paramMap = new HashMap<String, String>();			
			paramMap.put(paramName, param);
			//发送get请求
			//String strResult = HttpClientUtils.doGet(url, paramMap, true);
			String strResult = "";
			//test
			System.out.println(strResult);
			ResultEntity<List<DepartmentEntity>> result = GsonUtils.fromJsonArray(strResult, DepartmentEntity.class);
			//数据库保存执行记录
			SyncPushLogEntity log = new SyncPushLogEntity();
			log.setUrl(url);
			log.setFunctionName(name);
			log.setParam(paramName + "=" + param);
			log.setCreateTime(new Date());
			log.setReason("需要使用" + name + "接口，用以获取数据");
			//同步方式，0-定时，-1手动 ???
			log.setWay(-1);
			
			//如果获取数据为空就将返回结果状态置为-1
			if(result == null) {
				log.setResult(0);
				log.setResultDesc("获取接口返回失败,数据为空！");
			}else {	
				log.setCode(result.getCode().toString());
				//状态码为0，获取成功
				if(result.getCode() == 0) {
					if(result.getObj() != null) {
						List<DepartmentEntity> list = result.getObj();
						for(DepartmentEntity dept : list) {
							dept.setStatus(1);
						}
						departmentService.deleteThenSave(list);
						log.setResult(1);
						log.setResultDesc("获取部门信息成功！");
					}else {
						log.setResult(0);
						log.setResultDesc("没有部门信息返回！");
					}					
				}else {
					log.setResult(0);
					log.setResultDesc("获取部门信息失败！  " + result.getMsg());
				}
			}			
			log.setRemark("remark");
			syncPushLogService.save(log);
	}
}