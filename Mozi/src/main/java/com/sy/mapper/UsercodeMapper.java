package com.sy.mapper;

import java.sql.SQLException;

import com.sy.pojo.Usercode;

public interface UsercodeMapper {


	int deleteByPrimaryKey(Integer id);

	int insert(Usercode record);

	int insertSelective(Usercode record);

	Usercode selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Usercode record);

	int updateByPrimaryKey(Usercode record);

	public Usercode ifusercodeczai(Usercode c);

	public Usercode ifusercode(Usercode code);
	/**
	 *  删除获取验证码
	 * @param phoen
	 * @return
	 * @throws SQLException
	 */
	public int deleteUsercode(int phoen)throws SQLException;
}