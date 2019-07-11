package com.sy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.mapper.JfhealthdaoMapper;
import com.sy.mapper.UserMapper;
import com.sy.pojo.Jfhealthdao;
import com.sy.pojo.User;
import com.sy.service.JfhealthdaoService;
import com.sy.utils.Managementconstant;

@Controller
@RequestMapping(value = "jfhealthdao")
public class JfhealthdaoControllor {
	private final static Logger logger = LoggerFactory.getLogger(JfhealthdaoControllor.class);
	@Autowired
	private JfhealthdaoService jfhealthdaoservice;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JfhealthdaoMapper jfhealthdaoMapper ;
	@RequestMapping("jfhealthdao")
	public String jfhealthdao() {
		return "jfhealthdao";
	}
	/**
	 * 新增人工学习
	 * @param HRV
	 * @param sbpAve
	 * @param dbpAve
	 * @param heartrate
	 * @param bloodoxygen
	 * @param microcirculation
	 * @param respirationrate
	 * @param phone
	 * @param imei
	 * @param amedicalreport
	 * @return
	 */
	@RequestMapping("addjfhealthdao")
	public String addjfhealthdao(Integer HRV,Integer sbpAve,Integer dbpAve,Integer heartrate,Integer bloodoxygen,Integer microcirculation,
	Integer respirationrate,String phone,String imei,String amedicalreport) {
		Jfhealthdao health = new Jfhealthdao( );
		health.setAmedicalreport(amedicalreport);
		health.setBloodoxygen(bloodoxygen);
		health.setSbpAve(sbpAve);
		health.setDbpAve(dbpAve);
		health.setCreatetime(new Date());
		health.setHeartrate(heartrate);
		health.setHRV(HRV);
		health.setMicrocirculation(microcirculation);
		health.setRespirationrate(respirationrate);
		health.setPhone(Managementconstant.channel_id
				+ phone);
		health.setImei(imei);
		jfhealthdaoservice.addJfhealthdao(health);
		return "jfhealthdao";
	}
	/**
	 * 修改人工学习
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateJfhealthdao")
	public ResultBase updateJfhealthdao( @RequestBody Jfhealthdao jfhealthdao) {
		ResultBase re = new ResultBase();
		try{
			re=jfhealthdaoservice.updateJfhealthdao(jfhealthdao,re);
		}catch (Exception e) {
			re.setCode(400);
			re.setMessage("修改失败");
			logger.error("JfhealthdaoControllor>>>>>>>>>>>>>>>>>>updateJfhealthdao",e);
			
		}
		return re;
	}
	@RequestMapping("getinfo")
	@ResponseBody
	public ResultData<Map<String,Object>>  jfhealthdao(@RequestBody Map m) {
		
		
		ResultData<Map<String,Object>> re = new ResultData<Map<String,Object>>();
		try{
		String userId = (String)m.get("userId");
		userId = userId==null?((Integer)m.get("id")).toString():userId;
		if(userId==null){
			re.setCode(400);
			re.setMessage("userId为null！！！");
		}else{
		Jfhealthdao jfhealthdao = jfhealthdaoservice.getjfhealthdao("mozistar"+userId);
		User user = userMapper.selectByPrimaryKey(Integer.valueOf(userId));
		if(jfhealthdao==null){
			jfhealthdao = new Jfhealthdao();
			jfhealthdao.setCreatetime(new Date());
			jfhealthdao.setPhone("mozistar"+userId);
			jfhealthdao.setImei(user.getImei());
			jfhealthdao.setHeartrate(80);//心率
			jfhealthdao.setBloodoxygen(97);//血氧
			jfhealthdao.setHRV(59);//Hrv
			jfhealthdao.setMicrocirculation(85);//微循环
			jfhealthdao.setRespirationrate(16);//呼吸
			jfhealthdao.setSbpAve(120);//高压
			jfhealthdao.setDbpAve(80);//低压
			jfhealthdaoMapper.insert(jfhealthdao);
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createtime = formatter.format(jfhealthdao.getCreatetime());
		Map<String,Object> data = new HashMap<>();
			data.put("createtime", createtime);
			data.put("calibration", user.getCalibration());
			data.put("HRV", jfhealthdao.getHRV()==null?59:jfhealthdao.getHRV());
			data.put("Heartrate", jfhealthdao.getHeartrate()==null?80:jfhealthdao.getHeartrate());
			data.put("Bloodoxygen", jfhealthdao.getBloodoxygen()==null?97:jfhealthdao.getBloodoxygen());
			data.put("microcirculation", jfhealthdao.getMicrocirculation()==null?85:jfhealthdao.getMicrocirculation());
			data.put("respirationrate", jfhealthdao.getRespirationrate()==null?16:jfhealthdao.getRespirationrate());
			data.put("sbpAve", jfhealthdao.getSbpAve()==null?120:jfhealthdao.getSbpAve());
			data.put("dbpAve", jfhealthdao.getDbpAve()==null?80:jfhealthdao.getDbpAve());
			re.setCode(200);
			re.setData(data);
			re.setMessage("成功！！！");
		}
		}catch (Exception e) {
			e.printStackTrace();
			re.setCode(400);
			re.setMessage("服务出错");;
		}
		return re;
	}
}
