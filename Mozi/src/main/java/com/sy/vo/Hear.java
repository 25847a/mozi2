package com.sy.vo;

import java.util.Date;

public class Hear {
	private Integer id ;
	private Integer heartrate;
	private Date  createtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHeartrate() {
		return heartrate;
	}
	public void setHeartrate(Integer heartrate) {
		this.heartrate = heartrate;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Hear(Integer id, Integer heartrate, Date createtime) {
		super();
		this.id = id;
		this.heartrate = heartrate;
		this.createtime = createtime;
	}
	public Hear() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
