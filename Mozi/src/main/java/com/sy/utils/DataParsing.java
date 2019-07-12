package com.sy.utils;

import java.util.Date;
import java.util.Random;

import com.sy.pojo.Jfhealth;
import com.sy.pojo.Jfhealthdao;

public class DataParsing {

	/**
	 * 普通算法
	 * @param jfdao
	 * @param jfval
	 * @return
	 */
	public static Integer highLowPressureVal(Integer jfdao, Integer jfval) {
		// 用户设置的高压值*0.7
		jfdao = (int) (jfdao * 0.7);
		// 硬件的高压值*0.7
		jfval = (int) (jfval * 0.3);
		return jfdao + jfval;
	}
	/**
     * 时间换算
     * @param num
     * @return
     */
    public static String getConversionDate(int num){
    	 	int time = num*5;
	        int hours = (int) Math.floor(time / 60);
	        int minute = time % 60;
	        return hours + "小时" + minute + "分钟";
    }
	/**
	 * 血压算法
	 * @param health
	 * @return
	 */
	public static Jfhealth bloodPressure(Jfhealth health,Jfhealthdao jfdao,String heartr,String bloodxygen0,String bloodxygen1){
		 //心率校准值
  		double heartrdao = jfdao.getHeartrate()==0?80:jfdao.getHeartrate();
		// 高压值校准值
  		double highpressure = jfdao.getSbpAve()==0?120:jfdao.getSbpAve();
		// 低压校准值
  		double lowpressure = jfdao.getDbpAve()==0?80:jfdao.getDbpAve();
    	// 心率 检测值
		double hear = Double.parseDouble(heartr);
		// 高压值检测值
		double gy = Integer.parseInt(bloodxygen0);
	 	//低压检测值
		double dy = Integer.parseInt(bloodxygen1);
		 int num=0;
		 int gnum=0;
		 int dnum=0;
		// if(gy<highpressure){
			 if(hear<heartrdao && hear!=0){
				  double sum =(double)hear/(double)heartrdao*100;
				   if(97<sum && sum<=100){
					   gnum = (int) (99+Math.random()*(100-99+1));
					   dnum = (int) (97+Math.random()*(100-97+1));
					 }else if(94<sum && sum<=97){
						 gnum = (int) (97+Math.random()*(98-97+1));
						 dnum = (int) (97+Math.random()*(100-97+1));
					 } else if(92<sum && sum<=94){
						 gnum =  (int) (95+Math.random()*(96-95+1));
						 dnum = (int) (97+Math.random()*(100-97+1));
					 }else if(90<=hear && hear<=92){
						 gnum =  (int) (94+Math.random()*(95-94+1));
						 dnum = (int) (97+Math.random()*(100-97+1));
					 }else{
						 hear =heartrdao*(97+Math.random()*(100-97+1))/100;
						 gnum=(int)(97+Math.random()*(100-97+1));;
						 dnum = (int) (97+Math.random()*(100-97+1));
					 }
				   highpressure=(int) (highpressure*gnum/100);
				   lowpressure = (int) (lowpressure*dnum/100);
			  }else if(hear>=heartrdao){double sum =(double)hear/(double)heartrdao*100;
			  double gysum =(double)gy/(double)highpressure*100;//求出高压占校准值的比例
			  double dysum =(double)dy/(double)lowpressure*100;//求出低压占校准值的比例
			   if(100<=sum && sum<102){
				   if(90<=gysum && gysum<110){
					   highpressure=gy;
					   gnum=100;
				   }else{
					   gnum = (int) (100+Math.random()*(102-100+1));
				   } 
				   if(90<=dysum && dysum<110){
					   lowpressure=dy;
					   dnum=100; 
				   }else{
					  
					   dnum = (int) (100+Math.random()*(102-100+1));
				   }
				 }else if(102<=sum && sum<104){
					 if(90<=gysum && gysum<110){
						   highpressure=gy;
						   gnum=100;
					   }else{
						   gnum = (int) (103+Math.random()*(104-103+1));
					   } if(90<=dysum && dysum<110){
						   lowpressure=dy;
						   dnum=100; 
					   }else{
						   dnum = (int) (103+Math.random()*(104-103+1));
					   }
				 } else if(104<=sum && sum<108){
					 gnum = (int) (105+Math.random()*(106-105+1));
					 dnum = (int) (103+Math.random()*(104-103+1));
				 }else if(108<=sum && sum<110){
					 gnum = (int) (107+Math.random()*(108-107+1));
					 dnum = (int) (103+Math.random()*(104-103+1));
				 }else if(110<=sum && sum<112){
					 gnum = (int) (109+Math.random()*(110-109+1));
					 dnum = (int) (103+Math.random()*(104-103+1));
				 }else if(112<=sum && sum<114){
					 gnum = (int) (110+Math.random()*(112-110+1));
					 dnum = (int) (103+Math.random()*(104-103+1));
				 }else if(114<=sum && sum<116){
					 gnum = (int) (113+Math.random()*(114-113+1));
					 dnum = (int) (103+Math.random()*(104-103+1));
				 }else if(116<=sum && sum<118){
					 gnum = (int) (115+Math.random()*(116-115+1));
					 dnum = (int) (115+Math.random()*(116-115+1));
				 }/*else if(118<=sum && sum<120){
					 gnum = (int) (117+Math.random()*(118-117+1));
					 dnum = (int) (117+Math.random()*(118-117+1));
				 }else if(120<=sum && sum<122){
					 gnum = (int) (119+Math.random()*(120-119+1));
					 dnum = (int) (119+Math.random()*(120-119+1));
				 }else if(122<=sum && sum<124){
					 gnum = (int) (121+Math.random()*(122-121+1));
					 dnum = (int) (121+Math.random()*(122-121+1));
				 }else if(124<=sum && sum<126){
					 gnum = (int) (123+Math.random()*(124-123+1));
					 dnum = (int) (123+Math.random()*(124-123+1));
				 }else if(126<=sum && sum<128){
					 gnum = (int) (125+Math.random()*(127-125+1));
					 dnum = (int) (125+Math.random()*(127-125+1));
				 }else if(128<=sum && sum<130){
					 gnum = (int) (128+Math.random()*(130-128+1));
					 dnum = (int) (128+Math.random()*(130-128+1));
				 }else if(130<=sum && sum<132){
					 gnum = (int) (130+Math.random()*(131-130+1));
					 dnum = (int) (130+Math.random()*(131-130+1));
				 }*/else{
					 hear =heartrdao*(105+Math.random()*(120-105+1))/100;
					 gnum=(int)(105+Math.random()*(120-105+1));
					 dnum=(int)(105+Math.random()*(120-105+1));
				 }
			  highpressure=(int) (highpressure*gnum/100);
			  lowpressure = (int) (lowpressure*dnum/100);
			  }else{
				  hear =heartrdao*(98+Math.random()*(102-98+1))/100;
				  num=(int)(98+Math.random()*(102-98+1));;
				  highpressure=(int) (highpressure*num/100);
				  lowpressure = (int) (lowpressure*num/100);
			  }
			  health.setHeartrate((int)hear);
			  // 高压
			  health.setSbpAve((int)highpressure);
			  // 低压
			  health.setDbpAve((int)lowpressure);
			  return health;
	}
	/**
	 * HRV算法
	 * @param health
	 * @return
	 */
	public static Jfhealth DataHrv(Jfhealth health,Jfhealthdao jfdao,String hrv){
		int hrvcon =  Integer.parseInt(hrv);//hrv监测值
		int hrvcorrect= jfdao.getHrv();//hrv校准值
		if(hrvcon==0){//45-60
			if(hrvcorrect<45){
				hrvcon=(int)(45+Math.random()*(60-45+1));
			}else{
				Random r = new Random();
				hrvcon = r.nextInt(15)+45;
			}
			health.setHRV(hrvcon);
		}else{
			hrvcon=highLowPressureVal(hrvcorrect,hrvcon);
			if(hrvcon<45){
				hrvcon=(int)(45+Math.random()*(60-45+1));
			}
			health.setHRV(hrvcon);
		}
		return health;
	}
	/**
	 * 微循环算法
	 * @param health
	 * @param healthdao
	 * @param microcir
	 * @return
	 */
	public static Jfhealth DataMicrocirculation(Jfhealth health,Jfhealthdao jfdao,String microcir){
		//微循环检测值
		int microc = Integer.parseInt(microcir);
		//微循环校准值
		Integer microcirculation = jfdao.getMicrocirculation();
		if(microc==0){//75-90
			if(microcirculation<75){
				microc=(int)(75+Math.random()*(90-75+1));
			}else{
				Random r = new Random();
				microc = r.nextInt(14)+66;
			}
			health.setMicrocirculation(microc);
		}else{
			microc=highLowPressureVal(microcirculation,microc);
			if(microc<75){
				microc=(int)(75+Math.random()*(90-75+1));
			}
			health.setMicrocirculation(microc);
		}
		return health;
	}
	
	/**
	 * 呼吸值算法
	 * @param health
	 * @param healthdao
	 * @param respiration
	 * @return
	 */
	public static Jfhealth respirationrate(Jfhealth health,Jfhealthdao jfdao,String respiration){
		// 呼吸检测值
		int resp = Integer.parseInt(respiration);
		//呼吸校准值
		Integer respirationratedao = jfdao.getRespirationrate();
		if(resp==0){//16-20
			if(respirationratedao<16){
				resp=(int)(16+Math.random()*(21-16+1));
			}else{
				Random r =  new Random();
				resp = r.nextInt((respirationratedao/10)*2)+(respirationratedao-respirationratedao/10)+1;
			}
			health.setRespirationrate(resp);
		}else{
			resp=highLowPressureVal(respirationratedao,resp);//校准值0.8加检测值0.2
			if(resp<16){
				resp=(int)(16+Math.random()*(21-16+1));
			}
			health.setRespirationrate(resp);
		}
		return health;
		
	}
	/**
	 * 情绪值算法
	 * @param health
	 * @param healthdao
	 * @param respiration
	 * @return
	 */
	public static Jfhealth mood(Jfhealth health,String hrv){
		//情绪无算法, 情绪=hrv=RMSSD
		health.setMood(Integer.valueOf(hrv));
		return health;
	}
	/**
	 * 校准值算法
	 * @param h
	 * @param heartRate
	 * @param bloodOxygen
	 * @param microcir
	 * @param hrv
	 * @param respiration
	 * @param bloodrArr0
	 * @param bloodrArr1
	 * @return
	 */
	public static Jfhealthdao DataHealthdao(Jfhealthdao jfdao,String heartr,String amedical,String bloodxy,String bloodxygen0,String bloodxygen1,String hrv,String microcir,String respiration,String account,String imei){
		
		jfdao.setHeartrate(Integer.parseInt(heartr));

		jfdao.setAmedicalreport(amedical);
		if(Integer.valueOf(bloodxy)<94 || Integer.valueOf(bloodxy)>99){
			jfdao.setBloodoxygen((int)(94+Math.random()*(99-94+1)));// 血氧  94-99
		}else{
			jfdao.setBloodoxygen(Integer.valueOf(bloodxy));// 血氧  94-99
		}
		
		jfdao.setSbpAve(Integer.valueOf(bloodxygen0)<90?((int) (90+Math.random()*(125-90+1))):Integer.valueOf(bloodxygen0));//高压值(int) (110+Math.random()*(130-110+1))
		jfdao.setDbpAve(Integer.valueOf(bloodxygen1)<60?((int) (60+Math.random()*(85-60+1))):Integer.valueOf(bloodxygen1));//低压值(int) (75+Math.random()*(88-75+1))
		jfdao.setCreatetime(new Date());
		if(Integer.valueOf(hrv)<40){
			jfdao.setHrv((int)(40+Math.random()*(50-40+1)));// 呼吸频率
		}else{
			jfdao.setHrv(Integer.valueOf(hrv));//HRV
		}
		if(Integer.valueOf(microcir)<80){
			jfdao.setMicrocirculation((int)(80+Math.random()*(90-80+1)));// 微循环   >80
		}else{
			jfdao.setMicrocirculation(Integer.valueOf(microcir));// 微循环   >80
		}
		if(Integer.valueOf(respiration)<12 || Integer.valueOf(respiration)>20){
			jfdao.setRespirationrate((int)(12+Math.random()*(20-12+1)));// 呼吸频率
		}else{
			jfdao.setRespirationrate(Integer.valueOf(respiration));// 呼吸频率
		}
		jfdao.setPhone(account);
		jfdao.setImei(imei);
		return jfdao;
}
	
}
