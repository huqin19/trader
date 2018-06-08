package io.renren.modules.job.utils;

import io.renren.common.exception.RRException;
import io.renren.common.utils.ReadYml;
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
	private Integer way;
	public ScheduleRunnable(String beanName, String methodName, String params, Long jobId, Integer way) throws NoSuchMethodException, SecurityException {
		this.target = SpringContextUtils.getBean(beanName);
		this.params = params;
		this.jobId = jobId;
		this.way = way;
		if(StringUtils.isNotBlank(params)){
			if(params.equals(ReadYml.getMl("JOB_SEND_WX")) || params.equals(ReadYml.getMl("JOB_SEND_WX_TRADE_DAY"))) {
				this.method = target.getClass().getDeclaredMethod(methodName, String.class, Long.class, Integer.class);
			}else {
				this.method = target.getClass().getDeclaredMethod(methodName, String.class,Integer.class);
			}
		}else{
			this.method = target.getClass().getDeclaredMethod(methodName);
		}
	}

	@Override
	public void run() {
		try {
			ReflectionUtils.makeAccessible(method);
			if(StringUtils.isNotBlank(params)){
				if(params.equals(ReadYml.getMl("JOB_SEND_WX")) || params.equals(ReadYml.getMl("JOB_SEND_WX_TRADE_DAY"))) {
					method.invoke(target, params, jobId, way);
				}else {
					method.invoke(target, params, way);
				}
			}else{
				method.invoke(target, jobId);
			}
		}catch (Exception e) {
			throw new RRException("执行定时任务失败", e);
		}
	}

}
