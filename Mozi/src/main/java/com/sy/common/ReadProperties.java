package com.sy.common;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;


/**
 * 配置文件读取
 * 
 * @author 啊健
 * 
 */
@Service("readProperties")
public class ReadProperties {
	private static Properties prop = new Properties();
	public static Log log = LogFactory.getLog(ReadProperties.class);
	static {
		InputStream in = ReadProperties.class.getResourceAsStream("/db.properties");
		try {
			prop.load(in);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public static String getValue(String key) {
		return prop.get(key).toString().trim();
	}

	public static int getIntValue(String key) {
		Object obj = prop.get(key);
		return Integer.valueOf(obj.toString().trim());
	}

}
