package com.sy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.Appsdk;
import com.sy.pojo.AppsdkExample;

public interface AppsdkMapper {
    int countByExample(AppsdkExample example);

    int deleteByExample(AppsdkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Appsdk record);

    int insertSelective(Appsdk record);

    List<Appsdk> selectByExample(AppsdkExample example);

    Appsdk selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Appsdk record, @Param("example") AppsdkExample example);

    int updateByExample(@Param("record") Appsdk record, @Param("example") AppsdkExample example);

    int updateByPrimaryKeySelective(Appsdk record);

    int updateByPrimaryKey(Appsdk record);
}