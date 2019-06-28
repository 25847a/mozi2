package com.sy.service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;
import com.sy.pojo.EquipmentData;

public interface EquipmentDataService extends IService<EquipmentData>{
	
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
	
	/**获取用户最近的健康数据
	 * @param imei
	 * @return
	 */
	public EquipmentData selectdata(Integer userId);
	
	/**
	 * 查询当天的步行数
	 * @param   m.put("countdate", dd+"%");   m.put("userid",userId);
	 * @return
	 */
	public List<EquipmentData> selecttheycount(Map m);
}
