package com.sy.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("sensorstatus")
public class Sensorstatus extends Model<Sensorstatus>{
	private static final long serialVersionUID = 1L;
	
	@TableId(value="id", type= IdType.AUTO)
    private Integer id;

    private String imei;

    private String h;

    private String g;

    private Date adddate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h == null ? null : h.trim();
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g == null ? null : g.trim();
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}
}