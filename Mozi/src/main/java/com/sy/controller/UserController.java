package com.sy.controller;

import java.io.File;
import java.util.Map;
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
import org.springframework.web.servlet.ModelAndView;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
import com.sy.service.UseravatarService;
import com.sy.service.UsercodeService;
import com.sy.utils.DataRow;
import com.sy.utils.DeleteFileUtil;
import com.sy.utils.GB2312Utils;
import com.sy.utils.MD5Util;
import com.sy.utils.PageModel;
import com.sy.vo.LoginReturn;
import com.sy.vo.Loginuse;
import com.sy.vo.Loginuser;
import com.sy.vo.Usermanagement;
import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

@Controller
@RequestMapping(value = "user", method = RequestMethod.POST)
public class UserController {
	private static final String String = null;
	@Autowired
	private GroupPhoneMapper groupPhoneMapper;
	@Autowired
	private UseravatarService useravatarservice;
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
	public ResultBase selectImei(@RequestBody Map map) {
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
		if (u.getAccount() != null && u.getAccount().length() > 4
				&& u.getAccount().length() < 12) {
			if (u.getPassword() != null && u.getPassword() != ""
					&& !u.getPassword().equals("")) {
				if (userService.ifUser(u.getAccount())) {
					u.setRole("管理者");
					if (u.getName() == null || u.getName() == "") {
						u.setName(u.getAccount());
					}
					Usercode uservode = new Usercode();
					uservode.setCode(u.getCode());
					uservode.setPhoen(u.getPhone());
					if (usercodeservic.ifusercode(uservode)) {
						boolean status = userService.addUser(u);
						if (status) {
							EntityWrapper<User> ew = new EntityWrapper<User>();
							ew.eq("password", MD5Util.MD5(u.getPassword()));
							ew.eq("account", u.getAccount());
							ew.eq("isDelete", 0);
							User ulog = userService.selectOne(ew);
							Loginuse luser = new Loginuse(ulog.getId(),
									ulog.getRole(), ulog.getName(),
									ulog.getAge(), ulog.getGender(),
									ulog.getPhone(), ulog.getAddress(),
									ulog.getAvatar(), 
									 ulog.getCreatetime(),
									 ulog.getWeight(),
									ulog.getHeight(), ulog.getBorn());
							re.setData(luser);
							re.setMessage("添加成功！！！");
							re.setCode(200);
						} else {
							re.setMessage("添加失败！！！");
							re.setCode(400);
						}
					} else {
						re.setMessage("验证码有误！！！");
						re.setCode(405);
					}

				} else {
					re.setMessage("该账号存在！！！");
					re.setCode(405);
				}
			} else {
				re.setMessage("密码格式有错！！！");
				re.setCode(405);
			}
		} else {
			re.setMessage("账号格式有错误！！！");
			re.setCode(350);
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
	 * 
	 * @param avatar
	 * @param id
	 * @return
	 */
	@RequestMapping("updateavatar")
	@ResponseBody
	public ResultData<String> updateavatar(
			@RequestParam(value = "avatar", required = false) CommonsMultipartFile avatar,
			Integer id) {
		ResultData<String> re = new ResultData<String>();
		User u = userService.getUser(id);
		 try {
		 String[] st = u.getAvatar().split("/");
		 DeleteFileUtil.deleteFile("E:/Project/" + st[3] + "/" + st[4]);
		 } catch (Exception e) {
			 logger.info(e.getMessage());
		 }

		new File("E:\\Project\\avatars").mkdirs();
		com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\avatars", avatar);
		boolean status = userService.uploadavatar(
				"avatars/" + avatar.getOriginalFilename(), id);
		if (status) {
			String url = baseUrl + "avatars/" + avatar.getOriginalFilename();
			u.setAvatar(url);
			userService.updateUser(u);
			re.setData(url);
			re.setCode(200);
			re.setMessage("修改头像成功！！!");
		} else {
			re.setMessage("修改头像失败！！！");
			re.setCode(400);

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
	public ResultBase updateUser(@RequestBody User u) {
		ResultBase re = new ResultBase();
		re.setCode(200);
		re.setMessage("修改成功");
		
		String address = u.getAddress();
		String name = u.getName();
		String phone = u.getPhone();
		
		if(address!=null||name!=null){
			userService.updateUser(u);
			User user = userService.getUser(u.getId());
			
			if(user.getAccount()==null){
				
			Channel c =	NettyChannelMap.get(user.getImei());
				
				String R06 = "$R06|";
				try {
						R06 += GB2312Utils.gb2312eecode(user.getName()) + ":";
						R06 += GB2312Utils.gb2312eecode(user.getAddress()) + "\r\n";
				} catch (Exception e) {
					logger.info(e.getMessage());
					R06 = "$R06|ERR\r\n";
				}
				if(c!=null){
					c.writeAndFlush(R06);
				}
			}
		}else if(phone!=null){
			
			GroupPhone selectPhone = groupPhoneMapper.selectPhone(phone);
			if(selectPhone==null){
				re.setCode(350);
				re.setMessage("号码不存在");
			}else{
				userService.updateUser(u);
			}
		}else{
			boolean status = userService.updateUser(u);
			if (!status) {
				re.setMessage("修改失败");
				re.setCode(400);
			}
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
	public ResultBase updatepassword(@RequestBody Map m) {
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

	@RequestMapping(value = "list")
	public ModelAndView list(Integer pageNo, String keyword) {
		ModelAndView mo = new ModelAndView();
		PageModel<User> pagemodel = userService.getusersone(pageNo, keyword);
		mo.setViewName("user");
		mo.addObject("pagemodel", pagemodel);
		return mo;
	}
	/**
	 * 获取验证码
	 * @param m
	 * @return
	 */
	@RequestMapping("sendSMS")
	@ResponseBody
	public ResultBase sendSMS(@RequestBody Map m) {
		System.out.println("获取验证码================" + m.get("phone"));
		ResultBase re = new ResultBase();
		Integer smsMsg = userService.sendSMS(String.valueOf(m.get("phone")));
		if (smsMsg != 0) {
			Usercode c = new Usercode();
			c.setCode(String.valueOf(smsMsg));
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

	public static int getRandNum(int min, int max) {
		int randNum = min + (int) (Math.random() * ((max - min) + 1));
		return randNum;
	}

	/**
	 *  添加使用者
	 * @param u
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("addUsermanagement")
	@ResponseBody
	public ResultData<Loginuser> addUsermanagement(@RequestBody Usermanagement u) throws Exception {
		ResultData<Loginuser> re = new ResultData<Loginuser>();
		try{
			re=userService.addUsermanagement(u,re);
		}catch (Exception e) {
			logger.info(e.getMessage());
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
	 * 通过role判断该用户为该设备的那种角色---啊健
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("queryHomepageUserInfo")
	@ResponseBody
	public ResultData<User> queryHomepageUserInfo(
			@RequestBody Map<String, Object> map) {
		ResultData<User> re = new ResultData<User>();
		try {
			User user = userService.queryHomepageUserInfo(map);
			if (null != user && user.getRole().equals("使用者")) {
				if (user.getAvatar() == null) {
					user.setAvatar(useravatarservice.selectavartar()
							.getAvatar());
				}
				String calibration = user.getCalibration();
				if(calibration == null){
					user.setCalibration("0");
				}
				re.setCode(200);
				re.setData(user);
				re.setMessage("获取使用者个人信息成功！！");
			} else {
				re.setCode(400);
				re.setMessage("失败,必须是使用者ID或者权限为使用者！！！");
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return re;

	}
	/**
	 * 设置用户围栏信息
	 * 
	 * @param id rodius midpoint
	 * @return
	 */
	@RequestMapping("setRodiusAndMidpoint")
	@ResponseBody
	public ResultBase setRodiusAndMidpoint(@RequestBody User user) {
		ResultBase re = new ResultBase();
		
		try{
			
				userMapper.setRodiusAndMidpoint(user);
				re.setMessage("修改成功");
				re.setCode(200);
				
			}catch (Exception e) {
				logger.info(e.getMessage());
				re.setMessage("修改失败");
				re.setCode(400);
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
			//BluetoothMap.deletesb(imei);
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
			String calibration = user.getCalibration();
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
	public ResultBase recoverpassword(@RequestBody Map m){
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
			userService.updateUser(u);
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
	
}
