package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.UserEq;

public interface UserEqMapper extends BaseMapper<UserEq>{
	
	/**
	 * 查询关注人使用者列表
	 * @param u
	 * @return
	 */
	public List<Map<String, Object>> queryUserEqFollowUsersList(Map<String,Object> map)throws SQLException;
	/**
	 * 查询关注人观察者列表
	 * @param u
	 * @return
	 */
	public List<Map<String, Object>> queryUserEqFollowObserverList(Map<String,Object> map)throws SQLException;
	


    int deleteByPrimaryKey(Integer id);

    Integer insert(UserEq record);

    int insertSelective(UserEq record);


    UserEq selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(UserEq record);

    int updateByPrimaryKey(UserEq record);
    
    public UserEq getuserimei2(Integer  eqId) ;
    
    public Integer getimei(String imei);
    
    public Integer geteqid(Integer userId);
    
    public UserEq selectguardianship(Integer userId);
    
    /**
     * 根据设备eqid 获取 监护者的UserEq 
     * @param eqId
     * @return
     */
    public UserEq ifguardianship(Integer eqId) ;
   
    /**
     * 根据设备eqid 查询使用者
     * @param eqId
     * @return
     */
    public UserEq ifuse(Integer eqId);
    
    /**
     * 查询观察者 
     * @param eqId 和 userId
     * @return
     */
    public UserEq ifObserved(UserEq userEq);
    //
    public List<UserEq> selelctequser(Integer eqId);
    
    /**
     * 取消观察者
     * @param u eqId 和 userId赋值就好
     */
	public void deleteequse(UserEq u );
	
	
	/**
	 * 根据eqid userid typeof 匹配删除
	 * @param u
	 */
	public void deleteequsetype(UserEq u );
	
	public List<UserEq> selectuserqe( @Param("userId") Integer userId);

	/**
	 * 根据使用者id获取设备id
	 * 
	 * @param imei
	 * @return
	 */
	public Integer geteqiduse(Integer userId);
	
	/**
	 * 删除所有该设备号的
	 * @param eqId
	 */
	public Integer  deleteguardian( Integer eqId);
	
	/**
	 * 删除所有该用户id的
	 * @param eqId
	 */
	public void  deleteuserid( Integer userId);
	/**
	 * 查询有没有是否为监护者
	 * @param userId
	 * @return
	 */
	public UserEq  selectGuardian(@Param("eqId") Integer eqId,@Param("mid")Integer mid);
	/**
	 * 通过监护者ID查询出使用者ID
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,String>> queryUserIdUserEq(Map<String,String> map)throws SQLException;
	/**
	 * 通过监护者ID查询出使用者ID
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public Map<String,Object> queryUserEq(Map<String,Object> map)throws SQLException;
    
}