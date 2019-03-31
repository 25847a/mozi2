package com.sy.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.Push;
import com.sy.service.PushService;

@Controller
@RequestMapping("/push")
public class PushController {
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
			e.printStackTrace();
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
	public ResultBase updatePushById(@RequestBody Map<String,Object> map){
		ResultBase re = new ResultBase();
		try {
			int res = pushService.updatePushById(map);
			if(res>0) {
				re.setCode(200);
				re.setMessage("修改信息成功！！！");
			}else {
				re.setCode(400);
				re.setMessage("修改信息失败！！！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return re;
	}
}
