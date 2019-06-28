package com.sy.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.Group;
import com.sy.service.GroupService;
/**
 * 朋友圈群组
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "group", method = RequestMethod.POST)
public class GroupController {
	private final static Logger logger = LoggerFactory.getLogger(GroupController.class);
	
	@Autowired
	GroupService groupService;
	
	/**
	 * 创建社区群
	 * @param group
	 * @param code
	 * @return
	 */
	@RequestMapping("/insertGroup")
	@ResponseBody//
	public ResultBase insertGroup(@RequestBody Group group,@RequestParam(value="image",required=false)CommonsMultipartFile image){
		ResultBase re = new ResultBase();
		try {
			re = groupService.insertGroup(group,image,re);
		} catch (Exception e) {
			logger.error("GroupController>>>>>>>>>>>insertGroup",e);
		}
		return re;
	}
	/**
	 * 首页查询社区
	 * @param group
	 * @param code
	 * @return
	 */
	@RequestMapping("/queryGroupPage")
	@ResponseBody
	public ResultData<List<Map<String, Object>>> queryGroupPage(@RequestBody Map<String,String> map){
		ResultData<List<Map<String, Object>>> re = new ResultData<List<Map<String, Object>>>();
		try {
			re = groupService.queryGroupPage(map,re);
		} catch (Exception e) {
			logger.error("GroupController>>>>>>>>>>>queryGroupPage",e);
		}
		return re;
	}
	/**
	 * 查询社圈群员
	 * @param group
	 * @param code
	 * @return
	 */
	@RequestMapping("/queryRecommendGroup")
	@ResponseBody
	public ResultData<List<Map<String,Object>>> queryRecommendGroup(@RequestBody Map<String,String> map){
		ResultData<List<Map<String,Object>>> re = new ResultData<List<Map<String,Object>>>();
		try {
			re = groupService.queryRecommendGroup(map,re);
		} catch (Exception e) {
			logger.error("GroupController>>>>>>>>>>>queryRecommendGroup",e);
		}
		return re;
	}
	/**
	 * 查询活跃排行榜人数
	 * @param group
	 * @param code
	 * @return
	 */
	@RequestMapping("/queryActiveInfo")
	@ResponseBody
	public ResultData<List<Map<String, Object>>> queryActiveInfo(@RequestBody Map<String,String> map){
		ResultData<List<Map<String, Object>>> re = new ResultData<List<Map<String, Object>>>();
		try {
			re = groupService.queryActiveInfo(map,re);
		} catch (Exception e) {
			logger.error("GroupController>>>>>>>>>>>queryActiveInfo",e);
		}
		return re;
	}
	/**
	 * 手机号码查询社圈
	 * @param group
	 * @param code
	 * @return
	 */
	@RequestMapping("/queryPhoneGroup")
	@ResponseBody
	public ResultData<Map<String, Object>> queryPhoneGroup(@RequestBody Map<String,String> map){
		ResultData<Map<String, Object>> re = new ResultData<Map<String, Object>>();
		try {
			re = groupService.queryPhoneGroup(map,re);
		} catch (Exception e) {
			logger.error("GroupController>>>>>>>>>>>queryPhoneGroup",e);
		}
		return re;
	}
	/**
	 * 解散社群
	 * @param map
	 * @return
	 */
	@RequestMapping("/deleteDismiss")
	@ResponseBody
	public ResultBase deleteDismiss(@RequestBody Map<String,Object> map){
		ResultBase re = new ResultBase();
		try {
			re = groupService.deleteDismiss(map,re);
		} catch (Exception e) {
			logger.error("GroupController>>>>>>>>>>>deleteDismiss",e);
		}
		return re;
	}
}
