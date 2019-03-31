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
import com.sy.pojo.Auth;
import com.sy.pojo.Role;
import com.sy.pojo.RoleAuth;
import com.sy.service.AuthService;
import com.sy.service.RoleAnthService;
import com.sy.service.RoleService;
import com.sy.utils.PageModel;
import com.sy.vo.RoleAuthvo;
import com.sy.vo.RoleMenuvo;

@Controller
@RequestMapping(value = "roleAnth", method = RequestMethod.POST)
public class RoleAnthControllr {
	@Autowired
	private RoleAnthService roleanthservice;
	@Autowired
	private AuthService authserice;
	@Autowired
	private RoleService roleserice;
	
	@RequestMapping(value = "list")
	@ResponseBody
	public ModelAndView list(Integer pageNo, String keyword) {
		ModelAndView mo = new ModelAndView();
		PageModel<RoleMenuvo> pagemodel = roleanthservice.getusersone(pageNo,
				keyword);
		mo.setViewName("roleAuth");
		mo.addObject("pageModel", pagemodel);
		return mo;
	}
	
	@RequestMapping("/deleterolemun")
	@ResponseBody
	public ResultBase deleterolemun(Integer id ){
		ResultBase re = new ResultBase();
		boolean status=roleanthservice.deleteroleanth(id);
		if(status){
			re.setCode(200);
			re.setMessage("删除成功！！！");
		}else {
			re.setCode(400);
			re.setMessage("删除失败！！！");
		}
		return re ;	
		
	}
	@RequestMapping("/selectgetmeunroles")
	@ResponseBody
	public ResultData<RoleAuthvo> selectgetmeunroles(){
		ResultData<RoleAuthvo> re = new ResultData<RoleAuthvo>();
		List<Auth> ms = authserice.getauth();
		List<Role> rs = roleserice.getroless();
		RoleAuthvo me= new RoleAuthvo(ms,rs);
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
		 RoleAuth r = new RoleAuth();
		 r.setAuthId(menuid);
		 r.setRoleId(roleid);
		 r.setCratetime(new Date());
		boolean status =  roleanthservice.addRoleAnth(r);
		if(status){
			re.setCode(200);
			re.setMessage("添加成功！！！");
		}else {
			re.setCode(400);
			re.setMessage("添加失败！！！");
		}
		return re ;	
	}
		
}
