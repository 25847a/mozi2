package com.sy.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Equipment;
import com.sy.pojo.EquipmentExample;

public interface EquipmentMapper extends BaseMapper<Equipment>{
	int countByExample(EquipmentExample example);

	int deleteByExample(EquipmentExample example);

	int deleteByPrimaryKey(Integer id);
	Integer insert(Equipment record);

	int insertSelective(Equipment record);

	List<Equipment> selectByExample(EquipmentExample example);

	Equipment selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Equipment record,
			@Param("example") EquipmentExample example);

	int updateByExample(@Param("record") Equipment record,
			@Param("example") EquipmentExample example);

	int updateByPrimaryKeySelective(Equipment record);

	int updateByPrimaryKey(Equipment record);

	public Equipment getequipment(String imei);

	public List<Equipment> selectequipment();

	public Integer getcount(String keyWord);
	
	public Integer getcount2(Map map);

	public List<Equipment> list(Map map);

	public List<Object> selectImei(List<String> list);
	
	public Integer selectEqNumber(Integer agentid);

	boolean imeiUpdateAgentAccount(Equipment e);

	@Update("update equipment set bluetooth_list = #{bluetoothList}  where id = #{eqid}" )
	void updateBluetoothList(Map map);
	
    @Select("select imei from equipment where agentid = #{agentid}" )
    List<String> selectImeiList(Integer agentid);
	
}