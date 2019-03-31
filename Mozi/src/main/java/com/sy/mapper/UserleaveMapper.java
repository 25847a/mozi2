package com.sy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.Userleave;
import com.sy.pojo.UserleaveExample;

public interface UserleaveMapper {
	int countByExample(UserleaveExample example);

	int deleteByExample(UserleaveExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Userleave record);

	int insertSelective(Userleave record);

	List<Userleave> selectByExampleWithBLOBs(UserleaveExample example);

	List<Userleave> selectByExample(UserleaveExample example);

	Userleave selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Userleave record,
			@Param("example") UserleaveExample example);

	int updateByExampleWithBLOBs(@Param("record") Userleave record,
			@Param("example") UserleaveExample example);

	int updateByExample(@Param("record") Userleave record,
			@Param("example") UserleaveExample example);

	int updateByPrimaryKeySelective(Userleave record);

	int updateByPrimaryKeyWithBLOBs(Userleave record);

	int updateByPrimaryKey(Userleave record);

	public Integer getcount(String keyWord);

	public List<Userleave> list(Map map);
}