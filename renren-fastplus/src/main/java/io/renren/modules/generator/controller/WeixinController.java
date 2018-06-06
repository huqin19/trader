package io.renren.modules.generator.controller;

import java.util.ArrayList;
import java.util.Date;
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
import io.renren.modules.generator.entity.WeixinEntity;
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
		List<WeixinEntity> weixinList = weixinService.queryList(new HashMap<String, Object>());

		return R.ok().put("weixinList", weixinList);
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
		List<Content> content = new ArrayList<Content>();
		if (null != weixinEntity) {
			if (null != weixinEntity.getTitleArr() && weixinEntity.getTitleArr().length > 0) {
				for (String x : weixinEntity.getTitleArr()) {
					Content con = new Content();
					String date = DateUtils.format(new Date());
					String type = x.trim().equals("银行间每日债券借贷") ? "1" : (x.trim().equals("国债期货当日结算价") ? "2" : "3");
					con.setDescription(x);
					con.setTitle(x + "[" + DateUtils.format(date, DateUtils.DATE_PATTERN) + "]");
					con.setPicurl("https://img.alicdn.com/tfs/TB1e58ksHSYBuNjSspiXXXNzpXa-290-130.gif");
					con.setUrl("http://localhost:8080/renren-fastplus/modules/generator/weixin.html" + "?dt=" + date
							+ "&stype=" + type);
					content.add(con);
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
			Map<String, Object> resultMap = msgTask.sendPushdMessage(gsonString);
			Integer success = weixinEntity.getNewtreeName().length;
			if (resultMap.get("faildNum") != null ) {	
				success = (Integer) resultMap.get("faildNum") >=0 ? 
						success - (Integer) resultMap.get("faildNum") : 0;
				int num = 1;
				for (String x : weixinEntity.getTitleArr()) {
					resultDesc = resultDesc + num + "," + x + ":" + "\n" + "成功：" + success + "条" + "\n"
							+ resultMap.get("resultStr") + "\n";
					num++;
				}

			}
		}

		return R.ok().put("resultDesc", resultDesc);
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
