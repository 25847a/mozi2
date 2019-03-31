package com.sy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.mapper.UserleaveMapper;
import com.sy.pojo.Userleave;
import com.sy.service.LeaveService;
import com.sy.utils.PageModel;
@Service
public class LeaveServiceImpl implements LeaveService {
	@Autowired
	private UserleaveMapper leaveMapper;

	@Override
	public boolean addLeave(Userleave l) {
		l.setCratetime(new Date());
		Integer num = leaveMapper.insertSelective(l);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	
	}

	@Override
	public boolean deleteLeave(Integer id) {
		Integer num = leaveMapper.deleteByPrimaryKey(id);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public PageModel<Userleave> getusersone(Integer pageNo, String keyWord) {

		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		 //获取数据总数
		    Integer count=leaveMapper.getcount(keyWord);
		    Integer pageSize=10;
		    List<Userleave>Feedbacks = null;
		    Integer pageNo1 = ( pageNo - 1) * pageSize;
		    //获取页数
		    HashMap<String, Object> parameter = new HashMap<>();
		    parameter.put("pageNo", pageNo1);
		    parameter.put("keyWord", keyWord);
		    parameter.put("pageSize", pageSize);
		    Feedbacks = leaveMapper.list(parameter);
		    
		    PageModel<Userleave> pageModel = new PageModel<Userleave>(pageNo, pageSize,count, Feedbacks,"leave/list");
		if(pageModel.getCount() !=0){
			pageModel.init();
		}
		return pageModel;
	}

}
