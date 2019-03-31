package com.sy.vo;

import java.util.Date;

public class EquipmentVo {

	private Integer id;

	private String imei;
	private boolean eqStatus;
    private Integer eqtype;
	private Date createtime;
	private Date updatetime;
	private String role;
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
	public boolean isEqStatus() {
		return eqStatus;
	}
	public void setEqStatus(boolean eqStatus) {
		this.eqStatus = eqStatus;
	}
	public Integer getEqtype() {
		return eqtype;
	}
	public void setEqtype(Integer eqtype) {
		this.eqtype = eqtype;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public EquipmentVo(Integer id, String imei, boolean eqStatus,
			Integer eqtype, Date createtime, Date updatetime, String role) {
		super();
		this.id = id;
		this.imei = imei;
		this.eqStatus = eqStatus;
		this.eqtype = eqtype;
		this.createtime = createtime;
		this.updatetime = updatetime;
		this.role = role;
	}
	public EquipmentVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
