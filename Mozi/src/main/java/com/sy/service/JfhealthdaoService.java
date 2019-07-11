package com.sy.service;

import com.baomidou.mybatisplus.service.IService;
import com.sy.common.ResultBase;
import com.sy.pojo.Jfhealthdao;

public interface JfhealthdaoService extends IService<Jfhealthdao>{
	
	public boolean addJfhealthdao(Jfhealthdao j);
	
	public Jfhealthdao  selelctJfhealthdao(String  imei);
	/**
	 * 阿健-查询校验表
	 * @param imei
	 * @param account
	 * @return
	 */
	public Jfhealthdao  JfhealthdaoInfo(String  imei,String account);
	
	public Jfhealthdao  getjfhealthdao(String phone);
	
	public void  updatajf(Jfhealthdao bean);
	/**
	 * 修改人工学习
	 * @return
	 */
	public ResultBase updateJfhealthdao(Jfhealthdao jfhealthdao,ResultBase re)throws Exception;
	
	public void  delectjfhealthdao(String phone);
	
}
