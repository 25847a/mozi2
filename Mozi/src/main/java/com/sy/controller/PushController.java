package com.sy.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.Push;
import com.sy.service.PushService;
import com.sy.utils.DataRow;

@Controller
@RequestMapping("/push")
public class PushController {
	
	private final static Logger logger = LoggerFactory.getLogger(PushController.class);
	
	
	@Autowired
	private PushService pushService;
	/**
	 * 查询推送表
	 * @return
	 * @throws ParseException 
	 * @throws SQLException
	 */
	@RequestMapping("/selectPush")
	@ResponseBody
	public ResultData<Push> selectPush(@RequestBody Map<String,Object> map){
		ResultData<Push> re = new ResultData<Push>();
		try {
			 Push push =pushService.selectPush(map);
			 if(null!=push) {
				 re.setCode(200);
				 re.setData(push);
				 re.setMessage("获取推送数据成功！！！");
			 }else {
				 re.setCode(200);
					re.setMessage("暂无推送数据！！！");
			 }
		} catch (Exception e) {
			re.setCode(400);
			re.setMessage("请先设置开关数据");
			logger.error("PushController>>>>>>>>>>>>>selectPush",e);
		}
		return re;
	}
	/**
	 * 修改推送表
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("/updatePushById")
	@ResponseBody
	public ResultBase updatePushById(@RequestBody Push push){
		ResultBase re = new ResultBase();
		try {
			EntityWrapper<Push> ew = new EntityWrapper<Push>();
			ew.eq("alias", push.getAlias());
			ew.eq("userId", push.getUserId());
			boolean res =pushService.update(push, ew);
			// pushService.updatePushById(map);
			if(res) {
				re.setCode(200);
				re.setMessage("修改信息成功！！！");
			}else {
				re.setCode(400);
				re.setMessage("修改信息失败！！！");
			}
		} catch (Exception e) {
			logger.error("PushController>>>>>>>>>>>>>updatePushById",e);
		}
		return re;
	}
	/**
	 * 测试推送接口
	 * @return
	 */
	@RequestMapping("/testPush")
	@ResponseBody
	public ResultBase testPush(@RequestBody DataRow map){
		ResultBase re = new ResultBase();
		try {
			re = pushService.testPush(map,re);
		} catch (Exception e) {
			logger.error("PushController>>>>>>>>>>>>>testPush",e);
		}
		return re;
		
	}
}
