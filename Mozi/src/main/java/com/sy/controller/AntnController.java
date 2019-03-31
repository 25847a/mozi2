package com.sy.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.Auth;
import com.sy.service.AuthService;
import com.sy.utils.PageModel;

/**
 * 页面-权限管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "antn", method = RequestMethod.POST)
public class AntnController {
	@Autowired
	private AuthService authservice;

	@RequestMapping(value = "list")
	public ModelAndView list(Integer pageNo, String keyword) {
		ModelAndView mo = new ModelAndView();
		PageModel<Auth> pagemodel = authservice.getusersone(pageNo, keyword);
		mo.setViewName("Auth");
		mo.addObject("pageModel", pagemodel);
		return mo;
	}

	/**
	 * 菜单进行修改
	 * 
	 * @param id
	 */
	@RequestMapping("/modifymenu")
	@ResponseBody
	public ResultBase modifymenu(Integer id, String meun_name, String url,
			String description) {
		ResultBase re = new ResultBase();
		Auth a = new Auth();
		a.setAuthName(meun_name);
		a.setCreatetime(new Date());
		a.setDescn(description);
		a.setId(id);
		a.setUrl(url);
		a.setCreatetime(new Date());
		boolean status = authservice.modifymenu(a);
		if (status) {
			re.setCode(200);
			re.setMessage("修改成功！！！");
		} else {
			re.setCode(400);
			re.setMessage("修改成功！！！");
		}
		return re;
	}

	/**
	 * 菜单进行删除
	 * 
	 * @param id
	 */
	@RequestMapping("/deletemenu")
	@ResponseBody
	public ResultBase deletememu(Integer id) {
		ResultBase re = new ResultBase();
		boolean status = authservice.deletememu(id);
		if (status ) {
			re.setCode(200);
			re.setMessage("删除成功！！！");
		} else {
			re.setCode(400);
			re.setMessage("删除失败！！！");
		}
		return re;
	}

	/**
	 * 根据id查找对应的数据
	 * 
	 * @param id
	 */
	@RequestMapping("/findid")
	@ResponseBody
	public ResultData<Auth> findid(Integer id) {
		ResultData<Auth> re = new ResultData<Auth>();
		Auth a = authservice.findid(id);
		if (a != null) {
			re.setCode(200);
			re.setMessage("获取成功！！！");
			re.setData(a);
		} else {
			re.setCode(400);
			re.setMessage("获取失败！！！");
		}
		return re;

	}

	/**
	 * 添加菜单数据
	 * 
	 */
	@RequestMapping("/addmenucz")
	@ResponseBody
	public ResultBase addmenucz(String url, String meun_name, String description) {
		ResultBase re = new ResultBase();
		Auth a = new Auth();
		a.setAuthName(meun_name);
		a.setCreatetime(new Date());
		a.setUrl(url);
		a.setDescn(description);
		boolean status = authservice.addmenucz(a);
		if (status ) {
			re.setCode(200);
			re.setMessage("添加成功！！！");
		} else {
			re.setCode(400);
			re.setMessage("添加失败！！！");
		}
		return re;
	}
}
