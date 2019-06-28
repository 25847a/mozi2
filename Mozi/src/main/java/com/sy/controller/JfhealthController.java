package com.sy.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.sy.common.ResultData;
import com.sy.mapper.EquipmentDataMapper;
import com.sy.pojo.Jfhealth;
import com.sy.pojo.Management;
import com.sy.pojo.User;
import com.sy.service.EquipmentDataService;
import com.sy.service.ConfigService;
import com.sy.service.JfhealthService;
import com.sy.service.UserEqService;
import com.sy.service.UserService;
import com.sy.utils.DataRow;
import com.sy.utils.DataUtil;
import com.sy.utils.Managementconstant;
import com.sy.utils.PageModel;
import com.sy.vo.Chart;
import com.sy.vo.SHChart;

@Controller
@RequestMapping(value = "health")
public class JfhealthController {
	
	private final static Logger logger = LoggerFactory.getLogger(JfhealthController.class);
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
	ConfigService extendService;
	

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
	 * 二级页面数据展示
	 * @param m
	 * @return
	 */
	@RequestMapping("/querySecondaryData")
	@ResponseBody
	public ResultData<DataRow> querySecondaryData(@RequestBody DataRow map){
		ResultData<DataRow> re = new ResultData<DataRow>();
		try {
			re =jfhealthservice.querySecondaryData(map,re);
		} catch (Exception e) {
			logger.error("JfhealthController>>>>>>>>>>>>>querySecondaryData",e);
		}
		return re;
		
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
		m.put("userId", Managementconstant.channel_id+m.get("userId"));
		List<Chart> chart = jfhealthservice.selecthealth(m);//查询用户2018-04-11健康数据
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
			Map<String,String> map = jfhealthservice.selectBloodpressureInfo(m);
			List<Map<String,Object>> list = DataUtil.polymerization("最高血压","","最低血压","","最新血压","平均血压",map);	
			data.put("detail", list);
			data.put("createtime", map.get("createtime"));
			data.put("count", map.get("count"));
			data.put("show", DataUtil.tipsBloodpressure(m));
			data.put("chartData", bloodpressureList);
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
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("categoryId", "2");
		data.put("name", "heartrate");
		data.put("desc", "心率");
		ResultData<Map<String,Object>> re = new ResultData<Map<String,Object>>();
		m.put("userId", Managementconstant.channel_id+m.get("userId"));
		List<Chart> chart = jfhealthservice.selecthealth(m);
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
			Map<String,String> map = jfhealthservice.selectHeartRateInfo(m);
			List<Map<String,Object>> list = DataUtil.polymerization("最快心率","","最慢心率","","最新心率","平均心率",map);	
			data.put("detail", list);
			data.put("count", map.get("count"));
			data.put("createtime", map.get("createtime"));
			data.put("userId", map.get("userId"));
			data.put("show", DataUtil.tipsHeartRate(m));
			data.put("chartData", bloodpressureList);
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
		data.put("categoryId", "5");
		data.put("name", "hrv");
		data.put("desc", "心率变异性HRV");
		ResultData<Map<String,Object>> re = new ResultData<Map<String,Object>>();
		m.put("userId", Managementconstant.channel_id+m.get("userId"));
		List<Chart> chart = jfhealthservice.selecthealth(m);
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
				Map<String,String> map = jfhealthservice.selectHeartRateInfo(m);
				List<Map<String,Object>> list = DataUtil.polymerization("最高HRV","","最低HRV","","最新HRV","平均HRV",map);	
				data.put("detail", list);
				data.put("count", map.get("count"));
				data.put("createtime", map.get("createtime"));
				data.put("userId", map.get("userId"));
				data.put("show", DataUtil.tipsHrv(m));
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
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("categoryId", "3");
			data.put("name", "step_when");
			data.put("desc", "步数");
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
					Map<String,String> map = jfhealthservice.selectStepWhenInfo(m);
					List<Map<String,Object>> list = DataUtil.polymerization("步数最高",map.get("maxtime"),"步数最低",map.get("mintime"),"最新步数","平均步数",map);	
					data.put("detail", list);
					data.put("kilometre", map.get("kilometre"));
					data.put("createtime", map.get("createtime"));
					data.put("userId", m.get("userId"));
					data.put("show", DataUtil.tipsStepWhen(m));
					data.put("chartData", bloodpressureList);
					User user2 = userservice.getUser(Integer.valueOf(String.valueOf(m.get("userId"))));
					data.put("stepNumber", user2.getWalkCount());
					
					
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
	
}
