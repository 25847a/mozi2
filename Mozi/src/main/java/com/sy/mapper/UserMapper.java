package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.User;
import com.sy.utils.DataRow;
import com.sy.vo.LoginReturn;

public interface UserMapper extends BaseMapper<User>{

    /**
     * 更改user的状态,逻辑删除
     * @param id
     * @return
     */
    int deleteUser(Integer id);


    User selectByPrimaryKey(Integer id);

    /**
     * 查询app账号
     * @param account
     * @return
     */
	public User ifUser(String account);

	public User landingUser(User u);

	public User getpassword(Map m);

	public List<Object> list(Map map);

	public List<User> phoenselectuser(String phone);
	
	public User  selectaccount( String account);
	/**
	 * 查询个人资料
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public DataRow queryUserInfo(DataRow map)throws SQLException;
	/**
	 *  查询个人主页使用者的个人信息-啊健 
	 * @param map
	 * @return
	 */
	public User queryHomepageUserInfo(Map<String,Object> map)throws Exception;
	/**
	 *  根据imei查询用户 
	 * @param map
	 * @return
	 */
	public User getUser(String imei);
	/**
	 *  给用户setImei号
	 * @param User.imei User.id
	 * @return 
	 */
	@Update("update user set imei = #{imei} where id = #{id}")
	public Integer updateImei(User user);
	/**
	 *   给用户set校准状态
	 * @param User.calibration User.id
	 * @return
	 */ 
	@Update("update user set  calibration = #{calibration} where id = #{id}")
	public Integer updateCalibration(User user);
	
	/**
	 * 设置手机号码
	 * @param User.phone User.id
	 */
	@Update("update user set phone = #{phone} where id = #{id}")
	void setPhone(User m);

	/**
	 * 健康数据上传时间(分钟) 2 3 5 8 15
	 * @param User.jfdataUpdateTime User.id
	 */
	@Update("update user set jfdataUpdateTime = #{jfdataUpdateTime} where id = #{id}")
	void jfdataUpdateTime(User user);
	/**
	 * 根据ID查询用户信息
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	@Select("SELECT * FROM user  where id = #{userId} AND isDelete=0")
	public User queryIdUser(Map<String,String> map)throws SQLException;
	/**
	 * 根据ID查询用户信息
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	@Select("SELECT * FROM user  where id = #{userId} AND isDelete=0")
	public User queryIdUserInfo(Integer userId)throws SQLException;
	/**
	 * 个人中心页面数据
	 * @param data
	 * @return
	 * @throws SQLException
	 */
	public LoginReturn queryPersonalCenter(Integer userId)throws SQLException;
	/**
	 * 点击卡片查询
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public DataRow queryUserData(DataRow map)throws SQLException;
	/**
	 * 通过使用者ID查询首页需要的数据
	 * @param map
	 * @return
	 */
	public DataRow queryUsersInfo(DataRow map);
}