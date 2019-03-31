package com.sy.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.ManRole;
import com.sy.pojo.Management;
import com.sy.pojo.Role;
import com.sy.service.ManRoleService;
import com.sy.service.ManagementService;
import com.sy.service.RoleService;
import com.sy.utils.PageModel;
import com.sy.vo.ManRolelsvo;
import com.sy.vo.ManRolevo;

@Controller
@RequestMapping(value = "manrole", method = RequestMethod.POST)
public class ManRoleController {
	@Autowired
	private RoleService roleserice;
	@Autowired
	private ManagementService managementservice;
	@Autowired
	private ManRoleService manrleservice;

	@RequestMapping(value = "list")
	@ResponseBody
	public ModelAndView list(Integer pageNo, String keyword) {
		ModelAndView mo = new ModelAndView();
		PageModel<ManRolevo> pagemodel = manrleservice.getusersone(pageNo,
				keyword);
		mo.setViewName("manrole");
		mo.addObject("pageModel", pagemodel);
		return mo;
	}
	
	@RequestMapping("/selectgetmeunroles")
	@ResponseBody
	public ResultData<ManRolelsvo> selectgetmeunroles(){
		ResultData<ManRolelsvo> re = new ResultData<ManRolelsvo>();
		List<Management> ms = managementservice.selectmanagements();
		List<Role> rs = roleserice.getroless();
		ManRolelsvo me= new ManRolelsvo(null, ms,rs);
		re.setCode(200);
		re.setData(me);
		return re;
	}
	/**
	 * 添加数据
	 * 
	 */
	@RequestMapping("/addmenurole")
	@ResponseBody
	public ResultBase addmenurole(Integer roleid ,Integer  menuid, HttpServletResponse response){
		ResultBase re = new ResultBase();
		ManRole r = new ManRole();
		 r.setManId(roleid);
		 r.setRoleId(menuid);
		 r.setCratetime(new Date());
		boolean status =  manrleservice.addManRole(r);
		if(status){
			re.setCode(200);
			re.setMessage("添加成功！！！");
		}else {
			re.setCode(400);
			re.setMessage("添加失败！！！");
		}
		return re ;	
	}
	@RequestMapping("/deleterolemun")
	@ResponseBody
	public ResultBase deleterolemun(Integer id ){
		ResultBase re = new ResultBase();
		boolean status=manrleservice.deleteManRole(id);
		if(status){
			re.setCode(200);
			re.setMessage("删除成功！！！");
		}else {
			re.setCode(400);
			re.setMessage("删除失败！！！");
		}
		return re ;	
		
	}
}
