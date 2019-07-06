package com.sy.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
@TableName("equipment_record")
public class EquipmentRecord extends Model<EquipmentRecord>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@TableId(value="id", type= IdType.AUTO)
    private Long id;
	/**
	 * 使用者ID
	 */
    private Integer userId;
    /**
	 * 湿度
	 */
    private Integer humidity;
    /**
	 * 体温
	 */
    private Float temperature;
    /**
	 * 创建时间
	 */
    @TableField(value = "createtime",fill = FieldFill.INSERT )
    private Date createtime;


    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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