package com.sy.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName("push")
public class Push extends Model<Push>{
	private static final long serialVersionUID = 1L;
	/**
	 * 编号id
	 */
	private Integer id;
	/**
	 * 登录 的mid做为alias
	 */
	private Integer alias;
	/**
	 * 用户id做为alias
	 */
	private Integer userId;
	/**
	 * 总通知开关
	 */
	private Boolean allNotifyOn;
	/**
	 * 心率通知开关
	 */
	private Boolean heartNotifyOn;
	/**
	 * 血压通知开关
	 */
	private Boolean boolPreNotifyOn;
	/**
	 * 心率低阈值
	 */
	private Integer heartLowThd;
	/**
	 * 心率通知高阈值
	 */
	private Integer heartHigThd;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Integer lbpstart;
	/**
	 * 修改时间
	 */
	private Integer lbpend;
	/**
	 * 修改时间
	 */
	private Integer hbpstart;
	/**
	 * 修改时间
	 */
	private Integer hbpend;
	/**
	 * 修改时间
	 */
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAlias() {
		return alias;
	}

	public void setAlias(Integer alias) {
		this.alias = alias;
	}

	public Boolean getAllNotifyOn() {
		return allNotifyOn;
	}

	public void setAllNotifyOn(Boolean allNotifyOn) {
		this.allNotifyOn = allNotifyOn;
	}

	public Boolean getHeartNotifyOn() {
		return heartNotifyOn;
	}

	public void setHeartNotifyOn(Boolean heartNotifyOn) {
		this.heartNotifyOn = heartNotifyOn;
	}

	public Boolean getBoolPreNotifyOn() {
		return boolPreNotifyOn;
	}

	public void setBoolPreNotifyOn(Boolean boolPreNotifyOn) {
		this.boolPreNotifyOn = boolPreNotifyOn;
	}

	public Integer getHeartLowThd() {
		return heartLowThd;
	}

	public void setHeartLowThd(Integer heartLowThd) {
		this.heartLowThd = heartLowThd;
	}

	public Integer getHeartHigThd() {
		return heartHigThd;
	}

	public void setHeartHigThd(Integer heartHigThd) {
		this.heartHigThd = heartHigThd;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getLbpstart() {
		return lbpstart;
	}

	public void setLbpstart(Integer lbpstart) {
		this.lbpstart = lbpstart;
	}

	public Integer getLbpend() {
		return lbpend;
	}

	public void setLbpend(Integer lbpend) {
		this.lbpend = lbpend;
	}

	public Integer getHbpstart() {
		return hbpstart;
	}

	public void setHbpstart(Integer hbpstart) {
		this.hbpstart = hbpstart;
	}

	public Integer getHbpend() {
		return hbpend;
	}

	public void setHbpend(Integer hbpend) {
		this.hbpend = hbpend;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}

	
	
	
		
}
