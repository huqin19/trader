package io.renren.modules.job.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.renren.common.utils.DateUtils;
import io.renren.common.utils.GsonUtils;
import io.renren.common.utils.HttpClientUtils;
import io.renren.common.utils.ReadYml;
import io.renren.modules.generator.entity.ZqJobAttachEntity;
import io.renren.modules.generator.entity.ZqSheetsEntity;
import io.renren.modules.generator.service.WeixinService;
import io.renren.modules.job.entity.Content;
import io.renren.modules.job.entity.InvalidEntity;
import io.renren.modules.job.entity.MessageEntity;
import io.renren.modules.job.entity.ResultEntity;
import io.renren.modules.job.entity.SyncPushLogEntity;
import io.renren.modules.job.service.SyncPushLogService;
import io.renren.modules.job.service.UsersService;

/**
 * 定时任务推送消息 sendMessage为spring bean的名称
 * 
 * @author DHB
 * @date 2018年5月10日
 */
@Component("sendMessage")
public class SendMessageTask {
	// 接口名称
	@Value("${api.send-message.name}")
	private String name;
	// 请求URL
	@Value("${api.send-message.url}")
	private String url;
	// 参数名
	@Value("${api.send-message.param-name}")
	private String paramName;
	@Autowired
	private UsersService usersService;
	@Autowired
	private WeixinService weixinService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SyncPushLogService syncPushLogService;
	/**
	 * 1.3接口返回
	 * 
	 * @param param 入参
	 * 将返回数据日志记录数据库 
	 * 1，如果返回结果为空，获取返回信息失败
	 * 2，返回结果不为空，返回的obj不为空时
	 * 2.1,invaliduser和invalidparty都不为空，有成员和有部门没有获取消息失败
	 * 2.2,invaliduser为空invalidparty不为空，有成员获取消息失败
	 * 2.3,invaliduser不为空invalidparty为空，有部门获取消息失败
	 * 2.3,invaliduser和invalidparty都为空，获取消息都成功 3，返回结果不为空，返回的obj为空时，获取消息失败
	 */
	public Map<String, Object>  sendPushdMessage(String param , Integer way) {
		logger.info("我是带参数的senPushdMessage方法，正在被执行，参数为：" + param);
		String strResult = HttpClientUtils.doPost(url, param, true);
		// 测试
		Map<String, Object> resultMap = new HashMap<String, Object>();
		StringBuilder back = new StringBuilder();
		//String strResult = "{\"cause\":null,\"obj\":{\"invaliduser\":\"xinzhi_test\",\"invalidparty\":\"276\",\"invalidtag\":null},\"code\":0,\"msg\":\"成功！\"}";
		// Json解析成实体类
		ResultEntity<InvalidEntity> result = GsonUtils.fromJsonObject(strResult, InvalidEntity.class);
		// 数据库保存执行记录
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName(name);
		log.setCreateTime(new Date());
		log.setParam(param);
		log.setReason("需要使用" + name + "的接口，推送新闻");
		log.setWay(way);
		if (result == null) {
			log.setResult(-1);
			log.setResultDesc("消息推送失败,接收数据为空！");
			back.append("消息推送失败,接收数据为空！");
		} else {
			log.setCode(result.getCode().toString());
			// 返回的obj不为空
			if (result.getObj() != null && result.getObj() instanceof InvalidEntity) {
				InvalidEntity inv = (InvalidEntity) result.getObj();
				if (StringUtils.isNotBlank(inv.getInvaliduser()) || StringUtils.isNotBlank(inv.getInvalidparty())) {
					String m = "成功！".equals(result.getMsg()) ? "" : result.getMsg();
					String  users[] = inv.getInvaliduser().split("\\|");
					back.append("失败："+users.length+"条，成员：");
					for(String user : users) {
						back.append(usersService.queryObject(user).getName()+",");
					}
					back.deleteCharAt(back.length()-1);
					back.append("接收消息失败!");
					resultMap.put("faildNum", users.length);
					String udf =inv.getInvaliduser() + "成员接收消息失败!" +";"+ inv.getInvalidparty() + "部门接收消息失败! " + m;
					String uf = inv.getInvaliduser() + "成员接收消息失败!  " + m;
					String df = inv.getInvalidparty() + "部门接收消息失败!  " + m;
					log.setResult(0);
					log.setResultDesc(StringUtils.isNotBlank(inv.getInvaliduser()) 
							&& StringUtils.isBlank(inv.getInvalidparty()) ? uf
							: (StringUtils.isNotBlank(inv.getInvalidparty()) 
							&& StringUtils.isBlank(inv.getInvaliduser()) ? df : udf));
				} else if( result.getCode() == 0) {
					log.setResult(1);
					log.setResultDesc("所有成员或部门推送成功！");
					back.append("所有成员推送成功！");
					resultMap.put("faildNum", 0);
				}
			} else {
				log.setResult(0);
				log.setResultDesc("错误信息编号:"+result.getCode() + ";" + result.getMsg());
				back.append("错误信息编号:"+result.getCode() + ";" + result.getMsg());
				resultMap.put("faildNum", -1);
			}
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
		
		resultMap.put("resultStr", back.toString());
		return resultMap;
	}
	public void saveThenPush(String param,Long jobid, Integer way) {
		MessageEntity msg = new MessageEntity();
		List<Content> content = new ArrayList<Content>();
		ZqJobAttachEntity jobAttach = weixinService.queryObject(jobid);
		if(jobAttach != null) {
			String date = DateUtils.format( new Date(), DateUtils.DATE_PATTERN);
			String sheet = jobAttach.getSheetId().substring(1);
			String touser = jobAttach.getUserId().substring(1);
			String sheetid[] = sheet.split("\\|");
			if(sheetid != null && sheetid.length > 0) {
				for(String id:  sheetid) {
					Content con = new Content();
					ZqSheetsEntity zqSheetsEntity = weixinService.queryZqSheetsObject(new BigDecimal(id));
					if(null != zqSheetsEntity) {
						con.setDescription(zqSheetsEntity.getSheetName());
						con.setTitle(zqSheetsEntity.getSheetName() + "[" + date + "]");
						con.setPicurl("http://"+ReadYml.getMl("WEIXIN_ADDRESS")+":"+ReadYml.getMl("WEIXIN_PORT")+
								"/renren-fastplus/img/sheet00"+ id+".jpg");
						con.setUrl("http://"+ReadYml.getMl("WEIXIN_ADDRESS")+":"+ReadYml.getMl("WEIXIN_PORT")
								+ zqSheetsEntity.getSheetUrl() + "?dt=" + date + "&stype=" + id);
						content.add(con);
					}
				}
				String contstr = GsonUtils.gsonString(content);
				msg.setTouser(touser.toString());
				msg.setToparty("");
				msg.setNo("b21aa97019ac42658af0f107bc5a379f");
				msg.setMsgtype("news");
				msg.setContent(contstr);
				msg.setSendType("0");
				msg.setSafe("0");				
			}
		}
		String msgString = GsonUtils.gsonString(msg);
		sendPushdMessage(msgString , way);
	}
}
