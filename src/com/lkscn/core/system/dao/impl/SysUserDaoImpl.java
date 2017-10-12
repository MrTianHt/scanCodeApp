package com.lkscn.core.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.lkscn.common.dao.impl.BaseDaoImpl;
import com.lkscn.core.system.dao.ISysUserDao;
import com.lkscn.core.system.entity.SysUser;

@Repository("sysUserDao")
public class SysUserDaoImpl extends BaseDaoImpl<SysUser> implements ISysUserDao {

	@Override
	public SysUser login(String loginName, String password) {
		String hql = "FROM SysUser WHERE loginName=:loginName AND userPassword=:userPassword";
		SysUser user = (SysUser) getSession().createQuery(hql).setParameter("loginName", loginName).setParameter("userPassword", password).uniqueResult();
		return user;
	}

}
