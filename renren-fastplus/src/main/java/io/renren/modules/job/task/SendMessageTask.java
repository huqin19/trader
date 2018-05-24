package io.renren.modules.job.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.commons.lang.StringUtils;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.job.entity.InvalidEntity;
import io.renren.modules.job.entity.ResultEntity;
import io.renren.modules.job.entity.SyncPushLogEntity;
import io.renren.modules.job.service.SyncPushLogService;
import io.renren.common.utils.GsonUtils;
import io.renren.common.utils.HttpClientUtils;

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
	private Logger logger = LoggerFactory.getLogger(getClass());
	// 获取spring bean
	SyncPushLogService syncPushLogService = (SyncPushLogService) SpringContextUtils.getBean("syncPushLogService");

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
	public void sendPushdMessage(String param) {
		logger.info("我是带参数的senPushdMessage方法，正在被执行，参数为：" + param);
		String strResult = HttpClientUtils.doPost(url, param, true);
		// 测试
		//String strResult = "{\"cause\":null,\"obj\":{\"invaliduser\":\"fsdfdfgf|gdfhgfer\",\"invalidparty\":null,\"invalidtag\":null},\"code\":0,\"msg\":\"成功！\"}";
		//System.out.println(strResult + "---------------------");
		// Json解析成实体类
		ResultEntity<InvalidEntity> result = GsonUtils.fromJsonObject(strResult, InvalidEntity.class);
		// 数据库保存执行记录
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName(name);
		log.setParam(param);
		log.setReason("需要使用" + name + "的接口，推送新闻");
		// 同步方式，0-定时，-1手动 ???
		log.setWay(-1);
		if (result == null) {
			log.setResult(-1);
			log.setResultDesc("推送消息失败,接收数据为空！");
		} else {
			log.setCode(result.getCode().toString());
			// 返回的obj不为空
			if (result.getObj() != null && result.getObj() instanceof InvalidEntity) {
				InvalidEntity inv = (InvalidEntity) result.getObj();
				if (StringUtils.isNotBlank(inv.getInvaliduser()) || StringUtils.isNotBlank(inv.getInvalidparty())) {
					String m = "成功！".equals(result.getMsg()) ? "" : result.getMsg();
					String udf =inv.getInvaliduser() + "成员接收消息失败!" +";"+ inv.getInvalidparty() + "部门接收消息失败! " + m;
					String uf = inv.getInvaliduser() + "成员接收消息失败!  " + m;
					String df = inv.getInvalidparty() + "部门接收消息失败!  " + m;
					log.setResult(0);
					log.setResultDesc(StringUtils.isNotBlank(inv.getInvaliduser()) ? uf
							: (StringUtils.isNotBlank(inv.getInvalidparty()) ? df : udf));
				} else if( result.getCode() == 0) {
					log.setResult(1);
					log.setResultDesc("所有成员和部门接收消息成功！");
				}
			} else {
				log.setResult(0);
				log.setResultDesc("code:" + result.getCode() + "失败！" + result.getMsg());
			}
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
	}
}
