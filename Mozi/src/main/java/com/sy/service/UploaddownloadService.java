package com.sy.service;

import com.sy.pojo.Uploaddownload;

public interface UploaddownloadService {
	
	/**添加版本号
	 * @param u
	 * @return
	 */
	public boolean addUploaddownload(Uploaddownload u);
	
	/**获取最新的设备的版本
	 * @param imei
	 * @return
	 */
	public Uploaddownload selectUploaddownload(String imei);
	
	/**跟新版本信息
	 * @return
	 */
	public boolean updateUploaddownload(Uploaddownload u);
    /**删除
     * @param id
     * @return
     */
    public boolean deleteuplpoaddwnload(Integer id);
    public Uploaddownload selectModelVo(String model);
    public Uploaddownload selectModelAndImeiVo(String imei,String model);
}
