package com.sy.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.mapper.UseravatarMapper;
import com.sy.pojo.Useravatar;
import com.sy.service.UseravatarService;
@Service
public class UseravatarServiceImpl implements UseravatarService{
	@Autowired
	private UseravatarMapper useravatarMapper;

	@Override
	public boolean adduseravatar(Useravatar ua) {
		ua.setCratetime(new Date());
		Integer num =useravatarMapper.insertSelective(ua);
		if(num !=0){
			return true;
		}else{
			return false;
		}
	
	}

	@Override
	public Useravatar selectavartar() {
		// TODO Auto-generated method stub
		return useravatarMapper.selectavartar();
	}
	
	
}
