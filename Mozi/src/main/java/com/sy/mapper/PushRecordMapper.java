package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.PushRecord;
import com.sy.utils.DataRow;

public interface PushRecordMapper extends BaseMapper<PushRecord>{
	
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
	/**
	 * 查询预警记录的list集合
	 * @param map
	 * @return
	 */
	public List<PushRecord> queryPushRecordInfoList(int userId)throws SQLException;
	/**
	 * 删除预警记录
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public int deletePushrecord(int userId)throws SQLException;
}
