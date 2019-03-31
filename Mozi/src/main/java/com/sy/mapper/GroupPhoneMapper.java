package com.sy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sy.pojo.GroupPhone;

public interface GroupPhoneMapper {
 
	/**
	 * 查询未激活的号码
	 * @return
	 */
	@Select( "select phone from group_phone where status != 1 " )
	public List<String> selectNoActivateAllPhone();
	
	/**
	 * 查询单个号码
	 * @param phone
	 * @return GroupPhone
	 */
	@Select( "select * from group_phone where  phone=#{phone}")
	public GroupPhone selectPhone(String phone);
	
	/**
	 * 更新余额
	 * @param groupPhone
	 */
	@Update("Update group_phone set balance=#{balance}, totalMoney = #{totalMoney} where phone=#{phone}" )
	public void updateBalance(GroupPhone groupPhone);
	
	/**
	 * 更新为激活状态
	 * @param array
	 */
	@Update("<script>" +
            "Update group_phone set status = 1  where phone in\n" +
            "<foreach collection=\"list\" open=\"(\" close=\")\" separator=\",\" item=\"array\">\n" +
            "    #{array}\n" +
            "</foreach> and status != 1"+
            "</script>")
	public void updateStatus(List<String> array);

	
	/**
	 * 查询已经激活的列表
	 * @return
	 */
	@Select( "select * from group_phone where status = 1 " )
	public List<GroupPhone> selectActivateList();
	
	/**
	 * 批量更新
	 * @param list
	 * @return
	 */
	int batchUpdate(List<GroupPhone> list);

	/**
	 * 查询语音超出的
	 * @return
	 */
	@Select( "select * from group_phone where extraPkgVoice > 0 " )
	public List<GroupPhone> selectExtraPkgVoice();
	
	public int batchUpdateSuspended(List<GroupPhone> list);
	
	
	/**
	 * 更新数据 
	 *   主要更新字段:
	 *	 	balance 余额   
	 * 		usedMoney 已经使用的金额 
	 * 		lastCalculateVoice 已经扣费的超出语音 
	 * 		suspended 停开机状态  
	 */
	public int updateList(List<GroupPhone> list);
	
	/**
	 * 更新单个手机的激活状态
	 * @param map
	 * @return
	 */
	@Update( "update group_phone set status = 1 where phone = #{phone}" )
	public int updataStatus(Map map);
	
	
}