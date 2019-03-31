package com.sy.vo;

import java.util.List;

import com.sy.pojo.EquipmentData;

public class Userqedata {
	private List<Usereqvo> users;
	private EquipmentData eqdata;
	public Userqedata(List<Usereqvo> users, EquipmentData eqdata) {
		super();
		this.users = users;
		this.eqdata = eqdata;
	}
	public List<Usereqvo> getUsers() {
		return users;
	}
	public void setUsers(List<Usereqvo> users) {
		this.users = users;
	}
	public EquipmentData getEqdata() {
		return eqdata;
	}
	public void setEqdata(EquipmentData eqdata) {
		this.eqdata = eqdata;
	}
	public Userqedata() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
