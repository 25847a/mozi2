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

import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.mapper.PushMapper;
import com.sy.nettyulit.NettyChannelMap;
import com.sy.pojo.Equipment;
import com.sy.pojo.Push;
import com.sy.pojo.User;
import com.sy.pojo.UserEq;
import com.sy.service.EquipmentService;
import com.sy.service.UserEqService;
import com.sy.service.UserService;
import com.sy.vo.Userdata;
import io.netty.channel.Channel;

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
	 * 添加监护者
	 * 
	 * @param u
	 * @return
	 */
	/*@RequestMapping(value = "addguardianship")
	@ResponseBody
	public ResultBase addguardianship(@RequestBody Map m) {
		ResultBase re = new ResultBase();
		UserEq u = new UserEq();
		u.setUserId(Integer.parseInt((String) m.get("userid")));
		Equipment e = equipmentservice.selectquipmentimei((String) m
				.get("imei"));
		u.setEqId(e.getId());
		u.setTypeof(0);
		if (usereqservice.ifguardianship(u.getEqId())) {
			boolean status = usereqservice.addUserEq(u);
			if (status) {
				re.setCode(200);
				re.setMessage("添加设备监护者成功！！！");
			} else {
				re.setCode(400);
				re.setMessage("添加设备监护者失败！！！");
			}
		} else {
			re.setCode(350);
			re.setMessage("该设备已经被占用！！！");
		}
		return re;
	}*/

	/**
	 * 添加观察者
	 * 
	 * @param u
	 * @return
	 * @throws SQLException 
	 */
	/*@RequestMapping(value = "addObserved")
	@ResponseBody
	public ResultBase addObserved(@RequestBody Map m) throws SQLException {
		ResultBase re = new ResultBase();
		Equipment e = equipmentservice.selectquipmentimei((String) m
				.get("imei"));
		
		//用imei 获取使用者
		User user = userservice.getUser(e.getImei());
		if(user==null){
			re.setCode(350);
			re.setMessage("设备未绑定");
			return re;
		}
		
		//用eqId 获取监护者
		User use = usereqservice.getuserimei0(e.getId());
		
		Integer mid = Integer.parseInt((String) m.get("mid"));
		
		if (mid == use.getId()) {
			re.setCode(350);
			re.setMessage("已经是监护者");
		} else {
			UserEq u = new UserEq();
			u.setUserId(mid);
			u.setEqId(e.getId());
			u.setTypeof(1);
			u.setAuthorized((String) m.get("authorized"));
			if (usereqservice.ifObserved(u)) {
				boolean status = usereqservice.addUserEq(u);
				if (status) {
					 	Push push = new Push();
					    push.setUserId(user.getId());
					    push.setAlias(mid);
					    push.setAllNotifyOn(true);
					    push.setHeartHigThd(100);
					    push.setHeartLowThd(60);
					    push.setHbpstart(90);
					    push.setHbpend(120);
					    push.setLbpstart(60);
					    push.setLbpend(80);
					    pushMapper.addPush(push);
						re.setCode(200);
						re.setMessage("添加设备观察者成功！！！");
				} else {
					re.setCode(400);
					re.setMessage("添加设备观察者失败！！！");
				}
			} else {
				re.setCode(350);
				re.setMessage("已经是观察者！！！");
			}
		}

		return re;
	}*/

	/**
	 * 添加使用者
	 * 
	 * @param u
	 * @return
	 */
	/*@RequestMapping(value = "bindDev")
	@ResponseBody
	public ResultBase adduse(@RequestBody Map m) {
		ResultBase re = new ResultBase();
		UserEq u = new UserEq();
		u.setUserId(Integer.parseInt((String) m.get("userid")));
		Equipment e = equipmentservice.selectquipmentimei((String) m
				.get("imei"));
		u.setEqId(e.getId());
		u.setTypeof(2);
		if (usereqservice.ifuse(u.getEqId())) {
			boolean status = usereqservice.addUserEq(u);
			if (status) {
				re.setCode(200);
				re.setMessage("添加设备使用者成功！！！");
			} else {
				re.setCode(400);
				re.setMessage("添加设备使用者失败！！！");
			}
		} else {
			re.setCode(350);
			re.setMessage("该设备已经被占用！！！");
		}
		return re;
	}*/

	/**
	 * 获取该设备有关用户的关联关系数据,后台使用
	 * @param u
	 * @return
	 */
	@RequestMapping(value = "selectusereq")
	@ResponseBody
	public ResultData<List<User>> selectusereq(@RequestBody Map m) {
		ResultData<List<User>> re = new ResultData<List<User>>();
		Equipment e = equipmentservice.selectquipmentimei((String) m
				.get("imei"));
		List<User> us = usereqservice.selelctequser(e.getId());
		if (us != null) {
			re.setCode(200);
			re.setMessage("获取设备操作者成功！！！");
			re.setData(us);
		} else {
			re.setCode(400);
			re.setMessage("获取设备操作者失败！！！");
		}
		return re;
	}

	/**
	 * 删除设备操作者
	 * 
	 * @param u
	 * @return
	 */
	@RequestMapping(value = "deleteequse")
	@ResponseBody
	public ResultBase deleteguardian(@RequestBody Map m) {
		
		
		ResultBase re = new ResultBase();
		try {
		Integer typeof = Integer.parseInt((String) m.get("typeof"));
								
		Integer mid = Integer.parseInt((String) m.get("mid"));		
		
		Equipment e = equipmentservice.selectquipmentimei((String) m
				.get("imei"));
		
		String bluetoothmac = e.getBluetoothmac();
		String bluetoothName = e.getBluetoothName();
		String bluetoothType = e.getBluetoothType();
		
		//卡片的id
		Integer userId = Integer.parseInt((String) m.get("userId"));
		boolean status = false;
		
		if(typeof==2){
			if(bluetoothmac.equals("000000000000") && bluetoothName.equals("000000000000")&&bluetoothType.equals("0")){
				status = usereqservice.deleteguardian(e.getId(), userId,mid);
				if (status) {
					
					Channel c =	NettyChannelMap.get(e.getImei());
					if(c!=null){
						c.writeAndFlush("$R06|:\r\n");
					}
					re.setCode(200);
					re.setMessage("取消成功！！！");
					e.setBluetoothmac("000000000000");
					e.setBluetoothName("000000000000");
					e.setBluetoothList(null);
					e.setBluetoothStatus("0");
					e.setBluetoothType("0");
					e.setPhone1(null);
					e.setPhone2(null);
					equipmentservice.updatEequipmentst(e);
			} else {
				re.setCode(350);
				re.setMessage("取消失败！！！");
			}
			}else{
				re.setCode(350);
				re.setMessage("请先断开衣服");
			}
		}else if(typeof==1){
			status = usereqservice.deleteequse(e.getId(),
					Integer.parseInt((String) m.get("userId")), 1,mid);
			re.setCode(200);
			re.setMessage("取消成功！！！");
		}
		
		} catch (Exception et) {
			et.printStackTrace();
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
				UserEq u = new UserEq();
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
				    push.setHeartHigThd(100);
				    push.setHeartLowThd(60);
				    push.setHbpstart(90);
				    push.setHbpend(120);
				    push.setLbpstart(60);
				    push.setLbpend(80);
				    pushMapper.addPush(push);
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
	 * 获取多个imei里面的管理人员已经使用只最新数据
	 * 
	 * @return
	 */
	/*@RequestMapping(value = "selelctUsereqvo")
	@ResponseBody
	public ResultData<List<Userqedata>> selelctUsereqvo(@RequestBody Map m) {
		ResultData<List<Userqedata>> re = new ResultData<List<Userqedata>>();
		String imeis = (String) m.get("imeis");
		String imeiss[] = imeis.split(",");
		List<Userqedata> userdatas = new ArrayList<Userqedata>();
		if (imeiss.length >= 0) {
			for (String imei : imeiss) {
				Userqedata userdata = usereqservice.selectdata(imei);
				if (userdata != null) {
					userdatas.add(userdata);
				}
			}
			re.setCode(200);
			re.setMessage("获取设备管理员信息成功！！！");
			re.setData(userdatas);
		} else {
			re.setCode(350);
			re.setMessage("该设备没有对应的用户管理员！！！");
		}
		return re;
	}*/

	/**
	 * 获取app首页数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "selectuserdata")
	@ResponseBody
	public ResultData<List<Userdata>> selectuserdata(@RequestBody Map m) {
		String userIdStr = (String) m.get("userId");
		String useridStr = (String) m.get("userid");
		if(userIdStr==null){
			userIdStr = useridStr;
		}
		int parseInt = Integer.parseInt(userIdStr);
		ResultData<List<Userdata>> re = new ResultData<List<Userdata>>();
		try {
			List<Map<String, Object>> userdata = usereqservice
					.selectuserdata(parseInt);
			if (userdata != null && userdata.size() > 0) {
				re.setCode(200);
				re.setMessage("获取所有设备使用者信息成功！！！");
				logger.info("获取所有设备使用者信息成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+userdata);
				re.setData(userdata);
			} else {
				re.setCode(200);
				re.setMessage("未有用户数据 ！！");
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		logger.info("嘉康请求:usereq/selectuserdata>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+re.toString());
			return re;
	}

	/**
	 * 通过userid获取用户健康数据
	 * 
	 * @return
	 */
	/*@RequestMapping(value = "userhealthdata")
	@ResponseBody
	public ResultData<Map<String, Object>> userhealthdata(@RequestBody Map m) {
		Integer userid = Integer.parseInt((String) m.get("userid"));
		ResultData<Map<String, Object>> re = new ResultData<Map<String, Object>>();
		Map<String, Object> userdata = usereqservice.userhealthdata(userid);
		if (userdata != null) {
			re.setCode(200);
			re.setMessage("获取设备使用者健康数据成功！！！");
			re.setData(userdata);
			return re;
		} else {
			re.setCode(350);
			re.setMessage("该用户不是使用！！！");
			return re;
		}
	}*/

	/**
	 * 点击卡片接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "userdata")
	@ResponseBody
	public ResultData<Map<String, Object>> userdata(@RequestBody Map m) {
		ResultData<Map<String, Object>> re = new ResultData<Map<String, Object>>();
		Map<String, Object> map = usereqservice
				.userdata((String) m.get("imei"));
		if (map.size() > 0) {
			re.setCode(200);
			re.setMessage("获取设备使用者信息成功！！！");
			re.setData(map);
		} else {
			re.setCode(350);
			re.setMessage("该用户未有使用信息！！！");
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
				userservice.updateUser(user2);
				/*Equipment newe = equipmentservice.selectquipmentimei(imei);
				if (newe == null) {
					Equipment adde = new Equipment();
					adde.setImei(imei);
					adde.setCreatetime(new Date());
					adde.setUpdatetime(new Date());
					adde.setEqStatus("H:0");
					adde.setBluetoothElectricity(0);
					adde.setBluetoothStatus("0");
					adde.setBluetoothType("0");
					adde.setClock("闹钟");
					adde.setName("设备信息");
					adde.setEqtype("1");
					adde.setLordpower(0);
					adde.setVersion("0.0");
					adde.setSignalxhao("0");
					adde.setBluetoothName("蓝牙");
					equipmentservice.addEquipment(adde);
					newe = equipmentservice.selectquipmentimei(imei);
				}*/
				
				
				
				/*if (usereqservice.ifuse(usereq.getEqId())) {
					usereqservice.deleteequsetype(e.getId(), user0.getId(), 0);
					usereqservice.deleteequsetype(e.getId(), userid, 2);
					/*UserEq use = new UserEq();
					use.setEqId(usereq.getEqId());
					use.setUserId(user0.getId());
					use.setTypeof(0);
					usereqservice.addUserEq(use);
					boolean status = usereqservice.addUserEq(usereq);*/
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
	
	/**
	 * 后台删除使用
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteusereq")
	@ResponseBody
	public  ResultBase  deleteusereq(Integer id){
		ResultBase re = new ResultBase();
		if(usereqservice.deleteusereq(id)){
			re.setCode(200);
		}else {
			re.setCode(400);
		}
		return  re;
	}
}
