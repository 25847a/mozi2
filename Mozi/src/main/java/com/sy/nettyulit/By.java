package com.sy.nettyulit;

import java.util.ArrayList;
import java.util.List;

public class By {
	
	/**
	
	* @Title: getby
	
	* @Description: TODO(这里用一句话描述这个方法的作用)
	
	* @param @return    设定文件
	
	* @return List<String>    返回类型
	
	* @throws
	* 需要登录访问的接口
	
	*/
	public static List<String> getby() {
		List<String> list = new ArrayList<String>();
		
		return list;
	}

	/**
	
	* @Title: getbt
	
	* @Description: TODO(不需要登录就能登录的接口)
	
	* @param @return    设定文件
	
	* @return List<String>    返回类型
	
	* @throws
	
	*/
	public static List<String> getbt() {
		List<String> list = new ArrayList<String>();
		list.add("maveweb/index");
		list.add("send");
		list.add("server");
		list.add("user/Register");
		list.add("clent");
		list.add("report/geteports");
		return list;
	}
}
