package com.sy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.common.ResultBase;
import com.sy.pojo.Management;
import com.sy.service.ManagementService;
import com.sy.utils.Managementconstant;

@Controller
@RequestMapping(value = "login", method = RequestMethod.POST)
public class LoginController {
	@Autowired
	private ManagementService managementservice;

	@RequestMapping("login")
	@ResponseBody
	public ResultBase addManagement(String userName, String password, HttpSession session) {
		ResultBase re = new ResultBase();
		Management m = managementservice.loginmanagement(userName, password);

		if (m != null) {
			session.setAttribute(Managementconstant.user, m);
			re.setCode(200);
			re.setMessage("登陆成功！！！");
		} else {
			re.setCode(400);
			// re.setMessage("用户获者密码错误！！！");
			re.setMessage("帐号或者密码错误！！！");
		}

		return re;
	}
}
