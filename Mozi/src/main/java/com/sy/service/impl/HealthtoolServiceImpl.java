package com.sy.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
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
import com.sy.utils.DataParsing;
import com.sy.utils.DataRow;
import com.sy.utils.JfUtil;
import com.sy.utils.Managementconstant;

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
	 * 硬件上传惊凡数据
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
				
				String stattime = JfUtil.Uploadhealth(account, "123456", data, device_id, u, type);
				// 血压()
				String bloodr = JfUtil.bloodpressure(account, "123456", stattime, device_id);
				// 心率
				String heartr = JfUtil.Heartrate(account, "123456", stattime, device_id);
				// 血氧
				String bloodxy = JfUtil.Bloodoxygen(account, "123456", stattime, device_id);
				// 微循环
				String microcir = JfUtil.microcirculation(account, "123456", stattime, device_id);
				// 体检报告
				String amedical = JfUtil.Amedicalreport(account, "123456", stattime, device_id);
				String hrv = JfUtil.HRV(account, "123456", stattime, device_id);
				// 呼吸频率
				String respiration = JfUtil.respirationrate(account, "123456", stattime, device_id);
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
						//情绪=hrv
						health = DataParsing.mood(health,hrv);
						health.setPhone(account);
						health.setImei(imei);
						// 插入健康数据表
						logger.info("才健准备插入数据:>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
						jfhealthmapper.insert(health);
						//插入准确数据
						Realhealth record = new Realhealth();
	                    record.setAmedicalreport(amedical);
	                    record.setBloodoxygen(Integer.valueOf(bloodxy));
	                    record.setHeartrate(Integer.parseInt(heartr));
	                    record.setSbpAve(Integer.parseInt(bloodxygen[0]));
	                    record.setDbpAve(Integer.parseInt(bloodxygen[1]));
	                    record.setHRV(Integer.parseInt(hrv));
	                    record.setMicrocirculation(Integer.parseInt(microcir));
	                    record.setMood(Integer.parseInt(hrv));
	                    record.setPhone(account);
	                    record.setImei(imei);
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
						jfhealthNew.setMood(health.getMood());
						jfhealthNew.setPhone(health.getPhone());
						jfhealthNew.setImei(imei);
						logger.info("才健获取最新数据:>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
						JfhealthNew newjfhealthNew = jfhealthNewMapper.newJfhealthNew(imei.toString());
						if (newjfhealthNew != null) {
							jfhealthNew.setId(newjfhealthNew.getId());
							jfhealthNewMapper.updateById(jfhealthNew);
						} else {
							jfhealthNewMapper.insert(jfhealthNew);
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

}
