package com.sy.vo;

import java.util.List;

import com.sy.pojo.Management;
import com.sy.pojo.Role;

public class ManRolelsvo {
	
	private Integer id;
	private List<Management>ms;
	private List<Role> rs;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Management> getMs() {
		return ms;
	}
	public void setMs(List<Management> ms) {
		this.ms = ms;
	}
	public List<Role> getRs() {
		return rs;
	}
	public void setRs(List<Role> rs) {
		this.rs = rs;
	}
	public ManRolelsvo(Integer id, List<Management> ms, List<Role> rs) {
		super();
		this.id = id;
		this.ms = ms;
		this.rs = rs;
	}
	public ManRolelsvo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
