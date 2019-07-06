package com.sy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Realhealth;

public interface RealhealthMapper extends BaseMapper<Realhealth>{
	/**
	 * 有效真实数据--该表只为内部看，未修改的天津真实数据
	 * @param realhealth
	 */
	public  void insertRealhealth(Realhealth realhealth);
}
