package com.sy.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sy.common.ResultData;
import com.sy.pojo.JfhealthNew;

/**
 * APP 二级页面封装
 * @author Administrator
 *
 */
public class DataUtil {
	/*******************************************************二级页面的聚合函数值******************************************************/
	/**
	 * 二级页面的聚合函数值
	 * @return
	 */
	public static List<Map<String,Object>> polymerization(String max,String maxtime,String min,String mintime,String nw,String avg, Map<String,String> map){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();	
		Map<String,Object> detail = new HashMap<String,Object>();
		detail.put("detailId", "5");
		detail.put("name", max);
		detail.put("value", map.get("max"));
		detail.put("time", maxtime);
		list.add(detail);
		detail = new HashMap<String,Object>();
		detail.put("detailId", "10");
		detail.put("name", min);
		detail.put("value", map.get("min"));
		detail.put("time", mintime);
		list.add(detail);
		detail = new HashMap<String,Object>();
		detail.put("detailId", "15");
		detail.put("name", nw);
		detail.put("value", map.get("new"));
		detail.put("time", "");
		list.add(detail);
		detail = new HashMap<String,Object>();
		detail.put("detailId", "20");
		detail.put("name", avg);
		detail.put("value", map.get("avg"));
		detail.put("time", "");
		list.add(detail);
		return list;
	}
	/*******************************************************首页数据******************************************************/
	/**
	 * 步数type=0(正常) type=1(异常)
	 * @return
	 */
	public static DataRow stepWhenData(String name,String desc,int category,String unit,int lastestValue){
		DataRow map=healthyData(name,desc,category,unit,lastestValue,0);
		return map;
	}
	/**
	 * 心率type=0(正常) type=1(异常)
	 * @return
	 */
	public static DataRow heartrateData(String name,String desc,int category,String unit,int lastestValue){
		int type=1;
		if(lastestValue<Constant.heartHig && lastestValue>Constant.heartLow){
			type=0;
		}
		DataRow map=healthyData(name,desc,category,unit,lastestValue,type);
		return map;
	}
	/**
	 * 血压type=0(正常) type=1(异常)
	 * @return
	 */
	public static DataRow bloodData(String name,String desc,int category,String unit,int sbpAve,int dbpAve){
		int type=1;
		if(sbpAve<Constant.bloodHig && sbpAve>Constant.bloodLow){
			type=0;
		}
		DataRow map=healthyData(name,desc,category,unit,sbpAve+ "/"+ dbpAve,type);
		return map;
	}
	/**
	 *  湿度type=0(正常) type=1(异常)
	 * @return
	 */
	public static DataRow humidityData(String name,String desc,int category,String unit,float lastestValue){
		int type=1;
		if(lastestValue>=ReadProperties.getFloatValue("warmLow") && lastestValue<ReadProperties.getFloatValue("warmJust")){
			type=0;
		}
		DataRow map=healthyData(name,desc,category,unit,"--",type);
	//	DataRow map=healthyData(name,desc,category,unit,lastestValue,type);
		return map;
	}
	/**
	 * 体温type=0(正常) type=1(异常)
	 * @return
	 */
	public static DataRow temperatureData(String name,String desc,int category,String unit,float lastestValue){
		int type=1;
		if(lastestValue>=ReadProperties.getFloatValue("warmLow") && lastestValue<ReadProperties.getFloatValue("warmJust")){
			type=0;
		}
		DataRow map=healthyData(name,desc,category,unit,"--",type);
	//	DataRow map=healthyData(name,desc,category,unit,lastestValue,type);
		return map;
	}
	/**
	 * HRVtype=0(正常) type=1(异常)
	 * @return
	 */
	public static DataRow hrvData(String name,String desc,int category,String unit,int lastestValue,int age){
		int type=1;
		if(age>Constant.childLow &&  age<Constant.childHig){
			if(lastestValue>Constant.childHRVLow && lastestValue>Constant.childHRVHig){
				type=0;
			}
		}else if(age>Constant.youthLow && age<Constant.youthHig){
			if(lastestValue>Constant.youthHRVLow && lastestValue>Constant.youthHRVHig){
				type=0;
			}
		}else if(age>Constant.elderlyLow && age<Constant.elderlyHig){
			if(lastestValue>Constant.elderlyHRVLow && lastestValue>Constant.elderlyHRVHig){
				type=0;
			}
		}
		DataRow map=healthyData(name,desc,category,unit,lastestValue,type);
		return map;
	}
	/**
	 * 情绪type=0(正常) type=1(异常)
	 * @return
	 */
	public static DataRow moodData(String name,String desc,int category,String unit,int lastestValue){
		int type=0;
		String value = "";
		if(lastestValue>100){
			value="良好";
		}else if(lastestValue>=50 && lastestValue<=100){
			value="波动";
		}else if(lastestValue<50){
			value="改变";
			type=1;
		}
		DataRow map=healthyData(name,desc,category,unit,value,type);
		return map;
	}
	/**
	 * 微循环type=0(正常) type=1(异常)
	 * @return
	 */
	public static DataRow mocrocirculationData(String name,String desc,int category,String unit,int lastestValue){
		int type=1;
		if(lastestValue>Constant.microcirculationLow && lastestValue<Constant.microcirculationHig){
			type=0;
		}
		DataRow map=healthyData(name,desc,category,unit,lastestValue,type);
		return map;
	}
	/**
	 * 血氧type=0(正常) type=1(异常)
	 * @return
	 */
	public static DataRow qxygenData(String name,String desc,int category,String unit,int lastestValue){
		int type=1;
		if(lastestValue>Constant.bloodOxygenLow && lastestValue<Constant.bloodOxygenHig){
			type=0;
		}
		DataRow map=healthyData(name,desc,category,unit,lastestValue,type);
		return map;
	}
	/**
	 * 卡路里type=0(正常) type=1(异常)
	 * @return
	 */
	public static DataRow carrieroadData(String name,String desc,int category,String unit,int lastestValue){
		DataRow map=healthyData(name,desc,category,unit,lastestValue,0);
		return map;
	}
	/**
	 * 呼吸type=0(正常) type=1(异常)
	 * @return
	 */
	public static DataRow breatheData(String name,String desc,int category,String unit,int lastestValue){
		int type=1;
		if(lastestValue>Constant.respirationrateLow && lastestValue<Constant.respirationrateHig){
			type=0;
		}
		DataRow map=healthyData(name,desc,category,unit,lastestValue,type);
		return map;
	}
	public static DataRow healthyData(String name,String desc,int category,String unit,Object lastestValue,int type){
		DataRow map = new DataRow();
		map.put("name", name);
		map.put("desc", desc);
		map.put("category", category);
		map.put("unit", unit);
		map.put("lastestValue", String.valueOf(lastestValue));
		map.put("type", type);
		return map;
	}
	/*******************************************************二级页面提示语******************************************************/
	/**
	 * 二级页面HRV的提示语
	 * @return
	 */
	public static String tipsHrv(Map<String,Object> m){
		String show="";
		if(((String) m.get("service")) .equals("day")){
			show=Managementconstant.HRV_DAY;
		}else if(((String) m.get("service")) .equals("week")){
			show=Managementconstant.HRV_WEEK;
		}else if(((String) m.get("service")) .equals("month")){
			show=Managementconstant.HRV_MONTH;
		}
		return show;
	}
	/**
	 * 二级页面心率的提示语
	 * @return
	 */
	public static String tipsHeartRate(Map<String,Object> m){
		String show="";
		if(((String) m.get("service")) .equals("day")){
			show=Managementconstant.HEARTRATE_DAY;
		}else if(((String) m.get("service")) .equals("week")){
			show=Managementconstant.HEARTRATE_WEEK;
		}else if(((String) m.get("service")) .equals("month")){
			show=Managementconstant.HEARTRATE_MONTH;
		}
		return show;
	}
	/**
	 * 二级页面血压的提示语
	 * @return
	 */
	public static String tipsBloodpressure(Map<String,Object> m){
		String show="";
		if(((String) m.get("service")) .equals("day")){
			show=Managementconstant.BLOODPRESSURE_DAY;
		}else if(((String) m.get("service")) .equals("week")){
			show=Managementconstant.BLOODPRESSURE_WEEK;
		}else if(((String) m.get("service")) .equals("month")){
			show=Managementconstant.BLOODPRESSURE_MONTH;
		}
		return show;
	}
	/**
	 * 二级页面步数的提示语
	 * @return
	 */
	public static String tipsStepWhen(Map<String,Object> m){
		String show="";
		if(((String) m.get("service")) .equals("day")){
			show=Managementconstant.STEPWHEN_DAY;
		}else if(((String) m.get("service")) .equals("week")){
			show=Managementconstant.STEPWHEN_WEEK;
		}else if(((String) m.get("service")) .equals("month")){
			show=Managementconstant.STEPWHEN_MONTH;
		}
		return show;
	}
	/*******************************************************二级页面的聚合函数值和提示语******************************************************/
	/**
	 * 1.心率type=0(正常) type=1(异常)
	 * @return
	 */
	public static ResultData<DataRow> heartSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,ResultData<DataRow> re){
		String index = ReadProperties.getValue("heartIntroduce");
		String range = ReadProperties.getValue("heartRange");
		int low =ReadProperties.getIntValue("heartLow");
		int higt = ReadProperties.getIntValue("heartHigh");
		int type=1;
		if(jfhealth.getHeartrate()<low){
			re=micro(category,jfhealth.getHeartrate(),index,range,"心率","次/分钟",ReadProperties.getValue("heartAnalyC"),ReadProperties.getValue("heartProposalC"),"心动过慢",type,age,data,jfhealth,re);
		}else if(jfhealth.getHeartrate()>=low && jfhealth.getHeartrate()<=higt){
			type=0;
			re=micro(category,jfhealth.getHeartrate(),index,range,"心率","次/分钟",ReadProperties.getValue("heartAnalyA"),ReadProperties.getValue("heartProposalA"),"心动正常",type,age,data,jfhealth,re);
		}else if(jfhealth.getHeartrate()>higt){
			re=micro(category,jfhealth.getHeartrate(),index,range,"心率","次/分钟",ReadProperties.getValue("heartAnalyB"),ReadProperties.getValue("heartProposalB"),"心动过速",type,age,data,jfhealth,re);
		}
		return re;
	}
	/**
	 * 2.血氧type=0(正常) type=1(异常)
	 * @return
	 */
	public static ResultData<DataRow> bloodSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,ResultData<DataRow> re){
		String index = ReadProperties.getValue("bloodIntroduce");
		String range = ReadProperties.getValue("bloodRange");
		int low =ReadProperties.getIntValue("bloodLow");
		int type=1;
		if(jfhealth.getBloodoxygen()<low){
			re=micro(category,jfhealth.getBloodoxygen(),index,range,"血氧","%",ReadProperties.getValue("bloodAnalyB"),ReadProperties.getValue("bloodProposalB"),"氧供应不足",type,age,data,jfhealth,re);
		}else if(jfhealth.getBloodoxygen()>=low){
			type = 0;
			re=micro(category,jfhealth.getBloodoxygen(),index,range,"血氧","%",ReadProperties.getValue("bloodAnalyA"),ReadProperties.getValue("bloodProposalA"),"正常",type,age,data,jfhealth,re);
		}
		return re;
	}
	/**
	 * 3.微循环type=0(正常) type=1(异常)
	 * @return
	 */
	public static ResultData<DataRow> microcSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,ResultData<DataRow> re){
		String index = ReadProperties.getValue("microcIntroduce");
		String range = ReadProperties.getValue("microcRange");
		int in = ReadProperties.getIntValue("microcIn");//70
		int higt = ReadProperties.getIntValue("microcHigh");//80
		int type = 1;
		if(jfhealth.getMicrocirculation()<in){
			re=micro(category,jfhealth.getMicrocirculation(),index,range,"微循环","%",ReadProperties.getValue("microcAnalyC"),ReadProperties.getValue("microcProposalC"),"血管健康指数较差",type,age,data,jfhealth,re);
		}else if(jfhealth.getMicrocirculation()>=in && jfhealth.getMicrocirculation()<higt){
			re=micro(category,jfhealth.getMicrocirculation(),index,range,"微循环","%",ReadProperties.getValue("microcAnalyB"),ReadProperties.getValue("microcProposalB"),"血管健康指数良好",type,age,data,jfhealth,re);
		}else if(jfhealth.getMicrocirculation()>=higt){
			type=0;
			re=micro(category,jfhealth.getMicrocirculation(),index,range,"微循环","%",ReadProperties.getValue("microcAnalyA"),ReadProperties.getValue("microcProposalA"),"血管健康指数正常",type,age,data,jfhealth,re);
		}
		return re;
	}
	/**
	 * 4.呼吸type=0(正常) type=1(异常)
	 * @return
	 */
	public static ResultData<DataRow> breathSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,ResultData<DataRow> re){
		String index = ReadProperties.getValue("breathIntroduce");
		String range = ReadProperties.getValue("breathRange");
		int low =ReadProperties.getIntValue("breathLow");
		int higt = ReadProperties.getIntValue("breathHigh");
		int type =1;
		if(jfhealth.getRespirationrate()<low){
			re=micro(category,jfhealth.getRespirationrate(),index,range,"呼吸","次/分钟",ReadProperties.getValue("breathAnalyC"),ReadProperties.getValue("breathProposalA"),"呼吸过缓",type,age,data,jfhealth,re);
		}else if(jfhealth.getRespirationrate()>=low && jfhealth.getRespirationrate()<=higt){
			type =0;
			re=micro(category,jfhealth.getRespirationrate(),index,range,"呼吸","次/分钟",ReadProperties.getValue("breathAnalyB"),ReadProperties.getValue("breathProposalB"),"呼吸正常",type,age,data,jfhealth,re);
		}else if(jfhealth.getRespirationrate()>higt){
			re=micro(category,jfhealth.getRespirationrate(),index,range,"呼吸","次/分钟",ReadProperties.getValue("breathAnalyA"),ReadProperties.getValue("breathProposalA"),"呼吸过速",type,age,data,jfhealth,re);
		}
		return re;
	}
	/**
	 * 5.步数type=0(正常) type=1(异常)
	 * @return
	 */
	public static ResultData<DataRow> stepWhenSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,ResultData<DataRow> re){
		String index = ReadProperties.getValue("stepIntroduce");
		String range = ReadProperties.getValue("stepRange");
		re=micro(category,data.getInt("stepWhen"),index,range,"步数","步",ReadProperties.getValue("stepAnalyA"),ReadProperties.getValue("stepProposalA"),"",0,age,data,jfhealth,re);
		return re;
	}
	/**
	 * 6.血压type=0(正常) type=1(异常)
	 * @return
	 */
	public static ResultData<DataRow> pressureSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,ResultData<DataRow> re){
		String index = ReadProperties.getValue("pressureIntroduce");
		String range = ReadProperties.getValue("pressureRange");
		int low =ReadProperties.getIntValue("pressureLow");
		int just = ReadProperties.getIntValue("pressureJust");
		int in = ReadProperties.getIntValue("pressureIn");
		int inHigh = ReadProperties.getIntValue("pressureInHigh");
		int higt = ReadProperties.getIntValue("pressureHigh");
		int type=1;
		if(jfhealth.getSbpAve()<low){
			re=micro(category,jfhealth.getSbpAve()+"/"+jfhealth.getDbpAve(),index,range,"血压","mmHg",ReadProperties.getValue("pressureAnalyA"),ReadProperties.getValue("pressureProposalA"),"低血压",type,age,data,jfhealth,re);
		}else if(jfhealth.getSbpAve()>=low && jfhealth.getSbpAve()<just){
			type=0;
			re=micro(category,jfhealth.getSbpAve()+"/"+jfhealth.getDbpAve(),index,range,"血压","mmHg",ReadProperties.getValue("pressureAnalyB"),ReadProperties.getValue("pressureProposalB"),"正常",type,age,data,jfhealth,re);
		}else if(jfhealth.getSbpAve()>=just && jfhealth.getSbpAve()<in){
			re=micro(category,jfhealth.getSbpAve()+"/"+jfhealth.getDbpAve(),index,range,"血压","mmHg",ReadProperties.getValue("pressureAnalyC"),ReadProperties.getValue("pressureProposalB"),"正常高值血压",type,age,data,jfhealth,re);
		}else if(jfhealth.getSbpAve()>=in && jfhealth.getSbpAve()<inHigh){
			re=micro(category,jfhealth.getSbpAve()+"/"+jfhealth.getDbpAve(),index,range,"血压","mmHg",ReadProperties.getValue("pressureAnalyD"),ReadProperties.getValue("pressureProposalC"),"轻度高血压",type,age,data,jfhealth,re);
		}else if(jfhealth.getSbpAve()>=inHigh && jfhealth.getSbpAve()<higt){
			re=micro(category,jfhealth.getSbpAve()+"/"+jfhealth.getDbpAve(),index,range,"血压","mmHg",ReadProperties.getValue("pressureAnalyE"),ReadProperties.getValue("pressureProposalC"),"中度高血压",type,age,data,jfhealth,re);
		}else if(jfhealth.getSbpAve()>=higt){
			re=micro(category,jfhealth.getSbpAve()+"/"+jfhealth.getDbpAve(),index,range,"血压","mmHg",ReadProperties.getValue("pressureAnalyF"),ReadProperties.getValue("pressureProposalC"),"重度高血压",type,age,data,jfhealth,re);
		}
		return re;
	}
	/**
	 * 7.体温type=0(正常) type=1(异常)
	 * @return
	 */
	public static ResultData<DataRow> warmSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,Float healthData,ResultData<DataRow> re){
		String index = ReadProperties.getValue("warmIntroduce");
		String range = ReadProperties.getValue("warmRange");
		float low =ReadProperties.getFloatValue("warmLow");//36.3
		float just = ReadProperties.getFloatValue("warmJust");//37.5
		float in = ReadProperties.getFloatValue("warmIn");//38
		float inHigh = ReadProperties.getFloatValue("warmInHigh");//39
		float higt = ReadProperties.getFloatValue("warmHigh");//41
		int type=1;
		
		type=0;
		re=micro(category,"--",index,range,"体温","℃",ReadProperties.getValue("warmAnalyB"),ReadProperties.getValue("warmProposalB"),"",type,age,data,jfhealth,re);
		/*if(healthData<low){
			re=micro(category,String.valueOf(healthData),index,range,"体温","℃",ReadProperties.getValue("warmAnalyA"),ReadProperties.getValue("warmProposalA"),"低温",type,age,data,jfhealth,re);
		}else if(healthData>=low && healthData<just){
			type=0;
			re=micro(category,String.valueOf(healthData),index,range,"体温","℃",ReadProperties.getValue("warmAnalyB"),ReadProperties.getValue("warmProposalB"),"正常",type,age,data,jfhealth,re);
		}else if(healthData>=just && healthData<in){
			re=micro(category,String.valueOf(healthData),index,range,"体温","℃",ReadProperties.getValue("warmAnalyC"),ReadProperties.getValue("warmProposalC"),"低热",type,age,data,jfhealth,re);
		}else if(healthData>=in && healthData<inHigh){
			re=micro(category,String.valueOf(healthData),index,range,"体温","℃",ReadProperties.getValue("warmAnalyC"),ReadProperties.getValue("warmProposalC"),"中等度热",type,age,data,jfhealth,re);
		}else if(healthData>=inHigh && healthData<higt){
			re=micro(category,String.valueOf(healthData),index,range,"体温","℃",ReadProperties.getValue("warmAnalyC"),ReadProperties.getValue("warmProposalC"),"高热",type,age,data,jfhealth,re);
		}else if(healthData>=higt){
			re=micro(category,String.valueOf(healthData),index,range,"体温","℃",ReadProperties.getValue("warmAnalyC"),ReadProperties.getValue("warmProposalC"),"超高热",type,age,data,jfhealth,re);
		}*/
		return re;
	}
	/**
	 * 8.湿度type=0(正常) type=1(异常)
	 * @return
	 */
	public static ResultData<DataRow> humiditySecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,String healthData,ResultData<DataRow> re){
		String index = ReadProperties.getValue("humIntroduce");
		String range = ReadProperties.getValue("humIntroduce");
		re=micro(category,"--",index,range,"湿度","%RH",ReadProperties.getValue("humAnaly"),ReadProperties.getValue("humProposal"),"",0,age,data,jfhealth,re);
	//	re=micro(category,healthData,index,range,"湿度","%RH",ReadProperties.getValue("humAnaly"),ReadProperties.getValue("humProposal"),"正常",0,age,data,jfhealth,re);
		return re;
	}
	/**
	 * 9.HRVtype=0(正常) type=1(异常)
	 * @return
	 */
	public static ResultData<DataRow> hrvSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,ResultData<DataRow> re){
		String index = ReadProperties.getValue("hrvIntroduce");
		String range = ReadProperties.getValue("hrvRange");
		String hrvAnalyA = ReadProperties.getValue("hrvAnalyA");
		String hrvAnalyB = ReadProperties.getValue("hrvAnalyB");
		String hrvProposalA = ReadProperties.getValue("hrvProposalA");
		int type=1;
		if(age<30){
			if(jfhealth.getHRV()<25){
				re=micro(category,String.valueOf(jfhealth.getHRV()),index,range,"HRV","ms",hrvAnalyB,hrvProposalA,"异常",type,age,data,jfhealth,re);
			}else if(jfhealth.getHRV()>=25 && jfhealth.getHRV()<120){
				type=0;
				re=micro(category,String.valueOf(jfhealth.getHRV()),index,range,"HRV","ms",hrvAnalyA,hrvAnalyA,"正常",type,age,data,jfhealth,re);
			}else if(jfhealth.getHRV()>=120){
				re=micro(category,String.valueOf(jfhealth.getHRV()),index,range,"HRV","ms",hrvAnalyB,hrvProposalA,"异常",type,age,data,jfhealth,re);
			}
		}else if(age>=30 && age<50){
			if(jfhealth.getHRV()<27){
				re=micro(category,String.valueOf(jfhealth.getHRV()),index,range,"HRV","ms",hrvAnalyB,hrvProposalA,"异常",type,age,data,jfhealth,re);
			}else if(jfhealth.getHRV()>=27 && jfhealth.getHRV()<70){
				type=0;
				re=micro(category,String.valueOf(jfhealth.getHRV()),index,range,"HRV","ms",hrvAnalyA,hrvAnalyA,"正常",type,age,data,jfhealth,re);
			}else if(jfhealth.getHRV()>=70){
				re=micro(category,String.valueOf(jfhealth.getHRV()),index,range,"HRV","ms",hrvAnalyB,hrvProposalA,"异常",type,age,data,jfhealth,re);
			}
		}else if(age>=50 && age<70){
			if(jfhealth.getHRV()<22){
				re=micro(category,String.valueOf(jfhealth.getHRV()),index,range,"HRV","ms",hrvAnalyB,hrvProposalA,"异常",type,age,data,jfhealth,re);
			}else if(jfhealth.getHRV()>=22 && jfhealth.getHRV()<60){
				type=0;
				re=micro(category,String.valueOf(jfhealth.getHRV()),index,range,"HRV","ms",hrvAnalyA,hrvAnalyA,"正常",type,age,data,jfhealth,re);
			}else if(jfhealth.getHRV()>=60){
				re=micro(category,String.valueOf(jfhealth.getHRV()),index,range,"HRV","ms",hrvAnalyB,hrvProposalA,"异常",type,age,data,jfhealth,re);
			}
		}else if(age>70){
			if(jfhealth.getHRV()<22){
				re=micro(category,String.valueOf(jfhealth.getHRV()),index,range,"HRV","ms",hrvAnalyB,hrvProposalA,"异常",type,age,data,jfhealth,re);
			}else if(jfhealth.getHRV()>=22 && jfhealth.getHRV()<60){
				type=0;
				re=micro(category,String.valueOf(jfhealth.getHRV()),index,range,"HRV","ms",hrvAnalyA,hrvAnalyA,"正常",type,age,data,jfhealth,re);
			}else if(jfhealth.getHRV()>=60){
				re=micro(category,String.valueOf(jfhealth.getHRV()),index,range,"HRV","ms",hrvAnalyB,hrvProposalA,"异常",type,age,data,jfhealth,re);
			}
		}
		return re;
	}
	/**
	 * 10.情绪type=0(正常) type=1(异常)
	 * @return
	 */
	public static ResultData<DataRow> moodSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,ResultData<DataRow> re){
		String index = ReadProperties.getValue("moodIntroduce");
		String range = ReadProperties.getValue("moodRange");
		int moodLow =  ReadProperties.getIntValue("moodLow");
		int moodHigh =  ReadProperties.getIntValue("moodHigh");
		int type=0;
		if(jfhealth.getMood()<moodLow){
			type=1;
			re=micro(category,String.valueOf(jfhealth.getMood()),index,range,"情绪","",ReadProperties.getValue("moodAnalyA"),ReadProperties.getValue("moodProposalA"),"情绪波动",type,age,data,jfhealth,re);
		}else if(jfhealth.getMood()>=moodLow && jfhealth.getMood()<=moodHigh){
			re=micro(category,String.valueOf(jfhealth.getMood()),index,range,"情绪","",ReadProperties.getValue("moodAnalyB"),ReadProperties.getValue("moodProposalB"),"情绪良好",type,age,data,jfhealth,re);
		}else if(jfhealth.getMood()>moodHigh){
			re=micro(category,String.valueOf(jfhealth.getMood()),index,range,"情绪","",ReadProperties.getValue("moodAnalyC"),ReadProperties.getValue("moodProposalC"),"情绪平稳",type,age,data,jfhealth,re);
		}
		return re;
	}
	/**
	 * 11.卡里路
	 * @return
	 */
	public static ResultData<DataRow> carrieroadSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,ResultData<DataRow> re){
		String index = ReadProperties.getValue("carrIntroduce");
		String range = ReadProperties.getValue("carrRange");
		re=micro(category,data.getString("carrieroad"),index,range,"卡里路","焦耳/天",ReadProperties.getValue("carrAnaly"),ReadProperties.getValue("carrProposal"),"正常",0,age,data,jfhealth,re);
		return re;
	}
	/**
	 * 公共方法
	 * @return
	 */
	public static ResultData<DataRow> micro(int category,Object healthData,String index,String range,String name,String unit,String analy,String proposal,String reason,int type,int age,DataRow data,JfhealthNew jfhealth,ResultData<DataRow> re){
		DataRow dataRow = new DataRow();
		dataRow.put("index", index);
		dataRow.put("range", range);
		dataRow.put("healthData", healthData.toString());
		dataRow.put("unit", unit);
		dataRow.put("analy", analy);
		dataRow.put("proposal", proposal);
		dataRow.put("reason", reason);
		dataRow.put("name", name);
		dataRow.put("category", category);//排序ID
		dataRow.put("type", type);
		List<DataRow> list = new ArrayList<DataRow>();
		DataRow da = new DataRow();
		//心率
		da=DataUtil.heartrateData("heartrate","心率",1, "次/分",jfhealth.getHeartrate());
		list.add(da);
		//血氧
		da=DataUtil.mocrocirculationData("qxygen","血氧",2, "%",jfhealth.getBloodoxygen());
		list.add(da);
		//微循环
		da=DataUtil.mocrocirculationData("mocrocirculation","微循环",3, "%",jfhealth.getMicrocirculation());
		list.add(da);
		//呼吸
		da=DataUtil.mocrocirculationData("breathe","呼吸",4, "次/分钟",jfhealth.getRespirationrate());
		list.add(da);
		//步数
		da=DataUtil.stepWhenData("Step_when","计步",5, "步",data==null?0: data.getInt("stepWhen"));
		list.add(da);
		//血压
		da=DataUtil.bloodData("pressure","血压",6, "mmHg",jfhealth.getSbpAve(),jfhealth.getDbpAve());
		list.add(da);
		//湿度
		da=DataUtil.humidityData("humidity","湿度",8, "%RH",50);
		list.add(da);
		//体温
		da=DataUtil.temperatureData("temperature","体温",7, "℃",(float)36.5);
		list.add(da);
		//情绪
		da=DataUtil.moodData("mood","情绪",10, "",jfhealth.getMood());
		list.add(da);
		//hrv
		da=DataUtil.hrvData("hrv","心率变异性",9, "ms",jfhealth.getHRV(),age);
		list.add(da);
		//卡路里
		da=DataUtil.mocrocirculationData("carrieroad","卡路里",11, "焦耳/天",data==null?0: data.getInt("carrieroad"));
		list.add(da);
		for(int i =0;i<list.size();i++){
			if(dataRow.getInt("category")==list.get(i).getInt("category")){
				list.remove(i);
			}
		}
		dataRow.put("detail", list);
		re.setCode(200);
		re.setData(dataRow);
		re.setMessage("获取成功");
		return re;
	} 
}
