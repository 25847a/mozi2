package com.sy.service;

import java.sql.SQLException;
import com.sy.common.ResultData;
import com.sy.pojo.PushRecord;
import com.sy.utils.DataRow;

/**
 * 
 * @author Administrator
 *
 */
public interface PushRecordService {
	
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
	public ResultData<DataRow> queryPushRecordInfo(DataRow map,ResultData<DataRow> re)throws Exception;
}
