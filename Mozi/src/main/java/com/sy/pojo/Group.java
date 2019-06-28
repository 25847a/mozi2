package com.sy.pojo;

/**
 * 朋友圈群组
 * @author Administrator
 *
 */
public class Group {
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 群名称
	 */
	private String groupName;
	/**
	 * 群主ID(可以是监护者,也可以是使用者)
	 */
	private Integer userId;
	/**
	 * 手机号码(唯一)
	 */
	private String phone;
	/**
	 * 1.同心相映  2.同病相联  3.志同道合
	 */
	private Integer configId;
	/**
	 * 群总人数
	 */
	private Integer count;
	/**
	 * 描述
	 */
	private String remarks;
	/**
	 * 头像地址
	 */
	private String imageUrl;
	/**
	 * 验证码  专门接收数据的
	 */
	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getConfigId() {
		return configId;
	}
	public void setConfigId(Integer configId) {
		this.configId = configId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
