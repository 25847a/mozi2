package com.sy.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.mapper.EquipmentDataMapper;
import com.sy.mapper.EquipmentMapper;
import com.sy.mapper.JfhealthMapper;
import com.sy.mapper.JfhealthNewMapper;
import com.sy.mapper.JfhealthdaoMapper;
import com.sy.mapper.MemberMapper;
import com.sy.mapper.PositionigMapper;
import com.sy.mapper.PushMapper;
import com.sy.mapper.SensorstatusMapper;
import com.sy.mapper.UserMapper;
import com.sy.nettyulit.NettyChannelMap;
import com.sy.pojo.Equipment;
import com.sy.pojo.EquipmentData;
import com.sy.pojo.Jfhealth;
import com.sy.pojo.JfhealthNew;
import com.sy.pojo.Jfhealthdao;
import com.sy.pojo.Member;
import com.sy.pojo.Positionig;
import com.sy.pojo.Push;
import com.sy.pojo.Sensorstatus;
import com.sy.pojo.User;
import com.sy.pojo.UserEq;
import com.sy.pojo.Useravatar;
import com.sy.pojo.Usercode;
import com.sy.service.EquipmentService;
import com.sy.service.UserEqService;
import com.sy.service.UserService;
import com.sy.service.UseravatarService;
import com.sy.service.UsercodeService;
import com.sy.utils.DataRow;
import com.sy.utils.DateUtil;
import com.sy.utils.DeleteFileUtil;
import com.sy.utils.GB2312Utils;
import com.sy.utils.ImageUtil;
import com.sy.utils.JfUtil;
import com.sy.utils.MD5Util;
import com.sy.utils.Managementconstant;
import com.sy.vo.LoginReturn;
import com.sy.vo.Loginuse;
import com.sy.vo.Personal;
import com.sy.vo.Usermanagement;
import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;
import net.sf.json.JSONObject;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Value("#{configProperties['jdbc.server']}")
	private String baseUrl;
	@Autowired
	EquipmentService equipmentService;
	@Autowired
	UserService userService;
	@Autowired
	UserEqService userEqService;
	@Autowired
	UseravatarService useravatarService;
	@Autowired
	EquipmentDataMapper equipmentDataMapper;
	@Autowired
	JfhealthMapper jfhealthMapper;
	@Autowired
	JfhealthNewMapper jfhealthNewMapper;
	@Autowired
	JfhealthdaoMapper jfhealthdaoMapper;
	@Autowired
	PushMapper pushMapper;
	@Autowired
	EquipmentMapper equipmentMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	private UsercodeService usercodeservic;
	@Autowired
	SensorstatusMapper sensorstatusMapper;
	@Autowired
	PositionigMapper positionigMapper;
	@Override
	public boolean ifUser(String account) {
		User u = userMapper.ifUser(account);
		if(u==null){
			return true;
		}else {
			return false;
		}
		
	}
	/**
	 * 注册用户
	 * @param data
	 * @return
	 */
	public ResultData<Loginuse> addUser(User u,ResultData<Loginuse> re)throws Exception{
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
						u.setPassword(MD5Util.MD5(u.getPassword()));
						boolean status = userService.insert(u);
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
	 * 注册用户
	 * @param data
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public ResultData<Loginuse> addUser111(User user,ResultData<Loginuse> re)throws Exception{
		Useravatar u = new Useravatar();
		u.setAvatar("ddddddd");
		useravatarService.insert(u);
		Useravatar uuu =null;
		System.out.println(uuu.getAvatar());
		return re;
	}
	/**
	 * 用户登陆
	 * @param data
	 * @return
	 */
	@Override
	public ResultData<LoginReturn> landingUser(DataRow data, ResultData<LoginReturn> re) throws Exception {
		
		User user = userMapper.selectaccount(data.getString("account"));
		if(user!=null){
			String 	password = MD5Util.MD5(data.getString("password"));
			EntityWrapper<User> ew = new EntityWrapper<User>();
			ew.eq("account", user.getAccount());
			ew.eq("password", password);
			ew.eq("isDelete", 0);
			User u =this.selectOne(ew);
			if(u!=null){
				LoginReturn login = userMapper.queryPersonalCenter(u.getId());
				login.setAvatar(login.getAvatar()==null?useravatarService.selectavartar().getAvatar():login.getAvatar());
				re.setCode(200);
				re.setData(login);
				re.setMessage("登陆成功");
			}else{
				re.setCode(305);
				re.setMessage("密码错误");
			}
		}else{
			re.setCode(305);
			re.setMessage("帐号不存在,请注册");
		}
		return re;
	}
	@Override
	public boolean uploadavatar(String avatar, Integer id) {
		User u = new User();
		u.setAvatar(baseUrl+avatar);
		u.setId(id);
		Integer num =userMapper.updateById(u);
		 if (num != 0) {
				return true;
			} else {
				return false;
			}
	}

	@Override
	public User getUser(Integer id) {
		if(userMapper ==null ){
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
            userMapper=(UserMapper)webApplicationContext.getBean("userMapper");
		}
		Map<String,Object> map = new HashMap<>();
		map.put("userid", id);
		User user = null;
		try {
			user  = userMapper.queryHomepageUserInfo(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public User getUser(String imei) {
		if(userMapper ==null ){
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
			userMapper=(UserMapper)webApplicationContext.getBean("userMapper");
		}
		
		User user = userMapper.getUser(imei);
		return user;
	}
	/**
	 * 更新用户信息
	 * 
	 * @param u
	 * @return
	 */
	@Override
	public ResultBase updateUser(User u,ResultBase re) throws Exception{
		if(u.getBorn()!=null){
			u.setAge(DateUtil.getAgeByBirth(u.getBorn()));
		}
		int num =userMapper.updateById(u);
		if (num != 0) {
			if(u.getAddress()!=null||u.getName()!=null || u.getCity()!=null|| u.getProvince()!=null|| u.getArea()!=null){
				u=userMapper.selectById(u.getId());
				Channel c =	NettyChannelMap.get(u.getImei());
				String R06 = "$R06|";
				R06 += GB2312Utils.gb2312eecode(u.getName()) + ":";
				R06 += GB2312Utils.gb2312eecode(u.getProvince()+u.getCity()+u.getArea()+u.getAddress()) + "\r\n";
				if(c!=null){
					c.writeAndFlush(R06);
				}
			}
			
			re.setCode(200);
			re.setMessage("修改成功");
		} else {
			re.setCode(400);
			re.setMessage("修改失败");
		}
		return re;
	}
	/**
	 * 更新监护者用户信息
	 * @param u
	 * @return
	 */
	public ResultBase updateAliasUser(User u,ResultBase re)throws Exception{
		int row = userMapper.updateById(u);
		if(row>0){
			re.setCode(200);
			re.setMessage("修改成功");
		}else{
			re.setCode(400);
			re.setMessage("修改失败");
		}
		return re;
	}
	/**
	 * 更新头像
	 * @param avatar
	 * @param id
	 * @return
	 */
	@Override
	public ResultData<String> updateavatar(CommonsMultipartFile avatar ,Integer id,ResultData<String> re)throws Exception{
		 User u = userService.getUser(id);
		 String[] st = u.getAvatar().split("/");
		 DeleteFileUtil.deleteFile("E:/Project/" + st[3] + "/" + st[4]);
			File file = new File("E:\\Project\\avatars");
			if (!file.exists()) { //文件不存在 创建文件
				file.mkdirs();
	        }
		new File("E:\\Project\\avatars").mkdirs();
		String imageName =System.currentTimeMillis()+avatar.getOriginalFilename().substring(avatar.getOriginalFilename().indexOf("."),avatar.getOriginalFilename().length());
		ImageUtil.createThumbnail(avatar.getInputStream(), "E:\\Project\\avatars"+File.separator+imageName, 500, 600);
		u.setAvatar(baseUrl + "avatars/" + imageName);
		boolean status =userService.updateById(u);
		if (status) {
			re.setData(u.getAvatar());
			re.setCode(200);
			re.setMessage("修改头像成功！！!");
		} else {
			re.setMessage("修改头像失败！！！");
			re.setCode(400);

		}
		return re;
	}
	@Override
	public boolean updatepassword(String password, String newpassword,Integer id) {
	
		password = MD5Util.MD5(password);
		newpassword= MD5Util.MD5(newpassword);
		Map<String,Object> m= new HashMap<String,Object>();
		m.put("password", password);
		m.put("id", id);
		User olp = userMapper.getpassword(m);
		if(olp != null){
			olp.setPassword(newpassword);
			userMapper.updateById(olp);
			return true;
		}else {
			return false;
		}
		
	}
	/*private final String url = "http://gw.api.taobao.com/router/rest";
	private final String appkey = "23573164";
	private final String secret = "a81e2de9bc2ed29d394e212314a37226";*/
	@Override
	public  Integer sendSMS(String phone) {

		int smsMsg = getRandNum(1, 999999);
		//设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		//初始化ascClient需要的几个参数
		final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
		final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
		//替换成你的AK
		final String accessKeyId = "LTAIcx9GAEhZ8XOE";//你的accessKeyId,参考本文档步骤2
		final String accessKeySecret = "FqdButvAztVgmDSCKw2dpInGTXax6z";//你的accessKeySecret，参考本文档步骤2
		//初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
		accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IAcsClient acsClient = new DefaultAcsClient(profile);

		 //组装请求对象
		 SendSmsRequest request = new SendSmsRequest();
		 //使用post提交
		 request.setMethod(MethodType.POST);
		 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
		 request.setPhoneNumbers(phone);
		 //必填:短信签名-可在短信控制台中找到
		 request.setSignName("墨子星");
		 //必填:短信模板-可在短信控制台中找到
		 request.setTemplateCode("SMS_136045082");
		 //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		 //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		 JSONObject js = new JSONObject();
		 js.put("code", smsMsg);
		 request.setTemplateParam(js.toString());
		 //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		 //request.setSmsUpExtendCode("90997");

		 //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		 request.setOutId("yourOutId");

		//请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = null;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
		//请求成功
		}
			return smsMsg;
	}
	public  static Integer sendSMS1(String phone) {

		int smsMsg = getRandNum(1, 999999);
		//设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		//初始化ascClient需要的几个参数
		final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
		final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
		//替换成你的AK
		final String accessKeyId = "LTAIcx9GAEhZ8XOE";//你的accessKeyId,参考本文档步骤2
		final String accessKeySecret = "FqdButvAztVgmDSCKw2dpInGTXax6z";//你的accessKeySecret，参考本文档步骤2
		//初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
		accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IAcsClient acsClient = new DefaultAcsClient(profile);

		 //组装请求对象
		 SendSmsRequest request = new SendSmsRequest();
		 //使用post提交
		 request.setMethod(MethodType.POST);
		 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
		 request.setPhoneNumbers("13539264097");
		 //必填:短信签名-可在短信控制台中找到
		 request.setSignName("墨子星");
		 //必填:短信模板-可在短信控制台中找到
		 request.setTemplateCode("SMS_136045082");
		 //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		 //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		 request.setTemplateParam("{\"code\":\"123\"}");
		 //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		 //request.setSmsUpExtendCode("90997");

		 //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		 request.setOutId("116736564");

		//请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = null;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
		//请求成功
		}
			return smsMsg;
	}
	public static int getRandNum(int min, int max) {
		int randNum = min + (int) (Math.random() * ((max - min) + 1));
		return randNum;
	}

	@Override
	public User phoenselectuser(String phone) {
		 List<User>  us =userMapper.phoenselectuser(phone);
		return us.get(0);
	}
	@Override
	public User selectaccount(String account) {
		User  us =userMapper.selectaccount(account);
		return us;
	}
	@Override
	public Integer deleteUser(Integer userId) {
		int deleteByPrimaryKey = userMapper.deleteUser(userId);
		return deleteByPrimaryKey;
	}
	/**
	 * 根据设备号获取使用者id
	 */
	public Integer selectId(String imei) {
		if(userMapper ==null ){
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
            userMapper=(UserMapper)webApplicationContext.getBean("userMapper");
		}
		User user = userMapper.getUser(imei);
		Integer id = user!=null?user.getId():null;
		return id;
	}
	/**
	 *  添加使用者
	 * @param u
	 * @return
	 * @throws Exception 
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public ResultBase addUsermanagement(Usermanagement u, ResultBase re) throws Exception {
		SocketChannel c = (SocketChannel) NettyChannelMap.get(u.getImei());
		Equipment e =  equipmentService.selectquipmentimei(u.getImei());
			if (e == null){
				re.setCode(350);
				re.setMessage("设备号不存在,请联系经销商！！！");
				return re;
			}
			User user2 = userService.getUser(u.getImei());
			if(user2!=null){
				re.setCode(350);
				re.setMessage("该设备使用者已经存在！！！");
				return re;
			}
			boolean ifguardianship = userEqService.ifguardianship(e.getId());
			if(ifguardianship){
				re.setCode(350);
				re.setMessage("该设备监护者已经存在！！！");
				return re;
			}
			user2=userMapper.ifUser(u.getAccount());
			if(user2!=null){
				re.setCode(350);
				re.setMessage("该注册手机号码已存在");
				return re;
			}
			User user = new User();
			user.setRole("使用者");
			user.setImei(u.getImei());
			user.setAccount(u.getAccount());
			user.setName(u.getName());
			user.setPhone(u.getAccount());
			user.setPassword(MD5Util.MD5(u.getPassword()));
			user.setAge(DateUtil.getAgeByBirth(u.getBorn()));
			user.setAvatar(useravatarService.selectavartar().getAvatar());
			user.setGender(u.getGender());
			user.setAddress(u.getAddress());
			user.setWeight(u.getWeight());
			user.setHeight(u.getHeight());
			user.setBorn(u.getBorn());
			user.setProvince(u.getProvince());
			user.setArea(u.getArea());
			user.setCity(u.getCity());
			user.setIllness(u.getIllness());
			userService.insert(user);
			//插入会员表
			Member me = new Member();
			me.setUserId(user.getId());
			me.setEndTime(DateUtil.getNextDay(20));
			memberMapper.insert(me);
			boolean jfstatus = JfUtil.registered(Managementconstant.channel_id + String.valueOf(user.getId()),"12345", "123456");
			//在惊凡注册成功
			if (jfstatus) {
			EntityWrapper<UserEq> ew = new EntityWrapper<UserEq>();
			ew.eq("user_id", u.getMid());
			ew.eq("follow", 1);
			UserEq eq =userEqService.selectOne(ew);
			UserEq ue = new UserEq();
			if(eq==null){
				//设备与监护人的关联关系
				//就是mid
				ue.setUserId(u.getMid());
				ue.setEqId(e.getId());
				ue.setTypeof(0);
				ue.setFollow(1);
			}else{
				//设备与监护人的关联关系
				//就是mid
				ue.setUserId(u.getMid());
				ue.setEqId(e.getId());
				ue.setTypeof(0);
				ue.setFollow(0);
			}
			userEqService.insert(ue);
			//设备与使用者的关联关系
			ue.setUserId(user.getId());
			ue.setEqId(e.getId());
			ue.setTypeof(2);
			ue.setFollow(0);
			userEqService.insert(ue);
			EquipmentData data = new EquipmentData();
			data.setUserId(user.getId());
			equipmentDataMapper.insert(data);
			Jfhealth bean = new Jfhealth();
			bean.setPhone("mozistar"+user.getId());
			bean.setImei(e.getImei());
			jfhealthMapper.insert(bean);
			Push push = new Push();
			push.setUserId(user.getId());
			push.setAlias(u.getMid());
			pushMapper.insert(push);
			JfhealthNew jfhealthnew = new JfhealthNew();
			jfhealthnew.setPhone("mozistar"+user.getId());
			jfhealthnew.setImei(e.getImei());
			jfhealthNewMapper.insert(jfhealthnew);
			Jfhealthdao jfhealthdao = new Jfhealthdao();
			jfhealthdao.setPhone("mozistar"+user.getId());
			jfhealthdao.setImei(e.getImei());
			jfhealthdaoMapper.insert(jfhealthdao);
			Positionig p = new Positionig();
			p.setImei(user.getImei());
			p.setPositioningData("39.9134905988:116.4072638138");
			p.setPositioningS("0");
			positionigMapper.insert(p);
			Sensorstatus s = new Sensorstatus();
			s.setImei(user.getImei());
			s =sensorstatusMapper.selectOne(s);
			if(s==null){
				s = new Sensorstatus();
				s.setImei(user.getImei());
				s.setH("H:1");
				s.setG("G:1");
				s.setAdddate(new Date());
				sensorstatusMapper.insert(s);
			}
			
						re.setCode(200);
						re.setMessage("添加设备使用者成功！！！");
						if(c!=null){
							c.writeAndFlush("$R06|"+GB2312Utils.gb2312eecode(user.getName())+":"+GB2312Utils.gb2312eecode(user.getAddress())+"\r\n");
						}
					}else {
						re.setCode(350);
						re.setMessage("添加失败,JG！！！");
					}
		return re;
	}
	/**
	 * 点击卡片接口
	 * @return
	 */
	@Override
	public ResultData<DataRow> userdata(DataRow map,ResultData<DataRow> re) throws Exception{
		DataRow data = userMapper.queryUserData(map);
		if(data!=null){
			data.put("type_of", data.getInt("type_of")==1?1:2);
			data.put("jfdataUpdateTime", data.getString("jfdataUpdateTime")+"分钟");
			data.put("avatar",data.getString("avatar")==null? useravatarService.selectavartar().getAvatar():data.getString("avatar"));
			DataRow row =equipmentMapper.queryEquipmentMember(data.getString("imei"));
			if(row!=null){
				if(row.get("bluetoothList")==null || row.getString("bluetoothList").equals("") || row.getString("bluetoothList").equals("[]")){
					row.put("bluetooth_name", "");
					row.put("bluetoothList", 0);
				}else{
					if(row.getInt("bluetooth_type")==0){
						String bluetooths = row.getString("bluetoothList").substring(1, row.getString("bluetoothList").length() - 1).replace("\"", "");
						String[] bluetoothList = bluetooths.split(",");
						row.put("bluetooth_name", bluetoothList[0]);
					}
					row.put("bluetoothList", 1);
					
				}
				row.put("eq_status", row.getString("eq_status").equals("H:0")?0:1);
				
				row.put("bluetooth_type", row.getInt("bluetooth_type"));
			}
			data.put("equipment", row);
			re.setCode(200);
			re.setMessage("获取设备使用者信息成功！！！");
			re.setData(data);
			
		}else{
			re.setCode(400);
			re.setMessage("查询不到用户,多次查询无效请联系管理员");
		}
		return re;
	}
	/**
	 * 查看个人资料
	 * @return
	 */
	@Override
	public ResultData<DataRow> queryUserInfo(DataRow map, ResultData<DataRow> re) throws Exception {
		map=userMapper.queryUserInfo(map);
		if(map!=null){
			re.setData(map);
			re.setCode(200);
			re.setMessage("获取个人信息成功");
		}else{
			re.setCode(400);
			re.setMessage("获取个人信息失败");
		}
		return re;
	}
	/**
	 * 个人中心数据
	 * @param map
	 * @return
	 */
	@Override
	public ResultData<DataRow> queryPersonalCenter(DataRow map, ResultData<DataRow> re) throws Exception {
		Personal personal =userMapper.queryPersonalCenter2(map.getInt("alias"));
		if(personal!=null){
			personal.setAvatar(personal.getAvatar()==null?useravatarService.selectavartar().getAvatar():personal.getAvatar());
			re.setCode(200);
			re.setData(personal);
			re.setMessage("获取成功");
		}else{
			re.setCode(400);
			re.setMessage("获取失败,请重新登录APP尝试下");
		}
		return re;
	}

}
