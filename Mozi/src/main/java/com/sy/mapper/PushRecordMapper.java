package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sy.pojo.PushRecord;

public interface PushRecordMapper {
	
	/**
	 * 插入预警历史记录
	 * @param pushRecord
	 * @return
	 * @throws SQLException
	 */
	public int insertPushRecord(PushRecord pushRecord)throws SQLException;
	
	
	/**
	 * 查询预警记录的各项总数
	 * @param map
	 * @return
	 */
	public Map<String,Object> queryPushRecordCount(Map<String,String> map)throws SQLException;
	/**
	 * 查询预警记录的记录数
	 * @param map
	 * @return
	 */
	public List<Map<String,String>> queryPushRecordList(Map<String,String> map)throws SQLException;
}
