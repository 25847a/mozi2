package com.sy.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.nettyulit.NettyChannelMap;
import com.sy.service.PositionigService;

import io.netty.channel.Channel;

@Controller
@RequestMapping(value = "positionig", method = RequestMethod.POST)
public class PositionigController {
	@Autowired
	private PositionigService positionigservice;
	
	/**获取最新定位数据
	 * @param u
	 * @return
	 */
	@RequestMapping("selectimeiPositionig")
	@ResponseBody
	public ResultData<Map<String,String >> selectimeiPositionig(@RequestBody Map m) {
		ResultData<Map<String,String >>  re = new ResultData<Map<String,String >> ();
		  Map<String,String> map = positionigservice.selectimeiPositionig((String)m.get("imei"));
		
		
		if(map !=null) {
			re.setCode(200);
			re.setData(map);
			re.setMessage("获取定位信息成功！！！");
		}else {
			re.setCode(350);
			re.setMessage("该设备没有定位信息！！！");
		}
		return re;
		
	}

	
	@RequestMapping("positionigoperation")
	@ResponseBody
	public ResultBase positionigoperation(@RequestBody Map m){
		ResultBase re = new ResultBase();
		
		try {
			Channel c =	NettyChannelMap.get((String)m.get("imei"));
			String service=(String)m.get("service");
			if(service.equals("start")){
				c.writeAndFlush("$R25|1\r\n");
			}else if (service.equals("stop")) {
				c.writeAndFlush("$R25|0\r\n");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		re.setCode(200);
		re.setMessage("操作成功！！！");
		return re;
	}
}
