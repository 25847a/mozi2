package com.sy.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.mapper.ExtendMapper;
import com.sy.pojo.Extend;
import com.sy.service.ExtendService;
@Service
public class ExtendServiceImpl implements ExtendService{
	
	@Autowired
	ExtendMapper systemMapper;
	/**
	 * 查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Extend selectExtend(Integer id) throws Exception {
		Extend extend = systemMapper.selectExtend(id);
		return extend;
	}
	/**
	 * 修改
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateExtendById(Map<String, Object> map) throws Exception {
		int row = systemMapper.updateExtendById(map);
		return row;
	}

}
