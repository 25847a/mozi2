package com.sy.vo;

/**
 * 设备全部人员的数据
 * @author Groot
 *
 */
public class Usereqvo {
	//头像
	private String  iconPath;
	//用户名称
	private String username; 
	//蓝牙
	private String bluetoothStatus;
	//用户的运动状态
	private String userStatus;
	//电量
	private Integer deviceEnergy;
	//用户id
	private Integer userid;
	//是否为管理者
	private String managenment;
	//定位权限
	private boolean loactionStatus;
	//发送信息权限
	private boolean messageStatus;
	//闹钟权限
	private boolean  aclokStatus;
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBluetoothStatus() {
		return bluetoothStatus;
	}
	public void setBluetoothStatus(String bluetoothStatus) {
		this.bluetoothStatus = bluetoothStatus;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Integer getDeviceEnergy() {
		return deviceEnergy;
	}
	public void setDeviceEnergy(Integer deviceEnergy) {
		this.deviceEnergy = deviceEnergy;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getManagenment() {
		return managenment;
	}
	public void setManagenment(String managenment) {
		this.managenment = managenment;
	}
	public boolean isLoactionStatus() {
		return loactionStatus;
	}
	public void setLoactionStatus(boolean loactionStatus) {
		this.loactionStatus = loactionStatus;
	}
	public boolean isMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(boolean messageStatus) {
		this.messageStatus = messageStatus;
	}
	public boolean isAclokStatus() {
		return aclokStatus;
	}
	public void setAclokStatus(boolean aclokStatus) {
		this.aclokStatus = aclokStatus;
	}
	public Usereqvo(String iconPath, String username, String bluetoothStatus,
			String userStatus, Integer deviceEnergy, Integer userid,
			String managenment, boolean loactionStatus, boolean messageStatus,
			boolean aclokStatus) {
		super();
		this.iconPath = iconPath;
		this.username = username;
		this.bluetoothStatus = bluetoothStatus;
		this.userStatus = userStatus;
		this.deviceEnergy = deviceEnergy;
		this.userid = userid;
		this.managenment = managenment;
		this.loactionStatus = loactionStatus;
		this.messageStatus = messageStatus;
		this.aclokStatus = aclokStatus;
	}
	public Usereqvo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
