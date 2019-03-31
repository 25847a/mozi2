package com.sy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sy.utils.Managementconstant;

public class SecurityInterceptor implements HandlerInterceptor {

	private final static Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// return true;
		String requestUrl = request.getRequestURI();
		//System.out.println("请求接口》》》"+requestUrl);
		logger.info("请求接口》》》"+requestUrl);
		 if(requestUrl.contains("index/main")){
			 Object userObj = request.getSession().getAttribute(
					 Managementconstant.user);
			 if(userObj==null){
				 request.getRequestDispatcher("/").forward(request, response);
				 return false;
			 }else {
				 return true;
			}
		 }else {
			 return true;
		}
	}
	//执行contoller后执
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}
	//进入视图渲染执行
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
