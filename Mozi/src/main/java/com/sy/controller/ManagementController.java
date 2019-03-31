package com.sy.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.sy.pojo.Equipment;
import com.sy.pojo.Management;
import com.sy.service.EquipmentService;
import com.sy.service.ManagementService;
import com.sy.utils.DeleteFileUtil;
import com.sy.utils.MD5Util;
import com.sy.utils.PageModel;
import com.sy.vo.ManagementVos;

@Controller
@RequestMapping(value = "management", method = RequestMethod.POST)
public class ManagementController {

	@Autowired
	private EquipmentService eqserv;
	
	@Autowired
	private ManagementService managementservice;

	
	@RequestMapping(value = "addagent")
	@ResponseBody
	public ResultBase addagent(String account, String password,
			String name, Integer age, String gender, String position,
			String phone, String address,  String wechat,String role,
			String qq,@RequestParam(value = "avatar", required = false) CommonsMultipartFile avatar
			) {
		
		ResultBase re = new ResultBase();
		Management m =new Management();
		if(avatar!=null){
			new File("E:\\Project\\avatars").mkdirs();
			com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\avatars", avatar);
			String st = managementservice.uploadavatar("avatars/" + avatar.getOriginalFilename());
			m.setAvatar(st);
		}
		
		boolean notBlank1 = StringUtils.isNotBlank(password);
		boolean notBlank2 = StringUtils.isNotBlank(account);
		if(!notBlank1 || !notBlank2){
			re.setCode(400);
			re.setMessage("请设置帐号和密码！！！");
			return re;
		}
		m.setAccount(account);
		password =MD5Util.MD5(password);
		m.setPassword(password);
		m.setGender(gender);
		m.setPosition(position);
		m.setPhone(phone);
		m.setWechat(wechat);
		m.setQq(qq);
		m.setAddress(address);
		m.setCreatetime(new Date());
		m.setAtlasttime(new Date());
		m.setAge(age);
		m.setName(name);
		m.setRole(role);
		
		if (managementservice.ifmanagement(account)) {
			boolean status = managementservice.addManagement(m);
			if (status) {
				re.setCode(200);
				re.setMessage("添加代理商成功！！！");
			} else {
				re.setCode(400);
				re.setMessage("添加代理商失败！！！");
			}
		} else {
			re.setCode(305);
			re.setMessage("该代理商存在！！！");
		}
		return re;
	}
	@RequestMapping(value="addManagement")
	@ResponseBody
	public ResultBase addManagement(String account, String password,
			String name, Integer age, String gender, String position,
			String phone, String address,  String wechat,
			String qq,@RequestParam(value = "avatar", required = false) CommonsMultipartFile avatar
			) {
		
		ResultBase re = new ResultBase();
		Management m =new Management();
		if(avatar!=null){
			new File("E:\\Project\\avatars").mkdirs();
			com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\avatars", avatar);
			String st = managementservice.uploadavatar("avatars/" + avatar.getOriginalFilename());
			m.setAvatar(st);
		}
		
		boolean notBlank1 = StringUtils.isNotBlank(password);
		boolean notBlank2 = StringUtils.isNotBlank(account);
		if(!notBlank1 || !notBlank2){
			re.setCode(400);
			re.setMessage("请设置帐号和密码！！！");
			return re;
		}
		m.setAccount(account);
		password =MD5Util.MD5(password);
		m.setPassword(password);
		m.setGender(gender);
		m.setPosition(position);
		m.setPhone(phone);
		m.setWechat(wechat);
		m.setQq(qq);
		m.setAddress(address);
		m.setCreatetime(new Date());
		m.setAtlasttime(new Date());
		m.setAge(age);
		m.setName(name);
		
		if (managementservice.ifmanagement(account)) {
			boolean status = managementservice.addManagement(m);
			if (status) {
				re.setCode(200);
				re.setMessage("添加管理人员成功！！！");
			} else {
				re.setCode(400);
				re.setMessage("添加管理人员失败！！！");
			}
		} else {
			re.setCode(305);
			re.setMessage("该管理人员存在！！！");
		}
		return re;
	}
	@RequestMapping("updateManagement")
	@ResponseBody
	public ResultBase updateManagement(String account, String password,Integer id,
			String name, Integer age, String gender, String position,
			String phone, String address,  String wechat,
			String qq,@RequestParam(value = "avatar", required = false) CommonsMultipartFile avatar) {
		String stimg = null ;
		if(avatar !=null){
			Management a =	managementservice.getmanagementid(id);
			if(a !=null){
				if(a.getAvatar() !=null && a.getAvatar() !="" && !a.getAvatar().equals("")){
					String[] st = a.getAvatar().split("/");
					DeleteFileUtil.deleteFile("E:/Project/" + st[3] + "/" + st[4]);
				}
			}
			new File("E:\\Project\\avatars").mkdirs();
			com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\avatars", avatar);
			stimg = managementservice.uploadavatar("avatars/" + avatar.getOriginalFilename());	
	}
		
		Management m =new Management();
		m.setAvatar(stimg);
		m.setAccount(account);
		m.setGender(gender);
		m.setPosition(position);
		m.setPhone(phone);
		m.setWechat(wechat);
		m.setQq(qq);
		m.setAddress(address);
		m.setAge(age);
		m.setId(id);
		m.setName(name);
		ResultBase re = new ResultBase();
			boolean status = managementservice.updateManagement(m);
			if (status) {
				re.setCode(200);
				re.setMessage("修改成功！！！");
			} else {
				re.setCode(400);
				re.setMessage("修改失败！！！");
			}
		

		return re;
	}

	@RequestMapping("deleteManagement")
	@ResponseBody
	public ResultBase deleteManagement(Integer id) {
		ResultBase re = new ResultBase();
		boolean status = managementservice.deleteManagement(id);
		if (status) {
			re.setCode(200);
			re.setMessage("删除管理人员成功！！！");
		} else {
			re.setCode(400);
			re.setMessage("删除管理人员失败！！！");
		}

		return re;

	}

	@RequestMapping(value = "list")
	public ModelAndView list(Integer pageNo, String keyword) {
		ModelAndView mo = new ModelAndView();
		PageModel<Management> pagemodel = managementservice.getusersone(pageNo,
				keyword);
		mo.setViewName("Management");
		mo.addObject("pagemodel", pagemodel);
		return mo;
	}
	/**
	 * 代理商设备列表
	 * @param pageNo
	 * @param imei
	 * @param eqStatus
	 * @param time
	 * @param agentid
	 * @return
	 */
	@RequestMapping(value = "equipmentlist")
	public ModelAndView equipmentlist(Integer pageNo, String imei,String eqStatus,String time,Integer agentid
			) {
		ModelAndView mo = new ModelAndView();
		Map<String,Object> map = new HashMap<>();
		map.put("keyWord", imei);
		//map.put("eqStatus", eqStatus);
		//map.put("time", time);
		map.put("agentid", agentid);
		
		PageModel<Equipment> pagemodel = eqserv.getAgentEquipment(pageNo,map);
		pagemodel.setPageHtm("equipmentlist");
		mo.setViewName("agentequipment");
		mo.addObject("pagemodel", pagemodel);
		mo.addObject("agentid", agentid);
		mo.addObject("imei", imei);
		return mo;
	}
	@RequestMapping("getmanagementid")
	@ResponseBody
	public ResultData<Management> getmanagementid(Integer id){
		ResultData<Management> re = new ResultData<Management>();
		re.setCode(200);
		re.setData(managementservice.getmanagementid(id));
		return re;
	}
	
	/**
	 * 代理商列表
	 * @param pageNo
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "agentlist")
	public ModelAndView agentlist(Integer pageNo, String keyword) throws Exception {
		ModelAndView mo = new ModelAndView();
		PageModel<ManagementVos> pagemodel = managementservice.getagentList(pageNo, keyword);
		mo.setViewName("agent");
		mo.addObject("pagemodel", pagemodel);
		mo.addObject("keyword", keyword);
		return mo;
	}
	/**
	 * 全部录入
	 * 
	 * @param arr
	 * @param agentid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "allentry",method = RequestMethod.POST)
	@ResponseBody
	public ResultData<List<String>> allentry(String[] arr,Integer agentid,String model) {
		
		ResultData<List<String>> re = new ResultData<>();
		int length = arr.length;
		try{
		List<String> allentry = eqserv.allentry(Arrays.asList(arr),agentid,model);
		
			re.setCode(200);
			re.setMessage("录入成功,成功"+(length-allentry.size())+"条,失败"+allentry.size()+"条    "+allentry.toString()+"");
			re.setData(allentry);
		}catch (Exception e) {
			e.printStackTrace();
			re.setCode(305);
			re.setMessage("请联系后台理员");
		}
		return re;
	}
	/**
	 * 代理商编号列表
	 * @param arr
	 * @param agentid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "agentIdList",method = RequestMethod.POST)
	@ResponseBody
	public ResultData<List<String>> agentIdList(String[] arr,Integer agentid,String model) {
		
		ResultData<List<String>> re = new ResultData<>();
		try{
			List<Management> list = managementservice.agentIdList();
			
			re.setCode(200);
			re.setMessage("");
			re.setData(list);
		}catch (Exception e) {
			e.printStackTrace();
			re.setCode(305);
			re.setMessage("请联系后台理员");
		}
		return re;
	}
	@RequestMapping("imeiUpdateAgentAccount")
	@ResponseBody
	public ResultBase imeiUpdateAgentAccount(Equipment e) {
		
		ResultBase re = new ResultBase();
			boolean status = eqserv.imeiUpdateAgentAccount(e);
			if (status) {
				re.setCode(200);
				re.setMessage("修改成功！！！");
			} else {
				re.setCode(400);
				re.setMessage("修改失败！！！");
			}
		return re;
	}
	
}
