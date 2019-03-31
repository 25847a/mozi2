package com.sy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.Management;
import com.sy.pojo.ManagementExample;

public interface ManagementMapper {
    int countByExample(ManagementExample example);

    int deleteByExample(ManagementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Management record);

    int insertSelective(Management record);

    List<Management> selectByExample(ManagementExample example);

    Management selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Management record, @Param("example") ManagementExample example);

    int updateByExample(@Param("record") Management record, @Param("example") ManagementExample example);

    int updateByPrimaryKeySelective(Management record);

    int updateByPrimaryKey(Management record);
    
    public Management ifmanagement(String account);
    
    public Management loginmanagement(Map m );
    
    public Integer getcount(String keyWord);
    
    public List<Management> list(Map map);
    
    public List<Management> selectmanagements() ;
    
	public Integer getagentcount(String role);
	
	public List<Management> agentlist(Map map);

	List<Management> agentIdList();
}