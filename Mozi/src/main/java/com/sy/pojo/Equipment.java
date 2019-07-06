package com.sy.pojo;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("equipment")
public class Equipment extends Model<Equipment>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@TableId(value="id", type= IdType.AUTO)
    private Integer id;
	/**
	 * 设备imei
	 */
    private String imei;
    /**
	 * 主机电量
	 */
    private Integer lordpower;
    /**
	 * 主机信号
	 */
    private String signalxhao;
    /**
	 * 蓝牙类型
	 */
    @TableField(value="bluetooth_type")
    private String bluetoothType;
    /**
	 * 设备状态(在线或者离线)
	 */
    @TableField(value="eq_status")
    private String eqStatus;
    /**
	 * 注册时间
	 */
    private Date createtime;
    /**
	 * 最近更新数据时间
	 */
    private Date updatetime;
    /**
	 * 设备类型  0.旧版 1.新版
	 */
    private Integer eqtype;
    /**
	 * 蓝牙名称
	 */
    @TableField(value="bluetooth_name")
    private String bluetoothName;
    /**
	 * 蓝牙状态(连接，未连接)
	 */
    @TableField(value="bluetooth_status")
    private String bluetoothStatus;
    /**
	 * 蓝牙列表
	 */
    @TableField(value="bluetooth_electricity")
    private Integer bluetoothElectricity;
    /**
	 * 蓝牙电量
	 */
    private String clock;
    /**
	 * 时钟
	 */
    private String phone1;
    /**
	 * 紧急联系人1
	 */
    private String phone2;
    /**
	 * 紧急联系人2
	 */
    private String name;
    /**
	 * 昵称
	 */
    private String version;
    /**
	 * 版本
	 */
    private Date uploadtime;
    /**
	 * 设备更新时间
	 */
    private String bluetoothmac;
    /**
	 * 蓝牙mac地址
	 */
    @TableField(value="bluetooth_list")
    private String bluetoothList;
    /**
	 * 代理商id
	 */
    private Integer agentid;
    /**
	 * 机型
	 */
    private String model;

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

    public Integer getLordpower() {
        return lordpower;
    }

    public void setLordpower(Integer lordpower) {
        this.lordpower = lordpower;
    }

    public String getSignalxhao() {
        return signalxhao;
    }

    public void setSignalxhao(String signalxhao) {
        this.signalxhao = signalxhao == null ? null : signalxhao.trim();
    }

    public String getBluetoothType() {
        return bluetoothType;
    }

    public void setBluetoothType(String bluetoothType) {
        this.bluetoothType = bluetoothType == null ? null : bluetoothType.trim();
    }

    public String getEqStatus() {
        return eqStatus;
    }

    public void setEqStatus(String eqStatus) {
        this.eqStatus = eqStatus == null ? null : eqStatus.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getEqtype() {
        return eqtype;
    }

    public void setEqtype(Integer eqtype) {
        this.eqtype = eqtype == null ? null : eqtype;
    }

    public String getBluetoothName() {
        return bluetoothName;
    }

    public void setBluetoothName(String bluetoothName) {
        this.bluetoothName = bluetoothName == null ? null : bluetoothName.trim();
    }

    public String getBluetoothStatus() {
        return bluetoothStatus;
    }

    public void setBluetoothStatus(String bluetoothStatus) {
        this.bluetoothStatus = bluetoothStatus == null ? null : bluetoothStatus.trim();
    }

    public Integer getBluetoothElectricity() {
        return bluetoothElectricity;
    }

    public void setBluetoothElectricity(Integer bluetoothElectricity) {
        this.bluetoothElectricity = bluetoothElectricity;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock == null ? null : clock.trim();
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1 == null ? null : phone1.trim();
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2 == null ? null : phone2.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getBluetoothmac() {
        return bluetoothmac;
    }

    public void setBluetoothmac(String bluetoothmac) {
        this.bluetoothmac = bluetoothmac == null ? null : bluetoothmac.trim();
    }

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBluetoothList() {
		return bluetoothList;
	}

	public void setBluetoothList(String bluetoothList) {
		this.bluetoothList = bluetoothList;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
    
    
}