package com.sy.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String role;

    private String account;

    private String password;
    //是否删除（0、否1、是）
    private Integer isDelete;
    
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
    
    private String calibration;
    
    private String imei;
    
    private String radius;
    
    private String midpoint;
    
    private String jfdataUpdateTime;
    
    private String walkCount;
    
    private String walkPushTime;
    //1.重点关爱  0.不重点关爱
    private Integer love;
    //床位ID(0.代表无床位)
    private Long bedId;
    //护士ID
    private Long nurseId;
    //入住时间(养老院)
    private Date liveTime;
    //病史
    private String illness;

    
    public User(Integer id, String role, String account, String password,
			String name, Integer age, String gender, String phone,
			String address, String avatar, String wechat, String qq,
			Date createtime, Date atlasttime, Float weight, Float height,
			Date born, String code) {
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
	}
    public User(Integer id, String role, String account, String password,
    		String name, Integer age, String gender, String phone,
    		String address, String avatar, String wechat, String qq,
    		Date createtime, Date atlasttime, Float weight, Float height,
    		Date born, String code,String calibration, String imei,
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
	
	

	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getLove() {
		return love;
	}
	public void setLove(Integer love) {
		this.love = love;
	}
	public Long getBedId() {
		return bedId;
	}
	public void setBedId(Long bedId) {
		this.bedId = bedId;
	}
	public Long getNurseId() {
		return nurseId;
	}
	public void setNurseId(Long nurseId) {
		this.nurseId = nurseId;
	}
	public Date getLiveTime() {
		return liveTime;
	}
	public void setLiveTime(Date liveTime) {
		this.liveTime = liveTime;
	}
	public String getIllness() {
		return illness;
	}
	public void setIllness(String illness) {
		this.illness = illness;
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