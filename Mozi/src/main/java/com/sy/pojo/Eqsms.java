package com.sy.pojo;

import java.util.Date;

public class Eqsms {
    private Integer id;

    private String phone;

    private String esms;

    private String imei;

    private Date cratetime;

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

    public String getEsms() {
        return esms;
    }

    public void setEsms(String esms) {
        this.esms = esms == null ? null : esms.trim();
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
}