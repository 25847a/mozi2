package com.sy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Sensorstatus;

public interface SensorstatusMapper extends BaseMapper<Sensorstatus>{

    /**
     *  取消关注删除用户的相关信息
     * @param imei
     * @return
     */
    int deleteSensorstatusInfo(String imei);

    int updateByPrimaryKey(Sensorstatus record);
    /**
     * 获取imei设备最新的传感器状态
     * @param imei
     * @return
     */
    public Sensorstatus selecttimesensorstatus(String imei);
}