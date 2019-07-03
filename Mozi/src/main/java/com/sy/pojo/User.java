package com.sy.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("user")
public class User  extends Model<Config>{
	private static final long serialVersionUID = 1L;
	@TableId(value="id", type= IdType.AUTO)
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
    //省市区
    private String city;
    private String address;

    private String avatar;
    @TableField(value = "createtime",fill = FieldFill.INSERT )
    private Date createtime;

    private Float weight;
 
    private Float height;

    private Date born;

    private String code;
    
    private Integer calibration;
    
    private String imei;
    
    private String jfdataUpdateTime;
    
    private String walkCount;
    
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
			String address, String avatar,
			Date createtime, Float weight, Float height,
			Date born) {
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
		this.createtime = createtime;
		this.weight = weight;
		this.height = height;
		this.born = born;
	}
    public User(Integer id, String role, String account, String password,
    		String name, Integer age, String gender, String phone,
    		String address, String avatar,
    		Date createtime, Float weight, Float height,
    		Date born, String imei
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
    	this.createtime = createtime;
    	this.weight = weight;
    	this.height = height;
    	this.born = born;
    	this.imei = imei;
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

    public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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
	public Integer getCalibration() {
		return calibration;
	}

	public void setCalibration(Integer calibration) {
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
	public User() {
		super();
	}
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
    
}