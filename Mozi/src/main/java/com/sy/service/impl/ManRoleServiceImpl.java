package com.sy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.mapper.ManRoleMapper;
import com.sy.pojo.ManRole;
import com.sy.pojo.Management;
import com.sy.service.ManRoleService;
import com.sy.service.ManagementService;
import com.sy.service.RoleService;
import com.sy.utils.PageModel;
import com.sy.vo.ManRolevo;
@Service
public class ManRoleServiceImpl implements ManRoleService{
    @Autowired
	private ManRoleMapper manRoleMapper;
    @Autowired
    private RoleService roleservice;
    @Autowired
    private ManagementService managementservice;
    
    

	@Override
	public PageModel<ManRolevo> getusersone(Integer pageNo, String keyWord) {
		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		 //获取数据总数
		    Integer count=manRoleMapper.getcount(keyWord);
		    Integer pageSize=10;
		    List<ManRolevo>Feedbacks = new ArrayList<ManRolevo>();
		    Integer pageNo1 = ( pageNo - 1) * pageSize;
		    //获取页数
		    HashMap<String, Object> parameter = new HashMap<>();
		    parameter.put("pageNo", pageNo1);
		    parameter.put("keyWord", keyWord);
		    parameter.put("pageSize", pageSize);
		    List<ManRole> ras= manRoleMapper.list(parameter);
		    for(ManRole r : ras){
		    	com.sy.pojo.Role role = roleservice.selectid(r.getRoleId());
		    	Management m =managementservice.getmanagementid(r.getManId());
		    	ManRolevo vos = new ManRolevo();
		    	vos.setId(r.getId());
		    	vos.setManname(m.getName());
		    	vos.setRolename(role.getRoleName());
		    	Feedbacks.add(vos);
		    }
		    
		    PageModel<ManRolevo> pageModel = new PageModel<ManRolevo>(pageNo, pageSize,count, Feedbacks,"manrole/list");
		if(pageModel.getCount() !=0){
			pageModel.init();
		}
		return pageModel;
	}



	@Override
	public boolean addManRole(ManRole r) {
		Integer num =manRoleMapper.insertSelective(r);
		if(num !=0){
			return true;
		}else {
			return false;
		}
		
	}



	@Override
	public boolean deleteManRole(Integer id) {
		Integer num =manRoleMapper.deleteByPrimaryKey(id);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	}
    
   
}
