package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.UserEq;
import com.sy.utils.DataRow;

public interface UserEqMapper extends BaseMapper<UserEq>{
	
	/**
	 * 查询关注人使用者列表
	 * @param u
	 * @return
	 */
	public List<DataRow> queryUserEqFollowUsersList(DataRow map)throws SQLException;
	/**
	 * 查询关注人观察者列表
	 * @param u
	 * @return
	 */
	public List<DataRow> queryUserEqFollowObserverList(DataRow map)throws SQLException;
	/**
	 * 通过用户ID查询所有信息唯一一条
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public UserEq queryUserEqLimit(int userId)throws SQLException;
	/**
	 * 根据ID删除数据
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);
    /**
     * 根据ID查询数据 
     * @param id
     * @return
     */
    UserEq selectByPrimaryKey(Integer id);
    /**
     * 根据使用者ID查询监护者
     * @param id
     * @return
     */
    UserEq queryUserEqAlias(Integer userId);
    /**
     * 更新数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UserEq record);
    /**
     * 更新数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserEq record);
    /**
     * 通过设备ID查询监护者唯一一条
     * @param eqId
     * @return
     */
    public UserEq getuserimei2(Integer  eqId) ;
    
    /**
     * 通过用户ID查询监护者
     * @param userId
     * @return
     */
    public UserEq selectguardianship(Integer userId);
    /**
     * 通过设备ID查询监护者 
     * @param eqId
     * @return
     */
    public UserEq ifguardianship(Integer eqId) ;
   
    /**
     * 通过设备ID查询使用者
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
    /**
     * 通过设备ID查询所有信息
     * @param eqId
     * @return
     */
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
	/**
	 * 通过用户ID查询所有信息
	 * @param userId
	 * @return
	 */
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
	 * 查询监护者
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
	public DataRow queryUserEq(DataRow map)throws SQLException;
	/**
	 * 根据监护者ID更新所有follow为0
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateUserEqFollow(DataRow map)throws SQLException;
	/**
	 * 根据设备ID和用户ID更新follow为1
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateUserEqFollowInfo(DataRow map)throws SQLException;
    
}