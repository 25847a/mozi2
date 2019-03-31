package com.sy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.mapper.RoleAuthMapper;
import com.sy.pojo.Auth;
import com.sy.pojo.RoleAuth;
import com.sy.service.AuthService;
import com.sy.service.RoleAnthService;
import com.sy.service.RoleService;
import com.sy.utils.PageModel;
import com.sy.vo.RoleMenuvo;
@Service
public class RoleAnthServiceImpl implements RoleAnthService {
	@Autowired
	private RoleAuthMapper roleauthmapper;
	@Autowired
	private RoleService  rolemapper;
	@Autowired
	private AuthService authserice;

	@Override
	public PageModel<RoleMenuvo> getusersone(Integer pageNo, String keyWord) {
		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		 //获取数据总数
		    Integer count=roleauthmapper.getcount(keyWord);
		    Integer pageSize=10;
		    List<RoleMenuvo>Feedbacks = new ArrayList<RoleMenuvo>();
		    Integer pageNo1 = ( pageNo - 1) * pageSize;
		    //获取页数
		    HashMap<String, Object> parameter = new HashMap<>();
		    parameter.put("pageNo", pageNo1);
		    parameter.put("keyWord", keyWord);
		    parameter.put("pageSize", pageSize);
		    List<RoleAuth> ras= roleauthmapper.listRoleMenur(parameter);
		    for(RoleAuth r : ras){
		    	com.sy.pojo.Role role = rolemapper.selectid(r.getRoleId());
		    	Auth menu = authserice.findid(r.getAuthId());
		    	RoleMenuvo vos = new RoleMenuvo();
		    	vos.setId(r.getId());
		    	vos.setMenuname(menu.getAuthName());
		    	vos.setRolename(role.getRoleName());
		    	
		    	Feedbacks.add(vos);
		    }
		    
		    PageModel<RoleMenuvo> pageModel = new PageModel<RoleMenuvo>(pageNo, pageSize,count, Feedbacks,"roleAnth/list");
		if(pageModel.getCount() !=0){
			pageModel.init();
		}
		return pageModel;
	}

	@Override
	public boolean deleteroleanth(Integer id) {
		Integer num = roleauthmapper.deleteByPrimaryKey(id);
		if(num !=0){
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean addRoleAnth(RoleAuth ar) {
		Integer num = roleauthmapper.insertSelective(ar);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	}

}
