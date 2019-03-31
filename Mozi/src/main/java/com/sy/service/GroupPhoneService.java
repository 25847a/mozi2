package com.sy.service;

import java.util.List;
import java.util.Map;

import com.sy.pojo.GroupPhone;

public interface GroupPhoneService {

	/**
	 * 查询激活列表
	 */
	List<GroupPhone> selectActivateList();
	
	/**
	 * 查询单个手机号语音,流量和余额
	 */
	public GroupPhone selectOne(String phone);
	
	/**
	 * 更新激活状态
	 */
	public void updateStatus();
	
	/**
	 * 查询未激活的号码
	 * return 号码列表
	 */
	public List<String> selectNoActivateAllPhone();

	/**
	 * 批量更新号码语音使用情况
	 * @return
	 */
	public int batchUpdate();
	
	/**
	 * 查询语音超出的号码
	 * @return
	 */
	public List<GroupPhone> selectExtraPkgVoice();
	
	/**
	 * 设置为停机
	 * @param list
	 * @return
	 */
	public int batchUpdateSuspended(List<GroupPhone> list);

	/**
	 * 更新数据 
	 *   主要更新字段:
	 *	 	balance 余额   
	 * 		usedMoney 已经使用的金额 
	 * 		lastCalculateVoice 已经扣费的超出语音 
	 * 		suspended 停开机状态  
	 */
	int updateList(List<GroupPhone> updatelist);
	
	public void suspended(String phone);

}
