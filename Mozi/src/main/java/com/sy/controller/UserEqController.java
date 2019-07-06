package com.sy.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.mapper.PushMapper;
import com.sy.pojo.Equipment;
import com.sy.pojo.Push;
import com.sy.pojo.User;
import com.sy.pojo.UserEq;
import com.sy.service.EquipmentService;
import com.sy.service.UserEqService;
import com.sy.service.UserService;
import com.sy.utils.DataRow;
import com.sy.vo.Userdata;

@Controller
@RequestMapping(value = "usereq")
public class UserEqController {
	@Autowired
	private UserService userservice;
	@Autowired
	private EquipmentService equipmentservice;
	@Autowired
	private UserEqService usereqservice;
	@Autowired
	private PushMapper pushMapper;

	private final static Logger logger = LoggerFactory.getLogger(UserEqController.class);
	/**
	 * 关注列表
	 * @param u
	 * @return
	 */
	@RequestMapping(value = "queryUserEqFollowList")
	@ResponseBody
	public ResultData<List<DataRow>> queryUserEqFollowList(@RequestBody DataRow map) {
		ResultData<List<DataRow>> re = new ResultData<List<DataRow>>();
		try {
			re = usereqservice.queryUserEqFollowList(map,re);
		} catch (Exception e) {
			re.setCode(400);
			re.setMessage("获取设备操作者失败！！！");
			logger.error("UserEqController>>>>>>>>>>>>>>>>>>queryUserEqFollowList",e);
		}
		return re;
	}
	/**
	 *  更改默认关注列表
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("updateUserFollow")
	@ResponseBody
	public ResultBase updateUserFollow(@RequestBody DataRow map){
		ResultBase re = new ResultBase();
		try{
			re=usereqservice.updateUserFollow(map,re);
		}catch (Exception e) {
			logger.info(e.getMessage());
		}
		return re;
	}
	/**
	 * 删除设备操作者
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "deleteequse")
	@ResponseBody
	public ResultBase deleteguardian(@RequestBody DataRow map) {
		ResultBase re = new ResultBase();
		try {
		re = usereqservice.deleteguardian111111(map,re);
		} catch (Exception e) {
			logger.error("UserEqController>>>>>>>>>>>>>>>>>>>>deleteguardian",e);
		}
		return re;
	}

	/**
	 * 扫二维码添加观察者
	 * 
	 * @param u
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping(value = "addqrcodObserved")
	@ResponseBody
	public ResultBase addqrcodObserved(Integer mid,
			String imei, String authorized) throws SQLException {
		ResultBase re = new ResultBase();
		try{
		User user = userservice.getUser(imei);
		if(user==null){
			re.setCode(350);
			re.setMessage("设备未绑定");
		}else{
		
			Equipment e = equipmentservice.selectquipmentimei(imei);
			UserEq use = usereqservice.selectUserEq(e.getId(),mid);
			if (use!=null) {
				re.setCode(350);
				re.setMessage("已经绑定,不可再关注");
			} else {
				EntityWrapper<UserEq> ew = new EntityWrapper<UserEq>();
				ew.eq("user_id", mid);
				UserEq eq =usereqservice.selectOne(ew);
				UserEq u = new UserEq();
				if(eq==null){
					u.setFollow(1);
				}
				u.setUserId(mid);
				u.setEqId(e.getId());
				u.setTypeof(1);
				u.setAuthorized("已授权");
				if (usereqservice.ifObserved(u)) {
					usereqservice.addUserEq(u);
					Push push = new Push();
				    push.setUserId(user.getId());
				    push.setAlias(mid);
				    push.setAllNotifyOn(true);
				    pushMapper.insert(push);
				    re.setCode(200);
					re.setMessage("关注成功");
					} else {
						re.setCode(350);
						re.setMessage("已经关注,不可重复关注");
					}
				}
		}
		}catch (Exception e) {
			 re.setCode(400);
			 re.setMessage("关注失败");
		}
		return re;
	}


	/**
	 * 获取app首页数据
	 * m
	 * @return
	 */
	@RequestMapping(value = "selectuserdata")
	@ResponseBody
	public ResultData<List<Userdata>> selectuserdata(@RequestBody DataRow map) {
		ResultData<List<Userdata>> re = new ResultData<List<Userdata>>();
		try {
			re = usereqservice .selectuserdata(map,re);
			} catch (Exception e) {
				re.setCode(200);
				re.setMessage("未有用户数据 ！！！！");
				logger.error("UserEqController>>>>>>>>>>>>>>>>>selectuserdata",e);
			}
			return re;
	}
	/**
	 * 更换设备
	 * 
	 * @return
	 */
	@RequestMapping(value = "userequipmentstatus")
	@ResponseBody
	public ResultBase replaceequipment(@RequestBody Map m) {
		
		ResultBase re = new ResultBase();
		
		Integer userid = Integer.parseInt((String) m.get("userid"));
		String imei = (String) m.get("imei");
		String oldimei = (String) m.get("oldimei");
		
		if (userid != 0 && userid != null && imei != null
				&& !imei.trim().equals("") && oldimei != null
				&& !oldimei.trim().equals("")) {
			
		//先判断新的imei号有没有录入存在
		Equipment equip = equipmentservice.selectquipmentimei(imei);
		User user = userservice.getUser(imei);
		if(equip==null){
			re.setCode(350);
			re.setMessage("更换的设备不存在！！！");
		}else if (user!=null){
			re.setCode(350);
			re.setMessage("设备已经存在使用者！！！");
		}else{
			
			//原来的设备
			Equipment e = equipmentservice.selectquipmentimei(oldimei);
			//监护者
			User user0 = usereqservice.getuserimei0(e.getId());
			
			if (e != null && user0 !=null) {
				
				//创建使用者和设备的关联关系
				UserEq usereq2 = new UserEq();
				usereq2.setUserId(userid);
				usereq2.setEqId(equip.getId());
				usereq2.setTypeof(2);
				boolean status = usereqservice.addUserEq(usereq2);
				
				//创建监护者和设备的关联关系
				UserEq usereq0 = new UserEq();
				usereq0.setUserId(user0.getId());
				usereq0.setEqId(equip.getId());
				usereq0.setTypeof(0);
				status = usereqservice.addUserEq(usereq0);
				
				//删除旧设备的关联数据
				status = usereqservice.deleteEqUser(e.getId());
				User user2 = userservice.getUser(userid);
				user2.setImei(imei);
				userservice.updateById(user2);
					if (status) {
						re.setCode(200);
						re.setMessage("跟换设备成功！！！");
					} else {
						re.setCode(400);
						re.setMessage("跟换设备失败！！！");
					}
				} 
		}
		} else {
			re.setCode(350);
			re.setMessage("跟换设备参数不允许为空值！！！");
		}
		return re;
	}
	@RequestMapping(value = "selectuserequser")
	@ResponseBody
	public ResultData<User>   selectuserequser(Integer eqId){
		ResultData<User>  re = new ResultData<User>();
		List<User>listuser =  usereqservice.selelctequser(eqId);
		if(listuser.size()>0){
			re.setData(listuser);
			re.setCode(200);
			re.setMessage("获取关联数据成功！！！");
		}else {
			re.setCode(400);
			re.setMessage("该用户没有关联人员！！！");
		}
		return re;
	}
}
