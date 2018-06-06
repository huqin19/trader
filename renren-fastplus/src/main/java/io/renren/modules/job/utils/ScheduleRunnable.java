package io.renren.modules.job.utils;

import io.renren.common.exception.RRException;
import io.renren.common.utils.SpringContextUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 执行定时任务
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月30日 下午12:49:33
 */
public class ScheduleRunnable implements Runnable {
	private Object target;
	private Method method;
	private String params;
	private Long jobId;
	
	public ScheduleRunnable(String beanName, String methodName, String params, Long jobId) throws NoSuchMethodException, SecurityException {
		this.target = SpringContextUtils.getBean(beanName);
		this.params = params;
		this.jobId = jobId;
		
		if(StringUtils.isNotBlank(params)){
			this.method = target.getClass().getDeclaredMethod(methodName, String.class, Long.class);
		}else{
			this.method = target.getClass().getDeclaredMethod(methodName, Long.class);
		}
	}

	@Override
	public void run() {
		try {
			ReflectionUtils.makeAccessible(method);
			if(StringUtils.isNotBlank(params)){
				method.invoke(target, params, jobId);
			}else{
				method.invoke(target, jobId);
			}
		}catch (Exception e) {
			throw new RRException("执行定时任务失败", e);
		}
	}

}
