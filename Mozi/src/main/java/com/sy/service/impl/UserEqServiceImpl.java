package com.sy.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.mapper.ChatMapper;
import com.sy.mapper.EquipmentDataMapper;
import com.sy.mapper.EquipmentMapper;
import com.sy.mapper.EquipmentRecordMapper;
import com.sy.mapper.JfhealthNewMapper;
import com.sy.mapper.JfhealthdaoMapper;
import com.sy.mapper.PositionigMapper;
import com.sy.mapper.PushMapper;
import com.sy.mapper.SensorstatusMapper;
import com.sy.mapper.UserEqMapper;
import com.sy.mapper.UserMapper;
import com.sy.mapper.UsercodeMapper;
import com.sy.mapper.WaveformMapper;
import com.sy.nettyulit.NettyChannelMap;
import com.sy.pojo.Equipment;
import com.sy.pojo.EquipmentRecord;
import com.sy.pojo.JfhealthNew;
import com.sy.pojo.Message;
import com.sy.pojo.User;
import com.sy.pojo.UserEq;
import com.sy.pojo.Waveform;
import com.sy.service.EquipmentService;
import com.sy.service.JfhealthdaoService;
import com.sy.service.MessageService;
import com.sy.service.UserEqService;
import com.sy.service.UserService;
import com.sy.service.UseravatarService;
import com.sy.utils.DataRow;
import com.sy.utils.DataUtil;
import com.sy.vo.Userdata;

import io.netty.channel.Channel;

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
	private EquipmentService equipmentService;
	@Autowired
	private UserMapper usermapper;
	@Autowired
	private PushMapper pushMapper;
	@Autowired
	private UserEqService usereqservice;
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
	@Autowired
	MessageService messageService;
	@Autowired
	EquipmentRecordMapper equipmentRecordMapper;
	/**
	 * 关注列表
	 * @param u
	 * @return
	 */
	@Override
	public ResultData<List<DataRow>> queryUserEqFollowList(DataRow map,ResultData<List<DataRow>> re) throws Exception {
		List<DataRow> users=userEqMapper.queryUserEqFollowUsersList(map);
		List<DataRow> observer=userEqMapper.queryUserEqFollowObserverList(map);
		for(int i=0;i<users.size();i++){
			if(users.get(i).getString("avatar")==null){
				users.get(i).put("avatar", useravatarService.selectavartar().getAvatar());
			}
			users.get(i).put("eq_status", String.valueOf(users.get(i).get("eq_status")).equals("H:0")?false:true);
			users.get(i).put("bluetooth_type", String.valueOf(users.get(i).get("bluetooth_type")).equals("0")?false:true);
		}
		for(int i=0;i<observer.size();i++){
			if(observer.get(i).getString("avatar")==null){
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
	/**
	 *  更改默认使用者
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@Override
	public ResultBase updateUserFollow(DataRow map, ResultBase re) throws Exception {
		User user =userservice.selectById(map.getInt("userId"));
		if(user!=null){
			EntityWrapper<Equipment> ew = new EntityWrapper<Equipment>();
			ew.eq("imei", user.getImei());
			Equipment e = equipmentService.selectOne(ew);
			if(e!=null){
				map.put("id", e.getId());
				int row=userEqMapper.updateUserEqFollow(map);
				if(row>0){
					row=userEqMapper.updateUserEqFollowInfo(map);
					if(row>0){
						re.setCode(200);
						re.setMessage("更改成功");
					}else{
						re.setCode(400);
						re.setMessage("更改失败,请退出重新更改");
					}
				}else{
					re.setCode(400);
					re.setMessage("更改失败,请退出重新更改");
				}
			}else{
				re.setCode(400);
				re.setMessage("查询不到设备IMEI号");
			}
			}
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
		return eqMapper.geteqiduse(userid);
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
			Equipment eq = equipmentMapper.selectById(eqId);
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
	public ResultData<List<Userdata>> selectuserdata(DataRow map,ResultData<List<Userdata>> re) throws Exception {
		User user = userservice.selectById(map.getInt("userId"));
		DataRow detail = new DataRow();
		if(user.getRole().equals("使用者")){
			detail=usermapper.queryUsersInfo(map);
			UserEq  userEq =eqMapper.queryUserEqAlias(user.getId());
			EntityWrapper<Message> ew = new EntityWrapper<Message>();
			ew.eq("alias", userEq.getUserId());
			int messageCount =messageService.selectCount(ew);
			detail.put("messageCount", messageCount);
		}else{
			 detail =eqMapper.queryUserEq(map);
		}
		
		if(detail!=null){
			JfhealthNew jfhealth = jMapperNew.newJfhealthNew(String.valueOf(detail.get("imei")));
			if(jfhealth!=null){
				EquipmentRecord record=equipmentRecordMapper.queryEquipmentRecord(map.getInt("userId"));
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
				map=DataUtil.temperatureData("temperature","体温",7, "℃",record==null?(float)36.5:record.getTemperature());
				list.add(map);
				//湿度
				map=DataUtil.humidityData("humidity","湿度",8, "%RH",record==null?(float)36.5:record.getTemperature());
				list.add(map);
				/*map=new DataRow();
				map.put("lastestValue", "偏高");
				map.put("type", 1);
				list.add(map);*/
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
	 * 取消观察者
	 */
	public boolean deleteequse(Integer eqId, Integer userId, Integer typeof,Integer mid)throws Exception{
		UserEq u = new UserEq();
		u.setEqId(eqId);
		u.setUserId(mid);
		try {
			UserEq eq =eqMapper.selectOne(u);
			if(eq.getFollow()==1){//  是默认者
				eqMapper.deleteequse(u);//删除关系表数据
				pushMapper.removePush(userId,mid);//删除通知数据
				eq=eqMapper.queryUserEqLimit(mid);
				eq.setFollow(1);
				eqMapper.updateById(eq);
			}else{
				eqMapper.deleteequse(u);//删除关系表数据
				pushMapper.removePush(userId,mid);//删除通知数据
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 取消关注的
	 */
	public boolean deleteguardian(String imei,Integer eqId, Integer userId,Integer mid){
			try {
			//判断是否删除默认者
			UserEq eq =eqMapper.ifuse(eqId);
			if(eq.getFollow()==1){//  是默认者
				positionigMapper.deletePositionigInfo(imei);
				pushMapper.deletePushInfo(userId);//执行这个,删除所有的预警关联
				eqMapper.deleteguardian(eqId); //OK	//删除关系表数据
				userservice.deleteUser(userId);    //更改用户OK
				jfhealthFdaoservice.delectjfhealthdao("mozistar" + userId);//OK// 删除校准数据
				chatMapper.deleteCharInfo(imei);//APP发文本信息到设备的表
				jfhealthdaoMapper.deleteJfhealthdaoInfo(imei, "mozistar" + userId);
				sensorstatusMapper.deleteSensorstatusInfo(imei);
				eq=eqMapper.queryUserEqLimit(mid);
				eq.setFollow(1);
				eqMapper.updateById(eq);
			}else{
				positionigMapper.deletePositionigInfo(imei);
				pushMapper.deletePushInfo(userId);//执行这个,删除所有的预警关联
				eqMapper.deleteguardian(eqId); //OK	//删除关系表数据
				userservice.deleteUser(userId);    //更改用户OK
				jfhealthFdaoservice.delectjfhealthdao("mozistar" + userId);//OK// 删除校准数据
				chatMapper.deleteCharInfo(imei);//APP发文本信息到设备的表
				jfhealthdaoMapper.deleteJfhealthdaoInfo(imei, "mozistar" + userId);
				sensorstatusMapper.deleteSensorstatusInfo(imei);
			}
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


	public UserEq selectUserEq(Integer eqId,Integer mid) {
		return eqMapper.selectGuardian(eqId,mid);
	}
	/**
	 * 删除设备操作者
	 * @param map
	 * @return
	 */
	@Override
	public ResultBase deleteguardian111111(DataRow map, ResultBase re) throws Exception {
		Integer typeof = map.getInt("type_of");
		Integer mid = map.getInt("mid");		
		Equipment e = equipmentService.selectquipmentimei(map.getString("imei"));
		//卡片的id
		Integer userId = map.getInt("userId");
		boolean status = false;
		if(typeof==2){
			if(e.getEqtype()==1){//旧版
				if(e.getBluetoothmac().equals("000000000000") && e.getBluetoothName().equals("000000000000")&&e.getBluetoothType().equals("0")){
					status = usereqservice.deleteguardian(e.getImei(),e.getId(), userId,mid);//删除用户
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
						equipmentService.updatEequipmentst(e);
				} else {
					re.setCode(350);
					re.setMessage("取消失败！！！");
				}
				}else{
					re.setCode(350);
					re.setMessage("请先断开衣服");
				}
			}else{//新版可以直接删除
				usereqservice.deleteguardian(e.getImei(),e.getId(), userId,mid);//删除用户
			}
		}else if(typeof==1){
			status = usereqservice.deleteequse(e.getId(),map.getInt("userId"), 1,mid);//取消观察者
			re.setCode(200);
			re.setMessage("取消成功！！！");
		}
		return re;
	}

	


}
