package com.sy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.mapper.UsercodeMapper;
import com.sy.pojo.Usercode;
import com.sy.service.UsercodeService;
@Service
public class UsercodeServiceImpl implements UsercodeService{

	@Autowired
	private UsercodeMapper usercodeMapper;

	@Override
	public boolean addUsercode(Usercode code) {
		Usercode olcode =usercodeMapper.ifusercodeczai(code);
		if(olcode ==null){
			usercodeMapper.insertSelective(code);
		}else{
			olcode.setCode(code.getCode());
			
			usercodeMapper.updateByPrimaryKeySelective(olcode);
		}
		return true;
	}

	@Override
	public boolean ifusercode(Usercode code) {
		Usercode usecode = usercodeMapper.ifusercode(code);
		if(usecode !=null){
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
}
