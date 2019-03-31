package com.sy.service;

import java.util.List;
import java.util.Map;

import com.sy.pojo.EquipmentData;
import com.sy.utils.PageModel;

public interface EquipmentDataService {
	
	/**硬件注册或跟新数据
	 * @param eqStatus
	 * @param eqtype
	 * @return
	 */
	//public Equipment equipmentstatus(String eqStatus,String eqtype,String imei,Integer lordpower,String signalxhao,String verison);
	
	
	/**添加设备数据
	 * @param e
	 * @return
	 */
	public boolean updateEquipmentData(EquipmentData e);
	
	/**添加设备数据
	 * @param e
	 * @return
	 */
	public boolean addEquipmentData(EquipmentData e);
	
	/**分页获取数据
	 * @param pageNo
	 * @param keyword
	 * @return
	 */
	public PageModel<EquipmentData>  getusersone(Integer pageNo,String keyword);
	
	/**获取用户最近的健康数据
	 * @param imei
	 * @return
	 */
	public EquipmentData selectdata(Integer userId);
	
	public List<EquipmentData> selectdateequipmentDate(Map m);
	
	/**
	 * 查询当天的步行数
	 * @param   m.put("countdate", dd+"%");   m.put("userid",userId);
	 * @return
	 */
	public List<EquipmentData> selecttheycount(Map m);
}
