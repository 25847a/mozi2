package com.sy.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.mapper.GroupMapper;
import com.sy.mapper.GroupRelationMapper;
import com.sy.mapper.UserEqMapper;
import com.sy.mapper.UserMapper;
import com.sy.pojo.Group;
import com.sy.pojo.GroupRelation;
import com.sy.pojo.User;
import com.sy.pojo.Usercode;
import com.sy.service.ConfigService;
import com.sy.service.GroupService;
import com.sy.service.UseravatarService;
import com.sy.service.UsercodeService;
import com.sy.utils.DataParsing;
import com.sy.utils.StringUtil;

@Service
public class GroupServiceImpl implements GroupService {
	
	
	@Autowired
	GroupMapper groupMapper;
	@Autowired
	UsercodeService usercodeService;
	@Autowired
	ConfigService configService;
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserEqMapper userEqMapper;
	@Autowired
	GroupRelationMapper groupRelationMapper;
	@Autowired
	private UseravatarService useravatarservice;
	/**
	 * 创建朋友圈群
	 * @param group
	 * @param code
	 * @return
	 */
	@Override
	public ResultBase insertGroup(Group group,CommonsMultipartFile image, ResultBase re) throws Exception {
		Usercode usercode = new Usercode();
		usercode.setCode(group.getCode());
		usercode.setPhoen(group.getPhone());
		if(usercodeService.ifusercode(usercode)){
			groupMapper.insertGroup(group);
			GroupRelation relation = new GroupRelation();
			relation.setGroupId(group.getId());
			relation.setRelationUserId(group.getUserId());
			relation.setType(1);
			User user =userMapper.queryIdUserInfo(group.getUserId());
			if(user.getRole().equals("使用者")){
				relation.setKind(1);
			}else{
				relation.setKind(0);
			}
			StringUtil.arrayUploadFile("E:\\Project\\avatars", image);//处理图片的
			group.setImageUrl("http://120.76.201.150:8080/avatars/" + image.getOriginalFilename());
			int row =groupRelationMapper.insertGroupRelation(relation);
			if(row>0){
				re.setCode(200);
				re.setMessage("创建成功");
			}else{
				re.setCode(400);
				re.setMessage("创建失败");
			}
		}else{
			re.setCode(405);
			re.setMessage("验证码有误！！！");
		}
		return re;
	}
	/**
	 * 首页查询社区
	 * @param group
	 * @param code
	 * @return
	 */
	@Override
	public ResultData<List<Map<String, Object>>> queryGroupPage(Map<String, String> map, ResultData<List<Map<String, Object>>> re) throws Exception {
		User user =userMapper.queryIdUser(map);
		List<Map<String, Object>> es = new ArrayList<Map<String, Object>>();
		List<Map<String,String>> recommend = groupMapper.queryRecommendInfo();
		if(user!=null){
			Map<String,Object> listData = new HashMap<String,Object>();
			if(user.getRole().equals("管理者")){//监护者ID,展示出监护人,被监护人的s
				List<Map<String,String>> aliasList = groupMapper.queryGroupAliasList(map);
				listData.put("role", user.getRole().equals("管理者")?0:1);
				listData.put("name", user.getName());
				listData.put("userId", user.getId());
				listData.put("detail", aliasList);
				listData.put("recommend", recommend);
				es.add(listData);
				
				List<Map<String,String>> userIdUserEqList =userEqMapper.queryUserIdUserEq(map);
				for(Map<String,String> data:userIdUserEqList){
					listData = new HashMap<String,Object>();
					data.put("userId", String.valueOf(data.get("user_id")));
					List<Map<String,Object>> userIdList =groupMapper.queryGroupUserIdList(data);
					listData.put("role", data.get("role").equals("管理者")?0:1);
					listData.put("name", data.get("name"));
					listData.put("userId", Integer.valueOf(data.get("userId")));
					listData.put("detail", userIdList);
					listData.put("recommend", recommend);
					es.add(listData);
				}
				
			}else if(user.getRole().equals("使用者")){//使用者ID
				listData = new HashMap<String,Object>();
				List<Map<String,Object>> userIdList =groupMapper.queryGroupUserIdList(map);
				listData.put("role", user.getRole().equals("管理者")?0:1);
				listData.put("name", user.getName());
				listData.put("userId", user.getId());
				listData.put("detail", userIdList);
				listData.put("recommend", recommend);
				
			}
			re.setData(es);
			re.setCode(200);
			re.setMessage("获取数据成功");
		}else{
			re.setCode(400);
			re.setMessage("无用户无数据,请重新登录");
		}
		return re;
	}
	/**
	 * 查询社圈群员
	 * @param group
	 * @param code
	 * @return
	 */
	@Override
	public ResultData<List<Map<String,Object>>> queryRecommendGroup(Map<String, String> map,ResultData<List<Map<String,Object>>> re) throws Exception {
		List<Map<String,String>> data =groupRelationMapper.queryGroupRelationInfo(map);//获得群成员
		 if(!data.isEmpty()){
			 for(int i=0;i<data.size();i++){
					if(!data.get(i).isEmpty()){
						data.get(i).put("avatar",useravatarservice.selectavartar().getAvatar());
					}
				}
			 re.setData(data);
			 re.setCode(200);
			 re.setMessage("获取数据成功");
		 }else{
			 re.setCode(400);
			 re.setMessage("无用户无数据,请重新登录");
		 }
		return re;
	}
	/**
	 * 查询推荐社圈
	 * @param group
	 * @param code
	 * @return
	 */
	@Override
	public ResultData<List<Map<String, Object>>> queryActiveInfo(Map<String, String> map, ResultData<List<Map<String, Object>>> re)throws Exception {
		List<Map<String,Object>> list =groupRelationMapper.queryActiveInfo(map);
		if(!list.isEmpty()){
			/*for(int i=0;i<list.size();i++){
				list.get(i).put("count", DataParsing.getConversionDate(Integer.valueOf(String.valueOf(list.get(i).get("count")))));
				if(!list.get(i).get("avatar").is){
					Map<String,Object> s = new HashMap<String,Object>();
					s.get("ff").
					list.get(i).put("avatar",useravatarservice.selectavartar().getAvatar());
				}
			}*/
			 re.setData(list);
			 re.setCode(200);
			 re.setMessage("获取数据成功");
		}else{
			 re.setCode(400);
			 re.setMessage("无用户无数据");
		}
		return re;
	}
	/**
	 * 手机号码查询社圈
	 * @param group
	 * @param code
	 * @return
	 */
	@Override
	public ResultData<Map<String, Object>> queryPhoneGroup(Map<String, String> map, ResultData<Map<String, Object>> re) throws Exception {
		Map<String, Object> data =groupMapper.queryPhoneGroup(map);
		if(data!=null){
			 re.setData(data);
			 re.setCode(200);
			 re.setMessage("获取数据成功");
		}else{
			 re.setCode(400);
			 re.setMessage("查询不到该社群");
		}
		return re;
	}
	/**
	 * 解散社群
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultBase deleteDismiss(Map<String, Object> map, ResultBase re) throws Exception {
		groupMapper.deleteGroup(map);
		groupRelationMapper.deletegroupRelation(map);
		re.setCode(200);
		re.setMessage("解散成功");
		return re;
	}
}
