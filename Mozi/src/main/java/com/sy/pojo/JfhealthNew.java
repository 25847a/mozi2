package com.sy.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
@TableName("jfhealth_new")
public class JfhealthNew extends Model<JfhealthNew>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@TableId(value="id", type= IdType.AUTO)
    private int id;
	/**
	 * HRV
	 */
    private int HRV;
    /**
	 * 收缩压
	 */
    @TableField(value="sbp_ave")
    private int sbpAve;
    /**
	 * 舒张压
	 */
    @TableField(value="dbp_ave")
    private int dbpAve;
    /**
   	 * 心率
   	 */
    private int heartrate;
    /**
	 * 血氧
	 */
    private int bloodoxygen;
    /**
	 * 微循环
	 */
    private int microcirculation;
    /**
	 * 呼吸频率
	 */
    private int respirationrate;
    /**
	 * 情绪
	 */
    private int mood;
    /**
	 * 手机号码
	 */
    private String phone;
    /**
	 * 设备号
	 */
    private String imei;
    /**
	 * 数据创建时间
	 */
    @TableField(value = "createtime",fill = FieldFill.INSERT )
    private Date createtime;
    /**
   	 * 数据更新时间
   	 */
    @TableField(value = "updatetime",fill = FieldFill.UPDATE )
    private Date updatetime;
    /**
	 * 体检报告
	 */
    private String amedicalreport;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public int getHRV() {
		return HRV;
	}

	public void setHRV(int hRV) {
		HRV = hRV;
	}

	public int getSbpAve() {
        return sbpAve;
    }

    public void setSbpAve(int sbpAve) {
        this.sbpAve = sbpAve;
    }

    public int getDbpAve() {
        return dbpAve;
    }

    public void setDbpAve(int dbpAve) {
        this.dbpAve = dbpAve;
    }

    public int getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(int heartrate) {
        this.heartrate = heartrate;
    }

    public int getBloodoxygen() {
        return bloodoxygen;
    }

    public void setBloodoxygen(int bloodoxygen) {
        this.bloodoxygen = bloodoxygen;
    }

    public int getMicrocirculation() {
        return microcirculation;
    }

    public void setMicrocirculation(int microcirculation) {
        this.microcirculation = microcirculation;
    }

    public int getRespirationrate() {
        return respirationrate;
    }

    public void setRespirationrate(int respirationrate) {
        this.respirationrate = respirationrate;
    }

    public int getMood() {
		return mood;
	}

	public void setMood(int mood) {
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

    public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
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