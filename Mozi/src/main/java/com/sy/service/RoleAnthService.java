package com.sy.service;

import com.sy.pojo.RoleAuth;
import com.sy.utils.PageModel;
import com.sy.vo.RoleMenuvo;

public interface RoleAnthService {
	/**获取角色权限表
	 * @param pageNo
	 * @param keyword
	 * @return
	 */
	PageModel<RoleMenuvo> getusersone(Integer pageNo, String keyword);
	
	/**删除
	 * @param id
	 * @return
	 */
	public boolean deleteroleanth(Integer id);
	
	/**添加角色
	 * @param ar
	 * @return
	 */
	public boolean addRoleAnth(RoleAuth ar );
}
