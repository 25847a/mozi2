package com.sy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sy.common.ResultData;
import com.sy.mapper.MessageMapper;
import com.sy.pojo.Message;
import com.sy.service.MessageService;
import com.sy.utils.DataRow;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper,Message> implements MessageService {

	@Autowired
	MessageMapper messageMapper;
	
	
	
	/**
	 *  消息中心
	 * @param u
	 * @return
	 * @throws Exception 
	 */
	@Override
	public ResultData<DataRow> queryMessageCenter(DataRow map, ResultData<DataRow> re) throws Exception {
		List<Message> message =messageMapper.queryMessageCenter(map);
		if(message!=null){
			re.setCode(200);
			re.setData(message);
			re.setMessage("获取消息成功");
		}else{
			re.setCode(350);
			re.setMessage("没有消息展示");
		}
		
		return re;
		
	}

}
