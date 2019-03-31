package com.sy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.utils.Config;
/**
 * 按字面意思，获取app的版本
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "appversion")
public class AppVersionController {
	
		
		@RequestMapping(value="getVersion")
		@ResponseBody
		public String getVersion(){
			Config config = new Config("systemConfig.properties");
			String app_version = config.getString("app_version");
			
			return "{\"app_version\":\""+app_version+"\"}";
		}
	
}
