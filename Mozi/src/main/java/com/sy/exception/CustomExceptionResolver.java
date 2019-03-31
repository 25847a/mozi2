package com.sy.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;

/**
 * 全局异常处理器
 * 
 * @author DDM
 * 
 */
@Controller
public class CustomExceptionResolver  implements HandlerExceptionResolver {    
      
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  Exception ex) {    
    	  ModelAndView mv = new ModelAndView();  
          /*  使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常   */  
          FastJsonJsonView view = new FastJsonJsonView();  
          Map<String, Object> attributes = new HashMap<String, Object>();  
          attributes.put("code", "1000001");  
          attributes.put("message", "系统异常，请确认输入的参数是否有误！！！");  
          view.setAttributesMap(attributes);  
          mv.setView(view);   
          return mv;  
    }  
}
