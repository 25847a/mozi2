package com.sy.vo;

public class RoleMenuvo {
	private Integer id ;
	private String rolename;
	private String menuname;
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
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public RoleMenuvo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoleMenuvo(Integer id, String rolename, String menuname) {
		super();
		this.id = id;
		this.rolename = rolename;
		this.menuname = menuname;
	}
	

}
