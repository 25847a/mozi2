package com.sy.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sy.mapper.ChatMapper;
import com.sy.pojo.Chat;
import com.sy.service.ChatService;
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService{
	@Autowired
	private ChatMapper chatmapper;
	@Override
	public boolean addChat(Chat c) {
		Integer num = chatmapper.insert(c);
		if(num !=0) {
			return true;
		}else {
			return false;
		}
		
	}
	@Override
	public List<Chat> selectChat(String imei) throws Exception {
		return chatmapper.selectChat(imei);
	}
	
}
