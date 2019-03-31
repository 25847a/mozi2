package com.sy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.mapper.AdvertisingMapper;
import com.sy.pojo.Advertising;
import com.sy.service.AdvertisingService;
import com.sy.utils.PageModel;
@Service
public class AdvertisingServiceImpl implements AdvertisingService{
    @Autowired
	private AdvertisingMapper advertisingMapper;

	@Override
	public boolean addAdvertising(Advertising a) {
		a.setCreatetime(new Date());
		Integer num = advertisingMapper.insertSelective(a);
		if(num != 0){
			return true;
		}else {
			return false;	
		}
		
	}

	@Override
	public boolean deleteAdvertising(Integer id) {
		Integer num = advertisingMapper.deleteByPrimaryKey(id);
		if(num != 0){
			return true;
		}else {
			return false;	
		}
	}

	@Override
	public boolean updateAdvertising(Advertising a) {
		Integer num = advertisingMapper.updateByPrimaryKeySelective(a);
		if(num != 0){
			return true;
		}else {
			return false;	
		}
	}

	@Override
	public Advertising getadvertisingid(Integer id) {
		// TODO Auto-generated method stub
		return advertisingMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageModel<Advertising> getusersone(Integer pageNo, String keyWord) {

		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		 //获取数据总数
		    Integer count=advertisingMapper.getcount(keyWord);
		    Integer pageSize=10;
		    List<Advertising>Feedbacks = null;
		    Integer pageNo1 = ( pageNo - 1) * pageSize;
		    //获取页数
		    HashMap<String, Object> parameter = new HashMap<>();
		    parameter.put("pageNo", pageNo1);
		    parameter.put("keyWord", keyWord);
		    parameter.put("pageSize", pageSize);
		    Feedbacks = advertisingMapper.list(parameter);
		    
		    PageModel<Advertising> pageModel = new PageModel<Advertising>(pageNo, pageSize,count, Feedbacks,"advertising/list");
		if(pageModel.getCount() !=0){
			pageModel.init();
		}
		return pageModel;
	}
}
