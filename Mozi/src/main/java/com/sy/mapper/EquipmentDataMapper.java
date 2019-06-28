package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.EquipmentData;
import com.sy.utils.DataRow;

public interface EquipmentDataMapper extends BaseMapper<EquipmentData>{

    int deleteByPrimaryKey(Integer id);

    int insertSelective(EquipmentData record);

    EquipmentData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EquipmentData record);

    int updateByPrimaryKey(EquipmentData record);
    public Integer getcount(String keyWord);


	public EquipmentData selectdata(Integer userId);

	public void deletedata(Integer userId);
	/**
	 * 查询当天的步行数
	 * @param m
	 * @return
	 */
	public List<EquipmentData>   selecttheycount(Map m);
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