package com.sy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sy.common.ResultBase;
import com.sy.pojo.GroupRelation;
import com.sy.service.GroupRelationService;
/**
 * 朋友圈群组关联
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "groupRelation", method = RequestMethod.POST)
public class GroupRelationController {
	private final static Logger logger = LoggerFactory.getLogger(GroupRelationController.class);
	
	@Autowired
	GroupRelationService groupRelationService;
	
	/**
	 * 加入朋友圈群
	 * @param groupRelation
	 * @return
	*/
	@RequestMapping("/addGroupRelation")
	@ResponseBody
	public ResultBase addGroupRelation(@RequestBody GroupRelation groupRelation){
		ResultBase re = new ResultBase();
		try {
			re = groupRelationService.addGroupRelation(groupRelation,re);
		} catch (Exception e) {
			logger.error("GroupController>>>>>>>>>>>addGroupRelation",e);
		}
		return re;
	} 
	/**
	 * 删除个人群关联
	 * @param groupRelation
	 * @return
	*/
	@RequestMapping("/deleteGroupRelation")
	@ResponseBody
	public ResultBase deleteGroupRelation(@RequestBody GroupRelation groupRelation){
		ResultBase re = new ResultBase();
		try {
			re = groupRelationService.deleteGroupRelation(groupRelation,re);
		} catch (Exception e) {
			logger.error("GroupController>>>>>>>>>>>deleteGroupRelation",e);
		}
		return re;
	}
}
