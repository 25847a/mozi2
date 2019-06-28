package com.sy.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sy.common.ResultData;
import com.sy.pojo.EquipmentData;
import com.sy.service.EquipmentDataService;
@Controller
@RequestMapping(value = "equipmentData")
public class EquipmentDataController {
	@Autowired
	private EquipmentDataService equipmentdateservice;
	
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
