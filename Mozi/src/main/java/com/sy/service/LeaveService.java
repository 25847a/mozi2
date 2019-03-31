package com.sy.service;

import com.sy.pojo.Userleave;
import com.sy.utils.PageModel;

public interface LeaveService {
	
	/**添加留言
	 * @param l
	 * @return
	 */
	public boolean addLeave(Userleave l);

	/**删除
	 * @param id
	 * @return
	 */
	public boolean deleteLeave(Integer id );
	
	/**获取留言
	 * @param pageNo
	 * @param keyword
	 * @return
	 */
	PageModel<Userleave>  getusersone(Integer pageNo, String keyword);
}
