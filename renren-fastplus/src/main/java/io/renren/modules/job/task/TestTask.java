package io.renren.modules.job.task;

import io.renren.common.utils.GsonUtils;
import io.renren.common.utils.HttpClientUtils;
import io.renren.modules.job.entity.Content;
import io.renren.modules.job.entity.MessageEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

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
	@Value("${api.send-message.url}")
	private String url;
	@Value("${api.send-message.param-name}")
	private String paramName;
	public void test(String params, Long jobid){
		System.out.println(params + "=============" + jobid+"----------------------");
		System.out.println();
	}
	
	
	public void test2(){
		logger.info("我是不带参数的test2方法，正在被执行");
	}
}
