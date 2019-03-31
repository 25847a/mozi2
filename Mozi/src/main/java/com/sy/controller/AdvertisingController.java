package com.sy.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.Advertising;
import com.sy.pojo.Management;
import com.sy.service.AdvertisingService;
import com.sy.service.ManagementService;
import com.sy.utils.DeleteFileUtil;
import com.sy.utils.Managementconstant;
import com.sy.utils.PageModel;
/**
 * 页面-广告管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "advertising", method = RequestMethod.POST)
public class AdvertisingController {
	@Autowired
	private ManagementService managementservice;
	@Autowired
	private AdvertisingService advertisingservice; 

	/**
	 * 新增广告
	 * @param content
	 * @param title
	 * @param imgVideo
	 * @param session
	 * @return
	 */
	@RequestMapping("addAdvertising")
	@ResponseBody
	public ResultBase addAdvertising(  String content,String title,@RequestParam(value = "imgVideo", required = false) CommonsMultipartFile imgVideo,HttpSession session) {
		Management m = (Management) session.getAttribute(Managementconstant.user);
		ResultBase re = new ResultBase();
		if(m !=null){
			new File("E:\\Project\\advertising").mkdirs();
			com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\advertising", imgVideo);
			String st = managementservice.uploadavatar("advertising/" + imgVideo.getOriginalFilename());
		
			Advertising a = new Advertising();
			a.setContent(content);
			a.setCreatetime(new Date());
			a.setImgVideo(st);
			a.setManagementId(m.getId());
			a.setTitle(title);
				boolean status = advertisingservice.addAdvertising(a);
				if (status) {
					re.setCode(200);
					re.setMessage("添加广告成功！！！");
				} else {
					re.setCode(400);
					re.setMessage("添加广告失败！！！");
				}
		}else {
			re.setCode(350);
			re.setMessage("权限补足！！！");
		}
		
		return re;
	}
	/**
	 * 修改广告
	 * @param id
	 * @param content
	 * @param title
	 * @param imgVideo
	 * @param session
	 * @return
	 */
	@RequestMapping("updateAdvertising")
	@ResponseBody
	public ResultBase updateAdvertising( Integer id, String content,String title,@RequestParam(value = "imgVideo", required = false) CommonsMultipartFile imgVideo,HttpSession session) {
		Management m = (Management) session.getAttribute(Managementconstant.user);
		ResultBase re = new ResultBase();
		if(m !=null){
			if(imgVideo !=null){
				Advertising a =	advertisingservice.getadvertisingid(id);
				if(a !=null){
					if(a.getImgVideo() !=null && a.getImgVideo() !="" && !a.getImgVideo().equals("")){
						String[] st = a.getImgVideo().split("/");
						DeleteFileUtil.deleteFile("E:/Project/" + st[3] + "/" + st[4]);
					}
				}
				
		}
			
			new File("E:\\Project\\advertising").mkdirs();
			com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\advertising", imgVideo);
			String st = managementservice.uploadavatar("advertising/" + imgVideo.getOriginalFilename());
		
			Advertising a = new Advertising();
			a.setContent(content);
			a.setCreatetime(new Date());
			a.setImgVideo(st);
			a.setManagementId(m.getId());
			a.setId(id);
			a.setTitle(title);
				boolean status = advertisingservice.updateAdvertising(a);
				if (status) {
					re.setCode(200);
					re.setMessage("修改广告成功！！！");
				} else {
					re.setCode(400);
					re.setMessage("修改广告失败！！！");
				}
		}else {
			re.setCode(350);
			re.setMessage("权限补足！！！");
		}
		
		return re;
	}
	
	/**
	 * 删除广告
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteAdvertising")
	@ResponseBody
	public ResultBase deleteAdvertising(Integer id) {
		ResultBase re = new ResultBase();
		Advertising a =	advertisingservice.getadvertisingid(id);
		if(a !=null){
			if(a.getImgVideo() !=null && a.getImgVideo() !="" && !a.getImgVideo().equals("")){
				String[] st = a.getImgVideo().split("/");
				DeleteFileUtil.deleteFile("E:/Project/" + st[3] + "/" + st[4]);
			}
		}
		boolean status = advertisingservice.deleteAdvertising(id);
		if (status) {
			re.setCode(200);
			re.setMessage("删除广告成功！！！");
		} else {
			re.setCode(400);
			re.setMessage("删除广告失败！！！");
		}

		return re;

	}
	/**
	 * 广告列表
	 * @param pageNo
	 * @param keyword
	 * @return
	 */
	@RequestMapping(value = "list")
	public ModelAndView list(Integer pageNo, String keyword) {
		ModelAndView mo = new ModelAndView();
		PageModel<Advertising> pagemodel = advertisingservice.getusersone(pageNo,
				keyword);
		mo.setViewName("advertising");
		mo.addObject("pageModel", pagemodel);
		return mo;
	}
	/**
	 * 这个接口在jsp找不到,不知道是否是APP还是惊帆需要
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "applist")
	@ResponseBody
	public ResultData<List<Advertising>> applist(@RequestBody Map m ) {
		String p=(String)m.get("pageNo");
		String key=(String)m.get("keyword");
		
		Integer pageno=0;
		if(!p.equals("")){
			pageno = Integer.parseInt((String)m.get("pageNo"));
		}
		if(key.equals("")){
			key =null;
		}
		ResultData<List<Advertising>> re = new ResultData<List<Advertising>>();
		PageModel<Advertising> pagemodel = advertisingservice.getusersone(pageno,key);
		if(pagemodel.getList().size()>0){
			re.setCode(200);
			re.setMessage("获取广告数据成功！！！");
			re.setData(pagemodel.getList());
		}else {
			re.setCode(350);
			re.setMessage("获取广告数据失败！！！");
		}
		return re;
	}
	
}
