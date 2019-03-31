package com.sy.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class Exceptionmozi {

    /** 
     * 请求异常 
     * @return 
     * @throws Exception 
     * String 
     */  
    @RequestMapping(value = "/error_404", produces = "text/html;charset=UTF-8")  
    @ResponseBody  
    public ModelAndView error_404(HttpServletResponse response) throws Exception {   
    	ModelAndView mo = new ModelAndView();
    	
    //	response.sendRedirect("/WEB-INF/view/login.jsp");
       //  return "{\"msg\":\"找不到页面\",\"code\":\"1000001\"}";  
    	mo.setViewName("login");
    	return mo ;
    }  
    /** 
     * 服务器异常 
     * @return 
     * String 
     */  
    @RequestMapping(value ="/error_500", produces = "text/html;charset=UTF-8")  
    public String error_500() {    
    	return "{\"msg\":\"服务器处理失败\",\"code\":\"1000002\"}";  
    }  

}
