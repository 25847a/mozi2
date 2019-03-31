package com.sy.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.common.ResultData;
import com.sy.pojo.Bluetooth;
import com.sy.service.BluetoothService;
/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "bluetooth")
public class BluetoothController {
	@Autowired
	private BluetoothService bluetoothservice;

	/**
	 * 更换子设备      根据imei获取蓝牙数据(CJ)
	 * 
	 * @return
	 */
	@RequestMapping(value = "replace")
	@ResponseBody
	public ResultData<T> replace(@RequestBody Map m) {
		ResultData<T> re = new ResultData<T>();
		String imei = (String) m.get("imei");
		Bluetooth bt = bluetoothservice.getBluetoothid(imei);
		if (bt != null) {
			String[] bs = bt.getBluetoothnd().split(",");
			Map<String, String> map = new HashMap<String, String>();
			for (String b : bs) {
				String[] s = b.split(":");
				map.put(s[0], s[1]);
			}
			re.setData(map);
			re.setCode(200);
			re.setMessage("获取蓝牙信息成功！！！");
		} else {
			re.setCode(350);
			re.setMessage("该设备没有蓝牙信息！！！");
		}

		return re;
	}
}
