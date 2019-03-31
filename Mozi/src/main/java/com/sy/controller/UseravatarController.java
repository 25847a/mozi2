package com.sy.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sy.common.ResultData;
import com.sy.pojo.Useravatar;
import com.sy.service.UseravatarService;

@Controller
@RequestMapping(value = "useravatar")
public class UseravatarController {
	@Autowired
	private UseravatarService useravatarservice;
	private final String baseUrl = "http://120.76.201.150:8080/";
	/**
	 * 上传头像
	 * 
	 * @param avatar
	 * @param id
	 * @return
	 */
	@RequestMapping("adddavatar")
	@ResponseBody
	public ResultData<String> adddavatar(
			@RequestParam(value = "avatar", required = false) CommonsMultipartFile avatar) {
		ResultData<String> re = new ResultData<String>();
		new File("E:\\Project\\avatars").mkdirs();
		com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\avatars", avatar);
		 Useravatar ua = new Useravatar();
		 ua.setAvatar( baseUrl + "avatars/" + avatar.getOriginalFilename());
		 boolean status = useravatarservice.adduseravatar(ua);
		if (status) {
			re.setCode(200);
			re.setMessage("头像上传成功！！!");
		} else {
			re.setMessage("头像上传失败！！！");
			re.setCode(400);

		}

		return re;
	}

	
}
