package com.sy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.Message;
import com.sy.service.MessageService;
import com.sy.utils.DataRow;

/**用户发送信息接口
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "message", method = RequestMethod.POST)
public class MessageController {
	
	private final static Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	MessageService messageService;
	
	/**
	 * 消息中心
	 * @param u
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("queryMessageCenter")
	@ResponseBody
	public ResultData<DataRow> queryMessageCenter(@RequestBody DataRow map){
		ResultData<DataRow> re = new ResultData<DataRow>();
		try{
			re=messageService.queryMessageCenter(map,re);
		}catch (Exception e) {
			logger.error("MessageController>>>>>>>>>>>>>>>>queryMessageCenter",e);
		}
		return re;
	}
	/**
	 * 修改为已读状态
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("updateMessageRead")
	@ResponseBody
	public ResultBase updateMessageRead(@RequestBody Message message){
		ResultBase re = new ResultBase();
		try{
			re=messageService.updateMessageRead(message,re);
		}catch (Exception e) {
			logger.error("MessageController>>>>>>>>>>>>>>>>updateMessageRead",e);
		}
		return re;
	}
	
}
