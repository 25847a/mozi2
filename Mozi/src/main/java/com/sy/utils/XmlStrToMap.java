package com.sy.utils;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlStrToMap {
	
	
	public static void main(String[] args) throws UnsupportedEncodingException, DocumentException {
		
		
		String xml = "<xml>"+
		"<appid><![CDATA[wx1ca07fa56a87493d]]></appid>"+
		"<bank_type><![CDATA[CFT]]></bank_type>"+
		"<cash_fee><![CDATA[1]]></cash_fee>"+
		"<fee_type><![CDATA[CNY]]></fee_type>"+
		"<is_subscribe><![CDATA[Y]]></is_subscribe>"+
		"<mch_id><![CDATA[1480110732]]></mch_id>"+
		"<nonce_str><![CDATA[201811270959557925266]]></nonce_str>"+
		"<openid><![CDATA[oS-UYwIAFBe_vdyOAmtGyHjYOkgY]]></openid>"+
		"<out_trade_no><![CDATA[201811270959557925266]]></out_trade_no>"+
		"<result_code><![CDATA[SUCCESS]]></result_code>"+
		"<return_code><![CDATA[SUCCESS]]></return_code>"+
		"<sign><![CDATA[6CC62700007B057A39341BE65CF3633B]]></sign>"+
		"<time_end><![CDATA[20181127100005]]></time_end>"+
		"<total_fee>1</total_fee>"+
		"<trade_type><![CDATA[MWEB]]></trade_type>"+
		"<transaction_id><![CDATA[4200000207201811274184560118]]></transaction_id>"+
		"</xml>";
		
		
		
		Map<String, String> xmlToMap = xmlToMap(xml, "UTF-8");
		System.out.println(xmlToMap);
	}
	/**
	* XML格式字符串转换为Map
	* @作者 廖正瀚
	* @日期 2017年12月1日
	* @param xml
	* @param charset
	* @return
	* @throws DocumentException 
	* @throws UnsupportedEncodingException 
	*/
	public static SortedMap<String, String> xmlToMap(String xml, String charset) throws UnsupportedEncodingException, DocumentException{

	SortedMap<String, String> respMap = new TreeMap<String, String>();

	SAXReader reader = new SAXReader();
	Document doc = reader.read(new ByteArrayInputStream(xml.getBytes(charset)));
	Element root = doc.getRootElement();
	xmlToMap(root, respMap);
	return respMap;
	}

	public static SortedMap<String, String> xmlToMap(Element tmpElement, SortedMap<String, String> respMap){

	if (tmpElement.isTextOnly()) {
	respMap.put(tmpElement.getName(), tmpElement.getText());
	return respMap;
	}

	@SuppressWarnings("unchecked")
	Iterator<Element> eItor = tmpElement.elementIterator();
	while (eItor.hasNext()) {
	Element element = eItor.next();
	xmlToMap(element, respMap);
	}
	return respMap;
	}
}
