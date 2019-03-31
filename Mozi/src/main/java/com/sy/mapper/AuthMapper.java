package com.sy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.Auth;
import com.sy.pojo.AuthExample;

public interface AuthMapper {
	int countByExample(AuthExample example);

	int deleteByExample(AuthExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Auth record);

	int insertSelective(Auth record);

	List<Auth> selectByExample(AuthExample example);

	Auth selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Auth record,
			@Param("example") AuthExample example);

	int updateByExample(@Param("record") Auth record,
			@Param("example") AuthExample example);

	int updateByPrimaryKeySelective(Auth record);

	int updateByPrimaryKey(Auth record);

	public List<Auth> getauth();

	public Integer getcount(String keyWord);

	public List<Auth> list(Map map);
}