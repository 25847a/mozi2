package com.sy.vo;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

public class Equipmentbluetooth {
	 private Integer id;

	    private String imei;

	    private Integer lordpower;

	    private String signalxhao;

	    private String bluetoothType;

	    private boolean eqStatus;

	    private Date createtime;

	    private Date updatetime;

	    private String eqtype;

	    private String bluetoothName;

	    private boolean bluetoothStatus;

	    private Integer bluetoothElectricity;

	    private String phone1;
	   
	    private String phone2;

	    private String name;
	    
		private  String version;

	    private Date uploadtime;
	    
	    private String phone1name;
	    
	    private String phone2name;
	    
	    private String bluetoothmac;
	    
	    private String H;
	    
	    private String G;
	    
	    private JSONArray bluetoothList;
	    
	    
	    
		public Equipmentbluetooth(Integer id, String imei, Integer lordpower,
				String signalxhao, String bluetoothType, boolean eqStatus,
				Date createtime, Date updatetime, String eqtype,
				String bluetoothName, boolean bluetoothStatus,
				Integer bluetoothElectricity, String phone1, String phone2,
				String name, String version, Date uploadtime,
				String phone1name, String phone2name, String bluetoothmac) {
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
			this.phone1 = phone1;
			this.phone2 = phone2;
			this.name = name;
			this.version = version;
			this.uploadtime = uploadtime;
			this.phone1name = phone1name;
			this.phone2name = phone2name;
			this.bluetoothmac = bluetoothmac;
		}
		public Equipmentbluetooth(Integer id, String imei, Integer lordpower,
				String signalxhao, String bluetoothType, boolean eqStatus,
				Date createtime, Date updatetime, String eqtype,
				String bluetoothName, boolean bluetoothStatus,
				Integer bluetoothElectricity, String phone1, String phone2,
				String name, String version, Date uploadtime,
				String phone1name, String phone2name, String bluetoothmac,
				String H,String G,JSONArray bluetoothList
				) {
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
			this.phone1 = phone1;
			this.phone2 = phone2;
			this.name = name;
			this.version = version;
			this.uploadtime = uploadtime;
			this.phone1name = phone1name;
			this.phone2name = phone2name;
			this.bluetoothmac = bluetoothmac;
			this.H = H;
			this.G = G;
			this.bluetoothList = bluetoothList;
		}

		
		public JSONArray getBluetoothList() {
			return bluetoothList;
		}
		public void setBluetoothList(JSONArray bluetoothList) {
			this.bluetoothList = bluetoothList;
		}
		public String getH() {
			return H;
		}
		public void setH(String h) {
			H = h;
		}
		public String getG() {
			return G;
		}
		public void setG(String g) {
			G = g;
		}
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


		public boolean isEqStatus() {
			return eqStatus;
		}


		public void setEqStatus(boolean eqStatus) {
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


		public boolean isBluetoothStatus() {
			return bluetoothStatus;
		}


		public void setBluetoothStatus(boolean bluetoothStatus) {
			this.bluetoothStatus = bluetoothStatus;
		}


		public Integer getBluetoothElectricity() {
			return bluetoothElectricity;
		}


		public void setBluetoothElectricity(Integer bluetoothElectricity) {
			this.bluetoothElectricity = bluetoothElectricity;
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


		public String getPhone1name() {
			return phone1name;
		}


		public void setPhone1name(String phone1name) {
			this.phone1name = phone1name;
		}


		public String getPhone2name() {
			return phone2name;
		}


		public void setPhone2name(String phone2name) {
			this.phone2name = phone2name;
		}


		public String getBluetoothmac() {
			return bluetoothmac;
		}


		public void setBluetoothmac(String bluetoothmac) {
			this.bluetoothmac = bluetoothmac;
		}


		public Equipmentbluetooth() {
			super();
			// TODO Auto-generated constructor stub
		}

	
}
