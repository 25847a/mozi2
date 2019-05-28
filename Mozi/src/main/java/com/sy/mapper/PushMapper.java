package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sy.pojo.Push;
public interface PushMapper {
	/**
	 * 查询推送表
	 * @return
	 * @throws SQLException
	 */
	public Push selectPush(Map<String,Object> map)throws SQLException;
	/**
	 * 修改推送表
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	public int updatePushById(Map<String,Object> map) throws SQLException;
	/**
	 * 查询推送
	 * @return
	 * @throws SQLException
	 */
	public Push selectPushInfo(Map<String,Object> map)throws SQLException;
	/**
	 * 取消关注删除用户需要删除预警值
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public int deletePushInfo(Integer userId)throws SQLException;
	public int insertPush(Map<String,Object> map)throws SQLException;
	public int addPush(Push push)throws SQLException;
	public int addPushMap(Map<String,Object> map)throws SQLException;
	
	@Select("select * from push where userId = #{userId}")
	public List<Push> selectPushList(Integer userId);
	
	//@Select("select * from push where userId = #{userId} and alias = #{alias}")
	//public Push selectPushInfo(@Param("userId")Integer userId,@Param("alias")Integer alias);
	
	@Delete("delete from push where userId = #{userId} and alias = #{alias}")
	public Integer removePush(@Param("userId")Integer userId,@Param("alias")Integer alias);
	
	@Delete("delete from push where userId = #{userId}")
	public Integer removePushToUserId(@Param("userId")Integer userId);
}
