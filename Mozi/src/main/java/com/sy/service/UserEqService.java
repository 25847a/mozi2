package com.sy.service;

import java.util.List;
import java.util.Map;

import com.sy.pojo.User;
import com.sy.pojo.UserEq;
import com.sy.vo.Userqedata;

public interface UserEqService {

	/**
	 * 根据设备（eq_id）id获取监护者
	 * 
	 * @param id
	 * @return
	 */
	public User getuserimei0(Integer id);

	/**
	 * 根据imei获取使用者id
	 * 
	 * @param imei
	 * @return
	 */
	public Integer getimei(String imei);

	/**
	 * 根据用户id获取用户的设备id号
	 * 
	 * @param userid
	 * @return
	 */
	public Integer geteqid(Integer userid);

	/**
	 * 添加观察者
	 * 
	 * @param u
	 * @return
	 */
	public boolean addUserEq(UserEq u);

	/**
	 * userId获取监护者数据
	 * 
	 * @param userId
	 * @return
	 */
	//public UserEq selectguardianship(Integer userId);

	/**
	 * 判断该设备是否有监护者
	 * 
	 * @param eqId
	 * @return
	 */
	public boolean ifguardianship(Integer eqId);

	/**
	 * 判断该设备是否有使用者
	 * 
	 * @param eqId
	 * @return
	 */
	public boolean ifuse(Integer eqId);

	/**
	 * 判断该观察者是否存在
	 * 
	 * @param u
	 * @return
	 */
	public boolean ifObserved(UserEq u);

	/**
	 * 根据设备id获取设备操作人员数据（包括，监护者，操作者，使用者）
	 * 
	 * @param eqId
	 * @return
	 */
	public List<User> selelctequser(Integer eqId);

	/**
	 * 取消观察者
	 * 
	 * @param imei
	 * @param userId
	 * @return
	 */
	public boolean deleteequse(Integer eqId, Integer userId, Integer typeof,Integer alisa);

	/**
	 * 根据用户id获取用户所有相关联设备信息
	 * 
	 * @param userid
	 * @return
	 */
	public List<UserEq> selectuserqe(Integer userid);

	/**
	 * 返回当前imei的所有相关数据
	 * 
	 * @param imei
	 * @return
	 */
	//public Userqedata selectdata(String imei);

	/**
	 * 获取用户所对应的设备状态
	 * 
	 * @param userid
	 * @return
	 */
	public List<Map<String, Object>> selectuserdata(Integer userid);
	
	/**根据imei获取用户健康数据
	 * @param imei
	 * @return
	 */
	//public Map<String, Object>  userhealthdata(Integer imei);

	/**
	 * 获取用户所对应的设备数据还有使用信息
	 * 
	 * @param userid
	 * @return
	 */
	//public List<Map<String, Object>> Userequipmentdata(Integer userid);

	/**
	 * 获取单独一个使用者数据
	 * 
	 * @return
	 */
	public Map<String, Object> userdata(String imei);
	
	public boolean deleteguardian(String imei,Integer eqId, Integer userId,Integer alias)throws Exception;
	
	
	public boolean deleteequsetype(Integer eqId, Integer userId,Integer typeof);
	
	/**删除
	 * @param id
	 * @return
	 */
	public boolean deleteusereq(Integer id);
	public boolean deleteEqUser(Integer id);

	UserEq selectUserEq(Integer eqId, Integer mid);

	
}
