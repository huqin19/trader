package io.renren.modules.generator.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.DateUtils;
import io.renren.common.utils.GsonUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.ReadYml;
import io.renren.modules.generator.entity.WeixinEntity;
import io.renren.modules.generator.entity.ZqSheetsEntity;
import io.renren.modules.generator.service.WeixinService;
import io.renren.modules.job.entity.Content;
import io.renren.modules.job.entity.MessageEntity;
import io.renren.modules.job.task.SendMessageTask;

/**
 * 微信推送
 *
 * @author milk
 * @email
 * @date 2018-05-16 14:23:34
 */
@RestController
@RequestMapping("generator/weixin")
public class WeixinController {
	@Autowired
	private WeixinService weixinService;
	@Autowired
	private SendMessageTask msgTask;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("generator:weixin:list")
	public R list(@RequestParam Map<String, Object> params) {
		// Query query = new Query(new HashMap<String, Object>());
		//String JOB_SENDWX = ReadYml.getMl("JOB_SEND_WX");
		//查询成员
		List<WeixinEntity> weixinList = weixinService.queryList(new HashMap<String, Object>());
		//查询报表
		List<WeixinEntity> weixinSheetList = weixinService.querySheetList(new HashMap<String, Object>());
		return R.ok().put("weixinList", weixinList).put("weixinSheetList", weixinSheetList);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{weixinId}")
	@RequiresPermissions("generator:weixin:info")
	public R info(@PathVariable("weixinId") Long weixinId) {
		WeixinEntity weixin = null;
		// WeixinEntity weixin = weixinService.selectById(weixinId);

		return R.ok().put("weixin", weixin);
	}

	/**
	 * 推送消息
	 */
	@RequestMapping("/send")
	@RequiresPermissions("generator:weixin:send")
	public R sendMessage(@RequestBody WeixinEntity weixinEntity) {
		// weixinService.insert(weixin);
		StringBuilder touser = new StringBuilder();
		MessageEntity msg = new MessageEntity();
		String resultDesc = "";
		Map<String, String> sheetNameMap = new HashMap<String, String>();
		List<Content> content = new ArrayList<Content>();
		if (null != weixinEntity) {
			if (null != weixinEntity.getTitleArr() && weixinEntity.getTitleArr().length > 0) {
				for (String x : weixinEntity.getTitleArr()) {
					Content con = new Content();
//					String date = DateUtils.format(new Date());
//					String type = x.trim().equals("银行间每日债券借贷") ? "1" : (x.trim().equals("国债期货当日结算价") ? "2" : "3");
					//从日报表中获取日报名称和url
					String date = DateUtils.format( weixinEntity.getSheetDate(), DateUtils.DATE_PATTERN);
					String type = x;
					ZqSheetsEntity zqSheetsEntity = weixinService.queryZqSheetsObject(new BigDecimal(x));
					if(null != zqSheetsEntity) {
						sheetNameMap.put(x, zqSheetsEntity.getSheetName());
						con.setDescription(zqSheetsEntity.getSheetName());
						con.setTitle(zqSheetsEntity.getSheetName() + "[" + date + "]");
						con.setPicurl("http://"+ReadYml.getMl("WEIXIN_ADDRESS")+":"+ReadYml.getMl("WEIXIN_PORT")+
								"/renren-fastplus/img/0000"+ type+".jpg");
						con.setUrl("http://"+ReadYml.getMl("WEIXIN_ADDRESS")+":"+ReadYml.getMl("WEIXIN_PORT")
								+ zqSheetsEntity.getSheetUrl() + "?dt=" + date + "&stype=" + type);
						content.add(con);
					}
					
				}
			}
			if (null != weixinEntity.getNewtreeName() && weixinEntity.getNewtreeName().length > 0) {
				for (String y : weixinEntity.getNewtreeName()) {
					touser.append(y + "|");
				}
				touser.deleteCharAt(touser.length() - 1);
			}
			String contstr = GsonUtils.gsonString(content);
			msg.setTouser(touser.toString());
			msg.setToparty("");
			msg.setNo("b21aa97019ac42658af0f107bc5a379f");
			msg.setMsgtype("news");
			msg.setContent(contstr);
			msg.setSendType("0");
			msg.setSafe("0");
			String gsonString = GsonUtils.gsonString(msg);
			Map<String, Object> resultMap = msgTask.sendPushdMessage(gsonString, 0);
			Integer success = weixinEntity.getNewtreeName().length;
			if (resultMap.get("faildNum") != null ) {	
				success = (Integer) resultMap.get("faildNum") >=0 ? 
						success - (Integer) resultMap.get("faildNum") : 0;
				int num = 1;
				for (String x : weixinEntity.getTitleArr()) {
					resultDesc = resultDesc + num + "," + sheetNameMap.get(x) + ":" + "\n" + "成功：" + success + "条" + "\n"
							+ resultMap.get("resultStr") + "\n";
					num++;
				}

			}else {
				if(null != resultMap.get("resultStr")) {
					resultDesc = (String) resultMap.get("resultStr");
				}else {
					resultDesc = "无法访问服务器";
				}
			}
		}

		return R.ok().put("resultDesc", resultDesc);
	}
	
	/**
	 * 消息定时推送-提交
	 */
	@RequestMapping("/submitMessage")
	@RequiresPermissions("generator:weixin:submitMessage")
	public R submitMessage(@RequestBody WeixinEntity weixinEntity) {
		weixinService.submitMessage(weixinEntity);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("generator:weixin:update")
	public R update(@RequestBody WeixinEntity weixin) {
		// weixinService.updateById(weixin);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("generator:weixin:delete")
	public R delete(@RequestBody Long[] weixinIds) {
		// weixinService.deleteBatchIds(Arrays.asList(weixinIds));

		return R.ok();
	}

}
