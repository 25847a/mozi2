package com.sy.vo;

import java.util.Date;

public class SHChart {
	//睡眠
	private Integer sleeping;
	//步数
	private Integer step_when;
	//时间
	private Date date;
	public Integer getSleeping() {
		return sleeping;
	}
	public void setSleeping(Integer sleeping) {
		this.sleeping = sleeping;
	}
	public Integer getStep_when() {
		return step_when;
	}
	public void setStep_when(Integer step_when) {
		this.step_when = step_when;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
