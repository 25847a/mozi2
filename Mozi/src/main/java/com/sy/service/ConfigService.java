package com.sy.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.sy.pojo.Config;

public interface ConfigService extends IService<Config>{
	/**
	 * 通过ID查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Config queryConfigId(Integer id)throws Exception;
	/**
	 * 查询系统配置表集合
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Config> queryConfigList(String type,String lable)throws Exception;
	/**
	 * 查询系统配置表信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Config queryConfigInfo(String type,String lable)throws Exception;
}
