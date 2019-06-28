package com.sy.mapper;

import java.util.Map;

import com.sy.pojo.Extend;

public interface ExtendMapper {
	/**
	 * 查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Extend selectExtend(Integer id)throws Exception;
	/**
	 * 修改
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateExtendById(Map<String,Object> map)throws Exception;
}
