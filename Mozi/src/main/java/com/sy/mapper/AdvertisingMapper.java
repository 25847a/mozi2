package com.sy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.Advertising;
import com.sy.pojo.AdvertisingExample;

public interface AdvertisingMapper {
	int countByExample(AdvertisingExample example);

	int deleteByExample(AdvertisingExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Advertising record);

	int insertSelective(Advertising record);

	List<Advertising> selectByExampleWithBLOBs(AdvertisingExample example);

	List<Advertising> selectByExample(AdvertisingExample example);

	Advertising selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Advertising record,
			@Param("example") AdvertisingExample example);

	int updateByExampleWithBLOBs(@Param("record") Advertising record,
			@Param("example") AdvertisingExample example);

	int updateByExample(@Param("record") Advertising record,
			@Param("example") AdvertisingExample example);

	int updateByPrimaryKeySelective(Advertising record);

	int updateByPrimaryKeyWithBLOBs(Advertising record);

	int updateByPrimaryKey(Advertising record);

	public Integer getcount(String keyWord);

	public List<Advertising> list(Map map);
}