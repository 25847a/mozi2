package com.sy.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
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
	public void  updateByPhone(Map map);
	
	public void  delectjfhealthdao(String phone);
	
}
