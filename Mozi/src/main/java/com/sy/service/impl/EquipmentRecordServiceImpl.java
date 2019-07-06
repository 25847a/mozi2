package com.sy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sy.mapper.EquipmentRecordMapper;
import com.sy.pojo.EquipmentRecord;
import com.sy.service.EquipmentRecordService;

@Service
public class EquipmentRecordServiceImpl extends ServiceImpl<EquipmentRecordMapper, EquipmentRecord> implements EquipmentRecordService {
	
	
	@Autowired
	private EquipmentRecordMapper equipmentRecordMapper;
	
}
