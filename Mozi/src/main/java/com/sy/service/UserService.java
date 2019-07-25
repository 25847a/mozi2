package com.sy.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.baomidou.mybatisplus.service.IService;
import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.User;
import com.sy.utils.DataRow;
import com.sy.vo.LoginReturn;
import com.sy.vo.Loginuse;
import com.sy.vo.Usermanagement;

public interface UserService  extends IService<User>{

	/**判断该用户是否存在
	 * @param account
	 * @return
	 */
	public boolean ifUser(String account);
	/**
	 * 注册用户
	 * @param data
	 * @return
	 */
	public ResultData<Loginuse> addUser(User user,ResultData<Loginuse> re)throws Exception;
	/**
	 * 注册用户
	 * @param data
	 * @return
	 */
	public ResultData<Loginuse> addUser111(User user,ResultData<Loginuse> re)throws Exception;
	/**
	 * 用户登陆
	 * @param data
	 * @return
	 */
	public ResultData<LoginReturn> landingUser(DataRow data,ResultData<LoginReturn> re)throws Exception;
	/**
	 * 点击卡片接口
	 * @return
	 */
	public ResultData<DataRow> userdata(DataRow map,ResultData<DataRow> re)throws Exception;
	/**
	 * 查看个人资料
	 * @return
	 */
	public ResultData<DataRow> queryUserInfo(DataRow map,ResultData<DataRow> re)throws Exception;
	/**
	 *  添加使用者
	 * @param u
	 * @return
	 * @throws Exception 
	 */
	public ResultBase addUsermanagement(Usermanagement u,ResultBase re)throws Exception;
	/**
	 * 更新头像
	 * @param avatar
	 * @param id
	 * @return
	 */
	public ResultData<String> updateavatar(CommonsMultipartFile avatar ,Integer id,ResultData<String> re)throws Exception;
	/**头像上传
	 * @param avatar
	 * @param id
	 * @return
	 */
	public boolean uploadavatar(String avatar ,Integer id);
	
	/**根据id获取用户数据
	 * @return
	 */
	public User getUser(Integer id);
	
	/**修改用户数据
	 * @param u
	 * @return
	 */
	public ResultBase updateUser(User u,ResultBase re)throws Exception;
	/**
	 * 更新监护者用户信息
	 * @param u
	 * @return
	 */
	public ResultBase updateAliasUser(User u,ResultBase re)throws Exception;
	/**修改用戶
	 * @param password
	 * @param newpassword
	 * @return
	 */
	public boolean updatepassword(String password,String newpassword,Integer id);
	
	/**手机号码
	 * @param phone
	 */
	public Integer sendSMS(String phone);
	
	/**
	 * 根据手机号码获取用户信息
	 * @param phoen
	 * @return
	 */
	public  User  phoenselectuser(String phoen);
	/**
	 * 通过账号获取用户数据
	 * @param account
	 * @return
	 */
	public User selectaccount(String account);

	User getUser(String imei);
	/**
	 * 更改用户状态
	 * @param userId
	 * @return
	 */
	public Integer deleteUser(Integer userId);
	
	public Integer selectId(String imei);
	/**
	 * 个人中心数据
	 * @param map
	 * @return
	 */
	public ResultData<DataRow> queryPersonalCenter(DataRow map,ResultData<DataRow> re)throws Exception;
}
