package com.sy.pojo;

import java.util.Date;

public class Push {
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
	 * 跌倒通知开关
	 */
	private Boolean fallNotifyOn;
	
	/**
	 * 步数通知开关
	 */
	private Boolean walkNotifyOn;
	/**
	 * 围栏通知开关
	 */
	private Boolean fenceNotifyOn;
	
	/**
	 * 心率低阈值
	 */
	private Integer heartLowThd;
	/**
	 * 心率通知高阈值
	 */
	private Integer heartHigThd;
	/**
	 * 血压通知低阈值
	 */
	private Integer boolLowThd;
	/**
	 * 血压通知高阈值
	 */
	private Integer boolHigThd;
	
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Integer lbpstart;
	
	private Integer lbpend;
	
	private Integer hbpstart;
	
	private Integer hbpend;
	
	private Date updateTime;

	
	
	
	public Boolean getFenceNotifyOn() {
		return fenceNotifyOn;
	}

	public void setFenceNotifyOn(Boolean fenceNotifyOn) {
		this.fenceNotifyOn = fenceNotifyOn;
	}

	public Boolean getWalkNotifyOn() {
		return walkNotifyOn;
	}

	public void setWalkNotifyOn(Boolean walkNotifyOn) {
		this.walkNotifyOn = walkNotifyOn;
	}

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

	public Boolean getFallNotifyOn() {
		return fallNotifyOn;
	}

	public void setFallNotifyOn(Boolean fallNotifyOn) {
		this.fallNotifyOn = fallNotifyOn;
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

	public Integer getBoolLowThd() {
		return boolLowThd;
	}

	public void setBoolLowThd(Integer boolLowThd) {
		this.boolLowThd = boolLowThd;
	}

	public Integer getBoolHigThd() {
		return boolHigThd;
	}

	public void setBoolHigThd(Integer boolHigThd) {
		this.boolHigThd = boolHigThd;
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

	
	
	
		
}
