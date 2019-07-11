package com.sy.pojo;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


@TableName("positionig")
public class Positionig extends Model<Positionig>{
	
	private static final long serialVersionUID = 1L;
	@TableId(value="id", type= IdType.AUTO)
    private Integer id;

	@TableField(value="positioning_s")
    private String positioningS;
	
	@TableField(value="positioning_data")
    private String positioningData;
    @TableField(value = "createtime",fill = FieldFill.INSERT )
    private Date createtime;

    private String imei;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositioningS() {
        return positioningS;
    }

    public void setPositioningS(String positioningS) {
        this.positioningS = positioningS == null ? null : positioningS.trim();
    }

    public String getPositioningData() {
        return positioningData;
    }

    public void setPositioningData(String positioningData) {
        this.positioningData = positioningData == null ? null : positioningData.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}
}