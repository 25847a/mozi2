package com.sy.vo;

import java.util.List;

public class Sleepingsvo {

	private List<Sleepings> sleepings;
	//深度睡眠
	private String depthsleepings;
	//浅度睡眠
	private String shallowsleepings;
	//觉醒时间
	private String awakeningtime;
	//总睡眠时间
	private String  totalsleeping;
	public List<Sleepings> getSleepings() {
		return sleepings;
	}
	public void setSleepings(List<Sleepings> sleepings) {
		this.sleepings = sleepings;
	}
	public String getDepthsleepings() {
		return depthsleepings;
	}
	public void setDepthsleepings(String depthsleepings) {
		this.depthsleepings = depthsleepings;
	}
	public String getShallowsleepings() {
		return shallowsleepings;
	}
	public void setShallowsleepings(String shallowsleepings) {
		this.shallowsleepings = shallowsleepings;
	}
	public String getAwakeningtime() {
		return awakeningtime;
	}
	public void setAwakeningtime(String awakeningtime) {
		this.awakeningtime = awakeningtime;
	}
	public String getTotalsleeping() {
		return totalsleeping;
	}
	public void setTotalsleeping(String totalsleeping) {
		this.totalsleeping = totalsleeping;
	}
	public Sleepingsvo(List<Sleepings> sleepings, String depthsleepings,
			String shallowsleepings, String awakeningtime, String totalsleeping) {
		super();
		this.sleepings = sleepings;
		this.depthsleepings = depthsleepings;
		this.shallowsleepings = shallowsleepings;
		this.awakeningtime = awakeningtime;
		this.totalsleeping = totalsleeping;
	}
	public Sleepingsvo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
