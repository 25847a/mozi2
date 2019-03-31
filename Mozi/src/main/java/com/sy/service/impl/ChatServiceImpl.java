package com.sy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.mapper.ChatMapper;
import com.sy.pojo.Chat;
import com.sy.service.ChatService;
@Service
public class ChatServiceImpl implements ChatService{
	@Autowired
	private ChatMapper chatmapper;
	@Override
	public boolean addChat(Chat c) {
		Integer num = chatmapper.insertSelective(c);
		if(num !=0) {
			return true;
		}else {
			return false;
		}
		
	}
	@Override
	public List<Chat> selectChat(String imei) {
		Map<String,String> m = new HashMap<String,String>();
		m.put("imei", imei);
		return chatmapper.selectChat(m);
	}
	
}
