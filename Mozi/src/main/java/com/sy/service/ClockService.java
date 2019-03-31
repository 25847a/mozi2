package com.sy.service;

import java.util.List;

import com.sy.pojo.Clock;

public interface ClockService {
	
	/**添加闹钟
	 * @param c
	 * @return
	 */
	public boolean addClock(Clock c);
	
	/**根据id删除闹钟
	 * @param id
	 * @return
	 */
	public boolean deleteClock(Integer id );
	
	/**跟新闹钟
	 * @param c
	 * @return
	 */
	public boolean updateClock(Clock c);
	
	
	/**获取闹钟数据
	 * @return
	 */
	public List<Clock>selectclocks(String imei);
	
	
	
}
