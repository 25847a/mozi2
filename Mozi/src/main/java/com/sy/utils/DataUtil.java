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
	 * 体温type=0(正常) type=1(异常)
	 * @return
	 */
	public static DataRow humidityData(String name,String desc,int category,String unit,float lastestValue){
		int type=1;
		if(lastestValue>=ReadProperties.getFloatValue("warmLow") && lastestValue<ReadProperties.getFloatValue("warmJust")){
			type=0;
		}
		DataRow map=healthyData(name,desc,category,unit,lastestValue,type);
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
		DataRow map=healthyData(name,desc,category,unit,lastestValue,type);
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
	 * 微循环type=0(正常) type=1(异常)
	 * @return
	 */
	public static DataRow mocrocirculationData(String name,String desc,int category,String unit,int lastestValue){
		int type=1;
		if(lastestValue<Constant.microcirculationLow && lastestValue>Constant.microcirculationHig){
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
		if(lastestValue<Constant.bloodOxygenLow && lastestValue>Constant.bloodOxygenHig){
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
		if(lastestValue<Constant.respirationrateLow && lastestValue>Constant.respirationrateHig){
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
		DataRow dataRow = new DataRow();
		dataRow.put("index", ReadProperties.getValue("heartIntroduce"));
		dataRow.put("range", ReadProperties.getValue("heartRange"));
		dataRow.put("healthData", String.valueOf(jfhealth.getHeartrate()));
		dataRow.put("unit", "次/分钟");
		dataRow.put("name", "心率");
		dataRow.put("category", category);
		int low =ReadProperties.getIntValue("heartLow");
		int higt = ReadProperties.getIntValue("heartHigh");
		if(jfhealth.getHeartrate()<low){
			dataRow.put("analy", ReadProperties.getValue("heartAnalyC"));
			dataRow.put("proposal", ReadProperties.getValue("heartProposalC"));
			dataRow.put("reason", "心动过慢");
			dataRow.put("type", 1);
		}else if(jfhealth.getHeartrate()>=low && jfhealth.getHeartrate()<=higt){
			dataRow.put("analy", ReadProperties.getValue("heartAnalyA"));
			dataRow.put("proposal", ReadProperties.getValue("heartProposalA"));
			dataRow.put("reason", "心动正常");
			dataRow.put("type", 0);
		}else if(jfhealth.getHeartrate()>higt){
			dataRow.put("analy", ReadProperties.getValue("heartAnalyB"));
			dataRow.put("proposal", ReadProperties.getValue("heartProposalB"));
			dataRow.put("reason", "心动过速");
			dataRow.put("type", 1);
		}
		List<DataRow> list = new ArrayList<DataRow>();
		DataRow da = new DataRow();
		//血氧
		da=DataUtil.mocrocirculationData("qxygen","血氧",2, "%",jfhealth.getBloodoxygen()==null?0:jfhealth.getBloodoxygen());
		list.add(da);
		//微循环
		da=DataUtil.mocrocirculationData("mocrocirculation","微循环",3, "%",jfhealth.getMicrocirculation()==null?0:jfhealth.getMicrocirculation());
		list.add(da);
		//呼吸
		da=DataUtil.mocrocirculationData("breathe","呼吸",4, "次/分钟",jfhealth.getRespirationrate()==null?0:jfhealth.getRespirationrate());
		list.add(da);
		//步数
		da=DataUtil.stepWhenData("Step_when","计步",5, "步",data==null?0: data.getInt("stepWhen"));
		list.add(da);
		//血压
		da=DataUtil.bloodData("pressure","血压",6, "mmHg",jfhealth.getSbpAve()==null?0:jfhealth.getSbpAve(),jfhealth.getDbpAve()==null?0:jfhealth.getDbpAve());
		list.add(da);
		//体温
		da=new DataRow();
		da.put("name", "temperature");
		da.put("desc", "体温");
		da.put("category",7);
		da.put("unit", "℃");
		da.put("lastestValue", "39");
		da.put("type", 1);
		list.add(da);
		//湿度
		da=new DataRow();
		da.put("name", "humidity");
		da.put("desc", "湿度");
		da.put("category",8);
		da.put("unit", "%RH");
		da.put("lastestValue", "偏高");
		da.put("type", 1);
		list.add(da);
		//hrv
		da=DataUtil.hrvData("hrv","心率变异性HRV",9, "ms",jfhealth.getHRV()==null?0:jfhealth.getHRV(),age==null?30:age);
		list.add(da);
		//情绪
		da=new DataRow();
		da.put("name", "emotion");
		da.put("desc", "情绪");
		da.put("category",10);
		da.put("unit", "无");
		da.put("lastestValue", "激动");
		da.put("type", 1);
		list.add(da);
		//卡路里
		da=DataUtil.mocrocirculationData("carrieroad","卡路里",11, "焦耳/天",data==null?0: data.getInt("carrieroad"));
		list.add(da);
		dataRow.put("detail", list);
		re.setCode(200);
		re.setData(dataRow);
		re.setMessage("获取成功");
		return re;
	}
	/**
	 * 2.血氧type=0(正常) type=1(异常)
	 * @return
	 */
	public static ResultData<DataRow> bloodSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,ResultData<DataRow> re){
		DataRow dataRow = new DataRow();
		dataRow.put("index", ReadProperties.getValue("bloodIntroduce"));
		dataRow.put("range", ReadProperties.getValue("bloodRange"));
		dataRow.put("healthData", String.valueOf(jfhealth.getBloodoxygen()));
		dataRow.put("unit", "%");
		dataRow.put("name", "血氧");
		dataRow.put("category", category);
		int low =ReadProperties.getIntValue("bloodLow");
		int higt = ReadProperties.getIntValue("bloodHigh");
		if(jfhealth.getBloodoxygen()<low){
			dataRow.put("analy", ReadProperties.getValue("bloodAnalyB"));
			dataRow.put("proposal", ReadProperties.getValue("bloodProposalB"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 1);
		}else if(jfhealth.getBloodoxygen()>=low && jfhealth.getBloodoxygen()<=higt){
			dataRow.put("analy", ReadProperties.getValue("bloodAnalyA"));
			dataRow.put("proposal", ReadProperties.getValue("bloodProposalA"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 0);
		}else if(jfhealth.getBloodoxygen()>higt){
			dataRow.put("analy", ReadProperties.getValue("bloodAnalyA"));
			dataRow.put("proposal", ReadProperties.getValue("bloodProposalA"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 1);
		}
		List<DataRow> list = new ArrayList<DataRow>();
		DataRow da = new DataRow();
		//心率
		da=DataUtil.heartrateData("heartrate","心率",1, "次/分",jfhealth.getHeartrate()==null?0:jfhealth.getHeartrate());
		list.add(da);
		//微循环
		da=DataUtil.mocrocirculationData("mocrocirculation","微循环",3, "%",jfhealth.getMicrocirculation()==null?0:jfhealth.getMicrocirculation());
		list.add(da);
		//呼吸
		da=DataUtil.mocrocirculationData("breathe","呼吸",4, "次/分钟",jfhealth.getRespirationrate()==null?0:jfhealth.getRespirationrate());
		list.add(da);
		//步数
		da=DataUtil.stepWhenData("Step_when","计步",5, "步",data==null?0: data.getInt("stepWhen"));
		list.add(da);
		//血压
		da=DataUtil.bloodData("pressure","血压",6, "mmHg",jfhealth.getSbpAve()==null?0:jfhealth.getSbpAve(),jfhealth.getDbpAve()==null?0:jfhealth.getDbpAve());
		list.add(da);
		//体温
		da=new DataRow();
		da.put("name", "temperature");
		da.put("desc", "体温");
		da.put("category",7);
		da.put("unit", "℃");
		da.put("lastestValue", "39");
		da.put("type", 1);
		list.add(da);
		//湿度
		da=new DataRow();
		da.put("name", "humidity");
		da.put("desc", "湿度");
		da.put("category",8);
		da.put("unit", "%RH");
		da.put("lastestValue", "偏高");
		da.put("type", 1);
		list.add(da);
		//hrv
		da=DataUtil.hrvData("hrv","心率变异性HRV",9, "ms",jfhealth.getHRV()==null?0:jfhealth.getHRV(),age==null?30:age);
		list.add(da);
		//情绪
		da=new DataRow();
		da.put("name", "emotion");
		da.put("desc", "情绪");
		da.put("category",10);
		da.put("unit", "无");
		da.put("lastestValue", "激动");
		da.put("type", 1);
		list.add(da);
		//卡路里
		da=DataUtil.mocrocirculationData("carrieroad","卡路里",11, "焦耳/天",data==null?0: data.getInt("carrieroad"));
		list.add(da);
		dataRow.put("detail", list);
		re.setCode(200);
		re.setData(dataRow);
		re.setMessage("获取成功");
		return re;
	}
	/**
	 * 3.微循环血氧type=0(正常) type=1(异常)
	 * @return
	 */
	public static ResultData<DataRow> microcSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,ResultData<DataRow> re){
		String analy = "";
		String proposal = "";
		String reason="";
		int low =ReadProperties.getIntValue("microcLow");
		int in = ReadProperties.getIntValue("microcIn");
		int higt = ReadProperties.getIntValue("microcHigh");
		int type = 0;
		if(jfhealth.getMicrocirculation()<low){
			analy=ReadProperties.getValue("microcAnalyC");
			proposal = ReadProperties.getValue("microcProposalC");
			reason = "无定义";
			type=1;
		}else if(jfhealth.getMicrocirculation()>=low && jfhealth.getMicrocirculation()<in){
			analy=ReadProperties.getValue("microcAnalyC");
			proposal = ReadProperties.getValue("microcProposalC");
			reason = "无定义";
			type=1;
		}else if(jfhealth.getMicrocirculation()>=in && jfhealth.getMicrocirculation()<higt){
			analy=ReadProperties.getValue("microcAnalyB");
			proposal = ReadProperties.getValue("microcProposalB");
			reason = "无定义";
			type=1;
		}else if(jfhealth.getMicrocirculation()>=higt){
			analy=ReadProperties.getValue("microcAnalyA");
			proposal = ReadProperties.getValue("microcProposalA");
			reason = "无定义";
			type=0;
		}
		
		
		
		re=micro(category,jfhealth.getMicrocirculation(),ReadProperties.getValue("microcIntroduce"),ReadProperties.getValue("microcRange"),"%",analy,proposal,reason,type,data,re);
		
		return re;
	}
	public static ResultData<DataRow> micro(int category,int healthData,String index,String range,String unit,String analy,String proposal,String reason,int type,DataRow data,ResultData<DataRow> re){
		DataRow dataRow = new DataRow();
		dataRow.put("index", index);
		dataRow.put("range", range);
		dataRow.put("healthData", String.valueOf(healthData));
		dataRow.put("unit", unit);
		dataRow.put("analy", analy);
		dataRow.put("proposal", proposal);
		dataRow.put("reason", reason);
		dataRow.put("name", "血氧");
		dataRow.put("category", category);
		dataRow.put("type", type);
		dataRow.put("detail", data);
		re.setCode(200);
		re.setData(dataRow);
		re.setMessage("获取成功");
		return re;
	} 
	/**
	 * 4.呼吸type=0(正常) type=1(异常)
	 * @return
	 */
	public static ResultData<DataRow> breathSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,ResultData<DataRow> re){
		DataRow dataRow = new DataRow();
		dataRow.put("index", ReadProperties.getValue("breathIntroduce"));
		dataRow.put("range", ReadProperties.getValue("breathRange"));
		dataRow.put("healthData", String.valueOf(jfhealth.getRespirationrate()));
		dataRow.put("unit", "次/分钟");
		dataRow.put("name", "呼吸");
		dataRow.put("category", category);
		int low =ReadProperties.getIntValue("breathLow");
		int higt = ReadProperties.getIntValue("breathHigh");
		if(jfhealth.getRespirationrate()<low){
			dataRow.put("analy", ReadProperties.getValue("breathAnalyC"));
			dataRow.put("proposal", ReadProperties.getValue("breathProposalA"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 1);
		}else if(jfhealth.getRespirationrate()>=low && jfhealth.getRespirationrate()<=higt){
			dataRow.put("analy", ReadProperties.getValue("breathAnalyB"));
			dataRow.put("proposal", ReadProperties.getValue("breathProposalB"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 0);
		}else if(jfhealth.getRespirationrate()>higt){
			dataRow.put("analy", ReadProperties.getValue("breathAnalyA"));
			dataRow.put("proposal", ReadProperties.getValue("breathProposalA"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 1);
		}
		List<DataRow> list = new ArrayList<DataRow>();
		DataRow da = new DataRow();
		//心率
		da=DataUtil.heartrateData("heartrate","心率",1, "次/分",jfhealth.getHeartrate()==null?0:jfhealth.getHeartrate());
		list.add(da);
		//血氧
		da=DataUtil.mocrocirculationData("qxygen","血氧",2, "%",jfhealth.getBloodoxygen()==null?0:jfhealth.getBloodoxygen());
		list.add(da);
		//微循环
		da=DataUtil.mocrocirculationData("mocrocirculation","微循环",3, "%",jfhealth.getMicrocirculation()==null?0:jfhealth.getMicrocirculation());
		list.add(da);
		//步数
		da=DataUtil.stepWhenData("Step_when","计步",5, "步",data==null?0: data.getInt("stepWhen"));
		list.add(da);
		//血压
		da=DataUtil.bloodData("pressure","血压",6, "mmHg",jfhealth.getSbpAve()==null?0:jfhealth.getSbpAve(),jfhealth.getDbpAve()==null?0:jfhealth.getDbpAve());
		list.add(da);
		//体温
		da=new DataRow();
		da.put("name", "temperature");
		da.put("desc", "体温");
		da.put("category",7);
		da.put("unit", "℃");
		da.put("lastestValue", "39");
		da.put("type", 1);
		list.add(da);
		//湿度
		da=new DataRow();
		da.put("name", "humidity");
		da.put("desc", "湿度");
		da.put("category",8);
		da.put("unit", "%RH");
		da.put("lastestValue", "偏高");
		da.put("type", 1);
		list.add(da);
		//hrv
		da=DataUtil.hrvData("hrv","心率变异性HRV",9, "ms",jfhealth.getHRV()==null?0:jfhealth.getHRV(),age==null?30:age);
		list.add(da);
		//情绪
		da=new DataRow();
		da.put("name", "emotion");
		da.put("desc", "情绪");
		da.put("category",10);
		da.put("unit", "无");
		da.put("lastestValue", "激动");
		da.put("type", 1);
		list.add(da);
		//卡路里
		da=DataUtil.mocrocirculationData("carrieroad","卡路里",11, "焦耳/天",data==null?0: data.getInt("carrieroad"));
		list.add(da);
		dataRow.put("detail", list);
		re.setCode(200);
		re.setData(dataRow);
		re.setMessage("获取成功");
		return re;
	}
	/**
	 * 5.步数type=0(正常) type=1(异常)
	 * @return
	 */
	public static ResultData<DataRow> stepWhenSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,ResultData<DataRow> re){
		DataRow dataRow = new DataRow();
		dataRow.put("index", ReadProperties.getValue("stepIntroduce"));
		dataRow.put("range", ReadProperties.getValue("stepRange"));
		dataRow.put("healthData", data==null?0:data.getString("stepWhen"));
		dataRow.put("unit", "步");
		dataRow.put("analy", ReadProperties.getValue("stepAnalyA"));
		dataRow.put("proposal", ReadProperties.getValue("stepProposalA"));
		dataRow.put("reason", "无定义");
		dataRow.put("name", "步数");
		dataRow.put("category", category);
		dataRow.put("type", 0);
		List<DataRow> list = new ArrayList<DataRow>();
		DataRow da = new DataRow();
		//心率
		da=DataUtil.heartrateData("heartrate","心率",1, "次/分",jfhealth.getHeartrate()==null?0:jfhealth.getHeartrate());
		list.add(da);
		//血氧
		da=DataUtil.mocrocirculationData("qxygen","血氧",2, "%",jfhealth.getBloodoxygen()==null?0:jfhealth.getBloodoxygen());
		list.add(da);
		//微循环
		da=DataUtil.mocrocirculationData("mocrocirculation","微循环",3, "%",jfhealth.getMicrocirculation()==null?0:jfhealth.getMicrocirculation());
		list.add(da);
		//呼吸
		da=DataUtil.mocrocirculationData("breathe","呼吸",4, "次/分钟",jfhealth.getRespirationrate()==null?0:jfhealth.getRespirationrate());
		list.add(da);
		//血压
		da=DataUtil.bloodData("pressure","血压",6, "mmHg",jfhealth.getSbpAve()==null?0:jfhealth.getSbpAve(),jfhealth.getDbpAve()==null?0:jfhealth.getDbpAve());
		list.add(da);
		//体温
		da=new DataRow();
		da.put("name", "temperature");
		da.put("desc", "体温");
		da.put("category",7);
		da.put("unit", "℃");
		da.put("lastestValue", "39");
		da.put("type", 1);
		list.add(da);
		//湿度
		da=new DataRow();
		da.put("name", "humidity");
		da.put("desc", "湿度");
		da.put("category",8);
		da.put("unit", "%RH");
		da.put("lastestValue", "偏高");
		da.put("type", 1);
		list.add(da);
		//hrv
		da=DataUtil.hrvData("hrv","心率变异性HRV",9, "ms",jfhealth.getHRV()==null?0:jfhealth.getHRV(),age==null?30:age);
		list.add(da);
		//情绪
		da=new DataRow();
		da.put("name", "emotion");
		da.put("desc", "情绪");
		da.put("category",10);
		da.put("unit", "无");
		da.put("lastestValue", "激动");
		da.put("type", 1);
		list.add(da);
		//卡路里
		da=DataUtil.mocrocirculationData("carrieroad","卡路里",11, "焦耳/天",data==null?0: data.getInt("carrieroad"));
		list.add(da);
		dataRow.put("detail", list);
		re.setCode(200);
		re.setData(dataRow);
		re.setMessage("获取成功");
		return re;
	}
	/**
	 * 6.血压type=0(正常) type=1(异常)
	 * @return
	 */
	public static ResultData<DataRow> pressureSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,ResultData<DataRow> re){
		DataRow dataRow = new DataRow();
		dataRow.put("index", ReadProperties.getValue("pressureIntroduce"));
		dataRow.put("range", ReadProperties.getValue("pressureRange"));
		dataRow.put("healthData", jfhealth.getSbpAve()+"/"+jfhealth.getDbpAve());
		dataRow.put("unit", "mmHg");
		dataRow.put("name", "血压");
		dataRow.put("category", category);
		int low =ReadProperties.getIntValue("pressureLow");
		int just = ReadProperties.getIntValue("pressureJust");
		int in = ReadProperties.getIntValue("pressureIn");
		int inHigh = ReadProperties.getIntValue("pressureInHigh");
		int higt = ReadProperties.getIntValue("pressureHigh");
		if(jfhealth.getSbpAve()<low){
			dataRow.put("analy", ReadProperties.getValue("pressureAnalyA"));
			dataRow.put("proposal", ReadProperties.getValue("pressureProposalA"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 1);
		}else if(jfhealth.getSbpAve()>=low && jfhealth.getSbpAve()<just){
			dataRow.put("analy", ReadProperties.getValue("pressureAnalyB"));
			dataRow.put("proposal", ReadProperties.getValue("pressureProposalB"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 0);
		}else if(jfhealth.getSbpAve()>=just && jfhealth.getSbpAve()<in){
			dataRow.put("analy", ReadProperties.getValue("pressureAnalyC"));
			dataRow.put("proposal", ReadProperties.getValue("pressureProposalB"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 1);
		}else if(jfhealth.getSbpAve()>=in && jfhealth.getSbpAve()<inHigh){
			dataRow.put("analy", ReadProperties.getValue("pressureAnalyD"));
			dataRow.put("proposal", ReadProperties.getValue("pressureProposalC"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 1);
		}else if(jfhealth.getSbpAve()>=inHigh && jfhealth.getSbpAve()<higt){
			dataRow.put("analy", ReadProperties.getValue("pressureAnalyE"));
			dataRow.put("proposal", ReadProperties.getValue("pressureProposalC"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 1);
		}else if(jfhealth.getSbpAve()>=higt){
			dataRow.put("analy", ReadProperties.getValue("pressureAnalyF"));
			dataRow.put("proposal", ReadProperties.getValue("pressureProposalC"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 1);
		}
		List<DataRow> list = new ArrayList<DataRow>();
		DataRow da = new DataRow();
		//心率
		da=DataUtil.heartrateData("heartrate","心率",1, "次/分",jfhealth.getHeartrate()==null?0:jfhealth.getHeartrate());
		list.add(da);
		//血氧
		da=DataUtil.mocrocirculationData("qxygen","血氧",2, "%",jfhealth.getBloodoxygen()==null?0:jfhealth.getBloodoxygen());
		list.add(da);
		//微循环
		da=DataUtil.mocrocirculationData("mocrocirculation","微循环",3, "%",jfhealth.getMicrocirculation()==null?0:jfhealth.getMicrocirculation());
		list.add(da);
		//呼吸
		da=DataUtil.mocrocirculationData("breathe","呼吸",4, "次/分钟",jfhealth.getRespirationrate()==null?0:jfhealth.getRespirationrate());
		list.add(da);
		//步数
		da=DataUtil.stepWhenData("Step_when","计步",5, "步",data==null?0: data.getInt("stepWhen"));
		list.add(da);
		//体温
		da=new DataRow();
		da.put("name", "temperature");
		da.put("desc", "体温");
		da.put("category",7);
		da.put("unit", "℃");
		da.put("lastestValue", "39");
		da.put("type", 1);
		list.add(da);
		//湿度
		da=new DataRow();
		da.put("name", "humidity");
		da.put("desc", "湿度");
		da.put("category",8);
		da.put("unit", "%RH");
		da.put("lastestValue", "偏高");
		da.put("type", 1);
		list.add(da);
		//hrv
		da=DataUtil.hrvData("hrv","心率变异性HRV",9, "ms",jfhealth.getHRV()==null?0:jfhealth.getHRV(),age==null?30:age);
		list.add(da);
		//情绪
		da=new DataRow();
		da.put("name", "emotion");
		da.put("desc", "情绪");
		da.put("category",10);
		da.put("unit", "无");
		da.put("lastestValue", "激动");
		da.put("type", 1);
		list.add(da);
		//卡路里
		da=DataUtil.mocrocirculationData("carrieroad","卡路里",11, "焦耳/天",data==null?0: data.getInt("carrieroad"));
		list.add(da);
		dataRow.put("detail", list);
		re.setCode(200);
		re.setData(dataRow);
		re.setMessage("获取成功");
		return re;
	}
	/**
	 * 7.体温type=0(正常) type=1(异常)
	 * @return
	 */
	public static ResultData<DataRow> warmSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,Float healthData,ResultData<DataRow> re){
		DataRow dataRow = new DataRow();
		dataRow.put("index", ReadProperties.getValue("warmIntroduce"));
		dataRow.put("range", ReadProperties.getValue("warmRange"));
		dataRow.put("healthData", String.valueOf(healthData));
		dataRow.put("unit", "℃");
		dataRow.put("name", "体温");
		dataRow.put("category", category);
		Float low =ReadProperties.getFloatValue("warmLow");//36.3
		Float just = ReadProperties.getFloatValue("warmJust");//37.5
		Float in = ReadProperties.getFloatValue("warmIn");//38
		Float inHigh = ReadProperties.getFloatValue("warmInHigh");//39
		Float higt = ReadProperties.getFloatValue("warmHigh");//41
		if(healthData<low){
			dataRow.put("analy", ReadProperties.getValue("warmAnalyA"));
			dataRow.put("proposal", ReadProperties.getValue("warmProposalA"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 1);
		}else if(healthData>=low && healthData<just){
			dataRow.put("analy", ReadProperties.getValue("warmAnalyB"));
			dataRow.put("proposal", ReadProperties.getValue("warmProposalB"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 0);
		}else if(healthData>=just && healthData<in){
			dataRow.put("analy", ReadProperties.getValue("warmAnalyC"));
			dataRow.put("proposal", ReadProperties.getValue("warmProposalC"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 1);
		}else if(healthData>=in && healthData<inHigh){
			dataRow.put("analy", ReadProperties.getValue("warmAnalyD"));
			dataRow.put("proposal", ReadProperties.getValue("warmProposalC"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 1);
		}else if(healthData>=inHigh && healthData<higt){
			dataRow.put("analy", ReadProperties.getValue("warmAnalyE"));
			dataRow.put("proposal", ReadProperties.getValue("warmProposalC"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 1);
		}else if(healthData>=higt){
			dataRow.put("analy", ReadProperties.getValue("warmAnalyF"));
			dataRow.put("proposal", ReadProperties.getValue("warmProposalC"));
			dataRow.put("reason", "无定义");
			dataRow.put("type", 1);
		}
		List<DataRow> list = new ArrayList<DataRow>();
		DataRow da = new DataRow();
		//心率
		da=DataUtil.heartrateData("heartrate","心率",1, "次/分",jfhealth.getHeartrate()==null?0:jfhealth.getHeartrate());
		list.add(da);
		//血氧
		da=DataUtil.mocrocirculationData("qxygen","血氧",2, "%",jfhealth.getBloodoxygen()==null?0:jfhealth.getBloodoxygen());
		list.add(da);
		//微循环
		da=DataUtil.mocrocirculationData("mocrocirculation","微循环",3, "%",jfhealth.getMicrocirculation()==null?0:jfhealth.getMicrocirculation());
		list.add(da);
		//呼吸
		da=DataUtil.mocrocirculationData("breathe","呼吸",4, "次/分钟",jfhealth.getRespirationrate()==null?0:jfhealth.getRespirationrate());
		list.add(da);
		//步数
		da=DataUtil.stepWhenData("Step_when","计步",5, "步",data==null?0: data.getInt("stepWhen"));
		list.add(da);
		//血压
		da=DataUtil.bloodData("pressure","血压",6, "mmHg",jfhealth.getSbpAve()==null?0:jfhealth.getSbpAve(),jfhealth.getDbpAve()==null?0:jfhealth.getDbpAve());
		list.add(da);
		//湿度
		da=new DataRow();
		da.put("name", "humidity");
		da.put("desc", "湿度");
		da.put("category",8);
		da.put("unit", "%RH");
		da.put("lastestValue", "偏高");
		da.put("type", 1);
		list.add(da);
		//hrv
		da=DataUtil.hrvData("hrv","心率变异性HRV",9, "ms",jfhealth.getHRV()==null?0:jfhealth.getHRV(),age==null?30:age);
		list.add(da);
		//情绪
		da=new DataRow();
		da.put("name", "emotion");
		da.put("desc", "情绪");
		da.put("category",10);
		da.put("unit", "无");
		da.put("lastestValue", "激动");
		da.put("type", 1);
		list.add(da);
		//卡路里
		da=DataUtil.mocrocirculationData("carrieroad","卡路里",11, "焦耳/天",data==null?0: data.getInt("carrieroad"));
		list.add(da);
		dataRow.put("detail", list);
		re.setCode(200);
		re.setData(dataRow);
		re.setMessage("获取成功");
		return re;
	}
	/**
	 * 8.湿度
	 * @return
	 */
	public static ResultData<DataRow> humiditySecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,String healthData,ResultData<DataRow> re){
		DataRow dataRow = new DataRow();
		List<DataRow> list = new ArrayList<DataRow>();
		DataRow da = new DataRow();
		//心率
		da=DataUtil.heartrateData("heartrate","心率",1, "次/分",jfhealth.getHeartrate()==null?0:jfhealth.getHeartrate());
		list.add(da);
		//血氧
		da=DataUtil.mocrocirculationData("qxygen","血氧",2, "%",jfhealth.getBloodoxygen()==null?0:jfhealth.getBloodoxygen());
		list.add(da);
		//微循环
		da=DataUtil.mocrocirculationData("mocrocirculation","微循环",3, "%",jfhealth.getMicrocirculation()==null?0:jfhealth.getMicrocirculation());
		list.add(da);
		//呼吸
		da=DataUtil.mocrocirculationData("breathe","呼吸",4, "次/分钟",jfhealth.getRespirationrate()==null?0:jfhealth.getRespirationrate());
		list.add(da);
		//步数
		da=DataUtil.stepWhenData("Step_when","计步",5, "步",data==null?0: data.getInt("stepWhen"));
		list.add(da);
		//血压
		da=DataUtil.bloodData("pressure","血压",6, "mmHg",jfhealth.getSbpAve()==null?0:jfhealth.getSbpAve(),jfhealth.getDbpAve()==null?0:jfhealth.getDbpAve());
		list.add(da);
		//体温
		da=new DataRow();
		da.put("name", "temperature");
		da.put("desc", "体温");
		da.put("category",7);
		da.put("unit", "℃");
		da.put("lastestValue", "39");
		da.put("type", 1);
		list.add(da);
		//hrv
		da=DataUtil.hrvData("hrv","心率变异性HRV",9, "ms",jfhealth.getHRV()==null?0:jfhealth.getHRV(),age==null?30:age);
		list.add(da);
		//情绪
		da=new DataRow();
		da.put("name", "emotion");
		da.put("desc", "情绪");
		da.put("category",10);
		da.put("unit", "无");
		da.put("lastestValue", "激动");
		da.put("type", 1);
		list.add(da);
		//卡路里
		da=DataUtil.mocrocirculationData("carrieroad","卡路里",11, "焦耳/天",data==null?0: data.getInt("carrieroad"));
		list.add(da);
		dataRow.put("detail", list);
		re.setCode(200);
		re.setData(dataRow);
		re.setMessage("获取成功");
		return re;
	}
	/**
	 * 9.HRV
	 * @return
	 */
	public static ResultData<DataRow> hrvSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,ResultData<DataRow> re){
		DataRow dataRow = new DataRow();
		dataRow.put("index", ReadProperties.getValue("hrvIntroduce"));
		dataRow.put("range", ReadProperties.getValue("hrvRange"));
		dataRow.put("healthData", String.valueOf(jfhealth.getHRV()));
		dataRow.put("unit", "ms");
		dataRow.put("name", "HRV");
		dataRow.put("category", category);
		if(age<18){
			if(jfhealth.getHRV()<25){
				dataRow.put("analy", ReadProperties.getValue("hrvAnalyB"));
				dataRow.put("reason", "无定义");
				dataRow.put("type", 1);
			}else if(jfhealth.getHRV()>=25 && jfhealth.getHRV()<120){
				dataRow.put("analy", ReadProperties.getValue("hrvAnalyA"));
				dataRow.put("reason", "无定义");
				dataRow.put("type", 0);
			}else if(jfhealth.getHRV()>=120){
				dataRow.put("analy", ReadProperties.getValue("hrvAnalyB"));
				dataRow.put("reason", "无定义");
				dataRow.put("type", 1);
			}
		}else if(age>=18 && age<30){
			if(jfhealth.getHRV()<25){
				dataRow.put("analy", ReadProperties.getValue("hrvAnalyB"));
				dataRow.put("reason", "无定义");
				dataRow.put("type", 1);;
			}else if(jfhealth.getHRV()>=25 && jfhealth.getHRV()<120){
				dataRow.put("analy", ReadProperties.getValue("hrvAnalyA"));
				dataRow.put("reason", "无定义");
				dataRow.put("type", 0);
			}else if(jfhealth.getHRV()>=120){
				dataRow.put("analy", ReadProperties.getValue("hrvAnalyB"));
				dataRow.put("reason", "无定义");
				dataRow.put("type", 1);
			}
		}else if(age>=30 && age<50){
			if(jfhealth.getHRV()<27){
				dataRow.put("analy", ReadProperties.getValue("hrvAnalyB"));
				dataRow.put("reason", "无定义");
				dataRow.put("type", 1);
			}else if(jfhealth.getHRV()>=27 && jfhealth.getHRV()<70){
				dataRow.put("analy", ReadProperties.getValue("hrvAnalyA"));
				dataRow.put("reason", "无定义");
				dataRow.put("type", 0);
			}else if(jfhealth.getHRV()>=70){
				dataRow.put("analy", ReadProperties.getValue("hrvAnalyB"));
				dataRow.put("reason", "无定义");
				dataRow.put("type", 1);
			}
		}else if(age>=50 && age<70){
			if(jfhealth.getHRV()<22){
				dataRow.put("analy", ReadProperties.getValue("hrvAnalyB"));
				dataRow.put("reason", "无定义");
				dataRow.put("type", 1);
			}else if(jfhealth.getHRV()>=22 && jfhealth.getHRV()<60){
				dataRow.put("analy", ReadProperties.getValue("hrvAnalyA"));
				dataRow.put("reason", "无定义");
				dataRow.put("type", 0);
			}else if(jfhealth.getHRV()>=60){
				dataRow.put("analy", ReadProperties.getValue("hrvAnalyB"));
				dataRow.put("reason", "无定义");
				dataRow.put("type", 1);
			}
		}else if(age>70){
			if(jfhealth.getHRV()<22){
				dataRow.put("analy", ReadProperties.getValue("hrvAnalyB"));
				dataRow.put("reason", "无定义");
				dataRow.put("type", 1);
			}else if(jfhealth.getHRV()>=22 && jfhealth.getHRV()<60){
				dataRow.put("analy", ReadProperties.getValue("hrvAnalyA"));
				dataRow.put("reason", "无定义");
				dataRow.put("type", 0);
			}else if(jfhealth.getHRV()>=60){
				dataRow.put("analy", ReadProperties.getValue("hrvAnalyB"));
				dataRow.put("reason", "无定义");
				dataRow.put("type", 1);
			}
		}
		dataRow.put("proposal", ReadProperties.getValue("hrvProposalA"));
		List<DataRow> list = new ArrayList<DataRow>();
		DataRow da = new DataRow();
		//心率
		da=DataUtil.heartrateData("heartrate","心率",1, "次/分",jfhealth.getHeartrate()==null?0:jfhealth.getHeartrate());
		list.add(da);
		//血氧
		da=DataUtil.mocrocirculationData("qxygen","血氧",2, "%",jfhealth.getBloodoxygen()==null?0:jfhealth.getBloodoxygen());
		list.add(da);
		//微循环
		da=DataUtil.mocrocirculationData("mocrocirculation","微循环",3, "%",jfhealth.getMicrocirculation()==null?0:jfhealth.getMicrocirculation());
		list.add(da);
		//呼吸
		da=DataUtil.mocrocirculationData("breathe","呼吸",4, "次/分钟",jfhealth.getRespirationrate()==null?0:jfhealth.getRespirationrate());
		list.add(da);
		//步数
		da=DataUtil.stepWhenData("Step_when","计步",5, "步",data==null?0: data.getInt("stepWhen"));
		list.add(da);
		//血压
		da=DataUtil.bloodData("pressure","血压",6, "mmHg",jfhealth.getSbpAve()==null?0:jfhealth.getSbpAve(),jfhealth.getDbpAve()==null?0:jfhealth.getDbpAve());
		list.add(da);
		//体温
		da=new DataRow();
		da.put("name", "temperature");
		da.put("desc", "体温");
		da.put("category",7);
		da.put("unit", "℃");
		da.put("lastestValue", "39");
		da.put("type", 1);
		list.add(da);
		//湿度
		da=new DataRow();
		da.put("name", "humidity");
		da.put("desc", "湿度");
		da.put("category",8);
		da.put("unit", "%RH");
		da.put("lastestValue", "偏高");
		da.put("type", 1);
		list.add(da);
		//情绪
		da=new DataRow();
		da.put("name", "emotion");
		da.put("desc", "情绪");
		da.put("category",10);
		da.put("unit", "无");
		da.put("lastestValue", "激动");
		da.put("type", 1);
		list.add(da);
		//卡路里
		da=DataUtil.mocrocirculationData("carrieroad","卡路里",11, "焦耳/天",data==null?0: data.getInt("carrieroad"));
		list.add(da);
		dataRow.put("detail", list);
		re.setCode(200);
		re.setData(dataRow);
		re.setMessage("获取成功");
		return re;
	}
	/**
	 * 10.情绪
	 * @return
	 */
	public static ResultData<DataRow> emotionSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,int healthData,ResultData<DataRow> re){
		DataRow dataRow = new DataRow();
		List<DataRow> list = new ArrayList<DataRow>();
		DataRow da = new DataRow();
		//心率
		da=DataUtil.heartrateData("heartrate","心率",1, "次/分",jfhealth.getHeartrate()==null?0:jfhealth.getHeartrate());
		list.add(da);
		//血氧
		da=DataUtil.mocrocirculationData("qxygen","血氧",2, "%",jfhealth.getBloodoxygen()==null?0:jfhealth.getBloodoxygen());
		list.add(da);	
		//微循环
		da=DataUtil.mocrocirculationData("mocrocirculation","微循环",3, "%",jfhealth.getMicrocirculation()==null?0:jfhealth.getMicrocirculation());
		list.add(da);
		//呼吸
		da=DataUtil.mocrocirculationData("breathe","呼吸",4, "次/分钟",jfhealth.getRespirationrate()==null?0:jfhealth.getRespirationrate());
		list.add(da);
		//步数
		da=DataUtil.stepWhenData("Step_when","计步",5, "步",data==null?0: data.getInt("stepWhen"));
		list.add(da);
		//血压
		da=DataUtil.bloodData("pressure","血压",6, "mmHg",jfhealth.getSbpAve()==null?0:jfhealth.getSbpAve(),jfhealth.getDbpAve()==null?0:jfhealth.getDbpAve());
		list.add(da);
		//体温
		da=new DataRow();
		da.put("name", "temperature");
		da.put("desc", "体温");
		da.put("category",7);
		da.put("unit", "℃");
		da.put("lastestValue", "39");
		da.put("type", 1);
		list.add(da);
		//湿度
		da=new DataRow();
		da.put("name", "humidity");
		da.put("desc", "湿度");
		da.put("category",8);
		da.put("unit", "%RH");
		da.put("lastestValue", "偏高");
		da.put("type", 1);
		list.add(da);
		//hrv
		da=DataUtil.hrvData("hrv","心率变异性HRV",9, "ms",jfhealth.getHRV()==null?0:jfhealth.getHRV(),age==null?30:age);
		list.add(da);
		//卡路里
		da=DataUtil.mocrocirculationData("carrieroad","卡路里",11, "焦耳/天",data==null?0: data.getInt("carrieroad"));
		list.add(da);
		dataRow.put("detail", list);
		dataRow.put("category", category);
		re.setCode(200);
		re.setData(dataRow);
		re.setMessage("获取成功");
		return re;
	}
	/**
	 * 11.卡里路
	 * @return
	 */
	public static ResultData<DataRow> carrieroadSecondary(int category,DataRow data,JfhealthNew jfhealth,Integer age,ResultData<DataRow> re){
		DataRow dataRow = new DataRow();
		List<DataRow> list = new ArrayList<DataRow>();
		DataRow da = new DataRow();
		//心率
		da=DataUtil.heartrateData("heartrate","心率",1, "次/分",jfhealth.getHeartrate()==null?0:jfhealth.getHeartrate());
		list.add(da);
		//血氧
		da=DataUtil.mocrocirculationData("qxygen","血氧",2, "%",jfhealth.getBloodoxygen()==null?0:jfhealth.getBloodoxygen());
		list.add(da);	
		//微循环
		da=DataUtil.mocrocirculationData("mocrocirculation","微循环",3, "%",jfhealth.getMicrocirculation()==null?0:jfhealth.getMicrocirculation());
		list.add(da);
		//呼吸
		da=DataUtil.mocrocirculationData("breathe","呼吸",4, "次/分钟",jfhealth.getRespirationrate()==null?0:jfhealth.getRespirationrate());
		list.add(da);
		//步数
		da=DataUtil.stepWhenData("Step_when","计步",5, "步",data==null?0: data.getInt("stepWhen"));
		list.add(da);
		//血压
		da=DataUtil.bloodData("pressure","血压",6, "mmHg",jfhealth.getSbpAve()==null?0:jfhealth.getSbpAve(),jfhealth.getDbpAve()==null?0:jfhealth.getDbpAve());
		list.add(da);
		//体温
		da=new DataRow();
		da.put("name", "temperature");
		da.put("desc", "体温");
		da.put("category",7);
		da.put("unit", "℃");
		da.put("lastestValue", "39");
		da.put("type", 1);
		list.add(da);
		//湿度
		da=new DataRow();
		da.put("name", "humidity");
		da.put("desc", "湿度");
		da.put("category",8);
		da.put("unit", "%RH");
		da.put("lastestValue", "偏高");
		da.put("type", 1);
		list.add(da);
		//hrv
		da=DataUtil.hrvData("hrv","心率变异性HRV",9, "ms",jfhealth.getHRV()==null?0:jfhealth.getHRV(),age==null?30:age);
		list.add(da);
		//情绪
		da=new DataRow();
		da.put("name", "emotion");
		da.put("desc", "情绪");
		da.put("category",10);
		da.put("unit", "无");
		da.put("lastestValue", "激动");
		da.put("type", 1);
		list.add(da);
		dataRow.put("detail", list);
		dataRow.put("category", category);
		re.setCode(200);
		re.setData(dataRow);
		re.setMessage("获取成功");
		return re;
	}
}
