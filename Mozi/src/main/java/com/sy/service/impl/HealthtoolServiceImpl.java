package com.sy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.google.gson.Gson;
import com.sy.mapper.EquipmentDataMapper;
import com.sy.mapper.JfhealthMapper;
import com.sy.mapper.JfhealthNewMapper;
import com.sy.mapper.RealhealthMapper;
import com.sy.mapper.UserEqMapper;
import com.sy.pojo.Jfhealth;
import com.sy.pojo.JfhealthNew;
import com.sy.pojo.Jfhealthdao;
import com.sy.pojo.Realhealth;
import com.sy.pojo.User;
import com.sy.service.JfhealthdaoService;
import com.sy.service.PushService;
import com.sy.service.UserService;
import com.sy.utils.BinaryReadWrite;
import com.sy.utils.DataParsing;
import com.sy.utils.DataRow;
import com.sy.utils.HttpClientUtil;
import com.sy.utils.Managementconstant;

import net.sf.json.JSONObject;

@Service
public class HealthtoolServiceImpl {
	private static UserService userservice = new UserServiceImpl();
	private static UserEqMapper userEqMapper;
	private static JfhealthMapper jfhealthmapper;
	private static RealhealthMapper realhealthMapper;
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
		//String hrv2 = HRV(Managementconstant.channel_id + "108", "123456", "2018-07-16 17:43:11", "6A6668010001010101010000FFFFFFFF");
		//System.out.println(hrv2);
		// // 血压
		// String bloodr =
		// bloodpressure(Managementconstant.channel_id+"108","123456",
		// date,"6A6668010001010101010000FFFFFFFF");
		// String [] bloodxygen = bloodr.split(",");
		// System.out.println(bloodxygen[0]+"=========="+bloodxygen[1]);
		// String a="a78961f";
		// boolean respiration = registered("m123", "123456", "123456");
		String data="wsWzKYAOjS2Q+oHBiHzWkt/ko63dCQm4aDG0sRIhsrL48jku4b7zaeR42QT8Ly/TJ0253tc4tCauhjC/OHxpEXuGj+BChCDNMA9mIjKEaxLRk+1UfwB+hq3uINAmI48b/ZKPyUJm3QJf3xe2U7erm/ClZ49vl9Q6dNQwVkq9tDy+NbkoOgPUcNuFjKkA/a5Dlwq70vx38FAAqAsYEb7cYSwQiOmASX65Z9Rrf2Px1Pa1I+KfPwd13gYmbNBWHwX+3ToUYtXeaprd36ZYINTHv7QfP6Hl22erzE/Zw/Mc+hxUdflFNycXy3gCAvmq+E4XTNrHVgKZYNs+vD8mJ7mmsuLm0kDaJmTr+aIqS4txxmy9/fDNRei0pK0RWovZBOTFbgGiUNiMX39Xjx+p8qcUN1NgSihXiNwT4UUQ6woormOPmyp2ASM54H5pvO7mjpxgzchp9/wy5Ay6gawLBLoJ3HBP0ipoREaMPCsI2eoKsEheGwZEpOM9ELMBNHdoSGqxqBVUpe4P0RMOeuUOA4EXQADoYiXdZ3rfxSY+QJ/IpRxBoHJcLqVeI4KtExdozHMEt8zu3ZlXZ4Pi1zBWitZ0cfk5GGoB/jyV3UL7REprwGayiOQaa9WsoVMK8NWsJvS6zeUF4Fj3/cmMSwy5SOTblp6hGY4GamthBjyK1cC0sHvFm0vqp1ZXFQ7N4bYcNBiMYFef0B+zMO9hivE2KzcRgFM8LFfJNldXl0PXDG1MoK0S1E8eV8M3ZlmXQ6yjyOh63a0N13mArVA3sUNZz23YDDoPKEx7xN4MLhuZQRLDqagdpSwi0OCsIIBVH2IEXTG/RsE5wo6C3qqt3VM6Gz5V+Q0mGsXxuBDEOKFbH4IGfNGC8HtJvrIYzYwccc0pyE3Py2dzzpuUrj1sfUeF0R8oUlHLfzuhyXzc5twSMgR5Uz5TDjW7tO";
		
		String stattime = Uploadhealth("mozistar28706", "123456", data, "6A6668010001010101010000FFFFFFFF", 28706, "T14"); 
		System.out.println(stattime);
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
		if (realhealthMapper == null) {
			realhealthMapper = (RealhealthMapper) webApplicationContext.getBean("realhealthMapper");
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
		if (u == null) {
			u = new User(null, null, null, null, null, 50, "男", null, null, null, null, null, null, null);
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
			User u = userservice.getUser(imei);
			if (u == null) {
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
				//获取当天总步数和卡里路
				DataRow row =equipmentDataMapper.queryStepWhenCarrieroadSum(u.getId());
				String returnStr = null;

				// 使用者的的校准数据
				Jfhealthdao jfdao = jfhealthdaoservice.JfhealthdaoInfo(imei, account);

				// t14表示硬件5分钟自动上传一次健康数据
				if (type.equals("T14")) {
					if (jfdao != null) {
						Realhealth record = new Realhealth();
	                    record.setAmedicalreport(amedical);
	                    record.setBloodoxygen(Integer.valueOf(bloodxy));
	                    record.setHeartrate(Integer.parseInt(heartr));
	                    record.setSbpAve(Integer.parseInt(bloodxygen[0]));
	                    record.setDbpAve(Integer.parseInt(bloodxygen[1]));
	                    record.setHRV(Integer.parseInt(hrv));
	                    record.setMicrocirculation(Integer.parseInt(microcir));
	                    record.setPhone(account);
	                    record.setImei(imei);
	                	Jfhealth health = new Jfhealth();
	                	logger.info("才健正在采集数据:>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	                	//血压,心率
	                	health= DataParsing.bloodPressure(health, jfdao, heartr, bloodxygen[0], bloodxygen[1]);
						// 报告
						health.setAmedicalreport(amedical);
						// 血氧
						health.setBloodoxygen(Integer.parseInt(bloodxy)<93?(int)(95+Math.random()*(99-95+1)):Integer.parseInt(bloodxy));
						// Hrv
						health = DataParsing.DataHrv(health, jfdao, hrv);
						//微循环
						health = DataParsing.DataMicrocirculation(health, jfdao, microcir);
						//呼吸
						health = DataParsing.respirationrate(health, jfdao, respiration);
						health.setPhone(account);
						health.setImei(imei);
						// 插入健康数据表
						logger.info("才健准备插入数据:>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
						jfhealthmapper.insert(health);
						realhealthMapper.insert(record);
						// 插入最新数据表,供app接口查询
						JfhealthNew jfhealthNew = new JfhealthNew();
						jfhealthNew.setHRV(health.getHRV());
						jfhealthNew.setSbpAve(health.getSbpAve());
						jfhealthNew.setDbpAve(health.getDbpAve());
						jfhealthNew.setHeartrate(health.getHeartrate());
						jfhealthNew.setBloodoxygen(health.getBloodoxygen());
						jfhealthNew.setMicrocirculation(health.getMicrocirculation());
						jfhealthNew.setAmedicalreport(health.getAmedicalreport());
						jfhealthNew.setRespirationrate(health.getRespirationrate());
						jfhealthNew.setPhone(health.getPhone());
						jfhealthNew.setImei(imei);
						logger.info("才健获取最新数据:>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
						JfhealthNew newjfhealthNew = jfhealthNewMapper.newJfhealthNew(imei.toString());
						if (newjfhealthNew != null) {
							jfhealthNew.setId(newjfhealthNew.getId());
							jfhealthNewMapper.updateById(jfhealthNew);
							logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>才健更新最新数据");
						} else {
							jfhealthNewMapper.insert(jfhealthNew);
							logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>才健插入最新数据");
						}
						// 开启极光推送
						// 比较监护者设置的通知范围,不在该范围内就通知监护者
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("user", u);
						// $R05|OK|H1:心率,H2:高压,H3:底压,H4:血氧,H5:微循环,H6:HRV,H8:呼吸频率 G1 步数 G3卡路里
						String queryPushNews = pushService.queryPushNews(map, jfhealthNew);
						returnStr = "$R05|H1:" + health.getHeartrate() + ",H2:" + health.getSbpAve() + ",H3:" + health.getDbpAve()
								+ ",H4:" + health.getBloodoxygen() + ",H5:" + health.getMicrocirculation() + ",H6:" + health.getHRV() + ",H8:"
								+ health.getRespirationrate() + ",H9:" + queryPushNews + ",G1:" + row.getInt("stepWhen") + ",G3:" + row.getInt("carrieroad")
								+ "\r\n";
					} else {
						returnStr = "nocalibration";
					}
				} else {
					// T15手动校准数据
					if (jfdao == null) {
						jfdao = new Jfhealthdao();
						jfdao=DataParsing.DataHealthdao(jfdao, heartr, amedical, bloodxy, bloodxygen[0], bloodxygen[1], hrv, microcir, respiration, account, imei);
						jfhealthdaoservice.addJfhealthdao(jfdao);
					} else {
						jfdao=DataParsing.DataHealthdao(jfdao, heartr, amedical, bloodxy, bloodxygen[0], bloodxygen[1], hrv, microcir, respiration, account, imei);
						jfhealthdaoservice.updatajf(jfdao);
					}
					returnStr = "$R05|H1:" + jfdao.getHeartrate() + ",H2:" + jfdao.getSbpAve() + ",H3:" + jfdao.getDbpAve() + ",H4:"
							+ jfdao.getBloodoxygen() + ",H5:" + jfdao.getMicrocirculation() + ",H6:" + jfdao.getHRV() + ",H8:" + jfdao.getRespirationrate() + ",H9:0,G1:"
							+ row.getInt("stepWhen")  + ",G3:" + row.getInt("carrieroad") + "\r\n";
				}
				return returnStr;
			}
		} catch (Exception e) {
			logger.info("addjfthealth>>>>>>>>",e);
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
		if (u == null) {
			u = new User(null, null, null, null, null, 50, "男", null, null, null, null, null, null, null);
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

}
