package com.sy.service;

import com.sy.pojo.ManRole;
import com.sy.utils.PageModel;
import com.sy.vo.ManRolevo;

public interface ManRoleService {
	
	/**获取结束权限关联表
	 * @param pageNo
	 * @param keyword
	 * @return
	 */
	PageModel<ManRolevo>getusersone(Integer pageNo, String keyword);

	/**添加
	 * @param r
	 * @return
	 */
	public boolean addManRole(ManRole r);
	
	/**删除
	 * @param id
	 * @return
	 */
	public boolean deleteManRole(Integer id );
}
