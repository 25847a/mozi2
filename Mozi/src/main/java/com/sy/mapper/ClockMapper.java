package com.sy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.Clock;
import com.sy.pojo.ClockExample;

public interface ClockMapper {
    int countByExample(ClockExample example);

    int deleteByExample(ClockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Clock record);

    int insertSelective(Clock record);

    List<Clock> selectByExample(ClockExample example);

    Clock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Clock record, @Param("example") ClockExample example);

    int updateByExample(@Param("record") Clock record, @Param("example") ClockExample example);

    int updateByPrimaryKeySelective(Clock record);

    int updateByPrimaryKey(Clock record);
    
    public List<Clock> selectclocks(String imei);
}