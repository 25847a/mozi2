package com.sy.vo;

public class EquipmentManagement {
	//设备主机名称
	private String name ;
	//备注
	private String  note;
	//设备类型
	private String  equipmenttype;
	//在线状态
	private boolean status;
	//信号
	private  String signal;
	//电量
	private Integer electricity;
	//设备imei地址
	private String imei ;
	//版本号
	private String version;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getEquipmenttype() {
		return equipmenttype;
	}
	public void setEquipmenttype(String equipmenttype) {
		this.equipmenttype = equipmenttype;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	public Integer getElectricity() {
		return electricity;
	}
	public void setElectricity(Integer electricity) {
		this.electricity = electricity;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public EquipmentManagement(String name, String note, String equipmenttype,
			boolean status, String signal, Integer electricity, String imei,
			String version) {
		super();
		this.name = name;
		this.note = note;
		this.equipmenttype = equipmenttype;
		this.status = status;
		this.signal = signal;
		this.electricity = electricity;
		this.imei = imei;
		this.version = version;
	}
	public EquipmentManagement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
