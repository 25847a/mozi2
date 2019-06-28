package com.sy.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.Group;

public interface GroupService {
	/**
	 * 创建朋友圈群
	 * @param group
	 * @param code
	 * @return
	 */
	public ResultBase insertGroup(Group group,CommonsMultipartFile image,ResultBase re)throws Exception;
	/**
	 * 首页查询社区
	 * @param group
	 * @param code
	 * @return
	 */
	public ResultData<List<Map<String, Object>>> queryGroupPage(Map<String,String> map,ResultData<List<Map<String, Object>>> re)throws Exception;
	/**
	 * 查询社圈群员
	 * @param group
	 * @param code
	 * @return
	 */
	public ResultData<List<Map<String,Object>>> queryRecommendGroup(Map<String,String> map,ResultData<List<Map<String,Object>>> re)throws Exception;
	/**
	 * 查询推荐社圈
	 * @param group
	 * @param code
	 * @return
	 */
	public ResultData<List<Map<String, Object>>> queryActiveInfo(Map<String,String> map,ResultData<List<Map<String, Object>>> re)throws Exception;
	/**
	 * 手机号码查询社圈
	 * @param group
	 * @param code
	 * @return
	 */
	public ResultData<Map<String, Object>> queryPhoneGroup(Map<String,String> map,ResultData<Map<String, Object>> re)throws Exception;
	/**
	 * 解散社群
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public ResultBase deleteDismiss(Map<String,Object> map,ResultBase re)throws Exception;
	
}
