package com.sy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sy.pojo.Management;
import com.sy.service.ManagementService;
import com.sy.utils.Managementconstant;
import com.sy.vo.Managementvo;

@Controller
@RequestMapping(value = "index")
public class IndexController {
	@Autowired
	private ManagementService managementservice;
	@RequestMapping(value="main")
	public ModelAndView Authority(HttpSession session){
		ModelAndView mo = new ModelAndView();
		 Management m =  (Management) session.getAttribute(Managementconstant.user);
		 Managementvo mv = managementservice.Authority(m.getId());
		 mv.setM(m);
		 if(mv !=null){
			 mo.addObject("mv", mv);
			 mo.setViewName("index");
		 }
		 return mo;
	}
}
