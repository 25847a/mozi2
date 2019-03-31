package com.sy.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.common.ResultData;
import com.sy.pojo.GroupPhone;
import com.sy.service.GroupPhoneService;
@Controller
@RequestMapping(value = "groupphone")
public class GroupPhoneController {
	
	@Autowired
	private GroupPhoneService groupPhoneServce;
	
	@RequestMapping(value = "batchUpdate")
	@ResponseBody
	public ResultData<String> batchUpdate(){
		ResultData<String> re = new ResultData<>();
		try{
			Integer batchUpdate = groupPhoneServce.batchUpdate();
			re.setData(batchUpdate);
			re.setCode(200);
			re.setMessage("ok");        
		}catch (Exception e) {
			e.printStackTrace();
			re.setCode(400);
			re.setMessage("");
		}
		return re;
	}
	@RequestMapping(value = "selectOne")
	@ResponseBody
	public ResultData<String> selectOne(Map<String, Object> map){
		ResultData<String> re = new ResultData<>();
		try{
			String phone =  (String)map.get("phone");
			GroupPhone selectOne = groupPhoneServce.selectOne(phone);
			re.setCode(200);
			re.setMessage("查询失败");
			re.setData(selectOne);
		}catch (Exception e) {
			e.printStackTrace();
			re.setCode(400);
			re.setMessage("查询失败");
		}
		return re;
	}
		
		
}
