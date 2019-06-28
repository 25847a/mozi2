package com.sy.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sy.mapper.EquipmentDataMapper;
import com.sy.pojo.EquipmentData;
import com.sy.service.EquipmentDataService;

@Service
public class EquipmentDataServiceImpl extends ServiceImpl<EquipmentDataMapper, EquipmentData> implements EquipmentDataService {
	@Autowired
	private EquipmentDataMapper dataMapper;
	@Override
	public boolean addEquipmentData(EquipmentData e) {
		e.setCreatetime(new Date());

		if (dataMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			dataMapper = (EquipmentDataMapper) webApplicationContext
					.getBean("equipmentDataMapper");
		}
		Integer num = dataMapper.insertSelective(e);
		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public EquipmentData selectdata(Integer userId) {
		EquipmentData edata =dataMapper.selectdata(userId);
		return edata;
	}
	public boolean updateEquipmentData(EquipmentData record) {
		int num = dataMapper.updateByPrimaryKey(record);
		return num==0?false:true;
	}

	/**
	 * 查询当天的步行数
	 * @param   m.put("countdate", dd+"%");   m.put("userid",userId);
	 * @return
	 */
	public List<EquipmentData> selecttheycount(Map m) {
		return dataMapper.selecttheycount(m);
	}
	
}
