package com.sy.mapper;

import java.sql.SQLException;
import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.pojo.Realhealth;

public interface RealhealthMapper extends BaseMapper<Realhealth>{

	/**
	 * 查询真实数据集合
	 * @param phone
	 * @return
	 * @throws SQLException
	 */
	public List<Realhealth> queryRealhealthList(String phone)throws SQLException;
	/**
	 * 删除有效真实数据
	 * @param phone
	 * @return
	 * @throws SQLException
	 */
	public int deleteRealhealth(String phone)throws SQLException;
}
