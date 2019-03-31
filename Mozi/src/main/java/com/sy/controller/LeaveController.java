package com.sy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sy.common.ResultBase;
import com.sy.pojo.Userleave;
import com.sy.service.LeaveService;
import com.sy.utils.PageModel;

@Controller
@RequestMapping(value = "leave", method = RequestMethod.POST)
public class LeaveController {
	@Autowired
	private LeaveService leaveservice;
	@RequestMapping(value = "addLeave")
	@ResponseBody
	public ResultBase addLeave( @RequestBody Userleave l){
		ResultBase re = new ResultBase();
		boolean status = leaveservice.addLeave(l);
		if(status){
			re.setCode(200);
			re.setMessage("添加留言成功！！！");
		}else{
			re.setCode(400);
			re.setMessage("添加留言失败！！！");
		}
		return re;
	}
	@RequestMapping(value = "list")
	public ModelAndView list(Integer pageNo, String keyword) {
		ModelAndView mo = new ModelAndView();
		PageModel<Userleave> pagemodel = leaveservice.getusersone(pageNo,
				keyword);
		mo.setViewName("leave");
		mo.addObject("pageModel", pagemodel);
		return mo;
	}
	@RequestMapping(value = "deleteLeave")
	public ResultBase deleteLeave(Integer id){
		ResultBase re = new ResultBase();
		boolean status= leaveservice.deleteLeave(id);
		if(status){
			re.setCode(200);
			re.setMessage("删除留言成功！！！");
		}else{
			re.setCode(400);
			re.setMessage("删除留言失败！！！");
		}
		return re ;
	}
	
}
