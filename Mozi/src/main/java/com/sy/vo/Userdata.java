package com.sy.vo;


public class Userdata {
	private Usereqvo users;
	private Healthdata eqdata;

	public Userdata(Usereqvo users, Healthdata eqdata) {
		super();
		this.users = users;
		this.eqdata = eqdata;
	}
	public Usereqvo getUsers() {
		return users;
	}

	public void setUsers(Usereqvo users) {
		this.users = users;
	}

	public Healthdata getEqdata() {
		return eqdata;
	}

	public void setEqdata(Healthdata eqdata) {
		this.eqdata = eqdata;
	}

	public Userdata() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
