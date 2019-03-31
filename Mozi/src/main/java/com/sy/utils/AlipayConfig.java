package com.sy.utils;

public class AlipayConfig {

	// 商户appid
	public static String APPID = "2018110161927908";
	
	
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCHK/3pOyeXPx+tSlCXbty0H3s0hoEBYwIV6pSmElRCcauBAO/8Yhm6NUHkYqegU07JS1zYV491pELB/SHdkMuWdOuHQa5rg4JulKmuyScZ90NYDw8D3WkS1vo6gxVXo4WXXBDGwHs8lf/Pji3+9do8yGU9W5ykm5bdNoEciR1goOFCKvL6TtEkdsHH3Hw8IP4ClTIZP5AoKeKEgcDUMcRuHHeaX3r3OQLSuO1zZMAN/fOf2CTcywa8Z6o75InHsyb4HCsSwTZFxspKtYX9eAjywK6KXUPkSd/hXOQhVHlK7r/UosvdBgET5wP2SjI6MpW0gS4wCOe9pdYz33a8P7rtAgMBAAECggEAUrvxaLzlSaTATb/LvjSD79fJAGhHM8MW9rUjENbtLdIHSCl7uj2NQ7TvRMvBj9cNxRVUmbJWQ4ortjKqdMP4aHnd42anTHUs82QYy7uhLDp0xMRKy3HMq1Rbyk6LVJ3o2bDnL8+GXpns/mwm6QQv9L4vgTO+l94HpBSFSCMAkTBeqg9Fct67JqcLxAWKhqG+rpqRNCBJij9LqF/5t8rWbJZZR6WOdsN4w+1Q8laEukI8Is12N6V7SAgMQpv+jVL+Wy+i9AcMvLm10dlJWOndeFWNX4TLZH/rhwg0e3PECJBgmhpKFMWB31IC6DZhhFzrFi/BdFYm1v93UI12Nk2X4QKBgQDk9XAM7gT6oWRVTAuoQlqMRi9XgCLuLECxv3h5lP1aF5JaE3DJOPkzMRRoJRi1iAOis5isA8OnE/264AZB9yOs1/x02L6dj/mdjLyG/s0JJymq3Sc7vSO0bRlBiv10uiWuvxh3DIcfB4Pmfex4XHo9Yg5VULBQpODTAvhuzqWUWQKBgQCXIujQTB6oSpPXUUJAVYVJ6qV7vqxJYtci8swc52xvWF2/rIENJMnuxDndtGl3zABC9xmQIogpjFmldtY8+4H0qH5uoSFIVKb5pp0ryD28xuSDM2DPjkRZk7QYTGwespgvqDfHgsWxeEz33b3sV72YMTF0EZF7/3nTEICsCsGYtQKBgQChnAjKZ5uGGTNL7dBwpN1rFANInP6K5RuJG2B7WUn3NJouEHjU3+0uUB9aX4zwXaBLUL+b81A/96FqLcTc/QfmcdIV3AO4Da8goP1VwDaTllwX6eBOj3PLYBKqq+O5Z8c/cE1hgqtBoLlekVW/o275MuRxHfJGCgtThk47UIuZiQKBgDx5NGy8OHwgMx2xnu+9CHM2F3b3KVrH2MtcGT3rf6Dh28kpDg4jTUVjcL/8Q0My9lvVIX5hwtfVF720Wi7Wd/5cORKtGHMnpBQ6jaMKIWHat26j7TW/eCvbhPFACvcRgQF4rrD+63mCaFG5gRgDV5KBBIery+mwpJ9GeCXeu+4NAoGBAI/btiVUTFVG+eUg+vHiKf4y4t286dL9k9zxRSVsBft22OFV/KLwH49KZABeDmq6btmpDjMsaMGNmcN4uxbUF90O5XyvTq8h7QUDa+fplLzjjtNv6oM6g7NUpeMtAMiqfL5nnjWDcIHtA6nSGxU4V4kwtlW4uJvmWHf+5iZW8PW7";
	
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//public static String notify_url = "http://devmg08.mozistar.com/Mozi/h5/notify_url";
	 public static String notify_url = "http://lanotec.iask.in:32178/Mozi/h5/notify_url";
	
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	 //public static String return_url = "http://devmg08.mozistar.com/Mozi/h5/return_url";
	 public static String return_url = "http://lanotec.iask.in:32178/Mozi/h5/return_url";

	//支付中途返回的地址
	//public static String  QUIT__URL = "http://devmg08.mozistar.com/Mozi/h5/index?phone=";
	public static String  QUIT__URL = "http://lanotec.iask.in:32178/Mozi/h5/index?phone=";
	 
	// 请求网关地址
	public static String URL = "https://openapi.alipay.com/gateway.do";
	
	// 编码
	public static String CHARSET = "UTF-8";
	
	// 返回格式
	public static String FORMAT = "json";
	
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMII"
			+ "BCgKCAQEAqM8uviSG8jJa+9mfXZYTqI/oL7Nh35R8qxQzD7RVC+L6qnllsvDlGfQYHPnI"
			+ "1TcZwsvcAEmbLERh34NEvN0bO/7rULClKCpnMh50HsTABOPm972Q+k/2OfJzKbJJfs5gk"
			+ "OtBlit5/o/Z3K4hIVIHaS3PMUiiQzKiMr6mlWkQ+kA24V3cOMmZsKoAp2Bhit/dq3vsLE"
			+ "uwj4n2AUQbeSVbBbunUJaFTKfi6JTQLcSTJHKPmXQnqw2+Cl8gVCDsmb3tFfsgIy9+3Xp"
			+ "+CkKnx9VEauwSa5voiJ9DTg8miVVZAEp/WgM6sNLo32wUj5blOLLw8jE3Cz8VKcleybez"
			+ "XOL2HwIDAQAB";
	
	// 日志记录目录
	public static String log_path = "/log";
	
	// RSA2
	public static String SIGNTYPE = "RSA2";
}
