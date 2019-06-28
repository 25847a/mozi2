package com.sy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sy.common.ResultData;
import com.sy.pojo.Config;
import com.sy.service.ConfigService;

@Controller
@RequestMapping(value = "config", method = RequestMethod.POST)
public class ConfigController {
	private final static Logger logger = LoggerFactory.getLogger(ConfigController.class);
	
	@Autowired
	ConfigService configService;
	
	/**
	 * 获取朋友圈群类型
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryGroupName")
	@ResponseBody
	public ResultData<Config> queryGroupName(){
		ResultData<Config> re = new ResultData<Config>();
		try {
			List<Config> config = configService.queryConfigList("group","group_type");
			if(config!=null) {
				re.setData(config);
				re.setCode(200);
				re.setMessage("获取朋友圈类型成功");
			}else {
				re.setCode(400);
				re.setMessage("未配置朋友圈类型");
			}
		} catch (Exception e) {
			logger.error("ConfigController>>>>>>>>>>>queryGroupName",e);
		}
		return re;
	}
	/**
	 * 获取评论语
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryComments")
	@ResponseBody
	public ResultData<Config> queryComments(){
		ResultData<Config> re = new ResultData<Config>();
		try {
			EntityWrapper<Config> ew = new EntityWrapper<Config>();
			ew.eq("type", "comment");
			ew.eq("lable", "comment_type");
			List<Config> config = configService.queryConfigList("comment","comment_type");
			if(config!=null) {
				re.setData(config);
				re.setCode(200);
				re.setMessage("获取评论语成功");
			}else {
				re.setCode(400);
				re.setMessage("未配置评论语");
			}
		} catch (Exception e) {
			logger.error("ConfigController>>>>>>>>>>>queryComments",e);
		}
		return re;
	}
	/**
	 * 首页轮播和公告
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryHomePageNotice")
	@ResponseBody
	public ResultData<Config> queryHomePageNotice(){
		ResultData<Config> re = new ResultData<Config>();
		try {
			List<Config> config = configService.queryConfigList("home","home_carousel");
			Map<String,Object> map = new HashMap<String,Object>();
			
			if(!config.isEmpty()) {
				List<Config> con = configService.queryConfigList("home","home_notice");
				if(!con.isEmpty()){
					map.put("notice", con);
					re.setMessage("获取轮播和公告成功");
				}else{
					re.setMessage("未配置公告");	
				}
				map.put("carousel", config);
				re.setCode(200);
				re.setData(map);
			}else {
				re.setCode(400);
				re.setMessage("未配置轮播图片");
			}
		} catch (Exception e) {
			logger.error("ConfigController>>>>>>>>>>>queryHomePageNotice",e);
		}
		return re;
	}
}
