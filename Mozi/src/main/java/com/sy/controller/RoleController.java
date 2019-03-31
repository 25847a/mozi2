package com.sy.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.Role;
import com.sy.service.RoleService;
import com.sy.utils.PageModel;

@Controller
@RequestMapping("role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	/**
	 * 获取第一层目录数据
	 */
	@RequestMapping("getroles")
	@ResponseBody
	public ModelAndView getroles(Integer pageNo, String keyword) {
		ModelAndView mo = new ModelAndView();
		PageModel<Role> pageModel = roleService.getroles(pageNo, keyword);
		mo.addObject("pageModel", pageModel);
		mo.addObject("keyword", keyword);
		mo.setViewName("role");
		return mo;
	}

	@RequestMapping("addrole")
	@ResponseBody
	public void addrole(String rolename, String roledescription,
			HttpServletResponse response, HttpServletRequest request) {
		Role role = new Role();
		role.setDescn(roledescription);
		role.setRoleName(rolename);
		role.setCreatetime(new Date());
		roleService.addrole(role);
	}

	@RequestMapping("deleterole")
	@ResponseBody
	public ResultBase deleterole(Integer roleid, HttpServletResponse response) {
		ResultBase re = new ResultBase();
		boolean status = roleService.deleterole(roleid);
		if (status) {
			re.setCode(200);
			re.setMessage("删除成功！！！");
		} else {
			re.setCode(400);
			re.setMessage("删除失败！！！");
		}
		return re;
	}

	@RequestMapping("getroleId")
	@ResponseBody
	public ResultData<Role> getroleId(Integer roleid, HttpServletResponse response) {
		ResultData<Role> re = new ResultData<Role>();
		Role role = roleService.selectid(roleid);
		if(role !=null){
			re.setCode(200);
			re.setData(role);
			re.setMessage("获取角色成功！！！");
		}else {
			re.setCode(400);
			re.setMessage("获取角色失败！！！");
		}
		return re;
	}

	@RequestMapping("editarole")
	@ResponseBody
	public ResultBase editarole(String rolename, String roledescription,
			Integer roleid, HttpServletResponse response) {
		ResultBase re = new ResultBase();
		Role role = new Role();
		role.setDescn(roledescription);
		role.setId(roleid);
		role.setCreatetime(new Date());
		role.setRoleName(rolename);
		boolean status  = roleService.modify(role);
		if (status ) {
			re.setCode(200);
			re.setMessage("修改成功！！！");
		} else {
			re.setCode(200);
			re.setMessage("修改成功！！！");
		}
		return re;
	}

}
