package com.sy.service;

import com.sy.pojo.Advertising;
import com.sy.utils.PageModel;

public interface AdvertisingService {
	
	/**添加广告
	 * @param a
	 * @return
	 */
	public boolean addAdvertising(Advertising a );

	
	/**删除
	 * @param id
	 * @return
	 */
	public boolean deleteAdvertising(Integer id);
	
	/**更新
	 * @param a
	 * @return
	 */
	public boolean updateAdvertising(Advertising a);
	
	/**根据id获取广告
	 * @param id
	 * @return
	 */
	public Advertising getadvertisingid(Integer id);
	
	/**分页获取数据
	 * @param pageNo
	 * @param keyword
	 * @return
	 */
	PageModel<Advertising>getusersone(Integer pageNo,String keyword);
}
