package com.sy.date.converter;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * descrption: 多数据源的选择
 * @author 
 *  AbstractRoutingDataSource中的抽象方法determineCurrentLookupKey是实现数据源的route的核心
 *               .需要重写该方法
 * @time 2017年10月22日
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.getDbType();//获取数据源  
	} 
	
	
	
 }