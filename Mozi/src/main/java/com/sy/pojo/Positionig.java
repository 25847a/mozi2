package com.sy.pojo;

import java.util.Date;

public class Positionig {
    private Integer id;

    private String positioningS;

    private String positioningData;

    private Date cratetime;

    private String imei;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositioningS() {
        return positioningS;
    }

    public void setPositioningS(String positioningS) {
        this.positioningS = positioningS == null ? null : positioningS.trim();
    }

    public String getPositioningData() {
        return positioningData;
    }

    public void setPositioningData(String positioningData) {
        this.positioningData = positioningData == null ? null : positioningData.trim();
    }

    public Date getCratetime() {
        return cratetime;
    }

    public void setCratetime(Date cratetime) {
        this.cratetime = cratetime;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }
}