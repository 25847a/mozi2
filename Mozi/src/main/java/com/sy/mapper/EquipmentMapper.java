package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Equipment;
import com.sy.utils.DataRow;

public interface EquipmentMapper extends BaseMapper<Equipment>{
	/**
	 * 通过imei查询,目前用得上
	 * @param imei
	 * @return
	 */
    public Equipment getequipment(String imei);

	/**
	 *  查询出imei的总数量
	 * @param list
	 * @return
	 */
	public List<Object> selectImei(List<String> list);
    /**
     * 点击卡片查询设备信息
     * @param imei
     * @return
     * @throws SQLException
     */
	public DataRow queryEquipmentMember(String imei)throws SQLException;
}