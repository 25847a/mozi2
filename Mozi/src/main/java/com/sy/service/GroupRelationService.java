package com.sy.service;

import com.sy.common.ResultBase;
import com.sy.pojo.GroupRelation;

public interface GroupRelationService {
	/**
	 * 加入朋友圈群
	 * @param group
	 * @param code
	 * @return
	*/
	public ResultBase addGroupRelation(GroupRelation groupRelation,ResultBase re)throws Exception;
	/**
	 * 删除个人群关联
	 * @param groupRelation
	 * @return
	*/
	public ResultBase deleteGroupRelation(GroupRelation groupRelation,ResultBase re)throws Exception;
}
