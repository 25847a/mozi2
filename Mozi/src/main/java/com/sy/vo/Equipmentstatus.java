package com.sy.vo;

import java.util.Date;

public class Equipmentstatus {
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
	
	private String updatetimeStr;

	private String bluetoothmac;
	
	private String h;
	
	private String g;

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
		this.imei = imei;
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
		this.signalxhao = signalxhao;
	}

	public String getBluetoothType() {
		return bluetoothType;
	}

	public void setBluetoothType(String bluetoothType) {
		this.bluetoothType = bluetoothType;
	}

	public String getEqStatus() {
		return eqStatus;
	}

	public void setEqStatus(String eqStatus) {
		this.eqStatus = eqStatus;
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
		this.eqtype = eqtype;
	}

	public String getBluetoothName() {
		return bluetoothName;
	}

	public void setBluetoothName(String bluetoothName) {
		this.bluetoothName = bluetoothName;
	}

	public String getBluetoothStatus() {
		return bluetoothStatus;
	}

	public void setBluetoothStatus(String bluetoothStatus) {
		this.bluetoothStatus = bluetoothStatus;
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
		this.clock = clock;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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
		this.bluetoothmac = bluetoothmac;
	}

	public String getH() {
		return h;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getG() {
		return g;
	}

	public void setG(String g) {
		this.g = g;
	}
	

	public String getUpdatetimeStr() {
		return updatetimeStr;
	}

	public void setUpdatetimeStr(String updatetimeStr) {
		this.updatetimeStr = updatetimeStr;
	}

	public Equipmentstatus(Integer id, String imei, Integer lordpower,
			String signalxhao, String bluetoothType, String eqStatus,
			Date createtime, Date updatetime, String eqtype,
			String bluetoothName, String bluetoothStatus,
			Integer bluetoothElectricity, String clock, String phone1,
			String phone2, String name, String version, Date uploadtime,
			String bluetoothmac, String h, String g) {
		super();
		this.id = id;
		this.imei = imei;
		this.lordpower = lordpower;
		this.signalxhao = signalxhao;
		this.bluetoothType = bluetoothType;
		this.eqStatus = eqStatus;
		this.createtime = createtime;
		this.updatetime = updatetime;
		this.eqtype = eqtype;
		this.bluetoothName = bluetoothName;
		this.bluetoothStatus = bluetoothStatus;
		this.bluetoothElectricity = bluetoothElectricity;
		this.clock = clock;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.name = name;
		this.version = version;
		this.uploadtime = uploadtime;
		this.bluetoothmac = bluetoothmac;
		this.h = h;
		this.g = g;
	}
	public Equipmentstatus(Integer id, String imei, Integer lordpower,
			String signalxhao, String bluetoothType, String eqStatus,
			Date createtime, String updatetimeStr, String eqtype,
			String bluetoothName, String bluetoothStatus,
			Integer bluetoothElectricity, String clock, String phone1,
			String phone2, String name, String version, Date uploadtime,
			String bluetoothmac, String h, String g) {
		super();
		this.id = id;
		this.imei = imei;
		this.lordpower = lordpower;
		this.signalxhao = signalxhao;
		this.bluetoothType = bluetoothType;
		this.eqStatus = eqStatus;
		this.createtime = createtime;
		this.updatetimeStr = updatetimeStr;
		this.eqtype = eqtype;
		this.bluetoothName = bluetoothName;
		this.bluetoothStatus = bluetoothStatus;
		this.bluetoothElectricity = bluetoothElectricity;
		this.clock = clock;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.name = name;
		this.version = version;
		this.uploadtime = uploadtime;
		this.bluetoothmac = bluetoothmac;
		this.h = h;
		this.g = g;
	}

	public Equipmentstatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
