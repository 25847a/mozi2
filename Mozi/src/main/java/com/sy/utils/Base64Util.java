package com.sy.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import sun.misc.BASE64Decoder;

public class Base64Util {
	
	public static void main(String[] args) {
		String str = "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";
		byte[] fromBASE64 = Base64Utils.decodeFromString(str);
		
		for (byte b : fromBASE64) {
			System.out.println(b);
		}
		
		
	}
	private static final Logger logger = LoggerFactory.getLogger(Base64Util.class);
	
	/**
	 * 将 BASE64 编码的字符串 s 进行解码
	 * @param s
	 * @return
	 */
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
     * 对给定的字符串进行base64加密操作
     */
    public static String encodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.encodeBase64(inputData.getBytes("utf-8")), "utf-8");
        } catch (Exception e) {
            logger.error(inputData, e);
        }

        return null;
    }
}
