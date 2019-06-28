package com.sy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sy.mapper.ConfigMapper;
import com.sy.pojo.Config;
import com.sy.service.ConfigService;
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService{
	
	@Autowired
	ConfigMapper configMapper;
	/**
	 * 通过ID查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Config queryConfigId(Integer id) throws Exception {
		return configMapper.queryConfigId(id);
	}
	/**
	 * 查询系统配置表集合
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Config> queryConfigList(String type,String lable) throws Exception {
		return configMapper.queryConfigList(type,lable);
	}
	/**
	 * 查询系统配置表信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Config queryConfigInfo(String type, String lable) throws Exception {
		return configMapper.queryConfigInfo(type,lable);
	}

}
