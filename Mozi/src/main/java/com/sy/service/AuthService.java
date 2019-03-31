package com.sy.service;

import java.util.List;

import com.sy.pojo.Auth;
import com.sy.utils.PageModel;

public interface AuthService {
	
	/**根据id查找权限
	 * @param id
	 * @return
	 */
	public Auth findid(Integer id );
	
	/**获取所有权限
	 * @return
	 */
	public List<Auth> getauth();
	
	/**用户权限查询
	 * @param pageNo
	 * @param keyWord
	 * @return
	 */
	PageModel<Auth> getusersone(Integer pageNo, String keyWord);
	
	/**添加权限
	 * @param a
	 * @return
	 */
	public boolean addmenucz(Auth a);
	
	/**删除权限
	 * @param id
	 * @return
	 */
	public boolean deletememu(Integer id);
	
	/**修改
	 * @param a
	 * @return
	 */
	public boolean modifymenu(Auth a);
}
