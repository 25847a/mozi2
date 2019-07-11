package com.sy.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("jfhealth")
public class Jfhealth extends Model<UserEq>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@TableId(value="id", type= IdType.AUTO)
    private Integer id;
	/**
	 * HRV
	 */
    private Integer HRV;
    /**
	 * 收缩压
	 */
    @TableField(value="sbp_ave")
    private Integer sbpAve;
    /**
	 * 舒张压
	 */
    @TableField(value="dbp_ave")
    private Integer dbpAve;
    /**
	 * 心率
	 */
    private Integer heartrate;
    /**
	 * 血氧
	 */
    private Integer bloodoxygen;
    /**
	 * 微循环
	 */
    private Integer microcirculation;
    /**
	 * 呼吸频率
	 */
    private Integer respirationrate;
    /**
	 * 情绪
	 */
    private Integer mood;
    /**
	 * 手机号码
	 */
    private String phone;
    /**
	 * 设备号
	 */
    private String imei;
    /**
	 * 数据上传时间
	 */
    @TableField(value = "createtime",fill = FieldFill.INSERT )
    private Date createtime;
    /**
	 * 体检报告
	 */
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

    public Integer getMood() {
		return mood;
	}

	public void setMood(Integer mood) {
		this.mood = mood;
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

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}
}