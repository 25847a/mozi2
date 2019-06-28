package com.sy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
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
import com.sy.common.ResultData;
import com.sy.mapper.EquipmentDataMapper;
import com.sy.mapper.EquipmentMapper;
import com.sy.mapper.JfhealthMapper;
import com.sy.mapper.JfhealthNewMapper;
import com.sy.mapper.JfhealthdaoMapper;
import com.sy.mapper.PushMapper;
import com.sy.mapper.UserMapper;
import com.sy.nettyulit.NettyChannelMap;
import com.sy.pojo.Equipment;
import com.sy.pojo.EquipmentData;
import com.sy.pojo.Jfhealth;
import com.sy.pojo.JfhealthNew;
import com.sy.pojo.Jfhealthdao;
import com.sy.pojo.Push;
import com.sy.pojo.User;
import com.sy.pojo.UserEq;
import com.sy.service.EquipmentService;
import com.sy.service.UserEqService;
import com.sy.service.UserService;
import com.sy.service.UseravatarService;
import com.sy.utils.DataRow;
import com.sy.utils.DateUtil;
import com.sy.utils.GB2312Utils;
import com.sy.utils.MD5Util;
import com.sy.utils.Managementconstant;
import com.sy.utils.PageModel;
import com.sy.vo.LoginReturn;
import com.sy.vo.Loginuser;
import com.sy.vo.Usermanagement;
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
	JfhealthMapper jfhealthMapper;
	JfhealthNewMapper jfhealthNewMapper;
	JfhealthdaoMapper jfhealthdaoMapper;
	
	PushMapper pushMapper;
	@Override
	public boolean addUser(User u) {
		u.setCreatetime(new Date());
		String p=u.getPassword();
		u.setPassword(MD5Util.MD5(p));
		int num = userMapper.insertSelective(u);
		 if (num != 0) {
				return true;
			} else {
				return false;
			}
	}

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

	@Override
	public boolean updateUser(User u) {
		Integer num =userMapper.updateById(u);
		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updatepassword(String password, String newpassword,Integer id) {
	
		password = MD5Util.MD5(password);
		newpassword= MD5Util.MD5(newpassword);
		Map m= new HashMap();
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

	@Override
	public PageModel<User> getusersone(Integer pageNo, String keyWord) {
		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		 //获取数据总数
		    Integer count=userMapper.getcount(keyWord);
		    Integer pageSize=10;
		    List<User>Feedbacks = new ArrayList<User>();
		    Integer pageNo1 = ( pageNo - 1) * pageSize;
		    //获取页数
		    HashMap<String, Object> map = new HashMap<>();
		    map.put("pageNo", pageNo1);
		    map.put("keyWord", keyWord);
		    map.put("pageSize", pageSize);
		    List<Object> objs  = userMapper.list(map);
		    for (Object obj :objs){
		    	String  userid = obj.toString();
		    	Feedbacks.add(userMapper.selectByPrimaryKey(Integer.parseInt(userid)));
		    }
		    PageModel<User> pageModel = new PageModel<User>(pageNo, pageSize,count, Feedbacks,"user/list");
		if(pageModel.getCount() !=0){
			pageModel.init();
		}
		return pageModel;
	}
	private final String url = "http://gw.api.taobao.com/router/rest";
	private final String appkey = "23573164";
	private final String secret = "a81e2de9bc2ed29d394e212314a37226";
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
 public static void main(String[] args) {
	 sendSMS1(null);
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
	/**
	 * 啊健写的 查询使用者的详情信息
	 * @param map
	 * @return
	 */
	@Override
	public User queryUserInfo(Map map) {
		User user = userMapper.queryUserInfo(map);
		return user;
	}

	@Override
	public User selectaccount(String account) {
		User  us =userMapper.selectaccount(account);
		return us;
	}
	/**
	 * 啊健写的 查询使用者的详情信息
	 * @param map
	 * @return
	 */
	@Override
	public User queryHomepageUserInfo(Map<String, Object> map)throws Exception {
		User user =userMapper.queryHomepageUserInfo(map);
		return user;
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
	@Override
	public ResultData<Loginuser> addUsermanagement(Usermanagement u, ResultData<Loginuser> re) throws Exception {
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
			User user = new User();
			user.setRole("使用者");
			user.setImei(u.getImei());
			user.setAccount(u.getAccount());
			user.setName(u.getName());
			user.setPhone(u.getAccount());
			user.setCreatetime(new Date());
			user.setPassword(MD5Util.MD5(u.getPassword()));
			user.setAge(DateUtil.getAgeByBirth(u.getBorn()));
			user.setAvatar(useravatarService.selectavartar().getAvatar());
			user.setGender(u.getGender());
			user.setAddress(u.getAddress());
			user.setWeight(u.getWeight());
			user.setHeight(u.getHeight());
			user.setBorn(u.getBorn());
			EntityWrapper<UserEq> ew = new EntityWrapper<UserEq>();
			ew.eq("user_id", u.getMid());
			UserEq eq =userEqService.selectOne(ew);
			if(eq!=null){
				user.setFollow(1);
			}else{
				user.setFollow(0);
			}
			boolean key = userService.insert(user);
			boolean jfstatus = HealthtoolServiceImpl.registered(Managementconstant.channel_id + String.valueOf(user.getId()),"12345", "123456");
			//在惊凡注册成功
			if (jfstatus) {
			//设备与监护人的关联关系
			UserEq ue = new UserEq();
			//就是mid
			ue.setUserId(u.getMid());
			ue.setEqId(e.getId());
			ue.setTypeof(0);
			//设备与使用者的关联关系
			UserEq uue = new UserEq();
			uue.setUserId(user.getId());
			uue.setEqId(e.getId());
			uue.setTypeof(2);
			userEqService.addUserEq(ue);
			userEqService.addUserEq(uue);
			EquipmentData data = new EquipmentData();
			data.setUserId(user.getId());
			data.setCreatetime(new Date());
			equipmentDataMapper.insert(data);
			Jfhealth bean = new Jfhealth();
			bean.setPhone("mozistar"+user.getId());
			bean.setImei(e.getImei());
			bean.setCreatetime(new Date());
			jfhealthMapper.insert(bean);
			 Push push = new Push();
			    push.setUserId(user.getId());
			    push.setAlias(u.getMid());
			    push.setAllNotifyOn(true);
			    pushMapper.insert(push);
				JfhealthNew jfhealthnew = new JfhealthNew();
				jfhealthnew.setCreatetime(new Date());
				jfhealthnew.setPhone("mozistar"+user.getId());
				jfhealthnew.setImei(e.getImei());
				jfhealthNewMapper.insert(jfhealthnew);
				Jfhealthdao jfhealthdao = new Jfhealthdao();
				jfhealthdao.setCreatetime(new Date());
				jfhealthdao.setPhone("mozistar"+user.getId());
				jfhealthdao.setImei(e.getImei());
				jfhealthdaoMapper.insert(jfhealthdao);
			Loginuser luser = new Loginuser(user.getId(),
						u.getRole(), u.getName(), u.getAge(),
						u.getGender(), user.getPhone(),
						u.getAddress(), user.getAvatar(),
						u.getCreatetime(),
						u.getWeight(), u.getHeight(), u.getBorn());
						re.setData(luser);
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
}
