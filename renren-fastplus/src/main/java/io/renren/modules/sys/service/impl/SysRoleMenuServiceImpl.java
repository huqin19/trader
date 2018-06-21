package io.renren.modules.sys.service.impl;

import io.renren.common.sequence.Sequence;
import io.renren.modules.sys.dao.SysRoleMenuDao;
import io.renren.modules.sys.entity.SysRoleDeptEntity;
import io.renren.modules.sys.entity.SysRoleMenuEntity;
import io.renren.modules.sys.service.SysRoleMenuService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * 角色与菜单对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:44:35
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	Sequence sequence = new Sequence(0, 0);

	@Override
	@Transactional
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		//先删除角色与菜单关系
		sysRoleMenuDao.delete(roleId);

		if(menuIdList.size() == 0){
			return ;
		}
		List<SysRoleMenuEntity> sysRoleMenuList = new ArrayList<SysRoleMenuEntity>();
		for(Long menuId : menuIdList) {
			SysRoleMenuEntity sysRoleMenu = new SysRoleMenuEntity();
			//生成主键
			Long xid = sequence.nextId();
			sysRoleMenu.setId(xid);
			sysRoleMenu.setRoleId(roleId);
			sysRoleMenu.setMenuId(menuId);
			sysRoleMenuList.add(sysRoleMenu);
		}
		sysRoleMenuDao.saveBatch(sysRoleMenuList);
	}

	@Override
	public List<Long> queryMenuIdList(Long roleId) {
		return sysRoleMenuDao.queryMenuIdList(roleId);
	}

}
