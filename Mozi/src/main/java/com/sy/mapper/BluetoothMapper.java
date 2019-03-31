package com.sy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.Bluetooth;
import com.sy.pojo.BluetoothExample;

public interface BluetoothMapper {
    int countByExample(BluetoothExample example);

    int deleteByExample(BluetoothExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Bluetooth record);

    int insertSelective(Bluetooth record);

    List<Bluetooth> selectByExample(BluetoothExample example);

    Bluetooth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Bluetooth record, @Param("example") BluetoothExample example);

    int updateByExample(@Param("record") Bluetooth record, @Param("example") BluetoothExample example);

    int updateByPrimaryKeySelective(Bluetooth record);

    int updateByPrimaryKey(Bluetooth record);
    
    public Bluetooth getBluetoothid(String imei);
}