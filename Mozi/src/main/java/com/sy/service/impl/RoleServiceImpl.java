package com.sy.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.mapper.RoleMapper;
import com.sy.pojo.Role;
import com.sy.service.RoleService;
import com.sy.utils.PageModel;
@Service
public class RoleServiceImpl  implements RoleService {
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public PageModel<Role> getroles(Integer pageNo,String keyWord) {
	       
			if(pageNo == null ||  pageNo.intValue() == 0){
				pageNo=1;
			}
			 //获取数据总数
			    Integer count=roleMapper.getcount(keyWord);
			    Integer pageSize=10;
			    List<Role>Feedbacks = null;
			    Integer pageNo1 = ( pageNo - 1) * pageSize;
			    //获取页数
			    HashMap<String, Object> parameter = new HashMap<>();
			    parameter.put("pageNo", pageNo1);
			    parameter.put("keyWord", keyWord);
			    parameter.put("pageSize", pageSize);
			    Feedbacks = roleMapper.listRole(parameter);
			    
			    PageModel<Role> pageModel = new PageModel<Role>(pageNo, pageSize,count, Feedbacks,"role/getroles");
			if(pageModel.getCount() !=0){
				pageModel.init();
			}
			return pageModel;
	}

	@Override
	public Role selectid(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Role> getroless() {
		return roleMapper.getroless();
	}

	@Override
	public boolean addrole(Role role) {
		 try {
			roleMapper.insertSelective(role);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public boolean deleterole(Integer roleid) {
		try {
			roleMapper.deleteByPrimaryKey(roleid);
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}

	@Override
	public boolean modify(Role role) {
		try {
			roleMapper.updateByPrimaryKey(role);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	} 

}
