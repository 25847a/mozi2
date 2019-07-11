package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;
import com.sy.pojo.PushRecord;
import com.sy.utils.DataRow;

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
	public DataRow queryPushRecordCount(DataRow map)throws SQLException;
	/**
	 * 查询预警记录的记录数
	 * @param map
	 * @return
	 */
	public List<DataRow> queryPushRecordList(DataRow map)throws SQLException;
}
