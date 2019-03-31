package com.sy.vo;

import java.util.List;

import com.sy.pojo.Auth;
import com.sy.pojo.Role;

public class RoleAuthvo {
	
	private List<Auth> as;
	private List<Role> rs;
	public List<Auth> getAs() {
		return as;
	}
	public void setAs(List<Auth> as) {
		this.as = as;
	}
	public List<Role> getRs() {
		return rs;
	}
	public void setRs(List<Role> rs) {
		this.rs = rs;
	}
	public RoleAuthvo(List<Auth> as, List<Role> rs) {
		super();
		this.as = as;
		this.rs = rs;
	}
	public RoleAuthvo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
