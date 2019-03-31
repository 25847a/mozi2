package com.sy.utils;

/**
 * 微信h5支付配置
 * @author Admin
 *
 */
public class WechatConfig {
	
	//url
	public static final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	public static final String key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASC";

	//appid
	public static String appid = "wx1ca07fa56a87493d";
	
	//商户号
	public static String mch_id = "1480110732";

	public static String notify_url = "http://devmg08.mozistar.com/Mozi/h5/notifyurl";
	//public static String notify_url = "http://lanotec.iask.in:32178/Mozi/h5/notify_url";
		
	 public static String return_url = "http://devmg08.mozistar.com/Mozi/h5/index";
	 //public static String return_url = "http://lanotec.iask.in:32178/Mozi/h5/return_url";

	//商品描述
	public static String body = "话费充值";
	
	//交易类型 h5 固定值
	public static String trade_type = "MWEB";
	
	//场景信息
	public static String scene_info = "{\"h5_info\":{\"type\":\"Wap\",\"wap_url\":\"http://devmg08.mozisatr.com\",\"wap_name\":\"话费充值\"}}";
	
}
