package com.sy.pojo;

import java.util.Date;

public class AlipayOrder {
	
	private Integer id;                  

	/**
	 * 商户网站唯一订单号
	 */
	private String out_trade_no; 
	
	
	/**
	 * 订单总金额，单位为元，精确到小数点后两位
	 */
	private String total_amount;

	/**
	 * 充值的手机号
	 */
	private String phone;
	
	/**
	 * 公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。
	 * 支付宝会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝
	 */
	private String passback_params; 
	
	/**
	 * 异步rul返回的数据  查看https://docs.open.alipay.com/203/105286/
	 */
	private String notify_data;
	
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassback_params() {
		return passback_params;
	}

	public void setPassback_params(String passback_params) {
		this.passback_params = passback_params;
	}

	public String getNotify_data() {
		return notify_data;
	}

	public void setNotify_data(String notify_data) {
		this.notify_data = notify_data;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	
}
