package com.sy.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String role;

    private String account;

    private String password;

    private String name;

    private Integer age;

    private String gender;

    private String phone;

    private String address;

    private String avatar;

    private String wechat;

    private String qq;

    private Date createtime;

    private Date atlasttime;

    private Float weight;

    private Float height;

    private Date born;

    private String code;

    private Integer highpressure;

    private Integer lowpressure;
    
    private String calibration;
    
    private String imei;
    
    private String radius;
    
    private String midpoint;
    
    private String jfdataUpdateTime;
    
    private String walkCount;
    
    private String walkPushTime;

    
    public User(Integer id, String role, String account, String password,
			String name, Integer age, String gender, String phone,
			String address, String avatar, String wechat, String qq,
			Date createtime, Date atlasttime, Float weight, Float height,
			Date born, String code, Integer highpressure, Integer lowpressure) {
		super();
		this.id = id;
		this.role = role;
		this.account = account;
		this.password = password;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.avatar = avatar;
		this.wechat = wechat;
		this.qq = qq;
		this.createtime = createtime;
		this.atlasttime = atlasttime;
		this.weight = weight;
		this.height = height;
		this.born = born;
		this.code = code;
		this.highpressure = highpressure;
		this.lowpressure = lowpressure;
	}
    public User(Integer id, String role, String account, String password,
    		String name, Integer age, String gender, String phone,
    		String address, String avatar, String wechat, String qq,
    		Date createtime, Date atlasttime, Float weight, Float height,
    		Date born, String code, Integer highpressure, Integer lowpressure,String calibration, String imei,
    		String jfdataUpdateTime
    		) {
    	super();
    	this.id = id;
    	this.role = role;
    	this.account = account;
    	this.password = password;
    	this.name = name;
    	this.age = age;
    	this.gender = gender;
    	this.phone = phone;
    	this.address = address;
    	this.avatar = avatar;
    	this.wechat = wechat;
    	this.qq = qq;
    	this.createtime = createtime;
    	this.atlasttime = atlasttime;
    	this.weight = weight;
    	this.height = height;
    	this.born = born;
    	this.code = code;
    	this.highpressure = highpressure;
    	this.lowpressure = lowpressure;
    	this.calibration = calibration;
    	this.imei = imei;
    	this.jfdataUpdateTime = jfdataUpdateTime;
    }

    
    
	public String getWalkPushTime() {
		return walkPushTime;
	}
	public void setWalkPushTime(String walkPushTime) {
		this.walkPushTime = walkPushTime;
	}
	public String getWalkCount() {
		return walkCount;
	}
	public void setWalkCount(String walkCount) {
		this.walkCount = walkCount;
	}
	public String getJfdataUpdateTime() {
		return jfdataUpdateTime;
	}
	public void setJfdataUpdateTime(String jfdataUpdateTime) {
		this.jfdataUpdateTime = jfdataUpdateTime;
	}
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getAtlasttime() {
        return atlasttime;
    }

    public void setAtlasttime(Date atlasttime) {
        this.atlasttime = atlasttime;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getHighpressure() {
        return highpressure;
    }

    public void setHighpressure(Integer highpressure) {
        this.highpressure = highpressure;
    }

    public Integer getLowpressure() {
        return lowpressure;
    }

    public void setLowpressure(Integer lowpressure) {
        this.lowpressure = lowpressure;
    }
    
    
    
	public String getCalibration() {
		return calibration;
	}

	public void setCalibration(String calibration) {
		this.calibration = calibration;
	}
	
	

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	
	

	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
	}
	public String getMidpoint() {
		return midpoint;
	}
	public void setMidpoint(String midpoint) {
		this.midpoint = midpoint;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}