package com.sy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.Uploaddownload;
import com.sy.pojo.UploaddownloadExample;

public interface UploaddownloadMapper {
    int countByExample(UploaddownloadExample example);

    int deleteByExample(UploaddownloadExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Uploaddownload record);

    int insertSelective(Uploaddownload record);

    List<Uploaddownload> selectByExample(UploaddownloadExample example);

    Uploaddownload selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Uploaddownload record, @Param("example") UploaddownloadExample example);

    int updateByExample(@Param("record") Uploaddownload record, @Param("example") UploaddownloadExample example);

    int updateByPrimaryKeySelective(Uploaddownload record);

    int updateByPrimaryKey(Uploaddownload record);
    
    public Uploaddownload selectUploaddownload(String imei);
    

	public Integer getcount(String keyWord);

	public List<Uploaddownload> list(Map map);

	Uploaddownload selectModelVo(String model);
	Uploaddownload selectModelAndImeiVo(Uploaddownload record);
}