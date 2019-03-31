package com.sy.pojo;

import java.util.Date;

public class Bluetooth {
    private Integer id;

    private String bluetoothnd;

    private String current;

    private String imei;

    private Date cratetime;

    private Date recentime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBluetoothnd() {
        return bluetoothnd;
    }

    public void setBluetoothnd(String bluetoothnd) {
        this.bluetoothnd = bluetoothnd == null ? null : bluetoothnd.trim();
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current == null ? null : current.trim();
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

    public Date getRecentime() {
        return recentime;
    }

    public void setRecentime(Date recentime) {
        this.recentime = recentime;
    }
}