package com.sy.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.mapper.AuthMapper;
import com.sy.pojo.Auth;
import com.sy.service.AuthService;
import com.sy.utils.PageModel;
@Service
public class AuthServiceImpl implements AuthService{
	@Autowired
	private AuthMapper authMapper;

	@Override
	public Auth findid(Integer id) {
		// TODO Auto-generated method stub
		return authMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Auth> getauth() {
		// TODO Auto-generated method stub
		return authMapper.getauth();
	}

	@Override
	public PageModel<Auth> getusersone(Integer pageNo, String keyWord) {

		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		 //获取数据总数
		    Integer count=authMapper.getcount(keyWord);
		    Integer pageSize=10;
		    List<Auth>Feedbacks = null;
		    Integer pageNo1 = ( pageNo - 1) * pageSize;
		    //获取页数
		    HashMap<String, Object> parameter = new HashMap<>();
		    parameter.put("pageNo", pageNo1);
		    parameter.put("keyWord", keyWord);
		    parameter.put("pageSize", pageSize);
		    Feedbacks = authMapper.list(parameter);
		    
		    PageModel<Auth> pageModel = new PageModel<Auth>(pageNo, pageSize,count, Feedbacks,"antn/list");
		if(pageModel.getCount() !=0){
			pageModel.init();
		}
		return pageModel;
	}

	@Override
	public boolean addmenucz(Auth a) {
		Integer num = authMapper.insertSelective(a);
		if(num !=0){
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean deletememu(Integer id) {
		Integer num = authMapper.deleteByPrimaryKey(id);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean modifymenu(Auth a) {
		Integer num = authMapper.updateByPrimaryKeySelective(a);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	}

}
