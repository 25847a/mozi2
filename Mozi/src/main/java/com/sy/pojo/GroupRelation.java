package com.sy.pojo;

/**
 * 朋友圈群组关联
 * @author Administrator
 *
 */
public class GroupRelation {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 朋友圈群组ID
	 */
	private Long groupId;
	/**
	 * 关联群员ID
	 */
	private Integer relationUserId;
	/**
	 * 1.群主  0.群员
	 */
	private Integer type;
	/**
	 * 0.监护者   1.使用者
	 */
	private Integer kind;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Integer getRelationUserId() {
		return relationUserId;
	}
	public void setRelationUserId(Integer relationUserId) {
		this.relationUserId = relationUserId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getKind() {
		return kind;
	}
	public void setKind(Integer kind) {
		this.kind = kind;
	}
	
}
