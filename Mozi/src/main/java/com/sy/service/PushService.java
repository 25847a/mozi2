package com.sy.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.sy.common.ResultBase;
import com.sy.pojo.JfhealthNew;
import com.sy.pojo.Push;
import com.sy.utils.DataRow;

/**
 * 
 * @author Administrator
 *
 */
public interface PushService extends IService<Push>{
	/**
	 * 查询推送表
	 * @return
	 * @throws SQLException
	 */
	public Push selectPush(Map<String,Object> map)throws SQLException, ParseException;
	/**
	 * 修改推送表
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	public int updatePushById(Map<String,Object> map) throws SQLException;
	/**
	 * 推送消息
	 * @param map
	 * @throws SQLException
	 */
	public String queryPushNews(Map<String,Object> map,JfhealthNew jfhealthNew)throws SQLException;
	/**
	 * 测试推送接口
	 * @return
	 */
	public ResultBase testPush(DataRow map,ResultBase re)throws Exception;
	
}
