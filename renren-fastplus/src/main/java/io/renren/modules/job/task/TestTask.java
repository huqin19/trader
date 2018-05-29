package io.renren.modules.job.task;

import io.renren.common.utils.GsonUtils;
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
	
	
	public void test(String params){
		logger.info("我是带参数的test方法，正在被执行，参数为：" + params);
		MessageEntity msg = new MessageEntity();
		Content con = new Content();
		List<String> content = new ArrayList<String>();
		con.setDescription("2015年5月14日，北京，天安门广场的华灯上悬挂起中印两国国旗，欢迎印度总理莫迪访华");
		con.setTitle("天安门广场悬挂起中印国旗 欢迎莫迪来访");
		con.setPicurl("https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/super/whfpf%3D425%2C260%2C50/sign=c0c2b552881001e94e69474fde334fde/908fa0ec08fa513d45999065386d55fbb2fbd95e.jpg");
		con.setUrl("http://pic.chinadaily.com.cn/2015-05/15/content_20724037.htm?source=bdxsy");	
		String contstr = GsonUtils.gsonString(con);
		content.add(contstr);
		msg.setTouser("zhangliang|caihanting");
		msg.setToparty("1|2|22");
		msg.setNo("1254sdf5sd6dfd");
		msg.setMsgtype("news");
		msg.setContent(content);
		msg.setSendType("0");
		msg.setSafe("0");
		String gsonString = GsonUtils.gsonString(msg);
		System.out.println(gsonString+"=====");
	}
	
	
	public void test2(){
		logger.info("我是不带参数的test2方法，正在被执行");
	}
}
