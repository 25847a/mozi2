package com.sy.vo;

import java.util.Date;

public class Bloodpressure {

	private Integer id;
	//血压
	private Integer pressure;
	//血氧
	private Integer Oxygen;
	//时间
	private Date createtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPressure() {
		return pressure;
	}
	public void setPressure(Integer pressure) {
		this.pressure = pressure;
	}
	public Integer getOxygen() {
		return Oxygen;
	}
	public void setOxygen(Integer oxygen) {
		Oxygen = oxygen;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Bloodpressure(Integer id, Integer pressure, Integer oxygen,
			Date createtime) {
		super();
		this.id = id;
		this.pressure = pressure;
		Oxygen = oxygen;
		this.createtime = createtime;
	}
	public Bloodpressure() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
