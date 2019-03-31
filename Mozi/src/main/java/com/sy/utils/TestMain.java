package com.sy.utils;

import com.google.gson.Gson;

import net.sf.json.JSONObject;

public class TestMain {
	private String url = "https://api.jingfantech.com/V1.02/physical_exam/manufacturer";  
	  private String charset = "utf-8";  
	  private HttpClientUtil httpClientUtil = null;  
	     
	  public TestMain(){  
	    httpClientUtil = new HttpClientUtil();  
	  }  
	     
	  public void test(){  
	    String httpOrgCreateTest = url;
	  //  Map<String,String> createMap = new HashMap<String,String>();  
	 
	    
	    JSONObject createMap = new JSONObject();
	    
	    createMap.put("service", "HRV");
	    createMap.put("channel_id", "jftester");
	    createMap.put("channel_secret", "Xi9G3w7Qr5yO");
	    createMap.put("name", "15522518863");
	    createMap.put("secret", "123456");
	    createMap.put("client_id", "0");
	    createMap.put("device_id", "6A6668010001010101010000FFFFFFFF");
	    createMap.put("start_time", "2018-03-16 10:12:00");
	    createMap.put("period", "1d");
	    Gson gson = new Gson();
	    String json = gson.toJson(createMap);
	    String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,json,charset);  
	    System.out.println("result:"+httpOrgCreateTestRtn);  
	  }  
	     
	  public static void main(String[] args){  
	    TestMain main = new TestMain();  
	    main.test();  
	  }  
}
