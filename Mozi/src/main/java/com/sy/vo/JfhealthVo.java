package com.sy.vo;

import java.util.Date;

public class JfhealthVo {
	
	private Integer userid;
    private Integer id;
    
    private Integer hrv;

    private Integer sbpAve;

    private Integer dbpAve;

    private Integer heartrate;

    private Integer bloodoxygen;

    private Integer microcirculation;

    private Integer respirationrate;

    private String phone;

    private String imei;

    private Date createtime;

    private String amedicalreport;

    
    public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHrv() {
        return hrv;
    }

    public void setHrv(Integer hrv) {
        this.hrv = hrv;
    }

    public Integer getSbpAve() {
        return sbpAve;
    }

    public void setSbpAve(Integer sbpAve) {
        this.sbpAve = sbpAve;
    }

    public Integer getDbpAve() {
        return dbpAve;
    }

    public void setDbpAve(Integer dbpAve) {
        this.dbpAve = dbpAve;
    }

    public Integer getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(Integer heartrate) {
        this.heartrate = heartrate;
    }

    public Integer getBloodoxygen() {
        return bloodoxygen;
    }

    public void setBloodoxygen(Integer bloodoxygen) {
        this.bloodoxygen = bloodoxygen;
    }

    public Integer getMicrocirculation() {
        return microcirculation;
    }

    public void setMicrocirculation(Integer microcirculation) {
        this.microcirculation = microcirculation;
    }

    public Integer getRespirationrate() {
        return respirationrate;
    }

    public void setRespirationrate(Integer respirationrate) {
        this.respirationrate = respirationrate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getAmedicalreport() {
        return amedicalreport;
    }

    public void setAmedicalreport(String amedicalreport) {
        this.amedicalreport = amedicalreport == null ? null : amedicalreport.trim();
    }
}