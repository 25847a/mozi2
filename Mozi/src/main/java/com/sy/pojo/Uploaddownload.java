package com.sy.pojo;

import java.util.Date;
//上传下载表
public class Uploaddownload {
    private Integer id;

    private String name;

    private String url;

    private String imei;

    private String currentversion;

    private String ziversion;

    private String zhuversion;

    private String compilation;

    private String versiontype;

    private Date createtime;

    private Date updatetime;

    private String description;
    
    private String model;

    
    
    public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public String getCurrentversion() {
        return currentversion;
    }

    public void setCurrentversion(String currentversion) {
        this.currentversion = currentversion == null ? null : currentversion.trim();
    }

    public String getZiversion() {
        return ziversion;
    }

    public void setZiversion(String ziversion) {
        this.ziversion = ziversion == null ? null : ziversion.trim();
    }

    public String getZhuversion() {
        return zhuversion;
    }

    public void setZhuversion(String zhuversion) {
        this.zhuversion = zhuversion == null ? null : zhuversion.trim();
    }

    public String getCompilation() {
        return compilation;
    }

    public void setCompilation(String compilation) {
        this.compilation = compilation == null ? null : compilation.trim();
    }

    public String getVersiontype() {
        return versiontype;
    }

    public void setVersiontype(String versiontype) {
        this.versiontype = versiontype == null ? null : versiontype.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}