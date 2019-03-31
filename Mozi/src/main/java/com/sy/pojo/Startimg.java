package com.sy.pojo;

import java.util.Date;

public class Startimg {
    private Integer id;

    private String img;

    private Date cretatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Date getCretatetime() {
        return cretatetime;
    }

    public void setCretatetime(Date cretatetime) {
        this.cretatetime = cretatetime;
    }
}