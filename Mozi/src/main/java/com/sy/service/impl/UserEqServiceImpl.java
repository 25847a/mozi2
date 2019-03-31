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

import com.sy.mapper.EquipmentDataMapper;
import com.sy.mapper.EquipmentMapper;
import com.sy.mapper.GroupPhoneMapper;
import com.sy.mapper.JfhealthMapper;
import com.sy.mapper.JfhealthNewMapper;
import com.sy.mapper.PushMapper;
import com.sy.mapper.UserEqMapper;
import com.sy.mapper.UserMapper;
import com.sy.pojo.Equipment;
import com.sy.pojo.EquipmentData;
import com.sy.pojo.GroupPhone;
import com.sy.pojo.Jfhealth;
import com.sy.pojo.JfhealthNew;
import com.sy.pojo.Jfhealthdao;
import com.sy.pojo.User;
import com.sy.pojo.UserEq;
import com.sy.service.EquipmentDataService;
import com.sy.service.EquipmentService;
import com.sy.service.GroupPhoneService;
import com.sy.service.JfhealthService;
import com.sy.service.JfhealthdaoService;
import com.sy.service.UserEqService;
import com.sy.service.UserService;
import com.sy.service.UseravatarService;
import com.sy.vo.Usereqvo;
import com.sy.vo.Userqedata;

import net.sf.json.JSONArray;

@Service
public class UserEqServiceImpl implements UserEqService {
	@Autowired
	private UseravatarService useravatarservice;
	@Autowired
	private JfhealthMapper jMapper;
	@Autowired
	private JfhealthNewMapper jMapperNew;
	@Autowired
	private JfhealthdaoService jfhealthFdaoservice;
	@Autowired
	private EquipmentDataMapper dataMapper;
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

	/*@Override
	public Userqedata selectdata(String imei) {
		Equipment e = equipmentservice.selectquipmentimei(imei);
		if (e != null) {
			List<User> us = usereqservice.selelctequser(e.getId());
			List<Usereqvo> usereqvos = new ArrayList<Usereqvo>();
			Userqedata data = new Userqedata();
			if (us != null) {
				for (User u : us) {
					Usereqvo usereqvo = new Usereqvo();
					usereqvo.setIconPath(u.getAvatar());
					usereqvo.setUsername(u.getName());
					usereqvo.setBluetoothStatus(e.getBluetoothName());
					usereqvo.setUserStatus("跑步");
					usereqvo.setDeviceEnergy(e.getBluetoothElectricity());
					usereqvo.setUserid(u.getId());
					usereqvo.setManagenment(u.getRole());
					boolean stas = false;
					if (e.getEqStatus().equals("H:1")) {
						stas = true;
					}
					usereqvo.setLoactionStatus(stas);
					usereqvo.setMessageStatus(stas);
					usereqvo.setAclokStatus(stas);
					usereqvos.add(usereqvo);

				}
				data.setUsers(usereqvos);
			}
			EquipmentData eqdata = equipmentdateservice.selectdata(userEqservice.getimei(imei));
			if (eqdata != null) {
				data.setEqdata(eqdata);
			}

			return data;
		} else {
			return null;
		}
	}*/

	@Override
	public List<Map<String, Object>> selectuserdata(Integer userid) {

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
								if (eqdata.getMocrocirculation() == 0) {
									detail.put("userStatus", "静止");
								}
								if (eqdata.getMocrocirculation() == 1) {
									detail.put("userStatus", "慢走");
								}
								if (eqdata.getMocrocirculation() == 2) {
									detail.put("userStatus", "跑步");
								}
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

							// System.out.println("当天步数》》》》》》》》》》》》》》》》" +
							// Step_whennum);
							// System.out.println("当天卡里路》》》》》》》》》》》》》》》》" +
							// Carrieroadnum);
							map.put("name", "Step_when");
							map.put("desc", "计步");
							map.put("category", "9");
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
							//map.put("lastestValue", checkData(jfhealth.getHeartrate(), jfhealthdao.getHeartrate()));
							map.put("lastestValue", jfhealth.getHeartrate());
							map.put("unit", "次/分");
							list.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "pressure");
							map.put("desc", "血压");
							map.put("category", "3");
							//map.put("lastestValue", checkData(jfhealth.getSbpAve(), jfhealthdao.getSbpAve()) + "/"
							//		+ this.checkData(jfhealth.getDbpAve(), jfhealthdao.getDbpAve()));
							Integer sbpAve = jfhealth.getSbpAve();
							Integer dbpAve = jfhealth.getDbpAve();
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
							//map.put("lastestValue", checkData(jfhealth.getHrv(), jfhealthdao.getHrv()));
							map.put("lastestValue", jfhealth.getHRV());
							map.put("unit", "ms");
							list.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "mocrocirculation");
							map.put("desc", "微循环");
							map.put("category", "4");
							//map.put("lastestValue",
							//		checkData(jfhealth.getMicrocirculation(), jfhealthdao.getMicrocirculation()));
							map.put("lastestValue",jfhealth.getMicrocirculation());
							map.put("unit", "%");
							list.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "qxygen");
							map.put("desc", "血氧");
							map.put("category", "10");
							//map.put("lastestValue", checkData(jfhealth.getBloodoxygen(), jfhealthdao.getBloodoxygen()));
							map.put("lastestValue", jfhealth.getBloodoxygen());
							map.put("unit", "%");
							list.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "carrieroad");
							map.put("desc", "卡路里");
							map.put("category", "1");
							map.put("lastestValue", Carrieroadnum);
							map.put("unit", "焦耳/天");
							list.add(map);
							map = new HashMap<String, Object>();
							map.put("name", "breathe");
							map.put("desc", "呼吸");
							map.put("category", "12");
							//map.put("lastestValue",
							//		this.checkData(jfhealth.getRespirationrate(), jfhealthdao.getRespirationrate()));
							map.put("lastestValue",jfhealth.getRespirationrate());
							map.put("unit", "次/分钟");
							list.add(map);
							
							 /* map = new HashMap<String,Object>();
							 * map.put("name", "sleeping"); map.put("desc",
							 * "睡眠"); map.put("category", "5"); String time =
							 * String.valueOf(eqdata.getSleeping());
							 * if(time.contains(".")) { int qian =
							 * Integer.valueOf(time.substring(0,time.indexOf("."
							 * ))); int hou =
							 * Integer.valueOf(time.substring(time.indexOf(".")+
							 * 1,time.length()));
							 * map.put("lastestValue",qian*60*1000+hou*60*1000);
							 * }else {
							 * map.put("lastestValue",Integer.valueOf(time)*60*
							 * 1000); } map.put("unit", "秒"); list.add(map); map
							 * = new HashMap<String,Object>(); map.put("name",
							 * "skindegree"); map.put("desc", "肤度");
							 * map.put("category", "6");
							 * map.put("lastestValue","1"); map.put("unit",
							 * "度"); list.add(map); map = new
							 * HashMap<String,Object>(); map.put("name",
							 * "crash"); map.put("desc", "冲撞");
							 * map.put("category", "7");
							 * map.put("lastestValue",eqdata.getCrash());
							 * map.put("unit", "次"); list.add(map);
							 */

						}
						detail.put("detail", list);
						es.add(detail);
					}
				}

			}
			return es;
		} else {
			return null;
		}

	}

	public void adddata(int userid, String imei) {
		EquipmentData data = new EquipmentData();
		data.setUserId(userid);
		data.setHeartrate(0);
		data.setHighpressure(0);
		data.setBottompressure(0);
		data.setBloodpressure(0);
		data.setMocrocirculation(0);
		data.setBreathe(0);
		data.setSleeping(0.0);
		data.setStepWhen(0);
		data.setCarrieroad(0);
		data.setSedentary("0");
		data.setMovementstate(0);
		data.setBodytemp(0);
		data.setHumidity(0);
		data.setCrash(0);
		data.setCreatetime(new Date());
		data.setQxygen(0);
		data.setSleepingS(0);
		data.setRunS(0);
		data.setStepEach(0);
		data.setHrv(0);
		data.setMood(0);
		int eqnum = dataMapper.insert(data);
		Jfhealth jfdata = new Jfhealth();
		jfdata.setHRV(0);
		jfdata.setSbpAve(0);
		jfdata.setDbpAve(0);
		jfdata.setHeartrate(0);
		jfdata.setBloodoxygen(0);
		jfdata.setMicrocirculation(0);
		jfdata.setRespirationrate(0);
		jfdata.setPhone("mozistar" + userid);
		jfdata.setImei(imei);
		jfdata.setCreatetime(new Date());
		jfdata.setAmedicalreport("0");
		int jfnum = jMapper.insert(jfdata);
		System.out.println("edata" + eqnum + "jfdata>>>>>" + jfnum);
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
		user.put("highpressure", u.getHighpressure());
		user.put("lowpressure", u.getLowpressure());
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

	/*@Override
	public List<Map<String, Object>> Userequipmentdata(Integer userid) {
		List<UserEq> ues = usereqservice.selectuserqe(userid);

		if (ues != null && ues.size() >= 0) {
			List<Map<String, Object>> es = new ArrayList<Map<String, Object>>();
			for (UserEq ue : ues) {
				Map<String, Object> detail = new HashMap<String, Object>();
				if (ue.getTypeof() == 2) {
					Equipment e = equipmentMapper.selectByPrimaryKey(ue.getEqId());
					User u = userservice.getUser(ue.getUserId());
					detail.put("iconPath", u.getAvatar());
					detail.put("username", u.getName());
					detail.put("userStatus", "跑步");
					detail.put("deviceEnergy", e.getBluetoothElectricity());
					detail.put("userid", u.getId());
					detail.put("imei", e.getImei());
					boolean status = false;
					if (e.getBluetoothStatus().equals("1")) {
						status = true;
					}
					detail.put("bluetoothStatus", status);
					boolean stas = false;
					if (e.getEqStatus().equals("H:1")) {
						stas = true;
					}
					detail.put("loactionStatus", stas);
					detail.put("messageStatus", stas);
					detail.put("aclokStatus", stas);
					es.add(detail);
				}
			}
			return es;
		} else {
			return null;
		}
	}*/

	/*@Override
	public Map<String, Object> userhealthdata(Integer userid) {
		Map<String, Object> detail = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if (userid != null) {
			Equipment e = equipmentMapper.selectByPrimaryKey(eqMapper.geteqiduse(userid));
			User u = userservice.getUser(userid);
			detail.put("iconPath", u.getAvatar());
			detail.put("username", u.getName());
			detail.put("deviceEnergy", e.getBluetoothElectricity());
			detail.put("userid", u.getId());
			detail.put("imei", e.getImei());
			if (u.getRole().equals("使用者")) {
				detail.put("managenment", true);
			} else {
				detail.put("managenment", false);
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
			detail.put("loactionStatus", stas);
			detail.put("messageStatus", stas);
			detail.put("aclokStatus", stas);

			EquipmentData eqdata = null;
			if (userid != null) {
				eqdata = equipmentdateservice.selectdata(userid);
			}

			if (eqdata != null) {
				if (eqdata.getMocrocirculation() == 0) {
					detail.put("userStatus", "静止");
				}
				if (eqdata.getMocrocirculation() == 1) {
					detail.put("userStatus", "慢走");
				}
				if (eqdata.getMocrocirculation() == 2) {
					detail.put("userStatus", "跑步");
				}
				detail.put("updatetime", eqdata.getCreatetime());
				map.put("name", "heat");
				map.put("desc", "热量");
				map.put("category", "1");
				map.put("lastestValue", "1");
				map.put("unit", "卡");
				list.add(map);
				map = new HashMap<String, Object>();
				map.put("name", "heartrate");
				map.put("desc", "心率");
				map.put("category", "2");
				map.put("lastestValue", eqdata.getHeartrate());
				map.put("unit", "次/分");
				list.add(map);
				map = new HashMap<String, Object>();
				map.put("name", "pressure");
				map.put("desc", "血压");
				map.put("category", "3");
				map.put("lastestValue", eqdata.getBloodpressure());
				map.put("unit", "mmHg");
				list.add(map);
				map = new HashMap<String, Object>();
				map.put("name", "mocrocirculation");
				map.put("desc", "微循环");
				map.put("category", "4");
				map.put("lastestValue", eqdata.getMocrocirculation());
				map.put("unit", "单位");
				list.add(map);
				map = new HashMap<String, Object>();
				map.put("name", "sleeping");
				map.put("desc", "睡眠");
				map.put("category", "5");
				String time = String.valueOf(eqdata.getSleeping());
				if (time.contains(".")) {
					int qian = Integer.valueOf(time.substring(0, time.indexOf(".")));
					int hou = Integer.valueOf(time.substring(time.indexOf(".") + 1, time.length()));
					map.put("lastestValue", qian * 60 * 1000 + hou * 60 * 1000);
				} else {
					map.put("lastestValue", Integer.valueOf(time) * 60 * 1000);
				}
				map.put("unit", "秒");
				list.add(map);
				map = new HashMap<String, Object>();
				map.put("name", "skindegree");
				map.put("desc", "肤度");
				map.put("category", "6");
				map.put("lastestValue", "1");
				map.put("unit", "度");
				list.add(map);
				map = new HashMap<String, Object>();
				map.put("name", "crash");
				map.put("desc", "冲撞");
				map.put("category", "7");
				map.put("lastestValue", eqdata.getCrash());
				map.put("unit", "次");
				list.add(map);
				map = new HashMap<String, Object>();
				map.put("name", "hrv");
				map.put("desc", "心跳异常");
				map.put("category", "8");
				map.put("lastestValue", eqdata.getHrv());
				map.put("unit", "单位");
				list.add(map);
				map = new HashMap<String, Object>();
				map.put("name", "Step_when");
				map.put("desc", "当天步数");
				map.put("category", "9");
				map.put("lastestValue", eqdata.getStepWhen());
				map.put("unit", "步");
				list.add(map);
				map = new HashMap<String, Object>();
				map.put("name", "qxygen");
				map.put("desc", "血氧");
				map.put("category", "10");
				map.put("lastestValue", eqdata.getQxygen());
				map.put("unit", "单位");
				list.add(map);
			}
			detail.put("detail", list);

		}
		return detail;
	}*/

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
	public boolean deleteguardian(Integer eqId, Integer userId,Integer mid) {
		try {
			//删除关系表数据
			eqMapper.deleteguardian(eqId);
			//删除原始健康数据
			equipmentDataMapper.deletedata(userId);
			//删除用户
			userservice.deleteUser(userId);
			// 删除校准数据
			jfhealthFdaoservice.delectjfhealthdao("mozistar" + userId);
			// 删除上传的数据
			jMapper.deletejfhealth("mozistar" + userId);
			jMapperNew.deletejfhealth("mozistar"+userId);
			
			//删除通知数据
			pushMapper.removePush(userId,mid);
			
			return true;
		} catch (Exception e) {
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

	/**
	 * 后台设备取消和用户的关联关系 供后台使用,接口不调用
	 */
	public boolean deleteusereq(Integer userid) {
		try {
			// User user = userservice.getUser(userid);
			List<UserEq> usereqlist = eqMapper.selectuserqe(userid);

			// 如果没有关联关系,就单独删除这个用户的数据
			if (usereqlist == null || usereqlist.size() == 0) {
				
				// 先删除eqdata数据
				equipmentDataMapper.deletedata(userid);
				// 再删除全部的关联关系
				// eqMapper.deleteguardian(eqid);
				// 再删除这个使用者
				usermapper.deleteByPrimaryKey(userid);
				// 删除校准数据
				jfhealthFdaoservice.delectjfhealthdao("mozistar" + userid);
				// 删除上传的数据
				jMapper.deletejfhealth("mozistar" + userid);
				jMapperNew.deletejfhealth("mozistar"+userid);
				
			} else {
				// 监护者和设备关联关系
				UserEq userEq = usereqlist.get(0);
				Integer eqid = userEq.getEqId();

				Equipment e = equipmentMapper.selectByPrimaryKey(eqid);

				// 不等于1说明是要删除使用者和监护者
				if (userEq.getTypeof() != 1) {
					//如果是删除监护者
					if (userEq.getTypeof() == 0) {
						
						// 拿到使用者和设备的关联关系
						UserEq eq = eqMapper.getuserimei2(eqid);
						
						// 先删除eqdata数据
						equipmentDataMapper.deletedata(eq.getUserId());
						
						// 再删除全部的关联关系
						eqMapper.deleteguardian(eqid);
						
						// 再删除这个使用者
						usermapper.deleteByPrimaryKey(eq.getUserId());
						
						// 删除校准数据
						jfhealthFdaoservice.delectjfhealthdao("mozistar" + eq.getUserId());

						//删除通知数据
						pushMapper.removePushToUserId(eq.getUserId());
						
						// 删除上传的数据
						jMapper.deletejfhealth("mozistar" + userid);
						jMapperNew.deletejfhealth("mozistar"+userid);
						
					} else {
						//如果是删除使用者
						// 先删除eqdata数据
						equipmentDataMapper.deletedata(userid);
						
						// 再删除全部的关联关系
						eqMapper.deleteguardian(eqid);
						
						// 再删除这个使用者
						usermapper.deleteByPrimaryKey(userid);

						// 删除校准数据
						jfhealthFdaoservice.delectjfhealthdao("mozistar" + userid);
						
						//删除通知数据
						pushMapper.removePushToUserId(userid);
						
						// 删除上传的数据
						jMapper.deletejfhealth("mozistar" + userid);
						jMapperNew.deletejfhealth("mozistar"+userid);
					}
					e.setBluetoothmac("000000000000");
					e.setBluetoothName("000000000000");
					e.setBluetoothStatus("0");
					e.setBluetoothType("0");
					e.setPhone1(null);
					e.setPhone2(null);
					equipmentMapper.updateByPrimaryKey(e);

				} else {
					// 拿到使用者和设备的关联关系
					UserEq eq = eqMapper.getuserimei2(eqid);
					// 只删除观察者
					eqMapper.deleteuserid(userid);
					//删除通知数据
					pushMapper.removePush(eq.getUserId(),userid);
				}
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	public UserEq selectUserEq(Integer eqId,Integer mid) {
		return eqMapper.selectGuardian(eqId,mid);
	}

}
