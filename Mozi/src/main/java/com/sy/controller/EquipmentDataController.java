package com.sy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sy.common.ResultData;
import com.sy.pojo.EquipmentData;
import com.sy.service.EquipmentDataService;
import com.sy.utils.DataRow;
@Controller
@RequestMapping(value = "equipmentData")
public class EquipmentDataController {
	@Autowired
	private EquipmentDataService equipmentdateservice;
	
	@RequestMapping(value = "selectdata")
	@ResponseBody
	public ResultData<EquipmentData>selectdata(@RequestBody DataRow m){
		ResultData<EquipmentData> re = new ResultData<EquipmentData>();
		EquipmentData eqdata=equipmentdateservice.selectdata(m.getInt("userid"));
		if(eqdata !=null){
			re.setCode(200);
			re.setData(eqdata);
			re.setMessage("获取用户最新健康数据成功！！！");
		}else {
			re.setCode(350);
			re.setMessage("该用户没有健康数据！！！");
		}
		return re;
	}
	 
}
