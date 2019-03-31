package com.sy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.Startimg;
import com.sy.pojo.StartimgExample;

public interface StartimgMapper {
    int countByExample(StartimgExample example);

    int deleteByExample(StartimgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Startimg record);

    int insertSelective(Startimg record);

    List<Startimg> selectByExample(StartimgExample example);

    Startimg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Startimg record, @Param("example") StartimgExample example);

    int updateByExample(@Param("record") Startimg record, @Param("example") StartimgExample example);

    int updateByPrimaryKeySelective(Startimg record);

    int updateByPrimaryKey(Startimg record);
}