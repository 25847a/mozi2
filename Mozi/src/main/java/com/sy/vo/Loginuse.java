package com.sy.vo;

import java.util.Date;

public class Loginuse {
	  private Integer id;

	    private String role;


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

	    private Integer highpressure;

	    private Integer lowpressure;

		public Loginuse() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Loginuse(Integer id, String role, String name, Integer age,
				String gender, String phone, String address, String avatar,
				String wechat, String qq, Date createtime, Date atlasttime,
				Float weight, Float height, Date born, Integer highpressure,
				Integer lowpressure) {
			super();
			this.id = id;
			this.role = role;
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
			this.highpressure = highpressure;
			this.lowpressure = lowpressure;
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
			this.role = role;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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
			this.gender = gender;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public String getWechat() {
			return wechat;
		}

		public void setWechat(String wechat) {
			this.wechat = wechat;
		}

		public String getQq() {
			return qq;
		}

		public void setQq(String qq) {
			this.qq = qq;
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

	    
}
