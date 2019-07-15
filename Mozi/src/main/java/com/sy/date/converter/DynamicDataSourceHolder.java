package com.sy.date.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicDataSourceHolder {
	/**
	 * 注意：数据源标识保存在线程变量中，避免多线程操作数据源时互相干扰
	 */
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	private final static Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);
	
	 /**
     * 提供给AOP去设置当前的线程的数据源的信息
     * 切换数据库
     * @param dbType 数据库名-配置文件中一致
     */
	public static void setDbType(String dbType) {
		try {
			contextHolder.set(dbType);
			
		} catch (Exception e) {
			logger.error("DynamicDataSourceHolder>>>>>>>>>>>>>>>>>>setDbType",e);
		}
	}
	 /**
     * 提供给AbstractRoutingDataSource的实现类，通过key选择数据源
     * @return java.lang.String
     */
	public static  String getDbType() {
		String dataSourse  =  contextHolder.get();
		if(null == dataSourse){

			DynamicDataSourceHolder.setDbType(DataSourceType.MASTER);//获取主数据库
        }
        return  contextHolder.get();
	}
	/**
	 * 删除数据源
	 */
	public static void clearDataSource() {
		contextHolder.remove();
	}

}