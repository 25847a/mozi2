package com.sy.vo;

import java.util.List;

import com.sy.pojo.Management;

public class Managementvo {
     private Management m;
     private List< Ravo> ro ;
	public Management getM() {
		return m;
	}
	public void setM(Management m) {
		this.m = m;
	}
	public List<Ravo> getRo() {
		return ro;
	}
	public void setRo(List<Ravo> ro) {
		this.ro = ro;
	}
	public Managementvo(Management m, List<Ravo> ro) {
		super();
		this.m = m;
		this.ro = ro;
	}
	public Managementvo() {
		super();
		// TODO Auto-generated constructor stub
	}
     
}
