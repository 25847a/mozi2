package com.sy.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
@TableName("realhealth")
public class Realhealth extends Model<Realhealth>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 编号id
	 */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    private Integer HRV;
    private Integer sbpAve;
    private Integer dbpAve;
    private Integer heartrate;
    private Integer bloodoxygen;
    private Integer microcirculation;
    private Integer respirationrate;
    private String phone;
    private String imei;
    @TableField(value = "createtime",fill = FieldFill.INSERT )
    private Date createtime;
    
    private String amedicalreport;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHRV() {
		return HRV;
	}
	public void setHRV(Integer hRV) {
		HRV = hRV;
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
		this.phone = phone;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
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
		this.amedicalreport = amedicalreport;
	}
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}
    
    
    
}
