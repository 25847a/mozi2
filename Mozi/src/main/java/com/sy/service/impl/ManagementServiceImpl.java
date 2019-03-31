package com.sy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sy.mapper.AuthMapper;
import com.sy.mapper.EquipmentMapper;
import com.sy.mapper.ManRoleMapper;
import com.sy.mapper.ManagementMapper;
import com.sy.mapper.RoleAuthMapper;
import com.sy.mapper.RoleMapper;
import com.sy.pojo.Auth;
import com.sy.pojo.ManRole;
import com.sy.pojo.Management;
import com.sy.pojo.Role;
import com.sy.pojo.RoleAuth;
import com.sy.service.ManagementService;
import com.sy.utils.MD5Util;
import com.sy.utils.PageModel;
import com.sy.vo.ManagementVos;
import com.sy.vo.Managementvo;
import com.sy.vo.Ravo;
@Service
public class ManagementServiceImpl  implements ManagementService{
	@Value("#{configProperties['jdbc.server']}")
	private String baseUrl;
	@Autowired
	private ManagementMapper managementMapper ;
	@Autowired
	private ManRoleMapper manroleMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleAuthMapper roleauthmapper;
	@Autowired
	private AuthMapper authmapper;
	@Autowired
	private EquipmentMapper equipmentMapper;

	@Override
	public boolean addManagement(Management m) {
		Integer num = managementMapper.insertSelective(m);
		 if (num != 0) {
				return true;
			} else {
				return false;
			}
	}

	@Override
	public boolean deleteManagement(Integer id) {
		Integer num  = managementMapper.deleteByPrimaryKey(id);
		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean ifmanagement(String account) {
		Management m = managementMapper.ifmanagement(account);
		if(m ==null){
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public Management loginmanagement(String userName, String password) {
		Map<String, String>map = new HashMap<>();
		map.put("account", userName);
		map.put("password", MD5Util.MD5(password));
		Management m =managementMapper.loginmanagement(map);
		if(m !=null){
			m.setAtlasttime(new Date());
			managementMapper.updateByPrimaryKeySelective(m);
		}
		return m ;
	}

	@Override
	public Managementvo Authority(Integer managementid) {
		Managementvo mvo = new Managementvo();
		List<ManRole> mrs = manroleMapper.gemarole(managementid);
	    List<Ravo> rvos = new ArrayList<Ravo>();
		for(ManRole mr : mrs){
			Ravo rvo = new Ravo();
			Role r =roleMapper.selectByPrimaryKey(mr.getRoleId());
			rvo.setR(r);
			List<RoleAuth> ras =  roleauthmapper.getroleauth(r.getId());
			List<Auth> as = new ArrayList<Auth>();
			for(RoleAuth ra : ras){
				Auth a =authmapper.selectByPrimaryKey(ra.getAuthId());
				as.add(a);
			}
			rvo.setAl(as);
			rvos.add(rvo);
		}
		
		mvo.setRo(rvos);
		return mvo;
	}

	@Override
	public PageModel<Management> getusersone(Integer pageNo, String keyWord) {
	
				if(pageNo == null ||  pageNo.intValue() == 0){
					pageNo=1;
				}
				 //获取数据总数
				    Integer count=managementMapper.getcount(keyWord);
				    Integer pageSize=10;
				    List<Management>Feedbacks = null;
				    Integer pageNo1 = ( pageNo - 1) * pageSize;
				    //获取页数
				    HashMap<String, Object> parameter = new HashMap<>();
				    parameter.put("pageNo", pageNo1);
				    parameter.put("keyWord", keyWord);
				    parameter.put("pageSize", pageSize);
				    Feedbacks = managementMapper.list(parameter);
				    
				    PageModel<Management> pageModel = new PageModel<Management>(pageNo, pageSize,count, Feedbacks,"management/list");
				if(pageModel.getCount() !=0){
					pageModel.init();
				}
				return pageModel;
	}

	@Override
	public String uploadavatar(String avatar) {
		// TODO Auto-generated method stub
		return baseUrl+avatar;
	}

	@Override
	public Management getmanagementid(Integer id) {
		// TODO Auto-generated method stub
		return managementMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateManagement(Management m) {
		Integer num  = managementMapper.updateByPrimaryKeySelective(m);
		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Management> selectmanagements() {
		// TODO Auto-generated method stub
		return managementMapper.selectmanagements();
	}
	
	@Override
	public PageModel<ManagementVos> getagentList(Integer pageNo, String keyWord) throws Exception{
		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		//获取数据总数
		Integer count=managementMapper.getagentcount("代理商");
		Integer pageSize=10;
		
		Integer pageNo1 = ( pageNo - 1) * pageSize;
		//获取页数
		HashMap<String, Object> map = new HashMap<>();
		map.put("pageNo", pageNo1);
		map.put("keyWord", keyWord);
		map.put("role", "代理商");
		map.put("pageSize", pageSize);
		List<Management> userlist  = managementMapper.agentlist(map);
		List<ManagementVos> list = new ArrayList<>();
		for (Management management : userlist) {
			Integer id = management.getId();
			Integer selectEqNumber = equipmentMapper.selectEqNumber(id);
			ManagementVos m = new ManagementVos();
			BeanUtils.copyProperties(m, management);
			m.setEqNumber(selectEqNumber);
			list.add(m);
		}
		
		
		PageModel<ManagementVos> pagemodel = new PageModel<ManagementVos>(pageNo, pageSize,count, list,"management/agentlist");
		//if(pagemodel.getCount() !=0){
			pagemodel.init();
		//}
		return pagemodel;
	}

	@Override
	public List<Management> agentIdList() {
		return managementMapper.agentIdList();
	}
	
	
}
