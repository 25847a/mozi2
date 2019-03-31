package com.sy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sy.pojo.UserEq;
import com.sy.pojo.UserEqExample;

public interface UserEqMapper {
    int countByExample(UserEqExample example);

    int deleteByExample(UserEqExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserEq record);

    int insertSelective(UserEq record);

    List<UserEq> selectByExample(UserEqExample example);

    UserEq selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserEq record, @Param("example") UserEqExample example);

    int updateByExample(@Param("record") UserEq record, @Param("example") UserEqExample example);

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
    
}