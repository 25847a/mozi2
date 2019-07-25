package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.EquipmentRecord;

public interface EquipmentRecordMapper extends BaseMapper<EquipmentRecord>{

	public EquipmentRecord queryEquipmentRecord(int userId)throws SQLException;
	/**
	 * 查询湿度,体温集合
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public List<EquipmentRecord> queryEquipmentRecordList(int userId)throws SQLException;
	
	/**
	 *  删除数据
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public int deleteEquipmentRecord(int userId)throws SQLException;
	
}