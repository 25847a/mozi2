package com.sy.controller;

import java.io.File;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.mapper.GroupPhoneMapper;
import com.sy.mapper.UserMapper;
import com.sy.nettyulit.BluetoothMap;
import com.sy.nettyulit.NettyChannelMap;
import com.sy.pojo.Equipment;
import com.sy.pojo.GroupPhone;
import com.sy.pojo.User;
import com.sy.pojo.Usercode;
import com.sy.service.EquipmentService;
import com.sy.service.UserService;
import com.sy.service.UsercodeService;
import com.sy.utils.DataRow;
import com.sy.utils.MD5Util;
import com.sy.vo.LoginReturn;
import com.sy.vo.Loginuse;
import com.sy.vo.Usermanagement;
import io.netty.channel.socket.SocketChannel;

@Controller
@RequestMapping(value = "user", method = RequestMethod.POST)
public class UserController {
	private static final String String = null;
	@Autowired
	private GroupPhoneMapper groupPhoneMapper;
	@Autowired
	private EquipmentService equipmentservice;
	@Autowired
	private UsercodeService usercodeservic;
	@Autowired
	private UserService userService;
	@Autowired
	private  UserMapper userMapper;
	private final String baseUrl = "http://120.76.201.150:8080/";
	
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@RequestMapping(value = "selectImei")
	@ResponseBody
	public ResultBase selectImei(@RequestBody DataRow map) {
			ResultBase re = new ResultBase();
			String imei = (String)map.get("imei");
			if(!StringUtils.isNotBlank(imei.trim())){
				re.setCode(350);
				re.setMessage("请先输入Imei号！");
			}else{

				Equipment e = equipmentservice.selectquipmentimei(imei.trim());
				User user2 = userService.getUser(imei.trim());
				
				if (e == null){
					re.setCode(350);
					re.setMessage("设备号不存在,请联系经销商！！！");
				}else if(user2!=null) {
					re.setCode(350);
					re.setMessage("该设备使用者已经存在！！！");
				}else{
				re.setCode(200);
				re.setMessage("Ok");
				}
			}
		return re;
	}

	/**
	 * 注册用户
	 * @param u
	 * @return
	 */
	@RequestMapping("addUser")
	@ResponseBody
	public ResultData<Loginuse> addUser(@RequestBody User u) {
		ResultData<Loginuse> re = new ResultData<Loginuse>();
		try {
			re = userService.addUser(u,re);
		} catch (Exception e) {
			logger.error("UserController>>>>>>>>>>>>>>>>>>>>addUser",e);
		}
		return re;
	}
	
	
	/**
	 * 注册用户1111
	 * @param u
	 * @return
	 */
	@RequestMapping("addUser111")
	@ResponseBody
	public ResultData<Loginuse> addUser111(@RequestBody User u) {
		ResultData<Loginuse> re = new ResultData<Loginuse>();
		try {
			re = userService.addUser111(u,re);
		} catch (Exception e) {
			logger.error("UserController>>>>>>>>>>>>>>>>>>>>addUser",e);
		}
		return re;
	}
	
	
	/**
	 * 用户登陆
	 * @param data
	 * @return
	 */
	@RequestMapping("landingUser")
	@ResponseBody
	public ResultData<LoginReturn> landingUser(@RequestBody DataRow data) {
		ResultData<LoginReturn> re = new ResultData<LoginReturn>();
		try {
			re=userService.landingUser(data,re);
		} catch (Exception e) {
			re.setCode(400);
			re.setMessage("系统异常报错");
			logger.error("UserController>>>>>>>>>>>>>>>>>>>>landingUser",e);
		}
		return re;
	}
	/**
	 * 点击卡片接口
	 * @return
	 */
	@RequestMapping(value = "userdata")
	@ResponseBody
	public ResultData<DataRow> userdata(@RequestBody DataRow map) {
		ResultData<DataRow> re = new ResultData<DataRow>();
		try {
			re = userService.userdata(map,re);
		} catch (Exception e) {
			re.setCode(400);
			re.setMessage("查询出错,请返回重试");
			logger.error("UserEqController>>>>>>>>>>>>>>>userdata",e);
		}
		return re;
	}
	/**
	 * 查看个人资料
	 * @return
	 */
	@RequestMapping(value = "queryUserInfo")
	@ResponseBody
	public ResultData<DataRow> queryUserInfo(@RequestBody DataRow map) {
		ResultData<DataRow> re = new ResultData<DataRow>();
		try {
			re = userService.queryUserInfo(map,re);
		} catch (Exception e) {
			re.setCode(400);
			re.setMessage("查询出错,请返回重试");
			logger.error("UserEqController>>>>>>>>>>>>>>>userdata",e);
		}
		return re;
	}
	/**
	 * 上传头像
	 * 
	 * @param avatar
	 * @param id
	 * @return
	 */
	@RequestMapping("uploadavatar")
	@ResponseBody
	public ResultData<String> uploadavatar(
			@RequestParam(value = "avatar", required = false) CommonsMultipartFile avatar,
			Integer id) {
		ResultData<String> re = new ResultData<String>();
		new File("E:\\Project\\avatars").mkdirs();
		com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\avatars", avatar);
		boolean status = userService.uploadavatar(
				"avatars/" + avatar.getOriginalFilename(), id);
		if (status) {
			re.setCode(200);
			String url = baseUrl + "avatars/" + avatar.getOriginalFilename();
			re.setData(url);
			re.setMessage("头像上传成功！！!");
		} else {
			re.setMessage("头像上传失败！！！");
			re.setCode(400);

		}

		return re;
	}

	/**
	 * 更新头像
	 * @param avatar
	 * @param id
	 * @return
	 */
	@RequestMapping("updateavatar")
	@ResponseBody
	public ResultData<String> updateavatar(@RequestParam(value = "avatar", required = false) CommonsMultipartFile avatar,Integer id) {
		ResultData<String> re = new ResultData<String>();
		 try {
			 re = userService.updateavatar(avatar,id,re);
		 }catch(Exception e){
			 re.setMessage("头像上传失败,请检查图片格式");
			 re.setCode(400);
			 logger.error("UserController>>>>>>>>>>>>updateavatar",e);
		 }
		return re;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param u
	 * @return
	 */
	@RequestMapping("updateUser")
	@ResponseBody
	public ResultBase updateUser(@RequestBody User u,Integer phone1,Integer phone2) {
		ResultBase re = new ResultBase();
		try {
			re = userService.updateUser(u,re);
		} catch (Exception e) {
			/*String R06 = "$R06|ERR\r\n";
			Channel c =	NettyChannelMap.get(u.getImei());
			if(c!=null){
				c.writeAndFlush(R06);
			}*/
			re.setCode(400);
			re.setMessage("修改成功,硬件未修改,请检查硬件是否在线");
			logger.error("UserController>>>>>>>>>>>>updateUser",e);
		}
		return re;
	}
	/**
	 * 更新监护者用户信息
	 * @param u
	 * @return
	 */
	@RequestMapping("updateAliasUser")
	@ResponseBody
	public ResultBase updateAliasUser(@RequestBody User u) {
		ResultBase re = new ResultBase();
		try {
			re = userService.updateAliasUser(u,re);
		} catch (Exception e) {
			re.setCode(400);
			re.setMessage("修改异常,请联系管理员");
			logger.error("UserController>>>>>>>>>>>>updateAliasUser",e);
		}
		return re;
	}
	/**
	 * 更新密码
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping("updatepassword")
	@ResponseBody
	public ResultBase updatepassword(@RequestBody DataRow m) {
		ResultBase re = new ResultBase();
		boolean status = userService.updatepassword((String) m.get("password"),
				(String) m.get("newpassword"),
				Integer.parseInt((String) m.get("id")));
		if (status) {
			re.setCode(200);
			re.setMessage("修改成功！！!");
		} else {
			re.setMessage("修改失败！！！");
			re.setCode(400);

		}
		return re;
	}
	/**
	 * 获取验证码
	 * @param m
	 * @return
	 */
	@RequestMapping("sendSMS")
	@ResponseBody
	public ResultBase sendSMS(@RequestBody DataRow m) {
		System.out.println("获取验证码================" + m.get("phone"));
		ResultBase re = new ResultBase();
		Integer smsMsg = userService.sendSMS(m.getString("phone"));
		if (smsMsg != 0) {
			Usercode c = new Usercode();
			c.setCode(String.valueOf((int)smsMsg));
			c.setPhoen((String) m.get("phone"));
			usercodeservic.addUsercode(c);
			re.setCode(200);
			re.setMessage("发送验证码成功！！！");
		} else {
			re.setCode(400);
			re.setMessage("发送验证码失败！！！");
		}
		return re;
	}
	/**
	 *  添加使用者
	 * @param u
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("addUsermanagement")
	@ResponseBody
	public ResultBase addUsermanagement(@RequestBody Usermanagement u){
		ResultBase re = new ResultBase();
		try{
			re=userService.addUsermanagement(u,re);
		}catch (Exception e) {
			logger.error("UserController>>>>>>>>>>>>>>>>addUsermanagement",e);
		}
		return re;
	}
	/**
	 * 单独的修改用户手机号,updateUser也可以使用
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping("setPhone")
	@ResponseBody
	public ResultBase setPhone(@RequestBody User user){
		ResultBase re = new ResultBase();
		try{
			String phone = user.getPhone();
			
			//查询手机是否存在
			GroupPhone selectPhone = groupPhoneMapper.selectPhone(phone);
			if(selectPhone!=null){
				userMapper.setPhone(user);
				re.setCode(200);
				re.setMessage("修改成功");
			}else{
				re.setCode(350);
				re.setMessage("号码不存在");
			}
		}catch (Exception e) {
			logger.info(e.getMessage());
			re.setCode(400);
			re.setMessage("修改失败");
		}
		return re;
	}
	/**
	 * 设置用户健康数据的更新时间
	 * 
	 */
	@RequestMapping("jfdataUpdateTime")
	@ResponseBody
	public ResultBase jfdataUpdateTime(@RequestBody User user) {
		ResultBase re = new ResultBase();
		try{
			String imei = user.getImei();
			String jfdataUpdateTime = (String) user.getJfdataUpdateTime();
			SocketChannel c = (SocketChannel) NettyChannelMap.get(imei);
			if(c!=null){
				c.writeAndFlush("$R27|" + jfdataUpdateTime + "\r\n");
			int i = 0;
			while (true) {
				i++;
				Thread.sleep(1000);
				String msg = BluetoothMap.getBs(imei);
				if (msg != null) {
					if (msg.contains("R27|OK") ) {
						userMapper.jfdataUpdateTime(user);
						re.setMessage("设置成功");
						re.setCode(200);
						BluetoothMap.deletesb(imei);
						break;
					}
				}
				if(i==40){
					re.setMessage("设置失败");
					re.setCode(400);
					break;
				}
			}
			}else{
				re.setMessage("设置失败");
				re.setCode(400);
			}
		}catch (Exception e) {
			logger.info(e.getMessage());
			re.setMessage("设置失败");
			re.setCode(400);
		}
		return re;
	}
	/**
	 * 设置校准状态
	 * 
	 */
	@RequestMapping("updateCalibration")
	@ResponseBody
	public ResultBase updateCalibration(@RequestBody User user) {
		ResultBase re = new ResultBase();
		try{
			String calibration = String.valueOf(user.getCalibration());
			Integer id = user.getId();
			logger.info("设置学习状态id="+id+"calibration="+calibration);
			userMapper.updateCalibration(user);
		}catch (Exception e) {
			logger.info(e.getMessage());
		}
		return re;
	}
	/**\
	 * 忘记密码,重置密码
	 * @param m
	 * @return
	 */
	@RequestMapping("recoverpassword")
	@ResponseBody
	public ResultBase recoverpassword(@RequestBody DataRow m){
		ResultBase re = new ResultBase();
		String phone = (String) m.get("phone");
		String code =  (String) m.get("code");
		String password=(String) m.get("password");
		if( phone !=null && phone !="" && password !=null && password !=""){
		Usercode uservode = new Usercode();
		uservode.setCode(code);
		uservode.setPhoen(phone);
		if(usercodeservic.ifusercode(uservode)){
			User u = userMapper.ifUser(phone);
			if(u!=null){
			u.setPassword(MD5Util.MD5(password));
			userService.updateById(u);
			re.setCode(200);
			re.setMessage("找回密码成功！！！");
		}else {
			re.setCode(350);
			re.setMessage("帐号不存在,请重新注册");
		}	
		}else {
			re.setCode(350);
			re.setMessage("验证码错误！！！");
		}
		}else{
			re.setCode(350);
			re.setMessage("请确认参数是否带整齐！");
		
		}
		return re;
	}
	/**
	 * 个人中心数据
	 * @param m
	 * @return
	 */
	@RequestMapping("queryPersonalCenter")
	@ResponseBody
	public ResultData<DataRow> queryPersonalCenter(@RequestBody DataRow map){
		ResultData<DataRow> re = new ResultData<DataRow>();
		try {
			re = userService.queryPersonalCenter(map,re);
		} catch (Exception e) {
			logger.info("UserController>>>>>>>>>>>>>>>queryPersonalCenter",e);
		}
		return re;
	}
}
