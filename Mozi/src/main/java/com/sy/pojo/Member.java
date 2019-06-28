package com.sy.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName("member")
public class Member extends Model<Member>{
	
	private static final long serialVersionUID = 1L;
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
    private Date member;
    /**
     * 用户到期时间
     */
    private Date createtime;

    

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



	public Date getMember() {
		return member;
	}



	public void setMember(Date member) {
		this.member = member;
	}



	public Date getCreatetime() {
		return createtime;
	}



	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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