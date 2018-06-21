package io.renren.modules.sys.service.impl;

import io.renren.common.sequence.Sequence;
import io.renren.modules.sys.dao.SysRoleDeptDao;
import io.renren.modules.sys.entity.SysRoleDeptEntity;
import io.renren.modules.sys.service.SysRoleDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 角色与部门对应关系
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017年6月21日 23:42:30
 */
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl implements SysRoleDeptService {
	@Autowired
	private SysRoleDeptDao sysRoleDeptDao;
	
	Sequence sequence = new Sequence(0, 0);

	@Override
	@Transactional
	public void saveOrUpdate(Long roleId, List<Long> deptIdList) {
		//先删除角色与菜单关系
		sysRoleDeptDao.delete(roleId);

		if(deptIdList.size() == 0){
			return ;
		}
		List<SysRoleDeptEntity> sysRoleDeptList = new ArrayList<SysRoleDeptEntity>();
		for(Long deptId : deptIdList) {
			SysRoleDeptEntity sysRoleDept = new SysRoleDeptEntity();
			//生成主键
			Long xid = sequence.nextId();
			sysRoleDept.setId(xid);
			sysRoleDept.setRoleId(roleId);
			sysRoleDept.setDeptId(deptId);
			sysRoleDeptList.add(sysRoleDept);
		}
		sysRoleDeptDao.saveBatch(sysRoleDeptList);
	}

	@Override
	public List<Long> queryDeptIdList(Long roleId) {
		return sysRoleDeptDao.queryDeptIdList(roleId);
	}

}
