package com.sy.service;


import com.baomidou.mybatisplus.service.IService;
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
	
}
