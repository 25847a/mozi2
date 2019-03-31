package com.sy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sy.mapper.UploaddownloadMapper;
import com.sy.pojo.Uploaddownload;
import com.sy.service.UploaddownloadService;
import com.sy.utils.PageModel;
@Service
public class UploaddownloadServiceImpl implements UploaddownloadService{
	@Autowired
	private UploaddownloadMapper uploaddownloadMapper;

	@Override
	public boolean addUploaddownload(Uploaddownload u) {
		Integer num = uploaddownloadMapper.insert(u);
		if(num !=0){
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public Uploaddownload selectUploaddownload(String imei) {
		if (uploaddownloadMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			uploaddownloadMapper = (UploaddownloadMapper) webApplicationContext
					.getBean("uploaddownloadMapper");
		}
		return uploaddownloadMapper.selectUploaddownload(imei);
	}
	
	
	public Uploaddownload selectModelVo(String model) {
		if (uploaddownloadMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			uploaddownloadMapper = (UploaddownloadMapper) webApplicationContext
					.getBean("uploaddownloadMapper");
		}
		return uploaddownloadMapper.selectModelVo(model);
	}
	
	
	public Uploaddownload selectModelAndImeiVo(String imei,String model) {
		if (uploaddownloadMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			uploaddownloadMapper = (UploaddownloadMapper) webApplicationContext
					.getBean("uploaddownloadMapper");
		}
		Uploaddownload u = new Uploaddownload();
		u.setImei(imei);
		u.setModel(model);
		return uploaddownloadMapper.selectModelAndImeiVo(u);
	}

	@Override
	public PageModel<Uploaddownload> getusersone(Integer pageNo, String keyword) {
		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		 //获取数据总数
		    Integer count=uploaddownloadMapper.getcount(keyword);
		    Integer pageSize=10;
		    List<Uploaddownload>Feedbacks = new ArrayList<Uploaddownload>();
		    Integer pageNo1 = ( pageNo - 1) * pageSize;
		    //获取页数
		    HashMap<String, Object> map = new HashMap<>();
		    map.put("pageNo", pageNo1);
		    map.put("keyWord", keyword);
		    map.put("pageSize", pageSize);
		    Feedbacks= uploaddownloadMapper.list(map);
		 
		    PageModel<Uploaddownload> pageModel = new PageModel<Uploaddownload>(pageNo, pageSize,count, Feedbacks,"uploaddownload/list");
		if(pageModel.getCount() !=0){
			pageModel.init();
		}
		return pageModel;
	}

	@Override
	public boolean updateUploaddownload(Uploaddownload u) {
		Integer num = uploaddownloadMapper.updateByPrimaryKey(u);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteuplpoaddwnload(Integer id) {
		Integer num = uploaddownloadMapper.deleteByPrimaryKey(id);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	}
}
