package com.sy.service;

import java.util.List;

import com.sy.pojo.Management;
import com.sy.utils.PageModel;
import com.sy.vo.ManagementVos;
import com.sy.vo.Managementvo;
/**
 * 后台页面用户表
 * @author Administrator
 *
 */
public interface ManagementService {
	
	/**
	 * 添加管理人员
	 * @param m
	 * @return
	 */
	public boolean addManagement(Management m );
	
	/**根据id是删除管理员
	 * @param id
	 * @return
	 */
	public boolean deleteManagement(Integer id );
	
	/**判断改管理人员是否存在
	 * @param account
	 * @return
	 */
	public boolean ifmanagement(String account);
	
	/**管理人员登陆
	 * @param userName
	 * @param password
	 * @return
	 */
	public Management loginmanagement(String userName ,String password);
	
	/**获取用户对应的权限数据
	 * @param managementid
	 * @return
	 */
	public Managementvo Authority(Integer managementid);
	
	/**分页获取管理人员数据
	 * @param pageNo
	 * @param keyWord
	 * @return
	 */
	public PageModel<Management> getusersone(Integer pageNo,String keyWord);
	
	/**头像
	 * @param avatar
	 * @return
	 */
	public String uploadavatar(String avatar);
	
	/**根据id获取用户数据
	 * @param id
	 * @return
	 */
	public Management getmanagementid(Integer id);
	
	/**修改管理人员
	 * @param m
	 * @return
	 */
	public boolean updateManagement(Management m );
	
	/**获取所以管理人员
	 * @return
	 */
	public List<Management>  selectmanagements();

	public PageModel<ManagementVos> getagentList(Integer pageNo, String keyword) throws Exception;

	public List<Management> agentIdList();
}
