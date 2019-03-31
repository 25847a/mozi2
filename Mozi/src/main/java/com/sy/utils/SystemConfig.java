package com.sy.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统配置对象
 * 
 * @className SystemConfig.java
 * @description: TODO
 * @author ddm
 * @date 2017年7月26日-下午7:04:05
 */
public final class SystemConfig {

	private SystemConfig() {
	}

	private static Config config;

	/**
	 * 获取系统配置的值
	 * 
	 * @title null * * * * * * * * * *
	 * @author ddm
	 * @date 2017年7月26日-下午7:04:51
	 * @param
	 * @retrun {"result":"1"} #ok {"result":"0"} #error
	 */
	public static String getValue(String key) {
		if (null == config) {
			config = new Config("systemConfig.properties");
		}
		return config.getString(key);
	}
	
	/**
	 * 获取用户登录的 ip
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
