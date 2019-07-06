package com.sy.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sy.nettyulit.NettyChannelMap;
import com.sy.nettyulit.NettyServerBootstrap;
import com.sy.utils.Config;
import com.sy.utils.StringUtil;

public class HardwareController extends Thread implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		HardwareController h = new HardwareController();
		h.start();
		//启动自动返回信息
	//	task();
	}

	@Override
	public void run() {
		  try {
			  Config config = new Config("systemConfig.properties");
			  Integer port = Integer.valueOf(config.getString("port"));
			NettyServerBootstrap bootstrap = new NettyServerBootstrap(port);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	        while (true){
	            try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        }
	}
	/**
	 * 
	 * 
	 */
	public static void task(){
		   final long timeInterval = 10000;  
	        Runnable runnable = new Runnable() {  
	            public void run() {  
	                while (true) {  
	                	try {
							total();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                    System.out.println("Hello !!");  
	                    try {  
	                        Thread.sleep(timeInterval);  
	                    } catch (InterruptedException e) {  
	                        e.printStackTrace();  
	                    }  
	                }  
	            }  
	        };  
	        Thread thread = new Thread(runnable);  
	        thread.start();  
			
	}
	
	/**
	 * 
	 * 定时发送给硬件端口
	 * @throws ParseException 
	 * 
	 */
	public static void total() throws ParseException{
		System.out.println("定时器");
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		//当前时间
		NettyChannelMap.Traverse("$R01|OK|"+StringUtil.dateToStamp(new Date())+"|ERR\r\n");
		//用户信息
		NettyChannelMap.information();
		//天气信息
		NettyChannelMap.weather();
		//推送紧急联系人 R24
		NettyChannelMap.urgent();
	}
	
}
