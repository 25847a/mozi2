package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Positionig;
import com.sy.pojo.PositionigExample;

public interface PositionigMapper extends BaseMapper<Positionig>{
    int countByExample(PositionigExample example);

    int deleteByExample(PositionigExample example);

    int deleteByPrimaryKey(Integer id);
    //根据IMEI删除数据
    int deletePositionigInfo(String imei);

    int insertSelective(Positionig record);

    List<Positionig> selectByExample(PositionigExample example);

    Positionig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Positionig record, @Param("example") PositionigExample example);

    int updateByExample(@Param("record") Positionig record, @Param("example") PositionigExample example);

    int updateByPrimaryKeySelective(Positionig record);

    int updateByPrimaryKey(Positionig record);
    
    public String selectimeiPositionig(String imei) ;
    
    public String Positionigstate(String imei);
    /**
     * 通过imei查询所有数据
     * @param imei
     * @return
     * @throws SQLException
     */
    public List<Positionig> queryPositionigList(String imei)throws SQLException;
}