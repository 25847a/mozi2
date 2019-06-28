package com.sy.service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;
import com.sy.pojo.Equipment;
import com.sy.utils.PageModel;
import com.sy.vo.EquipmentVo;
import com.sy.vo.Equipmentstatus;

public interface EquipmentService extends IService<Equipment>{
	
	/**添加设备
	 * @param e
	 * @return
	 */
	public boolean  addEquipment (Equipment e);

	/**更新设备基本数据
	 * @param e
	 * @return
	 */
	public boolean updateEquipment(Equipment e);
	
	/**根据id获取设备数据
	 * @param id
	 * @return
	 */
	public Equipment selectequipment(Integer id);
	
	/**根据imei获取数据
	 * @param imei
	 * @return
	 */
	public Equipment selectquipmentimei(String imei);
	
	/**获取所有设备数据
	 * @return
	 */
	public List<Equipment> selectequipment();
	
	/**更新设备,不用if param != null
	 * @param eq
	 */
	public void updatEequipmentst(Equipment eq);
	
	/**根据用户id获取用户所有设备信息
	 * @param userid
	 * @return
	 */
	public List<EquipmentVo> selelctequipments(Integer userId );
	
	
	/**
	 * 分页获取设备信息
	 * @param pageNo
	 * @param keyWord
	 * @return
	 */
	public PageModel<Equipmentstatus> getusersone(Integer pageNo,String keyWord);
	
	
	/**
	 * 分页获取设备信息
	 * @param pageNo
	 * @param map
	 * @return 
	 */
	public PageModel<Equipment> getusersone(Integer pageNo,Map<String,Object> map);
	public PageModel<Equipment> getAgentEquipment(Integer pageNo,Map<String,Object> map);

	public List<String> allentry(List<String> list,Integer agentid,String model);
	
	public boolean imeiUpdateAgentAccount(Equipment e);

	public Equipment equipmentstatus(String eqStatus, String eqtype, String imei,Integer lordpower,String signalxhao,String version);
	public Integer countEqNumber(Integer agentid);

	public Equipment updateEqStatus(String string, String string2, String imei, Integer parseInt, String signalxhao,
			String string3, Equipment e);
	/**
	 * 更新蓝牙列表
	 * @param bluetoothList
	 * @return
	 */
	public void updateBluetoothList(Map map);
	
}
