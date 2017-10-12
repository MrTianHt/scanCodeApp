package com.lkscn.core.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lkscn.common.utils.StringUtils;
import com.lkscn.core.MD5.MD5Utils;
import com.lkscn.core.system.entity.SysUser;
import com.lkscn.core.system.service.ISysUserService;


@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Resource
	private ISysUserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response){
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		if(!StringUtils.isEmpty(password))
			password = MD5Utils.MD5Encode(password);
		
		SysUser sysUser = userService.login(loginName, password);
		if(sysUser != null)
			return "/login/success";
		else
			return "/login/error";
	}
}
