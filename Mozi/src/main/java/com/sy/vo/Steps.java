package com.sy.vo;

import java.util.Date;

/**
 * 步数时间
 * @author Administrator
 *
 */
public class Steps {
	
	private Integer id;
	//步数
	private Integer stepcount;
	//时间
	private Date  stepdatetime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStepcount() {
		return stepcount;
	}
	public void setStepcount(Integer stepcount) {
		this.stepcount = stepcount;
	}
	public Date getStepdatetime() {
		return stepdatetime;
	}
	public void setStepdatetime(Date stepdatetime) {
		this.stepdatetime = stepdatetime;
	}
	public Steps(Integer id, Integer stepcount, Date stepdatetime) {
		super();
		this.id = id;
		this.stepcount = stepcount;
		this.stepdatetime = stepdatetime;
	}
	public Steps() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
