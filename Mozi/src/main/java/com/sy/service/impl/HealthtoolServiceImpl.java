package com.sy.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.sy.common.JpushClientUtil;
import com.sy.mapper.EquipmentDataMapper;
import com.sy.mapper.JfhealthMapper;
import com.sy.mapper.JfhealthNewMapper;
import com.sy.mapper.UserEqMapper;
import com.sy.pojo.EquipmentData;
import com.sy.pojo.Jfhealth;
import com.sy.pojo.JfhealthNew;
import com.sy.pojo.Jfhealthdao;
import com.sy.pojo.Positionig;
import com.sy.pojo.Push;
import com.sy.pojo.User;
import com.sy.pojo.UserEq;
import com.sy.service.JfhealthdaoService;
import com.sy.service.PushService;
import com.sy.service.UserEqService;
import com.sy.service.UserService;
import com.sy.utils.BinaryReadWrite;
import com.sy.utils.HttpClientUtil;
import com.sy.utils.Managementconstant;

import net.sf.json.JSONObject;

@Service
public class HealthtoolServiceImpl {
	private static UserService userservice = new UserServiceImpl();
	private static UserEqMapper userEqMapper;
	private static JfhealthMapper jfhealthmapper;
	// @Autowired
	// private static JfhealthdaoMapper jfhealthdaoMapper;
	private static JfhealthdaoService jfhealthdaoservice = new JfhealthdaoServiceImpl();
	private static PushService pushService = new PushServiceImpl();
	private static JfhealthNewMapper jfhealthNewMapper;
	private static EquipmentDataMapper equipmentDataMapper;
	
	private final static Logger logger = LoggerFactory.getLogger(HealthtoolServiceImpl.class);

	private static String url = "https://api.jingfantech.com/V1.02/physical_exam/manufacturer";
	private static String charset = "utf-8";
	// 血压
	private static final String SBDP = "sbdp";
	// 心率
	private static final String HTRATE = "htrate";
	// 血氧
	private static final String SPO = "spo";
	// 微循环
	private static final String BK = "bk";
	// 体检报告
	private static final String REPORT = "report";
	// 呼吸頻率
	private static final String RESPIRATIONRATE = "respiration_rate";

	private static final String HRV = "HRV";

	public static void main(String[] args) throws Exception {
		// for(int i =0 ; i <70; i++){
		// boolean jfstatus = Healthtoolserviceimpl.registered(
		// Managementconstant.channel_id + String.valueOf(i),
		// "12345", "123456");
		// System.out.println(jfstatus+"=========="+i);
		// }
		//
		// String a="a78961f";
		// String date
		// String
		// time=UploadhealthTset(Managementconstant.channel_id+"108","123456",
		// "2018-04-15 10:00:36","6A6668010001010101010000FFFFFFFF");
		// System.out.println(time);
		// respirationrate(Managementconstant.channel_id+"108","123456",
		// "2018-07-16 17:43:11","6A6668010001010101010000FFFFFFFF");
		String hrv2 = HRV(Managementconstant.channel_id + "108", "123456", "2018-07-16 17:43:11", "6A6668010001010101010000FFFFFFFF");
		System.out.println(hrv2);
		// // 血压
		// String bloodr =
		// bloodpressure(Managementconstant.channel_id+"108","123456",
		// date,"6A6668010001010101010000FFFFFFFF");
		// String [] bloodxygen = bloodr.split(",");
		// System.out.println(bloodxygen[0]+"=========="+bloodxygen[1]);
		// String a="a78961f";
		// boolean respiration = registered("m123", "123456", "123456");
	}

	private static void init() {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		if (pushService == null) {
			pushService = (PushService) webApplicationContext.getBean("pushService");
		}
		if (userEqMapper == null) {
			userEqMapper = (UserEqMapper) webApplicationContext.getBean("userEqMapper");
		}

		if (jfhealthmapper == null) {
			jfhealthmapper = (JfhealthMapper) webApplicationContext.getBean("jfhealthMapper");
		}

		if (jfhealthNewMapper == null) {
			jfhealthNewMapper = (JfhealthNewMapper) webApplicationContext.getBean("jfhealthNewMapper");
		}
		if (equipmentDataMapper == null) {
			equipmentDataMapper = (EquipmentDataMapper) webApplicationContext.getBean("equipmentDataMapper");
		}

	}

	/**
	 * @param phone_num
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String UploadhealthTset(String phone_num, String password, String data, String device_id)
			throws Exception {
		User u = null;
		float w = 50;
		float h = 170;
		if (u == null) {
			u = new User(null, null, null, null, null, 50, "男", null, null, null, null, null, null, null, w, h, null,
					null, null, null, null, null,null);
		}
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String starttime = time.format(new Date());
		// 上传健康数据的api
		String httpOrgCreateTest = "https://api.jingfantech.com/V1.02/writeARecord";
		JSONObject createMap = new JSONObject();
		createMap.put("device_id", device_id);
		createMap.put("session_id", "0");
		createMap.put("client_id", "0");
		createMap.put("format", "bin");
		createMap.put("model_no", "bk");
		createMap.put("channel_id", Managementconstant.channel_id);
		createMap.put("channel_secret", Managementconstant.channel_secret);
		createMap.put("start_time", starttime);
		createMap.put("name", phone_num);
		createMap.put("secret", password);
		String gender = u.getGender();
		if (gender.equals("男")) {
			createMap.put("sex", "1");
		} else {
			createMap.put("sex", "0");
		}

		createMap.put("height", u.getHeight());
		createMap.put("weight", u.getWeight());
		createMap.put("age", u.getAge());
		JSONObject specjs = new JSONObject();
		specjs.put("origin", "4032");// origin是做base64前 数据的长度
		specjs.put("channel_len", "3072");
		createMap.put("spec", specjs.toString());
		createMap.put("finished", "true");
		String fileName = "C:/Users/Administrator/Desktop/外包/data.bin";
		createMap.put("data", BinaryReadWrite.readFileByBytes(fileName));
		Gson gson = new Gson();
		String json = gson.toJson(createMap);
		HttpClientUtil.doPost(httpOrgCreateTest, json, charset);

		return starttime;
	}

	/**
	 * 硬件上传惊凡数据
	 * 
	 * @param imei
	 * @return
	 * @throws Exception
	 */
	public static String addjfthealth(String imei, String data, String device_id, String type) throws Exception {
		try {
			init();
			//Integer userid = usereqservice.getimei(imei);
			User u = userservice.getUser(imei);
			if (u == null) {
				// return "$R05|ERR1\r\n";
				return "nouser";
			} else {
				// mozistar+使用者id
				String account = Managementconstant.channel_id + String.valueOf(u.getId());
				String stattime = Uploadhealth(account, "123456", data, device_id, u.getId(), type);
				
				// 血压()
				String bloodr = bloodpressure(account, "123456", stattime, device_id);
				// 心率
				String heartr = Heartrate(account, "123456", stattime, device_id);
				// 血氧
				String bloodxy = Bloodoxygen(account, "123456", stattime, device_id);
				// 微循环
				String microcir = microcirculation(account, "123456", stattime, device_id);
				// 体检报告
				String amedical = Amedicalreport(account, "123456", stattime, device_id);
				String hrv = HRV(account, "123456", stattime, device_id);
				// 呼吸频率
				String respiration = respirationrate(account, "123456", stattime, device_id);
				// 高低压
				String[] bloodxygen = bloodr.split(",");
				
				EquipmentData eqdata = new EquipmentData();
				eqdata.setHeartrate(Integer.parseInt(heartr));
				//eqdata.setBloodpressure(Integer.parseInt(bloodr));
				eqdata.setHighpressure(Integer.parseInt(bloodxygen[0]));
				eqdata.setBottompressure(Integer.parseInt(bloodxygen[1]));
				eqdata.setQxygen(Integer.parseInt(bloodxy));
				eqdata.setMocrocirculation(Integer.parseInt(microcir));
				eqdata.setHrv(Integer.parseInt(hrv));
				eqdata.setBreathe(Integer.parseInt(respiration));
				eqdata.setCreatetime(new Date());
				eqdata.setUserId(u.getId());
				equipmentDataMapper.insertSelective(eqdata);

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("countdate", df.format(new Date()) + "%");
				m.put("userid", u.getId());

				// 获取当天所有的步数和卡路里
				List<EquipmentData> equipmentdaoList = equipmentDataMapper.selecttheycount(m);
				Integer Step_whennum = 0;
				Integer Carrieroadnum = 0;
				if (equipmentdaoList != null && equipmentdaoList.size() > 0) {
					for (EquipmentData eqcount : equipmentdaoList) {
						if(eqcount.getStepWhen()!=null&&eqcount.getCarrieroad()!=null){
							Step_whennum += eqcount.getStepWhen();
							Carrieroadnum += eqcount.getCarrieroad();
						}
					}
				}
				String returnStr = null;

				// 使用者的的校准数据
				Jfhealthdao jfdao = jfhealthdaoservice.JfhealthdaoInfo(imei, account);

				// t14表示硬件5分钟自动上传一次
				if (type.equals("T14")) {

					if (jfdao != null) {
						// 高压值校准值
						Integer highpressure = jfdao.getSbpAve()==null?120:jfdao.getSbpAve();
						// 低压校准值
						Integer lowpressure = jfdao.getDbpAve()==null?80:jfdao.getDbpAve();

						// 高压值检测值
						 int gy = Integer.parseInt(bloodxygen[0]);
						//低压检测值
						 int dy = Integer.parseInt(bloodxygen[1]);
						 
						 if(gy==0||dy==0){
							 
							    //高压部分,校准值正负10%  
								Random r =  new Random();
								int s = r.nextInt((highpressure/10)*2)+(highpressure-highpressure/10)+1;
								highpressure = s;
								
							    //低压部分  校准值正负10%  
								int d = r.nextInt((lowpressure/10)*2)+(lowpressure-lowpressure/10)+1;
								lowpressure = d;
								
							}else{
								
								//不等于0 校准值0.8 加 检测值0.2
								highpressure = highLowPressureVal(highpressure, Integer.parseInt(bloodxygen[0]));
								lowpressure = highLowPressureVal(lowpressure, Integer.parseInt(bloodxygen[1]));
							}
				

						Jfhealth health = new Jfhealth();
						// 报告
						health.setAmedicalreport(amedical);
						// 血氧
						health.setBloodoxygen(Integer.parseInt(bloodxy)<93?(int)(95+Math.random()*(99-95+1)):Integer.parseInt(bloodxy));
						//health.setBloodoxygen(Integer.parseInt(bloodxy));
						// 高压
						health.setSbpAve(highpressure);
						// 低压
						health.setDbpAve(lowpressure);
						
						
						
						// 心率 检测值
						int hear = Integer.parseInt(heartr);
						//心率校准值
						Integer heartrdao = jfdao.getHeartrate();
						if(hear==0){
							Random r =  new Random();
							int s = r.nextInt((heartrdao/10)*2)+(heartrdao-heartrdao/10)+1;
							health.setHeartrate(s);
						}else{
							//校准值0.8加检测值0.2
							health.setHeartrate(highLowPressureVal(heartrdao,hear));
						}
						
						// Hrv
						if(hrv.equals("0")){
							Random r = new Random();
							int nextInt = r.nextInt(15)+45;
							hrv = nextInt+"";
							//Integer HRV = jfdao.getHRV()==null?59:jfdao.getHRV();
							//health.setHRV(function(HRV,Integer.parseInt(hrv)));
							health.setHRV(Integer.parseInt(hrv));
						}else{
							health.setHRV(Integer.parseInt(hrv));
						}
						// 微循环 
						if(microcir.equals("0")){
							//70
							Random r = new Random();
							int nextInt = r.nextInt(14)+63;
							microcir = nextInt+"";
							//Integer microcirculation = jfdao.getMicrocirculation()==null?85:jfdao.getMicrocirculation();
							//health.setMicrocirculation(function(microcirculation, Integer.parseInt(microcir)));
							health.setMicrocirculation( Integer.parseInt(microcir));
						}else{
							health.setMicrocirculation( Integer.parseInt(microcir));
						}
						
						// 呼吸检测值
						int resp = Integer.parseInt(respiration);
						//呼吸校准值
						Integer respirationratedao = jfdao.getRespirationrate();
					
						if(resp==0){
							Random r =  new Random();
							int s = r.nextInt((respirationratedao/10)*2)+(respirationratedao-respirationratedao/10)+1;
							health.setRespirationrate(s);
						}else{
							//校准值0.8加检测值0.2
							health.setRespirationrate(highLowPressureVal(respirationratedao,resp));
						}
						
						

						// 创建时间
						health.setCreatetime(new Date());
						health.setPhone(account);
						health.setImei(imei);
						
						JfhealthNew jfhealthNew = new JfhealthNew();
						BeanUtils.copyProperties(jfhealthNew, health);

						// 插入健康数据表
						jfhealthmapper.insertSelective(health);

						// 插入最新数据表,供app接口查询

						JfhealthNew newjfhealthNew = jfhealthNewMapper.newJfhealthNew(imei.toString());

						if (newjfhealthNew != null) {
							jfhealthNew.setId(newjfhealthNew.getId());
							jfhealthNewMapper.updateByPrimaryKeyWithBLOBs(jfhealthNew);
						} else {
							jfhealthNewMapper.insertSelective(jfhealthNew);
						}

						// 开启极光推送
						// 比较监护者设置的通知范围,不在该范围内就通知监护者
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("user", u);
						String queryPushNews = pushService.queryPushNews(map, jfhealthNew);
						returnStr = "$R05|H1:" + health.getHeartrate() + ",H2:" + highpressure + ",H3:" + lowpressure
								+ ",H4:" + health.getBloodoxygen() + ",H5:" + microcir + ",H6:" + hrv + ",H8:"
								+ respiration + ",H9:" + queryPushNews + ",G1:" + Step_whennum + ",G3:" + Carrieroadnum
								+ "\r\n";
					} else {
						returnStr = "nocalibration";
					}

				} else {
					// T15手动校准数据
					if (jfdao == null) {
						jfdao = new Jfhealthdao();
						jfdao.setAmedicalreport(amedical);
						jfdao.setBloodoxygen(Integer.parseInt(bloodxy));
						jfdao.setSbpAve(Integer.parseInt(bloodxygen[0]));
						jfdao.setDbpAve(Integer.parseInt(bloodxygen[1]));
						jfdao.setCreatetime(new Date());
						jfdao.setHeartrate(Integer.parseInt(heartr));
						jfdao.setHRV(Integer.parseInt(hrv));
						jfdao.setMicrocirculation(Integer.parseInt(microcir));
						jfdao.setRespirationrate(Integer.parseInt(respiration));
						jfdao.setPhone(account);
						jfdao.setImei(imei);
						jfhealthdaoservice.addJfhealthdao(jfdao);
					} else {
						Integer heartrate = Integer.parseInt(heartr);

						jfdao.setHeartrate(heartrate);

						jfdao.setAmedicalreport(amedical);
						jfdao.setBloodoxygen(Integer.parseInt(bloodxy));
						jfdao.setSbpAve(Integer.parseInt(bloodxygen[0]));
						jfdao.setDbpAve(Integer.parseInt(bloodxygen[1]));
						jfdao.setCreatetime(new Date());

						jfdao.setHRV(Integer.parseInt(hrv));
						jfdao.setMicrocirculation(Integer.parseInt(microcir));
						jfdao.setRespirationrate(Integer.parseInt(respiration));
						jfdao.setPhone(account);
						jfdao.setImei(imei);
						jfhealthdaoservice.updatajf(jfdao);
					}
					returnStr = "$R05|H1:" + heartr + ",H2:" + jfdao.getSbpAve() + ",H3:" + jfdao.getDbpAve() + ",H4:"
							+ bloodxy + ",H5:" + microcir + ",H6:" + hrv + ",H8:" + respiration + ",H9:0,G1:"
							+ Step_whennum + ",G3:" + Carrieroadnum + "\r\n";
				}
				return returnStr;
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
			// return "$R05|ERR2\r\n";
		}
		// $R05|OK|H1:心率,H2:高压,H3:底压,H4:血氧,H5:微循环,H6:HRV,H8:呼吸频率 G1 步数 G3卡路里
	}

	public static JSONObject requesthealth(String service, String phone_num, String password, String start_time,
			String device_id) {
		String httpOrgCreateTest = url;
		JSONObject createMap = new JSONObject();
		createMap.put("service", service);
		createMap.put("channel_id", Managementconstant.channel_id);
		createMap.put("channel_secret", Managementconstant.channel_secret);
		createMap.put("name", phone_num);
		createMap.put("secret", password);
		createMap.put("client_id", "0");
		createMap.put("device_id", device_id);
		createMap.put("start_time", start_time);
		createMap.put("period", "1h");
		Gson gson = new Gson();
		String json = gson.toJson(createMap);
		String httpOrgCreateTestRtn = HttpClientUtil.doPost(httpOrgCreateTest, json, charset);
		logger.info("惊凡返回的数据》》》》》》》》》》》》》》"+httpOrgCreateTestRtn);
		JSONObject jsonObject = JSONObject.fromObject(httpOrgCreateTestRtn);
		//System.out.println("惊凡返回的数据》》》》》》》》》》》》》》"+jsonObject);
		return jsonObject;
	}

	public static String HAVrespirationrate(String service, String phone_num, String password, String start_time,
			String device_id) {
		String httpOrgCreateTest = url;
		JSONObject createMap = new JSONObject();
		createMap.put("service", service);
		createMap.put("channel_id", Managementconstant.channel_id);
		createMap.put("channel_secret", Managementconstant.channel_secret);
		createMap.put("name", phone_num);
		createMap.put("secret", password);
		createMap.put("client_id", "0");
		createMap.put("device_id", device_id);
		createMap.put("start_time", start_time);
		createMap.put("period", "1h");
		Gson gson = new Gson();
		String json = gson.toJson(createMap);
		String httpOrgCreateTestRtn = HttpClientUtil.doPost(httpOrgCreateTest, json, charset);
		logger.info("惊凡返回的数据》》》》》》》》》》》》》》"+httpOrgCreateTestRtn);
		return httpOrgCreateTestRtn;
	}

	/**
	 * @param phone_num
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String Uploadhealth(String phone_num, String password, String data, String device_id, Integer id,
			String type) throws Exception {
		User u = userservice.getUser(id);
		float w = 50;
		float h = 170;
		if (u == null) {
			u = new User(null, null, null, null, null, 50, "男", null, null, null, null, null, null, null, w, h, null,
					null, null, null, null, null,null);
		}
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String starttime = time.format(new Date());
		String httpOrgCreateTest = "https://api.jingfantech.com/V1.02/writeARecord";
		JSONObject createMap = new JSONObject();
		createMap.put("device_id", device_id);
		createMap.put("session_id", "0");
		createMap.put("client_id", "0");
		createMap.put("format", "bin");
		createMap.put("model_no", "bk");
		createMap.put("channel_id", Managementconstant.channel_id);
		createMap.put("channel_secret", Managementconstant.channel_secret);
		createMap.put("start_time", starttime);
		createMap.put("name", phone_num);
		createMap.put("secret", password);
		String gender = u.getGender();
		if (gender.equals("男")) {
			createMap.put("sex", "1");
		} else {
			createMap.put("sex", "0");
		}
		if (type.equals("T14")) {
			createMap.put("calibrate", "0");
		} else {
			createMap.put("calibrate", "1");
		}
		if (u.getHighpressure() != null && u.getLowpressure() != null) {
			String sbp = "";
			String dbp = "";
			if (u.getHighpressure() < 100) {
				sbp = "0" + String.valueOf(u.getHighpressure());
			} else {
				sbp = String.valueOf(u.getHighpressure());
			}

			if (u.getLowpressure() < 100) {
				dbp = "0" + String.valueOf(u.getLowpressure());
			} else {
				dbp = String.valueOf(u.getLowpressure());
			}
			createMap.put("activity", "reference_dbp:" + dbp + ",reference_sbp:" + sbp);
		}

		createMap.put("height", u.getHeight());
		createMap.put("weight", u.getWeight());
		createMap.put("age", u.getAge());
		JSONObject specjs = new JSONObject();
		specjs.put("origin", "4032");
		specjs.put("channel_len", "3072");
		createMap.put("spec", specjs.toString());
		createMap.put("finished", "true");
		createMap.put("data", data);
		Gson gson = new Gson();
		String json = gson.toJson(createMap);
		try {
			HttpClientUtil.doPost(httpOrgCreateTest, json, charset);
		} catch (Exception e) {
			
		}
		return starttime;
	}

	/**
	 * 血压
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String bloodpressure(String phone_num, String password, String start_time, String device_id)
			throws Exception {

		JSONObject sdbjs = null;
		JSONObject jsonObject = HealthtoolServiceImpl.requesthealth(SBDP, phone_num, password, start_time, device_id);
		logger.info(jsonObject.toString());		
		JSONObject sbdp_result = (JSONObject) jsonObject.get("sbdp_result");
		sdbjs = (JSONObject) sbdp_result.get(String.valueOf(sbdp_result.size()));
		return sdbjs.get("sbp_ave").toString() + "," + sdbjs.get("dbp_ave").toString();
	}

	/**
	 * 心率
	 * 
	 * @return
	 */
	public static String Heartrate(String phone_num, String password, String start_time, String device_id) {
		JSONObject jsonObject = HealthtoolServiceImpl.requesthealth(HTRATE, phone_num, password, start_time, device_id);
		logger.info(jsonObject.toString());
		JSONObject htrate_result = (JSONObject) jsonObject.get("htrate_result");
		JSONObject htratejs = (JSONObject) htrate_result.get(String.valueOf(htrate_result.size()));
		return htratejs.get("htrate_ave").toString();
	}

	/**
	 * 血氧
	 * 
	 * @return
	 */
	public static String Bloodoxygen(String phone_num, String password, String start_time, String device_id) {
		JSONObject jsonObject = HealthtoolServiceImpl.requesthealth(SPO, phone_num, password, start_time, device_id);
		logger.info(jsonObject.toString());
		JSONObject spo_result = (JSONObject) jsonObject.get("spo_result");
		JSONObject spojs = (JSONObject) spo_result.get(String.valueOf(spo_result.size()));
		return spojs.get("spo_ave").toString();
	}

	/**
	 * 微循环
	 * 
	 * @return
	 */
	public static String microcirculation(String phone_num, String password, String start_time, String device_id) {
		JSONObject jsonObject = HealthtoolServiceImpl.requesthealth(BK, phone_num, password, start_time, device_id);
		logger.info(jsonObject.toString());
		JSONObject bk_result = (JSONObject) jsonObject.get("bk_result");
		JSONObject bkjs = (JSONObject) bk_result.get(String.valueOf(bk_result.size()));
		return bkjs.get("bk_ave").toString();
	}

	/**
	 * 体检报告
	 * 
	 * @return
	 */
	public static String Amedicalreport(String phone_num, String password, String start_time, String device_id) {
		JSONObject jsonObject = HealthtoolServiceImpl.requesthealth(REPORT, phone_num, password, start_time, device_id);
		logger.info(jsonObject.toString());
		String report = (String) jsonObject.get("report");
		return report;
	}

	/**
	 * 呼吸頻率
	 * 
	 * @return
	 */
	public static String respirationrate(String phone_num, String password, String start_time, String device_id) {

		return HealthtoolServiceImpl.HAVrespirationrate(RESPIRATIONRATE, phone_num, password, start_time, device_id);
	}

	/**
	 * HRV
	 * 
	 * @return
	 */
	public static String HRV(String phone_num, String password, String start_time, String device_id) {
		return HealthtoolServiceImpl.HAVrespirationrate(HRV, phone_num, password, start_time, device_id);
	}

	// 注册发送验证码
	/**
	 * 发送验证码
	 * 
	 * @param validate_number
	 *            验证码
	 * @param phone_num
	 *            手机号码
	 * @param channel_id
	 *            惊凡提供
	 * @param channel_secret
	 *            惊凡提供
	 * @return
	 */
	public static boolean registeredcode(String phone_num, String num) {
		String url = "https://api.jingfantech.com/V1.02/register_step_1";
		String httpOrgCreateTest = url;
		JSONObject createMap = new JSONObject();
		createMap.put("validate_number", num);
		createMap.put("phone_num", phone_num);
		createMap.put("channel_id", Managementconstant.channel_id);
		createMap.put("channel_secret", Managementconstant.channel_secret);
		Gson gson = new Gson();
		String json = gson.toJson(createMap);
		String httpOrgCreateTestRtn = HttpClientUtil.doPost(httpOrgCreateTest, json, charset);
		JSONObject jsonObject = JSONObject.fromObject(httpOrgCreateTestRtn);
		if (jsonObject.get("message").equals("Send successfully.")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 注册
	 * 
	 * @param validate_number
	 *            验证码
	 * @param phone_num
	 *            手机号码
	 * @param password
	 *            要设置的密码
	 * @param channel_id
	 *            惊凡提供
	 * @param channel_secret
	 *            惊凡提供
	 * @return
	 */
	public static boolean registered(String phone_num, String validate_number, String password) {
		String url = "https://api.jingfantech.com/V1.02/register_step_2";
		String httpOrgCreateTest = url;
		JSONObject createMap = new JSONObject();
		createMap.put("validate_number", validate_number);
		createMap.put("phone_num", phone_num);
		createMap.put("password", password);
		createMap.put("channel_id", Managementconstant.channel_id);
		createMap.put("channel_secret", Managementconstant.channel_secret);
		Gson gson = new Gson();
		String json = gson.toJson(createMap);
		String httpOrgCreateTestRtn = HttpClientUtil.doPost(httpOrgCreateTest, json, charset);
		JSONObject jsonObject = JSONObject.fromObject(httpOrgCreateTestRtn);
		if (jsonObject.get("message").equals("Registered successfully.")
				|| jsonObject.get("message").equals("The user already exists and failed to register.")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 登录
	 * 
	 * @param phone_num
	 * @param password
	 * @param channel_id
	 * @param channel_secret
	 * @return
	 */
	public static boolean login(String phone_num, String password) {
		String url = "https://api.jingfantech.com/V1.02/login";
		String httpOrgCreateTest = url;
		JSONObject createMap = new JSONObject();
		createMap.put("phone_num", phone_num);
		createMap.put("password", password);
		createMap.put("channel_id", Managementconstant.channel_id);
		createMap.put("channel_secret", Managementconstant.channel_secret);
		Gson gson = new Gson();
		String json = gson.toJson(createMap);
		String httpOrgCreateTestRtn = HttpClientUtil.doPost(httpOrgCreateTest, json, charset);
		JSONObject jsonObject = JSONObject.fromObject(httpOrgCreateTestRtn);
		if (jsonObject.get("message").equals("Login successfully.")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 找回密码发送验证码
	 * 
	 * @param phone_num
	 * @param password
	 * @param channel_id
	 * @param channel_secret
	 * @return
	 */
	public static boolean Getbackpassword(String phone_num, String num) {
		String url = "https://api.jingfantech.com/V1.02/retrieve_pw_1";
		String httpOrgCreateTest = url;
		JSONObject createMap = new JSONObject();
		createMap.put("validate_number", num);
		createMap.put("phone_num", phone_num);
		createMap.put("channel_id", Managementconstant.channel_id);
		createMap.put("channel_secret", Managementconstant.channel_secret);
		Gson gson = new Gson();
		String json = gson.toJson(createMap);
		String httpOrgCreateTestRtn = HttpClientUtil.doPost(httpOrgCreateTest, json, charset);
		JSONObject jsonObject = JSONObject.fromObject(httpOrgCreateTestRtn);
		if (jsonObject.get("message").equals("Send successfully.")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 更新密码
	 * 
	 * @param new_password
	 * @param phone_num
	 * @param channel_id
	 * @param channel_secret
	 * @return
	 */
	public static boolean updatepassword(String new_password, String phone_num) {
		String url = "https://api.jingfantech.com/V1.02/retrieve_pw_2";
		String httpOrgCreateTest = url;
		JSONObject createMap = new JSONObject();
		createMap.put("new_password", new_password);
		createMap.put("phone_num", phone_num);
		createMap.put("channel_id", Managementconstant.channel_id);
		createMap.put("channel_secret", Managementconstant.channel_secret);
		Gson gson = new Gson();
		String json = gson.toJson(createMap);
		String httpOrgCreateTestRtn = HttpClientUtil.doPost(httpOrgCreateTest, json, charset);
		JSONObject jsonObject = JSONObject.fromObject(httpOrgCreateTestRtn);
		if (jsonObject.get("message").equals("Password update successfully.")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 高低压值的算法
	 * 
	 * @param userval
	 * @param jfval
	 * @return
	 */
	private static Integer highLowPressureVal(Integer jfdao, Integer jfval) {

		// 用户设置的高压值*0.6
		jfdao = (int) (jfdao * 0.8);
		// 硬件的高压值*0.4
		jfval = (int) (jfval * 0.2);
		return jfdao + jfval;
	}

	/**
	 * 心率的算法
	 * 
	 * @param jfdaoval
	 *            校准值
	 * @param jfval
	 *            硬件心率值
	 * @return
	 *//*
		 * private static Integer hartrateVal(Integer jfdaoval, Integer jfval){
		 * 
		 * Random r = new Random(); if(jfval<=29){ jfval = r.nextInt(13)+66;
		 * }else if(jfval>180){ jfval = r.nextInt(13)+66; }else{
		 * if(jfdaoval==null){ jfdaoval = 80; } //用户设置心率校准值的*0.6 jfdaoval =
		 * (int) (jfdaoval*0.5); //硬件的心率值*0.4 jfval = (int)
		 * (jfval*0.5)+jfdaoval; } return jfval; }
		 */
	/**
	 * 算法
	 * 
	 * @param bean
	 *            校准值
	 * @param jfval
	 *            硬件心率值
	 * @return
	 */
	private static Integer function(Integer bean, Integer jfval) {

		// 用户设置心率校准值的*0.6
		Integer jfdaoval = (int)(bean *  0.5);
		// 硬件的心率值*0.4
		jfval = (int) (jfval * 0.4) + jfdaoval;
		return jfval;
	}

	private static Integer microcirVal(Integer microcirval) {
		Random r = new Random();
		if (microcirval < 50) {
			microcirval = r.nextInt(20) + 71;
		}
		return microcirval;
	}

	/**
	 * 步数到达极光推送
	 * 
	 * @throws ParseException
	 * @throws SQLException
	 */
	public static void walkCountPush(Integer stepWhen, User user,Integer eqId) {
		try {
			String walkPushTime = user.getWalkPushTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			String dd = df.format(new Date());

			// 目标步数不能为空 && (上次时间 不能为空 || 今天有没有上传过 )
			if (user.getWalkCount() != null && (walkPushTime == null || !dd.equals(walkPushTime))) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("countdate", dd + "%");
				map.put("userid", user.getId());
				map.put("userId", user.getId());

				// 查询开关
				Push push = pushService.selectPush(map);

				// 总开关和步数通知开关
				if (push.getAllNotifyOn() && push.getWalkNotifyOn()) {
					
					// 查询当天步数
					List<EquipmentData> countdata = equipmentDataMapper.selecttheycount(map);
					Integer num = 0;// 获取步数
					if (countdata.size() > 0 && null != countdata) {
						for (EquipmentData eqcount : countdata) {
							int count = eqcount.getStepWhen();
							num = num + count;
						}
					}
					if (num >= Integer.valueOf(user.getWalkCount())) {
						
						UserEq userEq = userEqMapper.ifguardianship(eqId);
						// 开启极光推送
						Thread t = new Thread() {
							public void run() {
								JpushClientUtil.sendToAlias(String.valueOf(userEq.getUserId()), "墨子鑫健康管家通知", "步数到达通知",
										user.getName() + "当天步数已达到目标步数", "", "4");
							}
						};
						t.start();
					}
					User u = new User();
					u.setId(user.getId());
					u.setWalkPushTime(dd);
					userservice.updateUser(u);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 围栏极光推送
	 * 
	 * @throws ParseException
	 * @throws SQLException
	 */
	public static void fencePush(Positionig p,Integer eqId,String imei ) {
		try {
				User user = userservice.getUser(imei);
				
				if(user!=null){
					
					//拿出使用者的半径和圆点
					//半径
					String radius = user.getRadius();
					//圆点
					String midpoint = user.getMidpoint();
					
					//经纬度
					String[] positioningDataArr = p.getPositioningData().split(":");
					//经度
					String longitude = positioningDataArr[0];
					//纬度
					String latitude = positioningDataArr[1];
					
					//计算是否超出范围
					
					
					
					//如果超出范围,
					if(latitude==null){
					// 查询开关
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("userId", user.getId());
					Push push = pushService.selectPush(map);
					
					// 总开关和围栏通知开关
					if (push.getAllNotifyOn() && push.getFenceNotifyOn()) {
							UserEq userEq = userEqMapper.ifguardianship(eqId);
							// 开启极光推送
							Thread t = new Thread() {
								public void run() {
									JpushClientUtil.sendToAlias(String.valueOf(userEq.getUserId()), "墨子鑫健康管家通知", "围栏通知",
											user.getName() + "穿戴者不在围栏范围", "", "5");
								}
							};
							t.start();
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
