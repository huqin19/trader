package io.renren.modules.job.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.renren.common.utils.SpringContextUtils;
import io.renren.modules.job.entity.ResultEntity;
import io.renren.modules.job.entity.SyncPushLogEntity;
import io.renren.modules.job.entity.UserDepartmentEntity;
import io.renren.modules.job.entity.UsersEntity;
import io.renren.modules.job.service.SyncPushLogService;
import io.renren.modules.job.service.UserDepartmentService;
import io.renren.modules.job.service.UsersService;
import io.renren.common.utils.GsonUtils;
import io.renren.common.utils.HttpClientUtils;
/**
 * 定时任务接受用户
 * @author DHB
 * @date 2018年5月4日 
 */
import io.renren.common.utils.ReadYml;

@Component("getUserTask")
public class GetUserTask {
	// 接口名称
	@Value("${api.request-user.name}")
	private String name;
	// 请求URL
	@Value("${api.request-user.url}")
	private String url;
	// 参数名
	@Value("${api.request-user.param-name}")
	private String paramName;
	@Autowired
	private UsersService usersService;
	@Autowired
	private UserDepartmentService userDepartmentService;;
	private Logger logger = LoggerFactory.getLogger(getClass());
	// 获取spring bean
	@Autowired
	private SyncPushLogService syncPushLogService;

	/**
	 * 1.2接口返回
	 * 
	 * @param param
	 * 入参 将返回数据日志记录数据库 
	 * 1，如果返回结果为空，获取成员信息失
	 * 2，如果返回结果状态为1，获取成员信息成功，否则就失败
	 * 3，成功就讲数据存入user表和user_department表
	 */
	public void getUserInformation(String param, Integer way) {
		logger.info("我是带参数的getUserInformation方法，正在被执行，参数为：" + param);
		Map<String, String> paramMap = new HashMap<String, String>();
		Map<String, Object> map = new HashMap<String, Object>();
		paramMap.put(paramName, param);
		// 发送get请求
		String strResult = HttpClientUtils.doGet(url, paramMap, true);
		ResultEntity<List<UsersEntity>> result = GsonUtils.fromJsonArray(strResult, UsersEntity.class);
		// 数据库保存执行记录
		SyncPushLogEntity log = new SyncPushLogEntity();
		log.setUrl(url);
		log.setFunctionName(name);
		log.setCreateTime(new Date());
		log.setParam(paramName + "=" + param);
		log.setReason("需要使用" + name + "接口，用以获取数据");
		log.setWay(way);
		if (result == null) {
			log.setResult(0);
			log.setResultDesc("获取接口返回失败,数据为空！");
		} else {
			log.setCode(result.getCode().toString());
			// 状态码为0，获取成功
			if (result.getCode() == 0) {
				if (result.getObj() != null) {
					List<UsersEntity> uList = result.getObj();
					List<UserDepartmentEntity> udList = new ArrayList<UserDepartmentEntity>();
					for (UsersEntity user : uList) {
						List<Integer> septlist = user.getDepartment();
						Integer deptId = Collections.max(septlist);
						UserDepartmentEntity userDept = new UserDepartmentEntity();
						userDept.setDepId(deptId.toString());
						userDept.setUserId(user.getUserid());
						udList.add(userDept);
					}
					Integer before = usersService.queryTotal(map);
					if (before > 0) {
						if (udList != null && udList.size() > 0 && uList != null && uList.size() > 0
								&& udList.size() == uList.size()) {
							userDepartmentService.deleteStatus();
							usersService.deleteStatus();
							int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE").toString());
							List<UserDepartmentEntity> saveudList = new ArrayList<UserDepartmentEntity>();
							List<UsersEntity> saveuList = new ArrayList<UsersEntity>();
							for (int i = 0; i < uList.size(); i++) {
								if (i > 0 && i % loopSize == 0) {
									userDepartmentService.saveBatch(saveudList);
									usersService.saveBatch(saveuList);
									saveudList.clear();
									saveuList.clear();
									saveudList = new ArrayList<UserDepartmentEntity>();
									saveuList = new ArrayList<UsersEntity>();
								}
								saveudList.add(udList.get(i));
								saveuList.add(uList.get(i));
							}
							if (saveudList.size() > 0 && saveuList.size() > 0) {
								userDepartmentService.saveBatch(saveudList);
								usersService.saveBatch(saveuList);
							}
						}
						userDepartmentService.updateStatus();
						usersService.updateStatus();
					} else {
						if (udList != null && udList.size() > 0 && uList != null && uList.size() > 0
								&& udList.size() == uList.size()) {
							int loopSize = Integer.parseInt(ReadYml.getMl("LOOP_SIZE").toString());
							List<UserDepartmentEntity> saveudList = new ArrayList<UserDepartmentEntity>();
							List<UsersEntity> saveuList = new ArrayList<UsersEntity>();
							for (int i = 0; i < uList.size(); i++) {
								if (i > 0 && i % loopSize == 0) {
									userDepartmentService.saveBatch(saveudList);
									usersService.saveBatch(saveuList);
									saveudList.clear();
									saveuList.clear();
									saveudList = new ArrayList<UserDepartmentEntity>();
									saveuList = new ArrayList<UsersEntity>();
								}
								saveudList.add(udList.get(i));
								saveuList.add(uList.get(i));
							}
							if (saveudList.size() > 0 && saveuList.size() > 0) {
								userDepartmentService.saveBatch(saveudList);
								usersService.saveBatch(saveuList);
							}
						}
					}
					map.put("status", 1);
					Integer after = usersService.queryTotal(map);
					log.setResult(1);
					log.setResultDesc("成功获取" + after + "条成员信息！");
				} else {
					log.setResult(1);
					log.setResultDesc("没有成员信息返回！！");
				}
			} else {
				log.setResult(0);
				log.setResultDesc("获取成员信息失败！  " + result.getMsg());
			}
		}
		log.setRemark("remark");
		syncPushLogService.save(log);
	}
}
