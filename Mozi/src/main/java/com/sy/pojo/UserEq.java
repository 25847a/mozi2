package com.sy.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("user_eq")
public class UserEq extends Model<UserEq>{
	private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer userId;

    private Integer eqId;

    private Integer typeof;

    private String authorized;

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

    public void setAuthorized(String authorized) {
        this.authorized = authorized == null ? null : authorized.trim();
    }

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}
}