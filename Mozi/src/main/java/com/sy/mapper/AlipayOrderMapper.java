package com.sy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sy.pojo.AlipayOrder;

public interface AlipayOrderMapper {
 
	
	@Insert( "insert into alipay_order (out_trade_no, total_amount,phone,notify_data,createTime) values (#{out_trade_no},#{total_amount},#{phone},#{notify_data},NOW())")
	public Integer insert(AlipayOrder alipayOrder);

	@Update( "update  alipay_order set notify_data=#{notify_data},phone=#{phone} where out_trade_no = #{out_trade_no}")
	public void updateNotifyDate(AlipayOrder ali);

	@Select( "select * from alipay_order where  out_trade_no= #{out_trade_no}")
	public AlipayOrder select(AlipayOrder ali);
	
}