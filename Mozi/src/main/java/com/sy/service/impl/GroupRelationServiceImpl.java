package com.sy.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sy.common.ResultBase;
import com.sy.mapper.GroupMapper;
import com.sy.mapper.GroupRelationMapper;
import com.sy.mapper.UserMapper;
import com.sy.pojo.Config;
import com.sy.pojo.Group;
import com.sy.pojo.GroupRelation;
import com.sy.pojo.User;
import com.sy.service.ConfigService;
import com.sy.service.GroupRelationService;

@Service
public class GroupRelationServiceImpl implements GroupRelationService {
	
	@Autowired
	GroupRelationMapper groupRelationMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	GroupMapper groupMapper;
	@Autowired
	ConfigService configService;
	/**
	 * 加入朋友圈群
	 * @param group
	 * @param code
	 * @return
	*/
	@Override
	public ResultBase addGroupRelation(GroupRelation groupRelation, ResultBase re) throws Exception {
		User user =userMapper.queryIdUserInfo(groupRelation.getRelationUserId());
		if(user.getRole().equals("使用者")){
			groupRelation.setKind(1);
		}else{
			groupRelation.setKind(0);
		}
		groupRelation.setType(0);
		//判断是否加入过该群
		GroupRelation relation = groupRelationMapper.queryGroupRelation(groupRelation.getGroupId(), groupRelation.getRelationUserId());
		if(relation==null){
			Group group = groupMapper.queryGroupInfo(groupRelation.getGroupId());
			Config config = configService.queryConfigInfo("group_count", "count_type");
			if(group.getCount()<Integer.valueOf(config.getValue())){
				int row =groupRelationMapper.insertGroupRelation(groupRelation);
				if(row>0){
					groupMapper.updateCountGroup(groupRelation.getGroupId());
					re.setCode(200);
					re.setMessage("加入成功");
				}else{
					re.setCode(400);
					re.setMessage("加入失败");
				}
			}else{
				re.setCode(400);
				re.setMessage("群人数已达上限, 无法入群");
			}
		}else{
			re.setCode(400);
			re.setMessage("该群您已加入过,无需重复加入");
		}
		return re;
	}
	/**
	 * 删除个人群关联
	 * @param groupRelation
	 * @return
	*/
	@Override
	public ResultBase deleteGroupRelation(GroupRelation groupRelation, ResultBase re) throws Exception {
		int row = groupRelationMapper.deletegroupRelationInfo(groupRelation);
		if(row>0){
			re.setCode(200);
			re.setMessage("移除成功");
		}else{
			re.setCode(400);
			re.setMessage("移除失败");
		}
		return re;
	}
}
