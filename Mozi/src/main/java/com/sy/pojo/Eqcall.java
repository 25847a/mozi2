package com.sy.pojo;

import java.util.Date;

public class Eqcall {
    private Integer id;

    private String phone;

    private String accsss;

    private String answers;

    private String imei;

    private Date cratetime;

    private Date coletime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAccsss() {
        return accsss;
    }

    public void setAccsss(String accsss) {
        this.accsss = accsss == null ? null : accsss.trim();
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers == null ? null : answers.trim();
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public Date getCratetime() {
        return cratetime;
    }

    public void setCratetime(Date cratetime) {
        this.cratetime = cratetime;
    }

    public Date getColetime() {
        return coletime;
    }

    public void setColetime(Date coletime) {
        this.coletime = coletime;
    }
}