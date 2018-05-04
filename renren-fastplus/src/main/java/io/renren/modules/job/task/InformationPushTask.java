package io.renren.modules.job.task;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.renren.modules.job.utils.HttpClientUtils;
/**
 * 定时任务推送测试
 * @author DHB
 * @date 2018年5月4日 
 */
@Component("pushTask")
public class InformationPushTask {
	private Logger pushLogger = LoggerFactory.getLogger(getClass());
	public void getUserInformation(String params) {
		pushLogger.info("我是带参数的InformationPushTask方法，正在被执行，参数为：" + params);
		HttpClientUtils clientUtils = new HttpClientUtils();
		Map<String, String> para = new HashMap<String, String>();
		para.put("departmentId", "10");
		try {
			clientUtils.doGetPush("http://172.16.45.177/RoxQyWeb/qy-no/user/getUserByDepartmentId.g", para);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
