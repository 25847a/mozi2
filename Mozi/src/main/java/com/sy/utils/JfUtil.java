package com.sy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.sy.pojo.User;
import net.sf.json.JSONObject;

/**
 * 天津供应商工具类
 * 
 * @author Administrator
 *
 */
public class JfUtil {

	private final static Logger logger = LoggerFactory.getLogger(JfUtil.class);

	private static String charset = "utf-8";
	private static String url = "https://api.jingfantech.com/V1.02/physical_exam/manufacturer";
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
	// HRV
	private static final String HRV = "HRV";

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
	 * 获取呼吸頻率,HRV的方法
	 * @param service
	 * @param phone_num
	 * @param password
	 * @param start_time
	 * @param device_id
	 * @return
	 */
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
		logger.info("惊凡返回的数据》》》》》》》》》》》》》》" + httpOrgCreateTestRtn);
		return httpOrgCreateTestRtn;
	}
	/**
	 * 获取血压,血氧,微循环,心率,体检报告的方法
	 * @param service
	 * @param phone_num
	 * @param password
	 * @param start_time
	 * @param device_id
	 * @return
	 */
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
		logger.info("惊凡返回的数据》》》》》》》》》》》》》》" + httpOrgCreateTestRtn);
		JSONObject jsonObject = JSONObject.fromObject(httpOrgCreateTestRtn);
		return jsonObject;
	}

	/**
	 * 获取stattime的方法
	 * 
	 * @param phone_num
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String Uploadhealth(String phone_num, String password, String data, String device_id, User u,
			String type) throws Exception {
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
		JSONObject jsonObject = JfUtil.requesthealth(SBDP, phone_num, password, start_time, device_id);
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
		JSONObject jsonObject = JfUtil.requesthealth(HTRATE, phone_num, password, start_time, device_id);
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
		JSONObject jsonObject = JfUtil.requesthealth(SPO, phone_num, password, start_time, device_id);
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
		JSONObject jsonObject = JfUtil.requesthealth(BK, phone_num, password, start_time, device_id);
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
		JSONObject jsonObject = JfUtil.requesthealth(REPORT, phone_num, password, start_time, device_id);
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

		return JfUtil.HAVrespirationrate(RESPIRATIONRATE, phone_num, password, start_time, device_id);
	}

	/**
	 * HRV
	 * 
	 * @return
	 */
	public static String HRV(String phone_num, String password, String start_time, String device_id) {
		return JfUtil.HAVrespirationrate(HRV, phone_num, password, start_time, device_id);
	}
}
