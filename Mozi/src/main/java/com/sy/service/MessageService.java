package com.sy.service;


import com.baomidou.mybatisplus.service.IService;
import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.Message;
import com.sy.utils.DataRow;

/**
 * 
 * @author Administrator
 *
 */
public interface MessageService extends IService<Message>{
	
	/**
	 *  消息中心
	 * @param u
	 * @return
	 * @throws Exception 
	 */
	public ResultData<DataRow> queryMessageCenter(DataRow map,ResultData<DataRow> re)throws Exception;
	/**
	 * 修改为已读状态
	 * @return
	 * @throws Exception 
	 */
	public ResultBase updateMessageRead(Message message,ResultBase re)throws Exception;
	
}
