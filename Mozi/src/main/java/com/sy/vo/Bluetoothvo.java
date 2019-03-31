package com.sy.vo;
//子设备管理
public class Bluetoothvo {
	//蓝牙名称
	private String   bluetoothname;
	//名称备注
	private String   note;
	//蓝牙类型
	private  String Bluetoothtype;
	//蓝牙是否在线状态
	private boolean  status;
	//电量
	private  Integer electricity;
	//设备唯一地址
	private String idaddress;
	//版本号
	private  String version;
	public String getBluetoothname() {
		return bluetoothname;
	}
	public void setBluetoothname(String bluetoothname) {
		this.bluetoothname = bluetoothname;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getBluetoothtype() {
		return Bluetoothtype;
	}
	public void setBluetoothtype(String bluetoothtype) {
		Bluetoothtype = bluetoothtype;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Integer getElectricity() {
		return electricity;
	}
	public void setElectricity(Integer electricity) {
		this.electricity = electricity;
	}
	public String getIdaddress() {
		return idaddress;
	}
	public void setIdaddress(String idaddress) {
		this.idaddress = idaddress;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Bluetoothvo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bluetoothvo(String bluetoothname, String note, String bluetoothtype,
			boolean status, Integer electricity, String idaddress,
			String version) {
		super();
		this.bluetoothname = bluetoothname;
		this.note = note;
		Bluetoothtype = bluetoothtype;
		this.status = status;
		this.electricity = electricity;
		this.idaddress = idaddress;
		this.version = version;
	}
	
	
}
