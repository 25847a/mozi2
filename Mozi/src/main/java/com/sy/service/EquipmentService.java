package com.sy.service;

import java.util.List;
import com.baomidou.mybatisplus.service.IService;
import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.Equipment;
import com.sy.utils.DataRow;

public interface EquipmentService extends IService<Equipment>{
	/**
	 * 获取设备基本信息
	 * @param m
	 * @return
	 */
	public ResultData<DataRow> selectdata(DataRow map,ResultData<DataRow> re)throws Exception;
	/**
	 * 修改设备紧急联系人旧
	 * @return
	 */
	public ResultBase updateurgent(DataRow map,ResultBase re)throws Exception;
	/**
	 * 修改设备紧急联系人新
	 * @return
	 */
	public ResultBase updateurgentInfo(Equipment equipment,ResultBase re)throws Exception;
	/**
	 * 获取智能服饰信息
	 * @return
	 */
	public ResultData<DataRow> queryClothesInfo(DataRow map,ResultData<DataRow> re)throws Exception;
	/**
	 * 连接蓝牙
	 * @return
	 */
	public ResultBase updatebluetooth(Equipment equipment,ResultBase re)throws Exception;
	/**
	 * 更新蓝牙列表
	 * @param bluetoothList
	 * @return
	 */
	public ResultBase updateBluetoothList(DataRow map,ResultBase re)throws Exception;
	/**
	 * 删除蓝牙
	 * @param bluetoothList
	 * @return
	 */
	public ResultBase deleteBluetoothList(DataRow map,ResultBase re)throws Exception;
	/**
	 * 发送学习指令
	 * @param map
	 * @return
	 */
	public ResultBase healthcali(DataRow map,ResultBase re)throws Exception;
	/**
	 * 添加设备
	 * @param e
	 * @return
	 */
	public boolean  addEquipment (Equipment e);

	/**更新设备基本数据
	 * @param e
	 * @return
	 */
	public boolean updateEquipment(Equipment e);
	
	
	/**
	 * 根据imei获取数据
	 * @param imei
	 * @return
	 */
	public Equipment selectquipmentimei(String imei);
	/**
	 * 获取所有设备数据
	 * @return
	 */
	public List<Equipment> selectequipment();
	/**
	 * 更新设备
	 * @param eq
	 */
	public void updatEequipmentst(Equipment eq);
	/**
	 * 硬件上传数据用到的
	 * @return
	 */
	public Equipment equipmentstatus(String eqStatus, Integer eqtype, String imei,Integer lordpower,String signalxhao,String version);
	/**
	 * 修改设备在线
	 * @return
	 */
	public Equipment updateEqStatus(String eqStatus, String imei, Equipment e);
	/**
	 * 修改设备在线、设备类型、版本号
	 * @return
	 */
	public Equipment updateEqStatus(String eqStatus, Integer eqtype, String imei, Integer lordpower, String signalxhao,String version, Equipment e);
	
	
}
