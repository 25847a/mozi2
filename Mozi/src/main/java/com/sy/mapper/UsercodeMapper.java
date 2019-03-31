package com.sy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.Usercode;
import com.sy.pojo.UsercodeExample;

public interface UsercodeMapper {
	int countByExample(UsercodeExample example);

	int deleteByExample(UsercodeExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Usercode record);

	int insertSelective(Usercode record);

	List<Usercode> selectByExample(UsercodeExample example);

	Usercode selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Usercode record,
			@Param("example") UsercodeExample example);

	int updateByExample(@Param("record") Usercode record,
			@Param("example") UsercodeExample example);

	int updateByPrimaryKeySelective(Usercode record);

	int updateByPrimaryKey(Usercode record);

	public Usercode ifusercodeczai(Usercode c);

	public Usercode ifusercode(Usercode code);
}