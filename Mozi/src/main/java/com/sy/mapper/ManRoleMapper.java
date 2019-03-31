package com.sy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.ManRole;
import com.sy.pojo.ManRoleExample;

public interface ManRoleMapper {
    int countByExample(ManRoleExample example);

    int deleteByExample(ManRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ManRole record);

    int insertSelective(ManRole record);

    List<ManRole> selectByExample(ManRoleExample example);

    ManRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ManRole record, @Param("example") ManRoleExample example);

    int updateByExample(@Param("record") ManRole record, @Param("example") ManRoleExample example);

    int updateByPrimaryKeySelective(ManRole record);

    int updateByPrimaryKey(ManRole record);
    
    public List<ManRole> gemarole(Integer  manId );
    //获取数据的总数
    public int getcount(String keyWord);

    public List<ManRole> list(Map<String, Object> parameter);
}