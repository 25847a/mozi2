package com.sy.vo;

import java.util.List;

import com.sy.pojo.Auth;
import com.sy.pojo.Role;

public class Ravo {
	
	private Role r ;
	private List<Auth>al ;
	public Role getR() {
		return r;
	}
	public void setR(Role r) {
		this.r = r;
	}
	public List<Auth> getAl() {
		return al;
	}
	public void setAl(List<Auth> al) {
		this.al = al;
	}
	public Ravo(Role r, List<Auth> al) {
		super();
		this.r = r;
		this.al = al;
	}
	public Ravo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
