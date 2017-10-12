package com.lkscn.core.system.service;

import com.lkscn.common.service.IBaseService;
import com.lkscn.core.system.entity.SysUser;

public interface ISysUserService extends IBaseService<SysUser>{
	
	SysUser login(String loginName,String password);
}
