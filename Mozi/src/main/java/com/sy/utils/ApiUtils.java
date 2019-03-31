package com.sy.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class ApiUtils {

	private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static final String secret = "ba34e86439c4986950d648440d749e27";
	private static final String appKey = "f4aequn0go";
	private static final String format = "json";
	public static final String jtNumber = "2000602467";
	private static final String v = "3.0";
	private static final String transID = jtNumber + dateFormat.format(new Date())+randomNum();
	private static final String url = "http://120.197.89.173:8081/openapi/router";

	public static void main(String[] args) throws Exception {

		Map<String, String> paramValues = new LinkedHashMap<String, String>();
		paramValues.put("method", "triopi.member.dailyflow.realtime.query");
		paramValues.put("msisdn", "18498703334"); 
		
		System.out.println(doPost(paramValues));
	}
	
	
	/**
	 * param 业务参数(手机号,集团编码等)
	 * method 调用的接口,必传key
	 * @param map
	 * @return
	 */
	
	public static JSONObject doPost(Map<String,String> map){
		
		 
		Map<String, String> allParam = allParam(map);
		String str = HttpUrlUtil.doPost(url, allParam);
		String decrypt = DESUtils.decrypt(str);
		JSONObject fromObject = JSONObject.fromObject(decrypt);
		return fromObject;
	}
	
	/**
	 * 返回调用接口所需的参数
	 * @return
	 */
	private static Map<String,String> allParam(Map<String,String> map){
		map.put("format", format); // format
		map.put("appKey", appKey); // appKey
		map.put("transID", transID); // transID
		map.put("v",v);
		String sign = sign(paramSort(map), secret);
		map.put("sign", sign);
		return map;
	}
	
	private static Map<String,String> paramSort(Map<String,String> map ) {
		
		List<String> list = new ArrayList<>(map.keySet());
		Collections.sort(list);
		Map<String,String> linkMap = new LinkedHashMap<>();
		for (String key : list) {
			linkMap.put(key, map.get(key));
		}
		return linkMap;
	}

	private static Integer randomNum() {
		Integer randomNum = (int) ((Math.random() * 9 + 1) * 1000);
		return randomNum;
	}

	/**
	 * 使用<code>secret</code>对paramValues按以下算法进行签名： <br/>
	 * uppercase(hex(sha1(secretkey1value1key2value2...secret))
	 *
	 * @param paramNames
	 *            需要签名的参数名
	 * @param paramValues
	 *            参数列表
	 * @param secret
	 * @return
	 */
	private static String sign(Map<String, String> paramValues, String secret) {
		return sign(paramValues, null, secret);
	}

	/**
	 * 对paramValues进行签名，其中ignoreParamNames这些参数不参与签名
	 * 
	 * @param paramValues
	 * @param ignoreParamNames
	 * @param secret
	 * @return
	 */
	private static String sign(Map<String, String> paramValues, List<String> ignoreParamNames, String secret) {
		try {
			StringBuilder sb = new StringBuilder();
			List<String> paramNames = new ArrayList<String>(paramValues.size());
			paramNames.addAll(paramValues.keySet());
			if (ignoreParamNames != null && ignoreParamNames.size() > 0) {
				for (String ignoreParamName : ignoreParamNames) {
					paramNames.remove(ignoreParamName);
				}
			}
			Collections.sort(paramNames);

			sb.append(secret);
			for (String paramName : paramNames) {
				sb.append(paramName).append(paramValues.get(paramName));
			}
			sb.append(secret);
			byte[] sha1Digest = getSHA1Digest(sb.toString());
			return byte2hex(sha1Digest);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String utf8Encoding(String value, String sourceCharsetName) {
		try {
			return new String(value.getBytes(sourceCharsetName), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private static byte[] getSHA1Digest(String data) throws IOException {
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			bytes = md.digest(data.getBytes("UTF-8"));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse);
		}
		return bytes;
	}

	/**
	 * 二进制转十六进制字符串
	 *
	 * @param bytes
	 * @return
	 */
	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}
}