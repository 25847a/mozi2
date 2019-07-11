package com.sy.mapper;
import java.sql.SQLException;
import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Message;
import com.sy.utils.DataRow;



public interface MessageMapper extends BaseMapper<Message>{
	
	/**
	 * 消息中心
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<DataRow> queryMessageCenter(DataRow map)throws SQLException;
	/**
	 * 消息中心总数
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int queryMessageCount(int alias)throws SQLException;
}
