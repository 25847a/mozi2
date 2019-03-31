package com.sy.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Tiaoqi {
	public static final String method = "GET";
	public static final String host = "http://jisutianqi.market.alicloudapi.com";
	public static final String appcode = "222bbecf0f1e4963a34ea074b0a096ec";
	public static final String path = "/weather/query";

	public static String tiaonqi(String city) throws Exception {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("city", city);
        String text ="";
		try {
			HttpResponse response = HttpUtils.doGet(host, path, method,
					headers, querys);
			JSONArray array = JSONArray.fromObject(JSONObject.fromObject(
					JSONObject.fromObject(
							EntityUtils.toString(response.getEntity())).get(
							"result")).get("daily"));
		   JSONObject indexjson=	(JSONObject) array.get(0);
		   JSONObject night=   (JSONObject) indexjson.get("night");
		   JSONObject day=   (JSONObject) indexjson.get("day");
		
			text =city+","+night.get("weather")+","+day.get("temphigh")+","+night.get("templow");
			System.out.println(text);
		} catch (Exception e) {

		}
		return text;
	}

	public static void main(String[] args) throws Exception {

		tiaonqi("揭东");
	}

}
