package com.sy.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PushRecord {
	/**
	 * 主键ID
	 */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	/**
	 * 使用者ID
	 */
	private Integer userId;
	/**
	 * 心率异常值
	 */
	private Integer heartUnusual;
	/**
	 * 舒张压异常值
	 */
	private Integer highBloodUnusual;
	/**
	 * 收缩压异常值
	 */
	private Integer lowBloodUnusual;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 
	private Date createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Integer getHeartUnusual() {
		return heartUnusual;
	}
	public void setHeartUnusual(Integer heartUnusual) {
		this.heartUnusual = heartUnusual;
	}
	public Integer getHighBloodUnusual() {
		return highBloodUnusual;
	}
	public void setHighBloodUnusual(Integer highBloodUnusual) {
		this.highBloodUnusual = highBloodUnusual;
	}
	public Integer getLowBloodUnusual() {
		return lowBloodUnusual;
	}
	public void setLowBloodUnusual(Integer lowBloodUnusual) {
		this.lowBloodUnusual = lowBloodUnusual;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
		
}
