package com.sy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.mapper.ClockMapper;
import com.sy.pojo.Clock;
import com.sy.service.ClockService;
@Service
public class ClockServiceImpl implements ClockService{
	@Autowired
	private ClockMapper clockMapper;

	@Override
	public boolean addClock(Clock c) {
		c.setCratetime(new Date());
		Integer num = clockMapper.insertSelective(c);
		if(num !=0){
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean deleteClock(Integer id) {
		
		Integer num = clockMapper.deleteByPrimaryKey(id);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean updateClock(Clock c) {
		c.setCratetime(new Date());
		Integer num = clockMapper.updateByPrimaryKeySelective(c);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Clock> selectclocks(String imei) {
		
		return clockMapper.selectclocks(imei);
	}
	
	
	
	

}
