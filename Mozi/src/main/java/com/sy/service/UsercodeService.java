package com.sy.service;

import com.sy.pojo.Usercode;

public interface UsercodeService {

	
	/**添加验证码
	 * @param code
	 * @return
	 */
	public boolean addUsercode(Usercode code);
	
	
	
	/**判断验证码是否正确
	 * @param code
	 * @return
	 */
	public boolean ifusercode(Usercode code);
}
