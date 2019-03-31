package com.sy.service;

import java.util.List;

import com.sy.pojo.Role;
import com.sy.utils.PageModel;

/**
 * 
 * @author Administrator
 *
 */
public interface RoleService {

	/**
	 * 分页
	 * 获取所有第一层的数据
	 * @return
	 */
	public PageModel<Role>getroles(Integer pageNo,String keyword);
	
	/**
	 * 根据id获取数据
	 * @param id
	 * @return
	 */
	public Role selectid(Integer id );
	
	/**
	 * 获取所有数据
	 * @return
	 */
	public List<Role>getroless();
	
	/**
	 * 添加数据
	 * @param role
	 * @return
	 */
	public boolean addrole(Role role);
	
	
	/**
	 *根据id 删除
	 * @param roleid
	 * @return
	 */
	public boolean deleterole(Integer roleid );
	
	/**
	 * 对数据进行修改
	 * @param role
	 * @return
	 */
	public boolean modify(Role role);
	
}
