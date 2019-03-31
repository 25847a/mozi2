package com.sy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.common.ResultBase;
import com.sy.mapper.RelationMapper;
import com.sy.pojo.Relation;

/**
 * 新模式添加关联关系
 * @author Admin
 *
 */
@Controller
@RequestMapping(value = "relation")
public class RelationController {
		
	@Autowired
	private RelationMapper relationMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(RelationController.class);
	@ResponseBody
	@RequestMapping("insert")
	public ResultBase insert(Relation relation){
		ResultBase r = new ResultBase();
		try{
			relationMapper.insert(relation);
			r.setCode(200);
			r.setMessage("关注成功");
		}catch (Exception e) {
			logger.info(e.getMessage());
			r.setCode(400);
			r.setMessage("关注失败");
		}
		return r;
	}
	
	
	
	
	
	
	
	
}
