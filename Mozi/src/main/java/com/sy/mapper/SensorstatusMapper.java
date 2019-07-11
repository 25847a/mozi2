package com.sy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Sensorstatus;
import com.sy.pojo.SensorstatusExample;

public interface SensorstatusMapper extends BaseMapper<Sensorstatus>{
    int countByExample(SensorstatusExample example);

    int deleteByExample(SensorstatusExample example);

    int deleteByPrimaryKey(Integer id);
    /**
     *  取消关注删除用户的相关信息
     * @param imei
     * @return
     */
    int deleteSensorstatusInfo(String imei);

    int insertSelective(Sensorstatus record);

    List<Sensorstatus> selectByExample(SensorstatusExample example);

    Sensorstatus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sensorstatus record, @Param("example") SensorstatusExample example);

    int updateByExample(@Param("record") Sensorstatus record, @Param("example") SensorstatusExample example);

    int updateByPrimaryKeySelective(Sensorstatus record);

    int updateByPrimaryKey(Sensorstatus record);
    
    /**获取imei设备最新的传感器状态
     * @param imei
     * @return
     */
    public Sensorstatus  selecttimesensorstatus(String imei);
}