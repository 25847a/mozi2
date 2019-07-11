package com.sy.vo;

/**
 * jian(接口javaBean,必须一个接口使用一个javaBean,否则后期人员无法维护)
 * jian(常量必须写上注释)
 * @author Administrator
 *
 */
public class Personal {
	/**
	 * 主键ID
	 */
	private int alias;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 头像地址
	 */
	private String avatar;
	/**
	 * 监护总数
	 */
	private int aliasCount;
	/**
	 * 观察总数
	 */
	private int observerCount;
	/**
	 * 社圈总数
	 */
	private int groupCount;
	public int getAlias() {
		return alias;
	}
	public void setSlias(int alias) {
		this.alias = alias;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getAliasCount() {
		return aliasCount;
	}
	public void setAliasCount(int aliasCount) {
		this.aliasCount = aliasCount;
	}
	public int getObserverCount() {
		return observerCount;
	}
	public void setObserverCount(int observerCount) {
		this.observerCount = observerCount;
	}
	public int getGroupCount() {
		return groupCount;
	}
	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}
	
}