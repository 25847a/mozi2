package com.sy.vo;

public class Healthdata {
	private Integer step_when;
	private Integer heat;
	private Integer heartrate;
	private Integer bloodpressure;
	private Integer skindegree;
	private Double sleeping;
	private Integer crash;
	private Integer hrv;
	private Integer mocrocirculation;
	private Integer qxygen;
	private String imei;
	
	public Integer getStep_when() {
		return step_when;
	}

	public void setStep_when(Integer step_when) {
		this.step_when = step_when;
	}

	public Integer getHeat() {
		return heat;
	}

	public void setHeat(Integer heat) {
		this.heat = heat;
	}

	public Integer getHeartrate() {
		return heartrate;
	}

	public void setHeartrate(Integer heartrate) {
		this.heartrate = heartrate;
	}

	public Integer getBloodpressure() {
		return bloodpressure;
	}

	public void setBloodpressure(Integer bloodpressure) {
		this.bloodpressure = bloodpressure;
	}

	public Integer getSkindegree() {
		return skindegree;
	}

	public void setSkindegree(Integer skindegree) {
		this.skindegree = skindegree;
	}

	public Double getSleeping() {
		return sleeping;
	}

	public void setSleeping(Double sleeping) {
		this.sleeping = sleeping;
	}

	public Integer getCrash() {
		return crash;
	}

	public void setCrash(Integer crash) {
		this.crash = crash;
	}

	public Integer getHrv() {
		return hrv;
	}

	public void setHrv(Integer hrv) {
		this.hrv = hrv;
	}

	public Integer getMocrocirculation() {
		return mocrocirculation;
	}

	public void setMocrocirculation(Integer mocrocirculation) {
		this.mocrocirculation = mocrocirculation;
	}

	public Integer getQxygen() {
		return qxygen;
	}

	public void setQxygen(Integer qxygen) {
		this.qxygen = qxygen;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Healthdata(Integer step_when, Integer heat, Integer heartrate,
			Integer bloodpressure, Integer skindegree, Double sleeping,
			Integer crash, Integer hrv, Integer mocrocirculation,
			Integer qxygen, String imei) {
		super();
		this.step_when = step_when;
		this.heat = heat;
		this.heartrate = heartrate;
		this.bloodpressure = bloodpressure;
		this.skindegree = skindegree;
		this.sleeping = sleeping;
		this.crash = crash;
		this.hrv = hrv;
		this.mocrocirculation = mocrocirculation;
		this.qxygen = qxygen;
		this.imei = imei;
	}

	public Healthdata() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
