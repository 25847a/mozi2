package com.sy.mapper;

import java.sql.SQLException;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Member;
public interface MemberMapper extends BaseMapper<Member>{
	/**
	 * 获取会员制度信息
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public Member queryMemberInfo(int userId)throws SQLException;
	/**
	 * 删除会员制度信息
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public int deleteMember(int userId)throws SQLException;
}
