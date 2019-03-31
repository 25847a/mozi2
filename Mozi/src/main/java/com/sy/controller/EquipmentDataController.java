package com.sy.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sy.common.ResultData;
import com.sy.pojo.EquipmentData;
import com.sy.service.EquipmentDataService;
import com.sy.service.UserEqService;
import com.sy.utils.PageModel;
import com.sy.vo.Sleepings;
import com.sy.vo.Sleepingsvo;
import com.sy.vo.Steps;
import com.sy.vo.Stepsvo;
@Controller
@RequestMapping(value = "equipmentData")
public class EquipmentDataController {
	@Autowired
	private EquipmentDataService equipmentdateservice;
	@Autowired
	private UserEqService userEqservice;
	@RequestMapping(value = "list")
	public ModelAndView list(Integer pageNo, String keyword) {
		ModelAndView mo = new ModelAndView();
		PageModel<EquipmentData> pagemodel = equipmentdateservice.getusersone(pageNo,
				keyword);
		mo.setViewName("equipmentData");
		mo.addObject("pagemodel", pagemodel);
		return mo;
	}
	@RequestMapping(value = "selectdata")
	@ResponseBody
	public ResultData<EquipmentData>selectdata(@RequestBody Map m){
		ResultData<EquipmentData> re = new ResultData<EquipmentData>();
		EquipmentData eqdata=equipmentdateservice.selectdata(Integer.valueOf((String) m.get("userid")));
		if(eqdata !=null){
			re.setCode(200);
			re.setData(eqdata);
			re.setMessage("获取用户最新健康数据成功！！！");
		}else {
			re.setCode(350);
			re.setMessage("该用户没有健康数据！！！");
		}
		return re;
	}
	@RequestMapping(value = "selectstepsDate")
	@ResponseBody
	public ResultData<Stepsvo> selectstepsDate(@RequestBody Map m){
		ResultData<Stepsvo> re = new ResultData<Stepsvo>();
		String imei = (String)m.get("imei");
		String service = (String)m.get("service"); 
		String timedata = (String)m.get("timedata"); 
		if(imei !=null && !imei.equals("") && service !=null && !service.equals("") ){
			Integer userId =userEqservice.getimei(imei);
			Map map = new HashMap();
			String [] timedatas = null ;
			if(!service.equals("week")){
			 timedatas =timedata.split("-");
			}
			//日
			if(service.equals("day")){
				map.put("month", timedatas[1]);
				map.put("timedata", timedatas[2]);
				map.put("year", timedatas[0]);
				map.put("keyWord", "day");
			//月
			}else if (service.equals("month")) {
				map.put("timedata", timedatas[1]);
				map.put("year", timedatas[0]);
				map.put("keyWord", "month");
			//年
			}else if (service.equals("year")) {
				map.put("timedata", timedatas[0]);
				map.put("keyWord", "year");
			}else if(service.equals("week")){
				map.put("keyWord", "week");
			}
			map.put("userId", userId);
			List<EquipmentData> equipmentdates =equipmentdateservice.selectdateequipmentDate(map);
			if(equipmentdates != null && equipmentdates.size() >0){
				Stepsvo svo = new Stepsvo();
				List<Steps> ls = new ArrayList<Steps>();
				//记录总步数
				Integer  totalsteps=0;
					for(int i =0; i< equipmentdates.size(); i++){
						EquipmentData data = 	equipmentdates.get(i);
						Steps stp = new Steps();
						stp.setId(i);
						stp.setStepcount(data.getStepWhen());
						stp.setStepdatetime(data.getCreatetime());
						ls.add(stp);
						totalsteps=totalsteps+data.getStepWhen();
					}
					svo.setStepss(ls);
					EquipmentData newdate =equipmentdates.get(equipmentdates.size()-1);
					 double dis = 0;
					dis = Math.round(newdate.getStepEach()/100d)/10d;
					System.out.println(dis+"米");
					svo.setTargetsteps(String.valueOf(newdate.getStepEach()));
					svo.setActiondistance(String.valueOf(dis)+"米");
					svo.setStepsduration(timejshu(equipmentdates.get(0).getCreatetime(), newdate.getCreatetime()));
					svo.setRunduration(Runduration(equipmentdates.get(0).getCreatetime(), newdate.getCreatetime()));
					svo.setTotalsteps(String.valueOf(totalsteps));
				re.setCode(200);
				re.setData(svo);
				re.setMessage("获取步数健康数据成功！！！");
			}else{
				re.setCode(350);
				re.setMessage("该用户没有健康数据！！！");
			}
		}else {
			re.setCode(400);
			re.setMessage("参数不能为空！！！");
		}

		return re;
	}
	@RequestMapping(value = "selectsleepingDate")
	@ResponseBody
	public ResultData<Sleepingsvo> selectsleepingDate(@RequestBody Map m){
		
		ResultData<Sleepingsvo> re = new ResultData<Sleepingsvo>();
		String imei = (String)m.get("imei");
		String service = (String)m.get("service"); 
		String timedata = (String)m.get("timedata"); 
		if(imei !=null && !imei.equals("") && service !=null && !service.equals("") ){
		Integer userId =userEqservice.getimei(imei);
		Map map = new HashMap();
		String [] timedatas = null ;
		if(!service.equals("week")){
		 timedatas =timedata.split("-");
		}
		//日
		if(service.equals("day")){
			map.put("month", timedatas[1]);
			map.put("timedata", timedatas[2]);
			map.put("year", timedatas[0]);
			map.put("keyWord", "day");
		//月
		}else if (service.equals("month")) {
			map.put("timedata", timedatas[1]);
			map.put("year", timedatas[0]);
			map.put("keyWord", "month");
		//年
		}else if (service.equals("year")) {
			map.put("timedata", timedatas[0]);
			map.put("keyWord", "year");
		}else if(service.equals("week")){
			map.put("keyWord", "week");
		}
		map.put("userId", userId);
		List<EquipmentData> equipmentdates =equipmentdateservice.selectdateequipmentDate(map);
		if(equipmentdates != null && equipmentdates.size() >0){
			Sleepingsvo svo = new Sleepingsvo();
			//深度睡眠
			double depthsleepings=0;
			//浅度睡眠
			double shallowsleepings=0;
			//觉醒时间
			double awakeningtime=0;
			//总睡眠时间
			double  totalsleeping = 0;
			List<Sleepings> ls = new ArrayList<Sleepings>();
			if(equipmentdates!=null && equipmentdates.size() >0){
				for(int i =0; i< equipmentdates.size(); i++){
					EquipmentData data = 	equipmentdates.get(i);
					Sleepings stp = new Sleepings();
					stp.setId(i);
					stp.setSleeping(data.getSleeping());
					stp.setSleepingtime(data.getCreatetime());
					ls.add(stp);
					if(data.getSleepingS().equals("0")) {
						awakeningtime=awakeningtime+data.getSleeping();
					}else if (data.getSleepingS().equals("1")) {
						depthsleepings = depthsleepings+data.getSleeping();
					}else if (data.getSleepingS().equals("2")) {
						shallowsleepings = shallowsleepings+data.getSleeping();
					}else if (data.getSleepingS().equals("3")) {
						
					}else if (data.getSleepingS().equals("4")) {
						
					}
					totalsleeping =totalsleeping+data.getSleeping();
				}
				svo.setSleepings(ls);
				svo.setAwakeningtime(String.valueOf(awakeningtime));
				svo.setDepthsleepings(String.valueOf(depthsleepings));
				svo.setShallowsleepings(String.valueOf(shallowsleepings));
				svo.setTotalsleeping(String.valueOf(totalsleeping));
			re.setCode(200);
			re.setData(svo);
			re.setMessage("获取睡眠数健康数据成功！！！");
			}else {
				re.setCode(350);
				re.setMessage("该用户没有健康数据！！！");
			}
		}else{
			re.setCode(350);
			re.setMessage("该用户没有健康数据！！！");
		}
		}else {
			re.setCode(400);
			re.setMessage("参数不能为空！！！");
		}
		return re;
	}
	public String timejshu(Date d1,Date d2 ){
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    String text = null;
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
        } 
        
        return text;
	}
	
	public String Runduration(Date d1,Date d2 ){
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    String text = null;
        try  
        {  
          long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别  
          long days = diff / (1000 * 60 * 60 * 24);  
       
          long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);  
          long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);  
          System.out.println(""+days+"天"+hours/2+"小时"+minutes/2+"分");  
          text=""+days+"天"+hours/2+"小时"+minutes/2+"分";
        }catch (Exception e)  
        {  
        } 
        
        return text;
	}
	 
}
