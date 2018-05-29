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
	public void test(String params){

		MessageEntity msg = new MessageEntity();
		Content con = new Content();
		List<String> content = new ArrayList<String>();
		con.setDescription("2015年5月14日，北京，天安门广场的华灯上悬挂起中印两国国旗，欢迎印度总理莫迪访华");
		con.setTitle("天安门广场悬挂起中印国旗 欢迎莫迪来访");
		con.setPicurl("http://101.231.210.71:30066/dfzq-wxitpm/images01/2.jpg");
		con.setUrl("http://101.231.210.71:30066/dfzq-wxitpm/wxitpm.do?xcase=vbondfutures&time=2017-09-18");	
		String contstr = GsonUtils.gsonString(con);
		content.add(contstr);
		msg.setTouser("xinzhi_test");
		msg.setToparty("");
		msg.setNo("b21aa97019ac42658af0f107bc5a379f");
		msg.setMsgtype("news");
		//msg.setContent(content);
		msg.setSendType("0");
		msg.setSafe("0");
		String gsonString = GsonUtils.gsonString(msg);
		System.out.println(gsonString+"=====");
		logger.info("我是带参数的test方法，正在被执行，参数为：" + params);
		String strResult = HttpClientUtils.doPost(url, params, true);
		System.out.println(strResult+"======================");
	}
	
	
	public void test2(){
		logger.info("我是不带参数的test2方法，正在被执行");
	}
}
