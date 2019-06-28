package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sy.pojo.Group;

public interface GroupMapper {
	/**
	 * 创建朋友圈群
	 * @param group
	 * @return
	 * @throws SQLException
	 */
	public Long insertGroup(Group group)throws SQLException;
	/**
	 * 通过监护者查询有社区群列表 
	 * @return
	 */
	public List<Map<String,String>> queryGroupAliasList(Map<String,String> map)throws SQLException;
	/**
	 * 通过使用者查询有社区群列表
	 * @return
	 */
	public List<Map<String,Object>> queryGroupUserIdList(Map<String,String> map)throws SQLException;
	/**
	 * 通过监护者查询有社区群信息
	 * @return
	 */
	public Map<String,String> queryGroupAliasInfo(Map<String,String> map)throws SQLException;
	/**
	 * 推荐的社圈
	 * @return
	 */
	public List<Map<String,String>> queryRecommendInfo()throws SQLException;
	/**
	 * 查询推荐社圈
	 * @return
	 */
	public Map<String,Object> queryPhoneGroup(Map<String, String> map)throws SQLException;
	/**
	 * 更新入群人数
	 * @param groupId
	 * @return
	 * @throws SQLException
	 */
	public int updateCountGroup(Long groupId)throws SQLException;
	/**
	 * 通过ID查询群信息
	 * @param groupId
	 * @return
	 * @throws SQLException
	 */
	public Group queryGroupInfo(Long groupId)throws SQLException;
	/**
	 * 删除群
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int deleteGroup(Map<String, Object> map)throws SQLException;
}