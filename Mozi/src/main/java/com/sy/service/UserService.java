package com.sy.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.sy.common.ResultData;
import com.sy.pojo.User;
import com.sy.utils.DataRow;
import com.sy.utils.PageModel;
import com.sy.vo.LoginReturn;
import com.sy.vo.Loginuser;
import com.sy.vo.Usermanagement;

public interface UserService  extends IService<User>{

	/**����û�
	 * @param u
	 * @return
	 */
	public boolean addUser(User u );
	
	/**判断该用户是否存在
	 * @param account
	 * @return
	 */
	public boolean ifUser(String account);
	/**
	 * 用户登陆
	 * @param data
	 * @return
	 */
	public ResultData<LoginReturn> landingUser(DataRow data,ResultData<LoginReturn> re)throws Exception;
	/**
	 *  添加使用者
	 * @param u
	 * @return
	 * @throws Exception 
	 */
	public ResultData<Loginuser>addUsermanagement(Usermanagement u,ResultData<Loginuser> re)throws Exception;
	
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
	public boolean updateUser(User u );
	
	/**修改用戶
	 * @param password
	 * @param newpassword
	 * @return
	 */
	public boolean updatepassword(String password,String newpassword,Integer id);
	
	/**分页获取用户数据
	 * @param pageNo
	 * @param keyword
	 * @return
	 */
	public PageModel<User>  getusersone(Integer pageNo,String keyword);
	
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
	 * 啊健写的 查询使用者的详情信息
	 * @param map
	 * @return
	 */
	public User queryUserInfo(Map map);
	
	/**通过账号获取用户数据
	 * @param account
	 * @return
	 */
	public User selectaccount(String account);
	/**
	 * 啊健写的 查询使用者的详情信息
	 * @param map
	 * @return
	 */
	public User queryHomepageUserInfo(Map<String,Object> map)throws Exception;

	User getUser(String imei);

	public Integer deleteUser(Integer userId);
	
	public Integer selectId(String imei);
	

}
