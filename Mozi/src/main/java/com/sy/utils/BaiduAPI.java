package com.sy.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 天气接口
 * 
 * @author
 */

public class BaiduAPI {
	private static final String method = "GET";
	private static final String host = "http://jisutianqi.market.alicloudapi.com";
	private static final String appcode = "222bbecf0f1e4963a34ea074b0a096ec";
	private static final String path = "/weather/query";
	public static final String APP_ID = "10489619";
	public static final String API_KEY = "xjH8GN3bAtIKGEzlOiyGLBa5ic1dbNW8";
	public static final String SECRET_KEY = "e7P1ZQGUNrF004sMfSM4b9KKDyWUfKI6";
	/*--------------------------------------------------------------------------*/

	// 音乐
	private static String URL = "http://tingapi.ting.baidu.com/v1/restserver/ting";

	// 天气
	private final static String BAIDURULR = "http://api.map.baidu.com/telematics/v3/weather?";
	private final static String BAIDUAK = "6tYzTvGZSOpYB5Oc2YGGOKt8";

	/*--------------------------------------------------------------------------*/

	public static void main(String[] args) {
		getWhether("北京市", null);
	}

	/**
	 * 
	 * @param city
	 *            城市
	 * @param whichDay
	 *            哪天天气
	 * @return
	 */
	public static String getWhether(String city, String whichDay) {
		System.out.println("请求天气==" + city + whichDay);

		String sr = BaiduAPI.get(BAIDURULR + "location=" + city
				+ "&output=json&ak=" + BAIDUAK);
		System.out.println("sr:" + sr);

		return null;
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
