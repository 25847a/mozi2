package com.sy.vo;

import java.util.Date;

public class Sleepings {
	
	private Integer id;
	//睡眠时间
	private double sleeping;
	//上报睡眠的时间
	private Date sleepingtime;
	
	public Sleepings(Integer id, double sleeping, Date sleepingtime) {
		super();
		this.id = id;
		this.sleeping = sleeping;
		this.sleepingtime = sleepingtime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getSleeping() {
		return sleeping;
	}

	public void setSleeping(double sleeping) {
		this.sleeping = sleeping;
	}

	public Date getSleepingtime() {
		return sleepingtime;
	}

	public void setSleepingtime(Date sleepingtime) {
		this.sleepingtime = sleepingtime;
	}

	public Sleepings() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}