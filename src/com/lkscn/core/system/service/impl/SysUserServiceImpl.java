package com.lkscn.core.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lkscn.common.service.impl.BaseServiceImpl;
import com.lkscn.core.system.dao.ISysUserDao;
import com.lkscn.core.system.entity.SysUser;
import com.lkscn.core.system.service.ISysUserService;

@Transactional
@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements ISysUserService{

	@Resource
	private ISysUserDao userDao;
	
	@Override
	public SysUser login(String loginName, String password) {
		return userDao.login(loginName, password);
	}

}
