package com.sy.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.sy.pojo.Positionig;

public interface PositionigService  extends IService<Positionig>{

	
	/**添加设备的定位信息
	 * @param p
	 * @return
	 */
	public boolean addPosition(Positionig p);
	
	/**
	 * 根据imei获取用户的最新定位数据
	 * @param imei
	 * @return
	 */
	public  Map<String,String>   selectimeiPositionig(String imei);
	
	public  String   selectimeiPositionigtcp(String imei);
}
