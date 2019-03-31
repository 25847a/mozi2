package com.sy.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sy.common.ResultData;
import com.sy.mapper.EquipmentDataMapper;
import com.sy.pojo.EquipmentData;
import com.sy.pojo.Extend;
import com.sy.pojo.Jfhealth;
import com.sy.pojo.Management;
import com.sy.service.EquipmentDataService;
import com.sy.service.ExtendService;
import com.sy.service.JfhealthService;
import com.sy.service.UserEqService;
import com.sy.service.UserService;
import com.sy.utils.Managementconstant;
import com.sy.utils.PageModel;
import com.sy.vo.Chart;
import com.sy.vo.SHChart;

@Controller
@RequestMapping(value = "health")
public class JfhealthController {
	private static final String String = null;
	@Autowired
	private JfhealthService jfhealthservice;
	@Autowired
	private UserEqService userEqservice;
	@Autowired
	private UserService userservice;
	@Autowired
	EquipmentDataService equipmentdateservice;
	@Autowired
	EquipmentDataMapper equipmentDataMapper;
	@Autowired
	ExtendService extendService;
	

	@RequestMapping(value = "list")
	public ModelAndView list(Integer pageNo, String keyword,String time,Integer userid, HttpSession session) {
		
		ModelAndView mo = new ModelAndView();
		
		Map<String,Object> map = new HashMap<>();
		Management m  = (Management)session.getAttribute("USER");
		String role = m.getRole();
		if("代理商".equals(role)){
			map.put("mid", m.getId());
		}
		map.put("keyword", keyword);
		map.put("userid", userid);
		map.put("time", time);
		PageModel<Jfhealth> pagemodel = jfhealthservice.getJfhealthVoLsit(pageNo, map);
		mo.setViewName("jfhealth");
		mo.addObject("keyword", keyword);
		mo.addObject("time", time);
		mo.addObject("userid", userid);
		mo.addObject("pagemodel", pagemodel);
		return mo;
	}
	/**
	 * 1获取血压数据 （根据年月日 周）查找
	 * @param m
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "bloodpressure")
	@ResponseBody
	public ResultData<Map<String,Object>> bloodpressure(@RequestBody Map<String,Object> m) throws ParseException {
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("categoryId", "1");
		data.put("name", "pressure");
		data.put("desc", "血压");
		ResultData<Map<String,Object>> re = new ResultData<Map<String,Object>>();
		String service = (String) m.get("service");
		String timedata = (String) m.get("timedata");
		Integer userId = userEqservice.getimei((String) m.get("imei"));//通过设备号获取用户ID
		Map<String,Object> map = new HashMap<String,Object>();
		String[] timedatas = null;
		if (!service.equals("week")) {//把2018-04-11分成3个数组
			timedatas = timedata.split("-");
		}
		// 日
		if (service.equals("day")) {
			map.put("month", timedatas[1]);//04
			map.put("timedata", timedatas[2]);//11
			map.put("year", timedatas[0]);//2018
			map.put("keyWord", "day");
			// 月
		} else if (service.equals("month")) {
			map.put("timedata", timedatas[1]);
			map.put("year", timedatas[0]);
			map.put("keyWord", "month");
			// 年
		} else if (service.equals("year")) {
			map.put("timedata", timedatas[0]);
			map.put("keyWord", "year");
		} else if (service.equals("week")) {
			map.put("keyWord", "week");
		}
		map.put("phone", Managementconstant.channel_id + String.valueOf(userservice.getUser(userId).getId()));//把用户手机放入Map
		m.put("phone", Managementconstant.channel_id + String.valueOf(userservice.getUser(userId).getId()));//把用户手机放入Map
		List<Chart> chart = jfhealthservice.selecthealth(m);//查询用户2018-04-11健康数据
		Jfhealth bloodpressureMax = jfhealthservice.selecthealthMax(map);//获取时间下最大血压
		Jfhealth bloodpressureMin = jfhealthservice.selecthealthMin(map);//获取时间下最小血压
		Jfhealth jfhealth= jfhealthservice.newjfhealth((String) m.get("imei"));
		if(bloodpressureMax != null && bloodpressureMin !=null && null!=jfhealth) {
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();	
			Map<String,Object> detail = new HashMap<String,Object>();
			detail.put(new String("detailId"), "5");
			detail.put(new String("name"), "血压最高值");
			detail.put(new String("value"), bloodpressureMax.getSbpAve()+"/"+bloodpressureMax.getDbpAve());
			detail.put(new String("updateTime"), bloodpressureMax.getCreatetime());
			list.add(detail);
			detail = new HashMap<String,Object>();
			detail.put(new String("detailId"), "10");
			detail.put(new String("name"), "血压最低值");
			detail.put(new String("value"), bloodpressureMin.getSbpAve()+"/"+bloodpressureMin.getDbpAve());
			detail.put(new String("updateTime"), bloodpressureMax.getCreatetime());
			list.add(detail);
			detail = new HashMap<String,Object>();
			detail.put(new String("detailId"), "15");
			detail.put(new String("name"), "血压");
			detail.put(new String("value"), jfhealth.getSbpAve()+"/"+jfhealth.getDbpAve());
			detail.put(new String("updateTime"), jfhealth.getCreatetime());
			list.add(detail);
			data.put("detail", list);
		}else {
			re.setCode(350);
			re.setMessage("暂无健康数据！！！");
			return re;
		}
		if (chart != null && chart.size() > 0) {//判断非空
			 List<Map<String,Object>> bloodpressureList = new ArrayList<Map<String,Object>>();	
			for (int i = 0; i < chart.size(); i++) {
				Map<String,Object> chartData = new HashMap<String,Object>();
				Chart j = chart.get(i);
				int[] bloodpressure = new int[2];
				bloodpressure[0]=j.getSbpAve();
				bloodpressure[1]=j.getDbpAve();
				chartData.put("value", bloodpressure);
				chartData.put("createtime", j.getDate());
				chartData.put("updateTime", j.getDate());
				bloodpressureList.add(chartData);
			}
			data.put("chartData", bloodpressureList);
			data.put("h5url","http://120.76.201.150:8080/avatars/120.png");
			data.put("imageurl", "http://120.76.201.150:8080/avatars/bloodpressure.png");
			re.setCode(200);
			re.setData(data);
			re.setMessage("获取血压健康数据成功！！！");
		} else {
			re.setCode(350);
			re.setMessage("暂无健康数据！！！");
		}
		return re;
	}	
	/**
	 * 获取心率数据 （根据年月日 周）查找
	 * @param m
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "selecthealth")
	@ResponseBody
	public ResultData<Map<String,Object>> selecthealth(@RequestBody Map<String,Object> m) throws ParseException {
		//List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//Map<String,Object> detail = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("categoryId", "2");
		data.put("name", "heartrate");
		data.put("desc", "心率");
		ResultData<Map<String,Object>> re = new ResultData<Map<String,Object>>();
		String imei = (String) m.get("imei");
		Integer userId = userservice.getUser(imei).getId();
		m.put("phone", Managementconstant.channel_id + String.valueOf(userservice.getUser(userId).getId()));
		List<Chart> chart = jfhealthservice.selecthealth(m);
		/*Jfhealth jfhealth= jfhealthservice.newjfhealth((String) m.get("imei"));
		if(null!=jfhealth) {
			detail.put(new String("detailId"), "1");
			detail.put(new String("name"), "心率");
			detail.put(new String("value"), jfhealth.getHeartrate());
			detail.put(new String("updateTime"), jfhealth.getCreatetime());
			list.add(detail);
			data.put("detail", list);
		}else {
			re.setCode(400);
			re.setMessage("没有健康数据！！！");
			return re;
		}*/
		if (chart != null && chart.size() > 0) {
		List<Map<String,Object>> bloodpressureList = new ArrayList<Map<String,Object>>();
			for (int i = 0; i < chart.size(); i++) {
				Chart j = chart.get(i);
				Map<String,Object> chartData = new HashMap<String,Object>();
				int[] bloodpressure = new int[1];
				bloodpressure[0] = j.getHeartrate();
				chartData.put("value", bloodpressure);
				chartData.put("createtime", j.getDate());
				chartData.put("updateTime",  j.getDate());
				bloodpressureList.add(chartData);
			}
			data.put("chartData", bloodpressureList);
			data.put("h5url","http://120.76.201.150:8080/avatars/120.png");
			data.put("imageurl","http://120.76.201.150:8080/avatars/health.png");
			re.setCode(200);
			re.setData(data);
			re.setMessage("获取心率健康数据成功！！！");
		} else {
			re.setCode(400);
			re.setMessage("没有健康数据！！！");
		}
		return re;
	}
	/**
	 * 获取血氧数据 （根据年月日 周）查找
	 * @param m
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="selectQxygen")
	@ResponseBody
	public ResultData<Map<String,Object>> selectBloodoxygen(@RequestBody Map<String,Object> m)throws ParseException {
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("categoryId", "3");
		data.put("name", "qxygen");
		data.put("desc", "血氧");
		ResultData<Map<String,Object>> re = new ResultData<Map<String,Object>>();
		Integer userId = userEqservice.getimei((String) m.get("imei"));
		m.put("phone", Managementconstant.channel_id + String.valueOf(userservice.getUser(userId).getId()));
		List<Chart> chart = jfhealthservice.selecthealth(m);
		if (chart != null && chart.size() > 0) {
			List<Map<String,Object>> bloodpressureList = new ArrayList<Map<String,Object>>();
				for (int i = 0; i < chart.size(); i++) {
					Chart j = chart.get(i);
					Map<String,Object> chartData = new HashMap<String,Object>();
					int[] bloodpressure = new int[1];
					bloodpressure[0] = j.getBloodoxygen();
					chartData.put("value", bloodpressure);
					chartData.put("createtime", j.getDate());
					chartData.put("updateTime",  j.getDate());
					bloodpressureList.add(chartData);
				}
				data.put("chartData", bloodpressureList);
				re.setCode(200);
				re.setData(data);
				re.setMessage("获取血氧健康数据成功！！！");
			} else {
				re.setCode(400);
				re.setMessage("没有血氧健康数据！！！");
			}
			return re;
	}
	/**
	 * 获取呼吸数据 （根据年月日 周）查找
	 * @param m
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="selectBreathe")
	@ResponseBody
	public ResultData<Map<String,Object>> selectBreathe(@RequestBody Map<String,Object> m)throws ParseException {
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("categoryId", "4");
		data.put("name", "breathe");
		data.put("desc", "呼吸");
		ResultData<Map<String,Object>> re = new ResultData<Map<String,Object>>();
		Integer userId = userEqservice.getimei((String) m.get("imei"));
		m.put("phone", Managementconstant.channel_id + String.valueOf(userservice.getUser(userId).getId()));
		List<Chart> chart = jfhealthservice.selecthealth(m);
		if (chart != null && chart.size() > 0) {
			List<Map<String,Object>> bloodpressureList = new ArrayList<Map<String,Object>>();
				for (int i = 0; i < chart.size(); i++) {
					Chart j = chart.get(i);
					Map<String,Object> chartData = new HashMap<String,Object>();
					int[] bloodpressure = new int[1];
					bloodpressure[0] = j.getRespirationrate();
					chartData.put("value", bloodpressure);
					chartData.put("createtime", j.getDate());
					chartData.put("updateTime",  j.getDate());
					bloodpressureList.add(chartData);
				}
				data.put("chartData", bloodpressureList);
				re.setCode(200);
				re.setData(data);
				re.setMessage("获取呼吸健康数据成功！！！");
			} else {
				re.setCode(400);
				re.setMessage("没有呼吸健康数据！！！");
			}
			return re;
	}
	/**
	 * 获取心跳变异数据 （根据年月日 周）查找
	 * @param m
	 * @return
	 * @throws ParseException
	*/
	@RequestMapping(value="selectHrv")
	@ResponseBody
	public ResultData<Map<String,Object>> selectHrv(@RequestBody Map<String,Object> m)throws ParseException {
		Map<String,Object> data = new HashMap<String,Object>();
		//List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//Map<String,Object> detail = new HashMap<String,Object>();
		data.put("categoryId", "5");
		data.put("name", "hrv");
		data.put("desc", "心率变异性HRV");
		ResultData<Map<String,Object>> re = new ResultData<Map<String,Object>>();
		Integer userId = userEqservice.getimei((String) m.get("imei"));
		m.put("phone", Managementconstant.channel_id + String.valueOf(userservice.getUser(userId).getId()));
		List<Chart> chart = jfhealthservice.selecthealth(m);
		/*Jfhealth jfhealth= jfhealthservice.newjfhealth((String) m.get("imei"));
		if(null!=jfhealth) {
			detail.put(new String("detailId"), "2");
			detail.put(new String("name"), "心跳变异");
			detail.put(new String("value"), jfhealth.getHrv());
			detail.put(new String("updateTime"), jfhealth.getCreatetime());
			list.add(detail);
			data.put("detail", list);
		}else{
			re.setCode(400);
			re.setMessage("没有心跳变异数据！！！");
			return re;
		}*/
		if (chart != null && chart.size() > 0) {
			List<Map<String,Object>> bloodpressureList = new ArrayList<Map<String,Object>>();
				for (int i = 0; i < chart.size(); i++) {
					Chart j = chart.get(i);
					Map<String,Object> chartData = new HashMap<String,Object>();
					int[] bloodpressure = new int[1];
					bloodpressure[0] = j.getHRV();
					chartData.put("value", bloodpressure);
					chartData.put("createtime", j.getDate());
					chartData.put("updateTime",  j.getDate());
					bloodpressureList.add(chartData);
				}
				data.put("chartData", bloodpressureList);
				re.setCode(200);
				re.setData(data);
				re.setMessage("获取心跳变异数据成功！！！");
			} else {
				re.setCode(400);
				re.setMessage("没有心跳变异数据！！！");
			}
			return re;
		
	} 
	/**
	 * 获取微循环数据 （根据年月日 周）查找
	 * @param m
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="selectMocrocirculation")
	@ResponseBody
	public ResultData<Map<String,Object>> selectMocrocirculation(@RequestBody Map<String,Object> m)throws ParseException {
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("categoryId", "6");
		data.put("name", "mocrocirculation");
		data.put("desc", "微循环");
		ResultData<Map<String,Object>> re = new ResultData<Map<String,Object>>();
		Integer userId = userEqservice.getimei((String) m.get("imei"));
		m.put("phone", Managementconstant.channel_id + String.valueOf(userservice.getUser(userId).getId()));
		List<Chart> chart = jfhealthservice.selecthealth(m);
		if (chart != null && chart.size() > 0) {
			List<Map<String,Object>> bloodpressureList = new ArrayList<Map<String,Object>>();
				for (int i = 0; i < chart.size(); i++) {
					Chart j = chart.get(i);
					Map<String,Object> chartData = new HashMap<String,Object>();
					int[] bloodpressure = new int[1];
					bloodpressure[0] = j.getMicrocirculation();
					chartData.put("value", bloodpressure);
					chartData.put("createtime", j.getDate());
					chartData.put("updateTime",  j.getDate());
					bloodpressureList.add(chartData);
			
	}
				data.put("chartData", bloodpressureList);
				re.setCode(200);
				re.setData(data);
				re.setMessage("获取微循环健康数据成功！！！");
			} else {
				re.setCode(400);
				re.setMessage("没有微循环健康数据！！！");
			}
			return re;
		
	}
	/**
	 * 获取步数数据 （根据年月日 周）查找
	 * @param m
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="selectStepWhen")
	@ResponseBody
	public ResultData<Map<String,Object>> selectStepWhen(@RequestBody Map<String,Object> m) {
		ResultData<Map<String,Object>> re = new ResultData<Map<String,Object>>();
		try {
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			Map<String,Object> detail = new HashMap<String,Object>();
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("categoryId", "7");
			data.put("name", "step_when");
			data.put("desc", "步数");
			Integer userId = userEqservice.getimei((String) m.get("imei"));
			m.put("userId", userId);
			
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			 String dd = df.format(new Date());
			 Map<String,Object> map= new HashMap<String, Object>();
			 map.put("countdate", dd+"%");
			 map.put("userid",userId);
			 List<EquipmentData> countdata= equipmentDataMapper.selecttheycount(map);
			 Integer num = 0;//获取步数
			 Integer reliang = 0;//获取卡里路
			 
			 
			 if(countdata.size() >0 && null!=countdata) {
					System.out.println("用户id》》》》》》》》》》》》》》》》"+userId);
					for(EquipmentData eqcount: countdata){
						int count=eqcount.getStepWhen();
						num+= count;
						int reliangNum = eqcount.getCarrieroad();
						reliang+= reliangNum;
					} 
			 }else {
				 num = 0;
				 reliang = 0;
			 }
			detail.put("detailId", "3");
			detail.put("name", "步数");
			detail.put("value", num);
			detail.put("unit", "步");
			list.add(detail);
			detail = new HashMap<String,Object>();
			detail.put(new String("detailId"), "4");
			detail.put(new String("name"), "今日消耗热量");
			detail.put(new String("value"), reliang);
			detail.put(new String("unit"), "千卡");
			list.add(detail);
			detail = new HashMap<String,Object>();
			detail.put(new String("detailId"), "6");
			detail.put(new String("name"), "今日行动距离");
			detail.put(new String("value"), String.valueOf(num*0.6).substring(0, String.valueOf(num*0.6).indexOf(".")));
			detail.put(new String("unit"), "米");
			list.add(detail);
			detail = new HashMap<String,Object>();
			Extend extend =extendService.selectExtend(1);
			detail.put(new String("detailId"), "7");
			detail.put(new String("name"), "目标步数");
			detail.put(new String("value"), extend.getOption1());
			detail.put(new String("unit"), "步");
			list.add(detail);
			data.put("detail", list);
			List<SHChart> shChart = jfhealthservice.selectSHChart(m);
			
			if (shChart != null && shChart.size() > 0) {
				List<Map<String,Object>> bloodpressureList = new ArrayList<Map<String,Object>>();
					for (int i = 0; i < shChart.size(); i++) {
						SHChart j = shChart.get(i);
						Map<String,Object> chartData = new HashMap<String,Object>();
						int[] bloodpressure = new int[1];
						bloodpressure[0] = j.getStep_when();
						chartData.put("value", bloodpressure);
						chartData.put("createtime", j.getDate());
						chartData.put("updateTime",  j.getDate());
						bloodpressureList.add(chartData);
					}
					data.put("chartData", bloodpressureList);
					re.setCode(200);
					re.setData(data);
					re.setMessage("获取步数健康数据成功！！！");
				} else {
					re.setCode(400);
					re.setMessage("没有步数健康数据！！！");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	/**
	 * 获取睡眠数据 （根据年月日 周）查找
	 * @param m
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="selecSleeping")
	@ResponseBody
	public ResultData<Map<String,Object>> selecSleeping(@RequestBody Map<String,Object> m)throws ParseException {
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("categoryId", "8");
		data.put("name", "step_when");
		data.put("desc", "睡眠");
		ResultData<Map<String,Object>> re = new ResultData<Map<String,Object>>();
		Integer userId = userEqservice.getimei((String) m.get("imei"));
		m.put("userId", userId);
		List<SHChart> shChart = jfhealthservice.selectSHChart(m);
		if (shChart != null && shChart.size() > 0) {
			List<Map<String,Object>> bloodpressureList = new ArrayList<Map<String,Object>>();
				for (int i = 0; i < shChart.size(); i++) {
					SHChart j = shChart.get(i);
					Map<String,Object> chartData = new HashMap<String,Object>();
					int[] bloodpressure = new int[1];
					bloodpressure[0] = j.getSleeping();
					chartData.put("value", bloodpressure);
					chartData.put("createtime", j.getDate());
					chartData.put("updateTime",  j.getDate());
					bloodpressureList.add(chartData);
				}
				data.put("chartData", bloodpressureList);
				re.setCode(200);
				re.setData(data);
				re.setMessage("获取睡眠健康数据成功！！！");
			} else {
				re.setCode(400);
				re.setMessage("没有睡眠健康数据！！！");
			}
			return re;
		
	}
	/**得出两个时间差距的天数时分秒
	 * @param d1减数
	 * @param d2被减数
	 */
	public String operating(Date d1,Date d2){
		String text=null;
        try  
        {  
          long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别  
          long days = diff / (1000 * 60 * 60 * 24);  
       
          long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);  
          long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);  
          System.out.println(""+days+"天"+hours+"小时"+minutes+"分");  
          text=""+days+"天"+hours+"小时"+minutes+"分";
        }catch (Exception e)  
        {  
        	return text;
        }
		return text;  
	}
	
	public long Minute(   Date d1 ,Date d2) throws ParseException{
		   long between=(d1.getTime()-d2.getTime())/1000;//除以1000是为了转换成秒
		   long min=between/60;
		   System.out.println(min);
		   return min;
	}
	
	/**根据分钟得出天数据小时分钟
	 * @param num
	 */
	public String  Minutecount(Integer num){
		int day = num/(24*60);
		int hour = (num%(24*60))/60;
		int minute = (num%(24*60))%60;
		
		System.out.println(day+"天"+hour+"小时"+minute+"分");
		return day+"天"+hour+"小时"+minute+"分";
	}
}
