package com.sy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sy.pojo.AlipayOrder;
import com.sy.pojo.WxpayOrder;

public interface WxpayOrderMapper {
 
	
	@Insert( "insert into wxpay_order (out_trade_no, total_amount,phone,notify_data,createTime) values (#{out_trade_no},#{total_amount},#{phone},#{notify_data},NOW())")
	public Integer insert(WxpayOrder alipayOrder);

	@Update( "update  wxpay_order set notify_data=#{notify_data},phone=#{phone} where out_trade_no = #{out_trade_no}")
	public void updateNotifyDate(WxpayOrder ali);

	@Select( "select * from wxpay_order where  out_trade_no= #{out_trade_no}")
	public WxpayOrder select(WxpayOrder ali);
	
}