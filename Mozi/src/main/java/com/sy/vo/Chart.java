package com.sy.vo;


/**
 * 这个是返回图表数据的实体类(啊健)
 * @author Administrator
 *
 */
public class Chart {
	//平均值
	private Integer HRV;
	//日期
	private String date;
	//高压
	private Integer sbpAve;
	//低压
    private Integer dbpAve;
    //心率
    private Integer heartrate;
    //血氧
    private Integer bloodoxygen;
    //微循环
    private Integer microcirculation;
    //呼吸频率
    private Integer respirationrate;
    
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
	
	public Integer getHRV() {
		return HRV;
	}
	public void setHRV(Integer hRV) {
		HRV = hRV;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
