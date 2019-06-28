package com.sy.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName("equipment_data")
public class EquipmentData extends Model<EquipmentData>{
	private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer userId;

    private Integer stepWhen;

   private Integer carrieroad;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Integer getStepWhen() {
        return stepWhen;
    }

    public void setStepWhen(Integer stepWhen) {
        this.stepWhen = stepWhen;
    }

    public Integer getCarrieroad() {
        return carrieroad;
    }

    public void setCarrieroad(Integer carrieroad) {
        this.carrieroad = carrieroad;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }


	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}
}