package com.sy.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sy.mapper.PositionigMapper;
import com.sy.pojo.Positionig;
import com.sy.service.PositionigService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class PositionigServiceImpl implements PositionigService{
	@Autowired
	private PositionigMapper positionigMapper;

	@Override
	public boolean addPosition(Positionig p) {
		if (positionigMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			positionigMapper = (PositionigMapper) webApplicationContext
					.getBean("positionigMapper");
		}
		Integer num = positionigMapper.insertSelective(p);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	
	}

	@Override
	public  Map<String,String> selectimeiPositionig(String imei) {
		
		if (positionigMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			positionigMapper = (PositionigMapper) webApplicationContext
					.getBean("positionigMapper");
		}
		Map<String, String>map =new HashMap<String, String>();
		String text=positionigMapper.selectimeiPositionig(imei);
		String state=positionigMapper.Positionigstate(imei);
		if(state==null || state.equals("")){
			state="0";
		}
		if(text ==null || text.equals("")) {
			text="39.914714:116.403406:10";
		}
		String [] pdatas = text.split(":");
		    String ak= "cMG4VFql0uL5qGAAANk4suQi2BkjGTlH";
			String re =get("http://api.map.baidu.com/geoconv/v1/?coords="+pdatas[1]+","+pdatas[0]+"&from=1&to=5&ak=cMG4VFql0uL5qGAAANk4suQi2BkjGTlH");
			JSONObject js = new JSONObject().fromObject(re);
			JSONArray jss = (JSONArray) js.get("result");
			JSONObject jw =(JSONObject) jss.get(0);
			System.out.println("经度=="+jw.get("x")+"纬度=="+jw.get("y"));
			
		  map = new HashMap<>();
		    map.put("longitude", jw.get("x").toString());
		    map.put("latitude", jw.get("y").toString());
		    map.put("state", state);
		return map;
	}

	@Override
	public String selectimeiPositionigtcp(String imei) {
		 
		if (positionigMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			positionigMapper = (PositionigMapper) webApplicationContext
					.getBean("positionigMapper");
		}
		Map<String, String>map =new HashMap<String, String>();
		String text=positionigMapper.selectimeiPositionig(imei);
		
		return text;
	}
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 */
	public static String get(String url) {
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	
	
}
