package com.sy.pojo;

import java.util.Date;

public class Equipment {
    private Integer id;

    private String imei;

    private Integer lordpower;

    private String signalxhao;

    private String bluetoothType;

    private String eqStatus;

    private Date createtime;

    private Date updatetime;

    private String eqtype;

    private String bluetoothName;

    private String bluetoothStatus;

    private Integer bluetoothElectricity;

    private String clock;

    private String phone1;

    private String phone2;

    private String name;

    private String version;

    private Date uploadtime;

    private String bluetoothmac;
    
    private String bluetoothList;
    
    private Integer agentid;
    
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

    public String getEqtype() {
        return eqtype;
    }

    public void setEqtype(String eqtype) {
        this.eqtype = eqtype == null ? null : eqtype.trim();
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
    
    
}