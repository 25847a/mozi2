package com.sy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.Versionhistory;
import com.sy.pojo.VersionhistoryExample;

public interface VersionhistoryMapper {
    int countByExample(VersionhistoryExample example);

    int deleteByExample(VersionhistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Versionhistory record);

    int insertSelective(Versionhistory record);

    List<Versionhistory> selectByExample(VersionhistoryExample example);

    Versionhistory selectByPrimaryKey(Integer id);
    Versionhistory selectVersion(Integer id);

    int updateByExampleSelective(@Param("record") Versionhistory record, @Param("example") VersionhistoryExample example);

    int updateByExample(@Param("record") Versionhistory record, @Param("example") VersionhistoryExample example);

    int updateByPrimaryKeySelective(Versionhistory record);

    int updateByPrimaryKey(Versionhistory record);
}