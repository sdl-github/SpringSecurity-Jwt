package com.sdl.times.system.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Menu)实体类
 *
 * @author makejava
 * @since 2020-03-24 15:18:04
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = -44698896688316158L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 上级资源ID
    */
    private String pid;
    /**
    * url
    */
    private String url;
    /**
    * 资源编码
    */
    private String resources;
    /**
    * 资源名称
    */
    private String title;
    /**
    * 资源图标
    */
    private String icon;
    /**
    * 类型 menu、button
    */
    private String type;
    /**
    * 备注
    */
    private String remarks;
    /**
    * 创建时间
    */
    private Date gmtCreate;
    /**
    * 更新时间
    */
    private Date gmtModified;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pid='" + pid + '\'' +
                ", url='" + url + '\'' +
                ", resources='" + resources + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", type='" + type + '\'' +
                ", remarks='" + remarks + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}