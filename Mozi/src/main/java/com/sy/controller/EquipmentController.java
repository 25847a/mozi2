package com.sy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.Equipment;
import com.sy.service.EquipmentService;
import com.sy.utils.DataRow;
@Controller
@RequestMapping(value = "equipment")
public class EquipmentController {

	private final static Logger logger = LoggerFactory.getLogger(EquipmentController.class);
	@Autowired
	private EquipmentService equipmentservice;
	/**
	 * 获取设备基本信息
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "selectEquipment")
	@ResponseBody
	public ResultData<DataRow> selectdata(@RequestBody DataRow map) {
		ResultData<DataRow> re = new ResultData<DataRow>();
		try {
			re = equipmentservice.selectdata(map, re);
		} catch (Exception e) {
			logger.info("EquipmentController>>>>>>>>>>>>>>>selectdata", e);
		}
		return re;
	}

	/**
	 * 修改设备紧急联系人
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateurgent")
	@ResponseBody
	public ResultBase updateurgent(@RequestBody DataRow map) {
		ResultBase re = new ResultBase();
		try {
			re = equipmentservice.updateurgent(map, re);
		} catch (Exception e) {
			logger.info("EquipmentController>>>>>>>>>>>>>>>updateurgent", e);
		}
		return re;
	}
	/**
	 * 修改设备紧急联系人
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateurgentInfo")
	@ResponseBody
	public ResultBase updateurgentInfo(@RequestBody Equipment equipment) {
		ResultBase re = new ResultBase();
		try {
			re = equipmentservice.updateurgentInfo(equipment,re);
		} catch (Exception e) {
			logger.info("EquipmentController>>>>>>>>>>>>>>>updateurgentInfo", e);
		}
		return re;
	}
	/**
	 * 获取智能服饰信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryClothesInfo")
	@ResponseBody
	public ResultData<DataRow> queryClothesInfo(@RequestBody DataRow map) {
		ResultData<DataRow> re = new ResultData<DataRow>();
		try {
			re = equipmentservice.queryClothesInfo(map, re);
		} catch (Exception e) {
			logger.info("EquipmentController>>>>>>>>>>>>>>>queryClothesInfo", e);
		}
		return re;
	}

	/**
	 * 连接蓝牙
	 * 
	 * @return
	 */
	@RequestMapping(value = "updatebluetooth")
	@ResponseBody
	public ResultBase updatebluetooth(@RequestBody Equipment equipment) {
		ResultBase re = new ResultBase();
		try {
			re = equipmentservice.updatebluetooth(equipment, re);
		} catch (Exception e) {
			re.setCode(350);
			re.setMessage("设备不在线！！");
			logger.info("EquipmentController>>>>>>>>>>>>>>>updatebluetooth", e);
		}
		return re;
	}
	/**
	 * 添加蓝牙
	 * 
	 * @param map
	 *            bluetoothList 蓝牙列表 eqid
	 * @return
	 */
	@RequestMapping(value = "updateBluetoothList")
	@ResponseBody
	public ResultBase updateBluetoothList(@RequestBody DataRow map) {
		ResultBase re = new ResultBase();
		try {
			re = equipmentservice.updateBluetoothList(map, re);
		} catch (Exception e) {
			re.setCode(400);
			re.setMessage("操作失败");
			logger.info("EquipmentController>>>>>>>>>>>>>>>updateBluetoothList", e);
		}
		return re;
	}
	/**
	 * 删除蓝牙
	 * 
	 * @param map
	 * bluetoothList 删除蓝牙
	 * @return
	 */
	@RequestMapping(value = "deleteBluetoothList")
	@ResponseBody
	public ResultBase deleteBluetoothList(@RequestBody DataRow map) {
		ResultBase re = new ResultBase();
		try {
			re = equipmentservice.deleteBluetoothList(map, re);
		} catch (Exception e) {
			re.setCode(400);
			re.setMessage("设备不在线");
			logger.info("EquipmentController>>>>>>>>>>>>>>>deleteBluetoothList", e);
		}
		return re;
	}
	/**
	 * 发送学习指令
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "healthcali")
	@ResponseBody
	public ResultBase healthcali(@RequestBody DataRow map) {
		ResultBase re = new ResultBase();
		try {
			re = equipmentservice.healthcali(map, re);
		} catch (Exception e) {
			re.setCode(350);
			re.setMessage("学习失败");
			logger.info("EquipmentController>>>>>>>>>>>>>>>healthcali", e);
		}
		return re;
	}
}
