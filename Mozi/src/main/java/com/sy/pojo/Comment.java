package com.sy.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 啊健
 * @author Administrator
 *
 */
@TableName("comment")
public class Comment extends Model<Comment>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	/**
	 * 使用者ID
	 */
	private Integer userId;
	/**
	 * config配置ID
	 */
	private Integer comment;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getComment() {
		return comment;
	}
	public void setComment(Integer comment) {
		this.comment = comment;
	}
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
}
