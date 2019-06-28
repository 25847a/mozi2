package com.sy.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sy.common.ResultData;
import com.sy.mapper.ChatMapper;
import com.sy.mapper.EquipmentDataMapper;
import com.sy.mapper.EquipmentMapper;
import com.sy.mapper.JfhealthNewMapper;
import com.sy.mapper.JfhealthdaoMapper;
import com.sy.mapper.PositionigMapper;
import com.sy.mapper.PushMapper;
import com.sy.mapper.SensorstatusMapper;
import com.sy.mapper.UserEqMapper;
import com.sy.mapper.UserMapper;
import com.sy.mapper.UsercodeMapper;
import com.sy.mapper.WaveformMapper;
import com.sy.pojo.Equipment;
import com.sy.pojo.EquipmentData;
import com.sy.pojo.GroupPhone;
import com.sy.pojo.JfhealthNew;
import com.sy.pojo.User;
import com.sy.pojo.UserEq;
import com.sy.pojo.Waveform;
import com.sy.service.GroupPhoneService;
import com.sy.service.JfhealthdaoService;
import com.sy.service.UserEqService;
import com.sy.service.UserService;
import com.sy.service.UseravatarService;
import com.sy.utils.DataRow;
import com.sy.utils.DataUtil;
import com.sy.vo.Userdata;

@Service
public class UserEqServiceImpl extends ServiceImpl<UserEqMapper, UserEq> implements UserEqService {
	@Autowired
	private UseravatarService useravatarservice;
	@Autowired
	private JfhealthNewMapper jMapperNew;
	@Autowired
	private JfhealthdaoService jfhealthFdaoservice;
	@Autowired
	private UserEqMapper eqMapper;
	@Autowired
	private EquipmentMapper equipmentMapper;
	@Autowired
	private UserService userservice;
	@Autowired
	private EquipmentDataMapper equipmentDataMapper;
	@Autowired
	private UserEqService userEqservice;
	@Autowired
	private UserMapper usermapper;
	@Autowired
	private PushMapper pushMapper;
	@Autowired
	private UserEqService usereqservice;
	@Autowired
	private GroupPhoneService groupPhoneMapper;
	@Autowired
	ChatMapper chatMapper;
	@Autowired
	JfhealthdaoMapper jfhealthdaoMapper;
	@Autowired
	SensorstatusMapper sensorstatusMapper;
	@Autowired
	UsercodeMapper usercodeMapper;
	@Autowired
	PositionigMapper positionigMapper;
	@Autowired
	WaveformMapper waveformMapper;
	@Autowired
	UserEqMapper userEqMapper;
	@Autowired
	UseravatarService useravatarService;
	/**
	 * 关注列表
	 * @param u
	 * @return
	 */
	@Override
	public ResultData<List<Map<String, Object>>> queryUserEqFollowList(Map<String, Object> map,ResultData<List<Map<String, Object>>> re) throws Exception {
		List<Map<String, Object>> users=userEqMapper.queryUserEqFollowUsersList(map);
		List<Map<String, Object>> observer=userEqMapper.queryUserEqFollowObserverList(map);
		for(int i=0;i<users.size();i++){
			if(users.get(i).get("avatar")==null){
				users.get(i).put("avatar", useravatarService.selectavartar().getAvatar());
			}
			users.get(i).put("eq_status", String.valueOf(users.get(i).get("eq_status")).equals("H:0")?false:true);
			users.get(i).put("bluetooth_type", String.valueOf(users.get(i).get("bluetooth_type")).equals("0")?false:true);
		}
		for(int i=0;i<observer.size();i++){
			if(observer.get(i).get("avatar")==null){
				observer.get(i).put("avatar", useravatarService.selectavartar().getAvatar());
			}
			observer.get(i).put("eq_status", String.valueOf(observer.get(i).get("eq_status")).equals("H:0")?false:true);
			observer.get(i).put("bluetooth_type", String.valueOf(observer.get(i).get("bluetooth_type")).equals("0")?false:true);
		}
		Map<String,Object> dd = new HashMap<String,Object>();
		dd.put("users", users);
		dd.put("observer", observer);
		re.setCode(200);
		re.setMessage("获取设备操作者成功！！！");
		re.setData(dd);
		return re;
	}
	
	@Override
	public User getuserimei0(Integer eqId) {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		if (eqMapper == null) {
			eqMapper = (UserEqMapper) webApplicationContext.getBean("userEqMapper");
		}
		if (usermapper == null) {
			usermapper = (UserMapper) webApplicationContext.getBean("userMapper");
		}
		try {
			UserEq ue = eqMapper.ifguardianship(eqId);
			return usermapper.selectByPrimaryKey(ue.getUserId());
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Integer getimei(String imei) {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		if (eqMapper == null) {
			eqMapper = (UserEqMapper) webApplicationContext.getBean("userEqMapper");
		}
		if (equipmentMapper == null) {
			equipmentMapper = (EquipmentMapper) webApplicationContext.getBean("equipmentMapper");
		}
		Equipment e = equipmentMapper.getequipment(imei);
		UserEq u = eqMapper.getuserimei2(e.getId());
		if (u != null) {
			return u.getUserId();
		} else {
			return null;
		}

	}

	@Override
	public Integer geteqid(Integer userid) {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		if (eqMapper == null) {
			eqMapper = (UserEqMapper) webApplicationContext.getBean("userEqMapper");
		}
		return eqMapper.geteqid(userid);
	}

	@Override
	public boolean addUserEq(UserEq u) {
		Integer num = eqMapper.insertSelective(u);
		if (num != 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean ifguardianship(Integer eqId) {
		UserEq ue = eqMapper.ifguardianship(eqId);
		if (ue != null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 判断该设备是否有使用者
	 */
	public boolean ifuse(Integer eqId) {
		UserEq ue = eqMapper.ifuse(eqId);
		if (ue != null) {
			return false;
		} else {
			return true;
		}
	}

	/**
     * 查询观察者 
     * @param eqId 和 userId
     * @return
     */
	public boolean ifObserved(UserEq u) {
		UserEq ue = eqMapper.ifObserved(u);
		if (ue != null) {
			return false;
		} else {
			return true;
		}
	}

	public List<User> selelctequser(Integer eqId) {

		List<UserEq> ls = eqMapper.selelctequser(eqId);
		List<User> lu = new ArrayList<User>();

		if (ls != null) {
			for (UserEq ue : ls) {
				User u = userservice.getUser(ue.getUserId());
				User user = new User();
				String roname = null;
				if (ue.getTypeof() == 0) {
					roname = "监护者";
				}
				if (ue.getTypeof() == 1) {
					roname = "观察者";
				}
				if (ue.getTypeof() == 2) {
					roname = "使用者";
				}
				user.setRole(roname);
				user.setId(u.getId());
				user.setAccount(u.getAccount());
				user.setName(u.getName());
				lu.add(user);
			}
		}
		if (lu.size() == 0) {
			Equipment eq = equipmentMapper.selectByPrimaryKey(eqId);
			if (eq != null) {
				String imei = eq.getImei();
				User user = usermapper.getUser(imei);
				if (user != null) {
					user.setRole("使用者");
					lu.add(user);
				}
			}
		}
		return lu;
	}

	/**
	 * 根据用户userid或mid获取用户所有相关联设备信息
	 */
	public List<UserEq> selectuserqe(Integer userid) {
		return eqMapper.selectuserqe(userid);
	}
	
	/**
	 * 获取app首页数据
	 * 
	 * @return
	 */
	@Override
	public ResultData<List<Userdata>> selectuserdata1(DataRow map,ResultData<List<Userdata>> re) throws Exception {
		Map<String, Object> detail =eqMapper.queryUserEq(map);
		if(detail!=null){
			JfhealthNew jfhealth = jMapperNew.newJfhealthNew(String.valueOf(detail.get("imei")));
			if(jfhealth!=null){
				SimpleDateFormat updatetimedf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				detail.put("updatetime", updatetimedf.format(jfhealth.getCreatetime()));
				detail.put("amedicalreport", jfhealth.getAmedicalreport() == null ? "" : jfhealth.getAmedicalreport());
				detail.put("avatar", detail.get("avatar")==null?useravatarservice.selectavartar().getAvatar():detail.get("avatar"));
				detail.put("eq_status", detail.get("eq_status").equals("H:0")?false:true);
				detail.put("count", 2);//临时加,啊亮这边需要 ,如需删除,和APP沟通
				DataRow equipmentData =  equipmentDataMapper.queryStepWhenCarrieroadSum(Integer.valueOf(String.valueOf(detail.get("userId"))));
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				//心率
				map=DataUtil.heartrateData("heartrate","心率",1, "次/分",jfhealth.getHeartrate()==null?0:jfhealth.getHeartrate());
				list.add(map);
				//血氧
				map=DataUtil.mocrocirculationData("qxygen","血氧",2, "%",jfhealth.getBloodoxygen()==null?0:jfhealth.getBloodoxygen());
				list.add(map);
				//微循环
				map=DataUtil.mocrocirculationData("mocrocirculation","微循环",3, "%",jfhealth.getMicrocirculation()==null?0:jfhealth.getMicrocirculation());
				list.add(map);
				//呼吸
				map=DataUtil.mocrocirculationData("breathe","呼吸",4, "次/分钟",jfhealth.getRespirationrate()==null?0:jfhealth.getRespirationrate());
				list.add(map);
				//步数
				map=DataUtil.stepWhenData("Step_when","计步",5, "步",equipmentData==null?0: equipmentData.getInt("stepWhen"));
				list.add(map);
				
				//血压
				map=DataUtil.bloodData("pressure","血压",6, "mmHg",jfhealth.getSbpAve()==null?0:jfhealth.getSbpAve(),jfhealth.getDbpAve()==null?0:jfhealth.getDbpAve());
				list.add(map);
				//体温
				map=new DataRow();
				map.put("name", "temperature");
				map.put("desc", "体温");
				map.put("category",7);
				map.put("unit", "℃");
				map.put("lastestValue", "39");
				map.put("type", 1);
				list.add(map);
				//湿度
				map=new DataRow();
				map.put("name", "humidity");
				map.put("desc", "湿度");
				map.put("category",8);
				map.put("unit", "%RH");
				map.put("lastestValue", "偏高");
				map.put("type", 1);
				list.add(map);
				//hrv
				map=DataUtil.hrvData("hrv","心率变异性HRV",9, "ms",jfhealth.getHRV()==null?0:jfhealth.getHRV(),detail.get("age")==null?30:(int) detail.get("age"));
				list.add(map);
				//情绪
				map=new DataRow();
				map.put("name", "emotion");
				map.put("desc", "情绪");
				map.put("category",10);
				map.put("unit", "无");
				map.put("lastestValue", "激动");
				map.put("type", 1);
				list.add(map);
				//卡路里
				map=DataUtil.mocrocirculationData("carrieroad","卡路里",11, "焦耳/天",equipmentData==null?0:equipmentData.getInt("carrieroad"));
				list.add(map);
				
				detail.put("detail", list);
				Waveform waveform =waveformMapper.queryWaveformInfo(Integer.valueOf(String.valueOf(detail.get("userId"))));
				detail.put("waveform", waveform==null?"0":waveform.getData());
				re.setData(detail);
				re.setCode(200);
				re.setMessage("获取所有设备使用者信息成功！！！");
			}else{
				re.setData(detail);
				re.setCode(200);
				re.setMessage("只获取个人信息");
			}
		}else{
			re.setCode(200);
			re.setMessage("未有用户数据 ！！");
		}
		return re;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 获取app首页数据
	 * 
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectuserdata(Integer userid)throws Exception {

		ArrayList<String> ls = new ArrayList<String>();
		List<UserEq> ues = usereqservice.selectuserqe(userid);
		if (ues != null && ues.size() > 0) {

			List<Map<String, Object>> es = new ArrayList<Map<String, Object>>();
			for (UserEq ue : ues) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Map<String, Object> detail = new HashMap<String, Object>();
				Map<String, Object> map = new HashMap<String, Object>();
				// 获取到监护者
				Equipment e = equipmentMapper.selectByPrimaryKey(ue.getEqId());
				if (!ls.contains(e.getImei())) {
					ls.add(e.getImei());
					User u = userservice.getUser(e.getImei());
					if (u != null) {
						if (u.getAvatar() == null) {
							detail.put("iconPath", useravatarservice.selectavartar().getAvatar());
						} else {
							detail.put("iconPath", u.getAvatar());
						}
						detail.put("eqid", e.getId());
						detail.put("calibration", u.getCalibration());
						detail.put("username", u.getName());
						detail.put("address", u.getAddress());
						detail.put("deviceEnergy", e.getLordpower());
						detail.put("userid", u.getId());
						detail.put("imei", e.getImei());
						if (ue.getTypeof() == 0) {
							detail.put("managenment", true);
							detail.put("role", ue.getTypeof());
						} else {
							detail.put("managenment", false);
							detail.put("role", ue.getTypeof());
						}

						boolean status = false;
						if (e.getBluetoothStatus().equals("1")) {
							status = true;
						}
						detail.put("bluetoothStatus", status);
						boolean stas = false;
						if (e.getEqStatus().equals("H:1")) {
							stas = true;
						}
						if (e.getBluetoothType()!=null&&e.getBluetoothType().equals("1")) {
							detail.put("bluetoothType", true);
						}else{
							detail.put("bluetoothType", false);
						}
						detail.put("loactionStatus", stas);
						detail.put("mDevOnline", stas);
						detail.put("messageStatus", stas);
						detail.put("aclokStatus", stas);
						detail.put("mainDevCap", e.getLordpower());

						EquipmentData eqdata = null;
						if (u.getId() != null) {
							eqdata = equipmentDataMapper.selectdata(u.getId());
						}

						JfhealthNew jfhealth = jMapperNew.newJfhealthNew(e.getImei());
						//Jfhealthdao jfhealthdao = jfhealthFdaoservice.selelctJfhealthdao(e.getImei());
						// System.out.println("惊凡最新数据" + jfhealth);
						if (eqdata != null && jfhealth != null) {
							try {
							} catch (Exception e2) {
								detail.put("userStatus", "静止");
							}

							SimpleDateFormat updatetimedf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
							detail.put("updatetime", updatetimedf.format(jfhealth.getCreatetime()));

							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
							String dd = df.format(new Date());
							Map<String, Object> m = new HashMap<String, Object>();
							m.put("countdate", dd + "%");
							m.put("userid", userEqservice.getimei(e.getImei()));
							List<EquipmentData> countdata = equipmentDataMapper.selecttheycount(m);
							Integer Step_whennum = 0;
							Integer Carrieroadnum = 0;
							// System.out.println("用户id》》》》》》》》》》》》》》》》" +
							// userEqservice.getimei(e.getImei()));
							if (countdata.size() > 0 && countdata != null) {
								for (EquipmentData eqcount : countdata) {
									if(eqcount.getStepWhen()!=null&& eqcount.getCarrieroad()!=null){
									int count = eqcount.getStepWhen();
									Step_whennum = Step_whennum + count;
									int carrnum = eqcount.getCarrieroad();
									Carrieroadnum = Carrieroadnum + carrnum;
									}
								}
							}
							System.out.println("当天步数》》》》》》》》》》》》》》》》" + Step_whennum);
							System.out.println("当天卡里路》》》》》》》》》》》》》》》》" + Carrieroadnum);
							map.put("name", "Step_when");
							map.put("desc", "计步");
							map.put("category", "13");
							if (eqdata.getStepWhen() == 0) {
								map.put("lastestValue", Step_whennum);
							} else {
								map.put("lastestValue", Step_whennum);
							}
							map.put("unit", "步");
							list.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "heartrate");
							map.put("desc", "心率");
							map.put("category", "2");
							map.put("lastestValue", jfhealth.getHeartrate()==null?0:jfhealth.getHeartrate());
							map.put("unit", "次/分");
							list.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "pressure");
							map.put("desc", "血压");
							map.put("category", "3");
							Integer sbpAve = jfhealth.getSbpAve()==null?0:jfhealth.getSbpAve();
							Integer dbpAve = jfhealth.getDbpAve()==null?0:jfhealth.getDbpAve();
							String sbpAveStr = "";
							String dbpAveStr = "";
							if(sbpAve != null ){
								sbpAveStr = sbpAve.toString();
							}
							if(dbpAve!=null){
								dbpAveStr = dbpAve.toString();
							}
							map.put("lastestValue", sbpAveStr+ "/"+ dbpAveStr);
							map.put("unit", "mmHg");
							list.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "hrv");
							map.put("desc", "心率变异性HRV");
							map.put("category", "8");
							map.put("lastestValue", jfhealth.getHRV()==null?0:jfhealth.getHRV());
							map.put("unit", "ms");
							list.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "mocrocirculation");
							map.put("desc", "微循环");
							map.put("category", "4");
							map.put("lastestValue",jfhealth.getMicrocirculation()==null?0:jfhealth.getMicrocirculation());
							map.put("unit", "%");
							list.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "qxygen");
							map.put("desc", "血氧");
							map.put("category", "10");
							map.put("lastestValue", jfhealth.getBloodoxygen()==null?0:jfhealth.getBloodoxygen());
							map.put("unit", "%");
							list.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "carrieroad");
							map.put("desc", "卡路里");
							map.put("category", "14");
							map.put("lastestValue", Carrieroadnum);
							map.put("unit", "焦耳/天");
							list.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "breathe");
							map.put("desc", "呼吸");
							map.put("category", "12");
							map.put("lastestValue",jfhealth.getRespirationrate()==null?0:jfhealth.getRespirationrate());
							map.put("unit", "次/分钟");
							list.add(map);
							detail.put("amedicalreport", jfhealth.getAmedicalreport() == null ? "" : jfhealth.getAmedicalreport());
						}
						detail.put("detail", list);
						Waveform waveform =waveformMapper.queryWaveformInfo(userid);
						detail.put("waveform", waveform==null?"0":waveform.getData());
						es.add(detail);
					}
				}

			}
			return es;
		} else {
			return null;
		}

	}

	@Override
	public Map<String, Object> userdata(String imei) {
		Map<String, Object> m = new HashMap<String, Object>();
		// 用户基本信息map
		Map<String, Object> user = new HashMap<String, Object>();
		Map<String, Object> equipment = new HashMap<String, Object>();
		Map<String, Object> guardian = new HashMap<String, Object>();

		Integer userid = usereqservice.getimei(imei);
		// 基本资料
		User u = userservice.getUser(userid);
		// 设备信息
		Equipment e = equipmentMapper.getequipment(imei);

		// 监护者列表
		List<User> us = usereqservice.selelctequser(e.getId());
		m.put("jfdataUpdateTime", u.getJfdataUpdateTime()==null?2:u.getJfdataUpdateTime());
		m.put("iconPath", u.getAvatar());
		m.put("username", u.getName());
		m.put("address", u.getAddress());
		m.put("userStatus", "跑步");
		m.put("deviceEnergy", e.getBluetoothElectricity());
		m.put("userid", userid);
		m.put("imei", e.getImei());
		boolean status = false;
		if (e.getBluetoothStatus().equals("1")) {
			status = true;
		}
		m.put("bluetoothStatus", status);
		boolean stas = false;
		if (e.getEqStatus().equals("H:1")) {
			stas = true;
		}
		m.put("loactionStatus", stas);
		m.put("messageStatus", stas);
		m.put("aclokStatus", stas);
		user.put("age", u.getAge());
		user.put("phone", u.getPhone());
//		user.put("highpressure", u.getHighpressure());
//		user.put("lowpressure", u.getLowpressure());
		// 出生
		user.put("born", u.getBorn());
		// 性别
		user.put("gender", u.getGender());
		// 身高
		user.put("height", u.getHeight());
		// 体重
		user.put("weight", u.getWeight());
		// 校准结果0未校准,1已校准
		if (u.getCalibration() == null) {
			user.put("calibration", 0);
		} else {
			user.put("calibration", u.getCalibration());
		}

		m.put("user", user);
		
		//手机数据
		GroupPhone groupPhone = null;
		if(StringUtils.isNotBlank(u.getPhone())){
			groupPhone = groupPhoneMapper.selectOne(u.getPhone());
		}
		m.put("groupPhone", groupPhone);
		
		
		// 设备名称
		equipment.put("ename", e.getName());
		// 设备连接状态
		equipment.put("estatus", stas);
		// 设备电量
		equipment.put("eElectricity", e.getBluetoothElectricity());

		// 蓝牙设备名称
		equipment.put("bname", e.getBluetoothName());
		boolean bstas = false;

		if (e.getBluetoothStatus().equals("1")) {
			bstas = true;
		}
		// 蓝牙设备连接状态
		equipment.put("bstatus", bstas);
		// 蓝牙设备电量
		equipment.put("bElectricity", e.getBluetoothElectricity());
		m.put("equipment", equipment);
		// 管理员人员
		for (User udom : us) {
			if (udom.getName() == null || udom.getName() == "" || udom.getName() == "null") {
				guardian.put("昵称", udom.getRole());
			} else {
				guardian.put(udom.getName(), udom.getRole());
			}
		}
		m.put("guardian", guardian);
		return m;
	}
	/**
	 * 取消观察者
	 */
	public boolean deleteequse(Integer eqId, Integer userId, Integer typeof,Integer mid) {
		UserEq u = new UserEq();
		u.setEqId(eqId);
		u.setUserId(mid);
		try {
			eqMapper.deleteequse(u);
			//删除通知数据
			pushMapper.removePush(userId,mid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取消关注的
	 */
	public boolean deleteguardian(String imei,Integer eqId, Integer userId,Integer mid)throws Exception {
		try {
			positionigMapper.deletePositionigInfo(imei);
			pushMapper.deletePushInfo(userId);
			eqMapper.deleteguardian(eqId); //OK	//删除关系表数据
			userservice.deleteUser(userId);    //更改用户OK
			jfhealthFdaoservice.delectjfhealthdao("mozistar" + userId);//OK// 删除校准数据
			chatMapper.deleteCharInfo(imei);
			jfhealthdaoMapper.deleteJfhealthdaoInfo(imei, "mozistar" + userId);
			sensorstatusMapper.deleteSensorstatusInfo(imei);
			//usercodeMapper   这个是验证码的，不删除也没事
			
			//删除通知数据
		//	pushMapper.removePush(userId,mid);
			
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	/**
	 * 取消与这个设备的关联关系
	 */
	public boolean deleteEqUser(Integer eqId) {
		try {
			eqMapper.deleteguardian(eqId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteequsetype(Integer eqId, Integer userId, Integer typeof) {
		UserEq u = new UserEq();
		u.setUserId(userId);
		u.setEqId(eqId);
		u.setTypeof(typeof);
		try {
			eqMapper.deleteequsetype(u);
			if (typeof == 2) {
				equipmentDataMapper.deletedata(userId);
				userservice.deleteUser(userId);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public UserEq selectUserEq(Integer eqId,Integer mid) {
		return eqMapper.selectGuardian(eqId,mid);
	}

	


}
