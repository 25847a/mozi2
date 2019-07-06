package com.sy.mapper;

import java.sql.SQLException;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.EquipmentData;
import com.sy.utils.DataRow;

public interface EquipmentDataMapper extends BaseMapper<EquipmentData>{

    int deleteByPrimaryKey(Integer id);

    EquipmentData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EquipmentData record);

    int updateByPrimaryKey(EquipmentData record);
    public Integer getcount(String keyWord);


	public EquipmentData selectdata(Integer userId);

	public void deletedata(Integer userId);
	/**
	 * 啊健写的获取卡里路提供给硬件
	 * @param userId
	 * @return
	 */
	public EquipmentData queryEquipmentDataInfo(Integer userId);
	/**
	 * 啊健写的获取卡里路提供给硬件
	 * @param userId
	 * @return
	 */
	public DataRow queryStepWhenCarrieroadSum(Integer userId)throws SQLException;
	 
	
}