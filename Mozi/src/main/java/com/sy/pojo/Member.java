package com.sy.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName("member")
public class Member extends Model<Member>{
	
	private static final long serialVersionUID = 1L;
	@TableId(value="id", type= IdType.AUTO)
	/**
	 * 主键ID
	 */
    private Integer id;
    /**
     * 使用者ID
     */
    private Integer userId;
    /**
     * 话费
     */
    private BigDecimal charges;
    /**
     * 积分
     */
    private Integer integral;
    /**
     * 会员等级(0.普通会员  1.高级会员 2.黄金会员 3.VIP会员)
     */
    private Integer member;
    /**
     * 用户到期时间
     */
    private Date endTime;

    

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public BigDecimal getCharges() {
		return charges;
	}



	public void setCharges(BigDecimal charges) {
		this.charges = charges;
	}



	public Integer getIntegral() {
		return integral;
	}



	public void setIntegral(Integer integral) {
		this.integral = integral;
	}



	public Integer getMember() {
		return member;
	}



	public void setMember(Integer member) {
		this.member = member;
	}



	public Date getEndTime() {
		return endTime;
	}



	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}
}