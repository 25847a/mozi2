package com.sy.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.Carousel;
import com.sy.pojo.Management;
import com.sy.service.CarouselService;
import com.sy.service.ManagementService;
import com.sy.utils.DeleteFileUtil;
import com.sy.utils.Managementconstant;
import com.sy.utils.PageModel;
/**
 * 页面-轮播图片管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "carousel", method = RequestMethod.POST)
public class Carouselcontoller {
	@Autowired
	private ManagementService managementservice;
	@Autowired
	private CarouselService carouselservice;
	/**
	 * 新增图片
	 * @param content
	 * @param title
	 * @param img
	 * @param session
	 * @return
	 */
	@RequestMapping("addCarousel")
	@ResponseBody
	public ResultBase addCarousel(  String content,String title,@RequestParam(value = "img", required = false) CommonsMultipartFile img,HttpSession session) {
		Management m = (Management) session.getAttribute(Managementconstant.user);
		ResultBase re = new ResultBase();
		if(m !=null){
			new File("E:\\Project\\Carousel").mkdirs();
			com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\Carousel", img);
			String st = managementservice.uploadavatar("Carousel/" + img.getOriginalFilename());
		
			Carousel a = new Carousel();
			a.setImg(st);
			a.setCreatetime(new Date());
			a.setManagementId(m.getId());
			a.setTitle(title);
				boolean status = carouselservice.addCarousel(a);
				if (status) {
					re.setCode(200);
					re.setMessage("添加图片成功！！！");
				} else {
					re.setCode(400);
					re.setMessage("添加图片失败！！！");
				}
		}else {
			re.setCode(350);
			re.setMessage("权限补足！！！");
		}
		
		return re;
	}
	/**
	 * 更新图片
	 * @param id
	 * @param content
	 * @param title
	 * @param img
	 * @param session
	 * @return
	 */
	@RequestMapping("updateCarousel")
	@ResponseBody
	public ResultBase updateCarousel( Integer id, String content,String title,@RequestParam(value = "img", required = false) CommonsMultipartFile img,HttpSession session) {
		Management m = (Management) session.getAttribute(Managementconstant.user);
		ResultBase re = new ResultBase();
		if(m !=null){
			if(img !=null){
				Carousel a =	carouselservice.getCarousel(id);
				if(a !=null){
					if(a.getImg() !=null && a.getImg() !="" && !a.getImg().equals("")){
						String[] st = a.getImg().split("/");
						DeleteFileUtil.deleteFile("E:/Project/" + st[3] + "/" + st[4]);
					}
				}
				
		}
			
			new File("E:\\Project\\Carousel").mkdirs();
			com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\Carousel", img);
			String st = managementservice.uploadavatar("Carousel/" + img.getOriginalFilename());
		
			Carousel a = new Carousel();
			a.setCreatetime(new Date());
			a.setImg(st);
			a.setManagementId(m.getId());
			a.setId(id);
			a.setTitle(title);
				boolean status = carouselservice.updateCarousel(a);
				if (status) {
					re.setCode(200);
					re.setMessage("修改图片成功！！！");
				} else {
					re.setCode(400);
					re.setMessage("修改图片失败！！！");
				}
		}else {
			re.setCode(350);
			re.setMessage("权限补足！！！");
		}
		
		return re;
	}
	/**
	 * 删除图片
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteCarousel")
	@ResponseBody
	public ResultBase deleteCarousel(Integer id) {
		ResultBase re = new ResultBase();
		Carousel a =	carouselservice.getCarousel(id);
		if(a !=null){
			if(a.getImg() !=null && a.getImg() !="" && !a.getImg().equals("")){
				String[] st = a.getImg().split("/");
				DeleteFileUtil.deleteFile("E:/Project/" + st[3] + "/" + st[4]);
			}
		}
		boolean status = carouselservice.deleteCarousel(id);
		if (status) {
			re.setCode(200);
			re.setMessage("删除图片成功！！！");
		} else {
			re.setCode(400);
			re.setMessage("删除图片失败！！！");
		}

		return re;

	}

	/**
	 * 轮播图片列表
	 * @param pageNo
	 * @param keyword
	 * @return
	 */
	@RequestMapping(value = "list")
	public ModelAndView list(Integer pageNo, String keyword) {
		ModelAndView mo = new ModelAndView();
		PageModel<Carousel> pagemodel = carouselservice.getusersone(pageNo,
				keyword);
		mo.setViewName("carousel");
		mo.addObject("pageModel", pagemodel);
		return mo;
	}
	/**
	 * 这个接口在jsp找不到,不知道是否是APP还是惊凡需要
	 * @return
	 */
	@RequestMapping(value = "applist")
	@ResponseBody
	public ResultData<List<Carousel>>  applist() {
		ResultData<List<Carousel>> re = new ResultData<List<Carousel>>();
		List<Carousel> ls = carouselservice.selectcarousel();
		if(ls.size()>0){
			re.setCode(200);
			re.setMessage("获取轮播图片成功！！！");
			re.setData(ls);
		}else {
			re.setCode(350);
			re.setMessage("获取轮播图片失败！！！");
		}
		return re;
	}
	
}
