package com.sy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.EquipmentData;
import com.sy.pojo.EquipmentDataExample;

public interface EquipmentDataMapper {
    int countByExample(EquipmentDataExample example);

    int deleteByExample(EquipmentDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EquipmentData record);

    int insertSelective(EquipmentData record);

    List<EquipmentData> selectByExample(EquipmentDataExample example);

    EquipmentData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EquipmentData record, @Param("example") EquipmentDataExample example);

    int updateByExample(@Param("record") EquipmentData record, @Param("example") EquipmentDataExample example);

    int updateByPrimaryKeySelective(EquipmentData record);

    int updateByPrimaryKey(EquipmentData record);
    public Integer getcount(String keyWord);

	public List<EquipmentData> list(Map map);

	public EquipmentData selectdata(Integer userId);

	public void deletedata(Integer userId);

	public List<EquipmentData> selectdateequipmentDate(Map m);
	
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
	 
	
}