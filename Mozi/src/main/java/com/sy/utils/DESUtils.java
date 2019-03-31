package com.sy.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author zhangsy
 * @create 2016-11-15 14:18
 */
public class DESUtils {

	private static final String key = "ba34e86439c4986950d64844";

	public static void main(String[] args) {

		String str = "XXvRTTwz6Bty4ZvhEFyOWK/YgyYjTK4OLSrX7aNOwhfyALlyLj/WDot8wZpl7GQvo5Wu/HdbdtZZhfI7CnbyR0gKTHld+EJAZLMl7TQb8BGk6hxGM4PO91BXjZP4Nr5exDFVz4fyNu4b3nrJp4MyzrGgyNe4/eSs2rhkXIJIJawWmp/AC9BYhJ7ZXfep6qJZXiwJy+KiL0nllLHay62uaQr3yWjXHioT7e5mAoE+aOAI6HJ6nSTi9DBV32F3h1CSz4r97WlA9Hcf3to/aXg6kFcFrNuDrLfK5XGpF1xqhQ6+NqrzGGa/a6SdmwptCghmYczsQJ4yexEwr7Ty2xqBFj5K+INA9xBDigfXWsI8cakZOttI2FN6getXR40ttp1Gf9y1Ugu4DUpax3rHDRNtiN08cn9HHGo/PnKaOa15oagRjd26bcGZAbfyHo/8WFKJB5HuXS/oLkj60XCPUVafHpgnPujkOj6NXoQ8gYj/Oh5KswLzCRHb2tiRO+1zux+aO6j0rgZFZjx/g2Neg4nXS8vmc4vMvEEOZoDxnk/XSVJflDSrN8b2S5LKTGU7TpLsFBmrTfKTHBPd2EMGrM2t/bk55yXfVv00FskGRP2iHLM/TaDmE8d9fk0AUpM3rs/NkH3MnqdKQ4U=";

		String decrypt = decrypt(str);
		JSONObject fromObject = JSONObject.fromObject(decrypt);
		String data = fromObject.optString("data");
		System.out.println(data);
		System.out.println(fromObject.toString());
	}

	// 加密
	public static String encrypt(String src) {
		try {
			DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			SecretKey securekey = keyFactory.generateSecret(dks);

			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, securekey);
			byte[] b = cipher.doFinal(src.getBytes("UTF-8"));

			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(b);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 解密
	public static String decrypt(String src) {
		try {
			// --通过base64,将字符串转成byte数组
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bytesrc = decoder.decodeBuffer(src);

			// --解密的key
			DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			SecretKey securekey = keyFactory.generateSecret(dks);

			// --Chipher对象解密
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.DECRYPT_MODE, securekey);
			byte[] retByte = cipher.doFinal(bytesrc);

			return new String(retByte, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
