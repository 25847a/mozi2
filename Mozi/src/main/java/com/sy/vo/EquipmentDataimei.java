package com.sy.vo;

import com.sy.pojo.EquipmentData;

public class EquipmentDataimei {

	private EquipmentData equipmentData;
	private String  imei;
	public EquipmentData getEquipmentData() {
		return equipmentData;
	}
	public void setEquipmentData(EquipmentData equipmentData) {
		this.equipmentData = equipmentData;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public EquipmentDataimei(EquipmentData equipmentData, String imei) {
		super();
		this.equipmentData = equipmentData;
		this.imei = imei;
	}
	public EquipmentDataimei() {
		super();
		// TODO Auto-generated constructor stub
	}

}
