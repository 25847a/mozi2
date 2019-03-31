package com.sy.pojo;

public class UserEq {
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
}