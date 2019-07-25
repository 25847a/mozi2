package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sy.pojo.GroupRelation;

public interface GroupRelationMapper {
	/**
	 * 查询社区群成员
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,String>> queryGroupRelationInfo(Map<String,String> map)throws SQLException;
	/**
	 *  插入到群主关联群
	 * @param relation
	 * @return
	 * @throws SQLException
	 */
	public int insertGroupRelation(GroupRelation relation)throws SQLException;
	/**
	 * 查询活跃排行榜人数
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> queryActiveInfo(Map<String,String> map)throws SQLException;
	/**
	 * 查询朋友圈群组关联
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public GroupRelation queryGroupRelation(@Param(value="groupId") Long groupId,@Param(value="relationUserId") Integer relationUserId)throws SQLException;
	/**
	 * 删除朋友圈群组关联
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int deletegroupRelationId(long groupId)throws SQLException;
	/**
	 * 删除朋友圈群组关联
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int deletegroupRelation(Map<String, Object> map)throws SQLException;
	/**
	 * 删除朋友圈群组关联
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int deletegroupRelationInfo(GroupRelation relation)throws SQLException;
}