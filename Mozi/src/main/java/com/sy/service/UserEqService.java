package com.sy.service;

import java.util.List;
import com.baomidou.mybatisplus.service.IService;
import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.User;
import com.sy.pojo.UserEq;
import com.sy.utils.DataRow;
import com.sy.vo.Userdata;

public interface UserEqService extends IService<UserEq>{
	
	
	/**
	 * 关注列表
	 * @param u
	 * @return
	 */
	public ResultData<List<DataRow>> queryUserEqFollowList(DataRow map,ResultData<List<DataRow>> re)throws Exception;
	/**
	 *  更改默认使用者
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	public ResultBase updateUserFollow(DataRow map,ResultBase re)throws Exception;

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
	 * @param imei
	 * @param userId
	 * @return
	 */
	public boolean deleteequse(Integer eqId, Integer userId, Integer typeof,Integer alisa)throws Exception;

	/**
	 * 根据用户id获取用户所有相关联设备信息
	 * 
	 * @param userid
	 * @return
	 */
	public List<UserEq> selectuserqe(Integer userid);
	/**
	 * 获取用户所对应的设备状态
	 * 
	 * @param userid
	 * @return
	 */
	public ResultData<List<Userdata>> selectuserdata(DataRow map,ResultData<List<Userdata>> re)throws Exception;
	/**
	 * 取消使用者
	 */
	public boolean deleteguardian(String imei,Integer eqId, Integer userId,Integer alias)throws Exception;
	/**
	 * 删除设备操作者
	 * @param map
	 * @return
	 */
	public ResultBase deleteguardian111111(DataRow map,ResultBase re)throws Exception;
	
	public boolean deleteequsetype(Integer eqId, Integer userId,Integer typeof)throws Exception;
	
	public boolean deleteEqUser(Integer id);

	UserEq selectUserEq(Integer eqId, Integer mid);

	
}
