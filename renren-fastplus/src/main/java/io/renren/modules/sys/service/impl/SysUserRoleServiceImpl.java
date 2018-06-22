package io.renren.modules.sys.service.impl;

import io.renren.common.sequence.Sequence;
import io.renren.modules.sys.dao.SysUserRoleDao;
import io.renren.modules.sys.entity.SysRoleMenuEntity;
import io.renren.modules.sys.entity.SysUserRoleEntity;
import io.renren.modules.sys.service.SysUserRoleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 用户与角色对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:45:48
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	Sequence sequence = new Sequence(0, 0);

	@Override
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
		if(null == roleIdList || roleIdList.size() == 0){
			return ;
		}
		
		//先删除用户与角色关系
		sysUserRoleDao.delete(userId);
		List<SysUserRoleEntity> sysUserRoleList = new ArrayList<SysUserRoleEntity>();
		for(Long roleId : roleIdList) {
			SysUserRoleEntity sysUserRole = new SysUserRoleEntity();
			//生成主键
			Long xid = sequence.nextId();
			sysUserRole.setId(xid);
			sysUserRole.setUserId(userId);
			sysUserRole.setRoleId(roleId);		
			sysUserRoleList.add(sysUserRole);
		}
		sysUserRoleDao.saveBatch(sysUserRoleList);
	}

	@Override
	public List<Long> queryRoleIdList(Long userId) {
		return sysUserRoleDao.queryRoleIdList(userId);
	}

	@Override
	public void delete(Long userId) {
		sysUserRoleDao.delete(userId);
	}
}
