package com.lkscn.core.system.dao;

import com.lkscn.common.dao.IBaseDao;
import com.lkscn.core.system.entity.SysUser;

public interface ISysUserDao extends IBaseDao<SysUser> {
	
	SysUser login(String loginName,String password);
}
