package com.sy.vo;

public class ManRolevo {
	private Integer id;
	private String rolename;
	private String manname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getManname() {
		return manname;
	}
	public void setManname(String manname) {
		this.manname = manname;
	}
	public ManRolevo(Integer id, String rolename, String manname) {
		super();
		this.id = id;
		this.rolename = rolename;
		this.manname = manname;
	}
	public ManRolevo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
