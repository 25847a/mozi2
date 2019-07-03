package com.sy.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("user_eq")
public class UserEq extends Model<UserEq>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@TableId(value="id", type= IdType.AUTO)
    private Integer id;
	/**
	 * 用户ID
	 */
    @TableField(value="user_id")
    private Integer userId;
    /**
     * 设备ID
     */
    @TableField(value="eq_id")
    private Integer eqId;
    /**
     * 关联关系
     */
    private Integer typeof;
    /**
     * 已授权--未授权
     */
    private String authorized;
    /**
     * 默认关注首页显示  0.隐藏  1.显示	
     */
    private Integer follow; 
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEqId() {
        return eqId;
    }

    public void setEqId(Integer eqId) {
        this.eqId = eqId;
    }

    public Integer getTypeof() {
        return typeof;
    }

    public void setTypeof(Integer typeof) {
        this.typeof = typeof;
    }

    public String getAuthorized() {
        return authorized;
    }

    public Integer getFollow() {
		return follow;
	}

	public void setFollow(Integer follow) {
		this.follow = follow;
	}

	public void setAuthorized(String authorized) {
        this.authorized = authorized == null ? null : authorized.trim();
    }

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}
}