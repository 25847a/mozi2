package com.sy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.Positionig;
import com.sy.pojo.PositionigExample;

public interface PositionigMapper {
    int countByExample(PositionigExample example);

    int deleteByExample(PositionigExample example);

    int deleteByPrimaryKey(Integer id);
    //根据IMEI删除数据
    int deletePositionigInfo(String imei);
    
    int insert(Positionig record);

    int insertSelective(Positionig record);

    List<Positionig> selectByExample(PositionigExample example);

    Positionig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Positionig record, @Param("example") PositionigExample example);

    int updateByExample(@Param("record") Positionig record, @Param("example") PositionigExample example);

    int updateByPrimaryKeySelective(Positionig record);

    int updateByPrimaryKey(Positionig record);
    
    public String selectimeiPositionig(String imei) ;
    public String Positionigstate(String imei);
}