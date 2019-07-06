package com.sy.mapper;

import java.sql.SQLException;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.EquipmentRecord;

public interface EquipmentRecordMapper extends BaseMapper<EquipmentRecord>{

	public EquipmentRecord queryEquipmentRecord(int userId)throws SQLException;
}