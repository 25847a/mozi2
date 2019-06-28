package com.sy.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.common.ResultBase;
import com.sy.service.ExtendService;

@Controller
@RequestMapping(value = "extend", method = RequestMethod.POST)
public class ExtendController {
	
	
	@Autowired
	ExtendService extendService;
	
	/**
	 * 修改扩展表(目标步数)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateExtendById")
	@ResponseBody
	public ResultBase updateExtendById(@RequestBody Map<String,Object> map){
		ResultBase re = new ResultBase();
		try {
			int res = extendService.updateExtendById(map);
			if(res>0) {
				re.setCode(200);
				re.setMessage("修改信息成功！！！");
			}else {
				re.setCode(400);
				re.setMessage("修改信息失败！！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
}
