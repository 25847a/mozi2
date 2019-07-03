package com.sy.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.common.ResultBase;
import com.sy.pojo.Uploaddownload;
import com.sy.pojo.Versionhistory;
import com.sy.service.UploaddownloadService;
import com.sy.service.VersionhistoryService;

@Controller
@RequestMapping(value = "uploaddownload")
public class UploaddownloadController {
	@Autowired
	private VersionhistoryService versionhistoryservice;
	@Autowired
	private UploaddownloadService uploaddownloadservice;
	
	
	@RequestMapping(value = "addUploaddownload")
	public ResultBase addUploaddownload(String imei ,Integer id){
		Versionhistory v=versionhistoryservice.seleversiontory(id);		
		ResultBase re = new ResultBase();
		Uploaddownload u = new Uploaddownload();
		u.setName(v.getName());
		u.setUrl(v.getUrl());
		u.setImei(imei);
		u.setZiversion(v.getZiversion());
		u.setZhuversion(v.getZhuversion());
		u.setCompilation(v.getCompilation());
		u.setVersiontype(v.getVersiontype());
		u.setDescription(v.getDescription());
		u.setCreatetime(new Date());
		u.setUpdatetime(new Date());
		u.setModel(v.getModel());
		boolean status = uploaddownloadservice.addUploaddownload(u);
		if(status){
			re.setCode(200);
			re.setMessage("跟新版成功！！！");
		}else {
			re.setCode(400);
			re.setMessage("跟新版失败！！！");
		}
		return re ;
	}
	

	
	
	@RequestMapping("deleteuploaddownload")
	@ResponseBody
	public ResultBase deleteuplpoaddwnload(Integer id){
		ResultBase re = new ResultBase();
		boolean status =uploaddownloadservice.deleteuplpoaddwnload(id);
		if(status){
			re.setCode(200);
			re.setMessage("删除成功！！！");
		}else {
			re.setCode(400);
			re.setMessage("删除失败！！！");
		}
		return re ;
		
	}
}
