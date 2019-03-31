package com.sy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.mapper.VersionhistoryMapper;
import com.sy.pojo.Versionhistory;
import com.sy.service.VersionhistoryService;
@Service
public class VersionhistoryServiceImpl implements VersionhistoryService{
	@Autowired
	private VersionhistoryMapper mapper;

	@Override
	public boolean addVersionhistory(Versionhistory v) {
		Integer num = mapper.insert(v);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteversionhistory(Integer id) {
		Integer num = mapper.deleteByPrimaryKey(id);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Versionhistory> selectVersionhistory() {
		List<Versionhistory> ls =	mapper.selectByExample(null);
		return ls;
	}

	@Override
	public Versionhistory seleversiontory(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectVersion(id);
	}

}
