package com.sy.date.converter;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置主从数据库
 * @author Administrator
 *
 */
public class DataSourceType {
	//主数据库
	public static final String MASTER = "masterDataSource";
	//从数据库
	public static final String SLAVE = "slaveDataSource";
	
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("1", null);
		System.out.println(map.get("1"));
	}
	
}
